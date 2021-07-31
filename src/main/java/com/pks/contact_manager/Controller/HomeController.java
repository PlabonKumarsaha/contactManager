package com.pks.contact_manager.Controller;


import com.pks.contact_manager.dao.UserRepository;
import com.pks.contact_manager.entity.User;
import com.pks.contact_manager.helper.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
public class HomeController {
    @Autowired
    UserRepository userRepository;

    @RequestMapping("/")
    public String getHome( Model model) {
        model.addAttribute("title","home-smart contract");
        return "home";
    }

    @RequestMapping("/about")
    public String getAbout( Model model) {
        model.addAttribute("title","About-smart contract");
        return "about";
    }

    @RequestMapping("/signUp/")
    public String getSignUp( Model model) {
        model.addAttribute("title","Register-smart contract");
        model.addAttribute("user", new User());
        return "signup";
    }

    @RequestMapping(value = "/do_register", method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user , @RequestParam(value ="agreement",defaultValue = "false") boolean agreement,
                               Model model, HttpSession session) {
       try {
           if(agreement != true){
               System.out.println("agreement not selected");
               throw new Exception();
           }
           user.setRole("ROLE_USER");
           user.setEnabled(true);
           user.setImageUrl("default.png");
           System.out.println("Agreement "+agreement);
           System.out.println("User : "+user);
           User savedUser = userRepository.save(user);
           session.setAttribute("message",new Message("successfully registered","alert-success"));
           model.addAttribute("user",new User());
           return "signup";
       }catch (Exception e) {
           e.printStackTrace();
           model.addAttribute("user",user);
           session.setAttribute("message",new Message(e.getMessage().toString(),"alert-error"));
           return "signup";
       }

    }

}
