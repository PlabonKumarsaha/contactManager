package com.pks.contact_manager.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/home")
    public String getHome( Model model) {
        model.addAttribute("title","home-smart contract");
        return "home";
    }

    @RequestMapping("/about")
    public String getAbout( Model model) {
        model.addAttribute("title","About-smart contract");
        return "about";
    }
}
