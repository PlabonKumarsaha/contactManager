package com.pks.contact_manager.Controller;


import com.pks.contact_manager.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class HomeController {

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
    public String registerUser( @ModelAttribute("user") User user , @RequestParam(value ="agreement",defaultValue = "false") boolean agreement,
                                Model model) {
        System.out.println("Agreement"+agreement);
        System.out.println("User"+user);
        System.out.println();
        return "signup";
    }

}
