package com.pks.contact_manager.Controller;

import com.pks.contact_manager.dao.UserRepository;
import com.pks.contact_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // principle stores the unique value of a user. In this case email.
    @GetMapping("/index")
    public String dashBoard(Model model, Principal principal){
        String userName = principal.getName();
        User user = userRepository.getUserByUserName(userName);
        System.out.println("User Name "+userName);
        System.out.println("User  "+user);
        model.addAttribute("user",user);

        return "normal/user_dashboard";
    }

}
