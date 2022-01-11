package com.bezkoder.springjwt.adminAuthInterceptors;

import com.bezkoder.springjwt.models.Role;
import com.bezkoder.springjwt.models.User;
import com.bezkoder.springjwt.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
public class AdminAuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    UserRepository userRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Principal principal = request.getUserPrincipal();
        User user =  this.userRepository.findByUsername(principal.getName().toString()).get();
        Set<Role> set = user.getRoles();
//        System.out.println(set.size());

      try {
          if( new ArrayList<Role>(user.getRoles()).get(0).getName().toString().equals("ROLE_ADMIN"))
          {
              return true;
          }
          else
          {
              throw new RuntimeException("*** Invalid ROLe ***");
          }
      }
      catch (Exception e)
      {
          e.printStackTrace();
          return false;
      }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
}
