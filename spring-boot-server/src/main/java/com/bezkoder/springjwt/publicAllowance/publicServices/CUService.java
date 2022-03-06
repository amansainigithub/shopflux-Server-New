package com.bezkoder.springjwt.publicAllowance.publicServices;

import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@Service
public class CUService {

    @Autowired
    private UserRepository userRepository;

    public User getCurrentUser_service(HttpServletRequest request)
    {
        Principal principal   =  request.getUserPrincipal();
        return this.userRepository.findByUsername(principal.getName().toString()).get();
    }
}
