package com.pks.contact_manager.Controller;

import com.pks.contact_manager.dao.UserRepository;
import com.pks.contact_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/test")
    @ResponseBody
    public String test(){
        User user = new User();
        user.setAbout("ss");
        user.setEmail("gamil");
        user.setPassword("pass");
        userRepository.save(user);
        return "working";
    }

}
