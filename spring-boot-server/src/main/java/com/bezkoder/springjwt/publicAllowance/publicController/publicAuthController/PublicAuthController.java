package com.bezkoder.springjwt.publicAllowance.publicController.publicAuthController;

import com.bezkoder.springjwt.helper.RandomNumber;
import com.bezkoder.springjwt.helper.email.EmailSender;
import com.bezkoder.springjwt.models.ERole;
import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.*;
import com.bezkoder.springjwt.payload.response.JwtResponse;
import com.bezkoder.springjwt.payload.response.MessageResponse;
import com.bezkoder.springjwt.publicAllowance.publicServices.PublicAuthService;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.AuthUrlMappings;
import com.bezkoder.springjwt.publicAllowance.publicURLMappings.PublicURLMappings;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import com.bezkoder.springjwt.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping(PublicURLMappings.PUBLIC_ACCESS_URL)
public class PublicAuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    private PublicAuthService publicAuthService;

    @Autowired
    JwtUtils jwtUtils;

    private User user;


    @PostMapping(AuthUrlMappings.PUBLIC_SIGN_IN)
    public ResponseEntity<?> signIn(@Valid @RequestBody LoginRequest loginRequest) {

        User user =  this.userRepository.findByEmail(loginRequest.getUsername()).get();

        ArrayList<Role> list =  new ArrayList<Role>(user.getRoles());
        Role role = list.get(0);

        if(role.getName().toString().equals("ROLE_USER")) {


            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), loginRequest.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = jwtUtils.generateJwtToken(authentication);

            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            List<String> roles = userDetails.getAuthorities().stream()
                    .map(item -> item.getAuthority())
                    .collect(Collectors.toList());

            return ResponseEntity.ok(new JwtResponse(jwt,
                    userDetails.getId(),
                    userDetails.getUsername(),
                    userDetails.getEmail(),
                    user.getStatus(),
                    roles));
        }
        else
        {
            throw new RuntimeException("Error : Here is Something Missing !!");
        }
    }




    @PostMapping(AuthUrlMappings.PUBLIC_SIGN_UP)
    public ResponseEntity<?> SignUpUser(@Valid @RequestBody SignupRequest signUpRequest , HttpServletRequest request) {

//        List<String> list = new ArrayList<String>(signUpRequest.getRole());
//        if(!list.get(0).toString().equals("user"))
//        { return ResponseEntity
//                    .badRequest()
//                    .body(new MessageResponse("Error: Please Enter the Valid Role!"));
//        }

        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()));

        Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not 1 found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not 2 found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not 3 found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not 4 found."));
                        roles.add(userRole);
                }
            });
        }

        //user.setStatus("false");
        user.setRoles(roles);
        this.user = user;
     //   userRepository.save(user);
        boolean result = this.emailSenderForRegistrationTime(signUpRequest.getEmail(),request);
        if(result)
        {
            return ResponseEntity.ok(new MessageResponse("SEND EMAIL SUCCESS | DATA IS SAVED "));
        }
        else
        {
            return ResponseEntity.ok(new MessageResponse("DATA IS SAVED BUT EMAIL NOT SENT !"));
        }

    }

    HttpSession session =null;
    public boolean emailSenderForRegistrationTime(String email, HttpServletRequest request)
    {
        boolean flag= false;

       try {
           int otp =RandomNumber.getRandomNumber();
           String content =  email + "OTP is  : " + otp+" : PLEASE VERIFY OTP";
           if(EmailSender.sendEmail(email.trim(),content))
           {
               session = request.getSession();
               session.setAttribute(email,String.valueOf(otp));
               session.setMaxInactiveInterval(120);
               flag  = true;
           }
       }
       catch (Exception e)
       {
           e.printStackTrace();
       }
        return flag;
    }

    @PostMapping(AuthUrlMappings.OTP_VERIFY)
    public ResponseEntity<?> otpVerifier(@RequestBody OtpVerify otpVerify, HttpServletRequest request) throws InterruptedException {
        try {
           // User user =   this.userRepository.findByEmail(otpVerify.getEmail()).get();

            if(otpVerify.getEmail().trim().equals(this.user.getEmail()))
            {
                String sessionOtp  =  (String)session.getAttribute(otpVerify.getEmail().trim());
                if(sessionOtp.equals(otpVerify.getOtp()))
                {
                    this.user.setStatus("true");
                    this.userRepository.save(this.user);
                    return ResponseEntity.ok(new MessageResponse("Registration Success"));
                }
                else
                {
                    throw new UsernameNotFoundException("User Not Found Here !!");
                }
            }
        }
        catch (Exception e)
        {
                e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
    }

    @PostMapping(AuthUrlMappings.EMAIL_VALIDATOR)
    public ResponseEntity<?> emailValidator(@Valid @RequestBody EmailValidator emailValidator,HttpServletRequest request) {

        if(this.publicAuthService.existsBYUserEmail(emailValidator.getEmail(),request))
        {
            return ResponseEntity.ok(new MessageResponse("OTP Sent to your : Email ID"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }
    }

    @PostMapping(AuthUrlMappings.CHANGE_PASSWORD_BY_EMAIL_AND_OTP)
    public ResponseEntity<?> changePasswordByEmailAndOtp(@Valid @RequestBody ChangePasswordForm changePasswordForm, HttpServletRequest request) {

        if(!changePasswordForm.getPassword().equals(changePasswordForm.getConformPassword()))
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

        if(this.publicAuthService.changePasswordByEmailAndOtp(changePasswordForm,request))
        {
            return ResponseEntity.ok(new MessageResponse("Password Update Success"));
        }
        else
        {
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).build();
        }

    }





}
