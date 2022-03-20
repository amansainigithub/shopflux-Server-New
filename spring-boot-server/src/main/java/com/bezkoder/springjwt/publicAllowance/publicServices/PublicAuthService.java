package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.helper.RandomNumber;
import com.bezkoder.springjwt.helper.email.EmailSender;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.payload.request.ChangePasswordForm;
import com.bezkoder.springjwt.publicAllowance.publicInterfaces.PublicAuthInterface;
import com.bezkoder.springjwt.repository.RoleRepository;
import com.bezkoder.springjwt.repository.UserRepository;
import com.bezkoder.springjwt.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Service
public class PublicAuthService  implements PublicAuthInterface {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    HttpSession session;
    private String email;

    @Override
    public boolean existsBYUserEmail(String userEmail, HttpServletRequest request) {
        boolean flag=false;
        try {

            if(this.userRepository.existsByEmail(userEmail.trim()))
            {
                String otp =String.valueOf( RandomNumber.getRandomNumberByDigits(999999));
                System.out.println("*********** OTP : "+otp);
                if(EmailSender.sendEmail(userEmail,this.getForgetPasswordContent(otp)))
                {
                    this.email=userEmail;
                    session  =  request.getSession();
                    session.setAttribute("UP-OTP"+userEmail,otp);
                    session.setMaxInactiveInterval(120);
                    flag=true;
                }else
                {
                    throw  new Exception("Something Went Wrong !");
                }

            }
            else
            {
                throw new UsernameNotFoundException("User Not Found !");
            };
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }


    public boolean changePasswordByEmailAndOtp(ChangePasswordForm changePasswordForm, HttpServletRequest request)
    {
        boolean flag=false;
        try {
            String otp  = (String)session.getAttribute("UP-OTP"+changePasswordForm.getEmail());
            if(this.email.equals(changePasswordForm.getEmail()) && otp.equals(changePasswordForm.getOtp()))
            {
                User user =  this.userRepository.findByEmail(this.email).get();
                user.setPassword( encoder.encode(changePasswordForm.getPassword()));
                this.userRepository.save(user);
                flag =  true;
            }
            else
            {
                throw new Exception("Please Enter valid Otp");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return flag;
    }

    public String getForgetPasswordContent(String otp)
    {
        String str="Your Forget Password OTP Is " + otp;
        return str;
    }
}
