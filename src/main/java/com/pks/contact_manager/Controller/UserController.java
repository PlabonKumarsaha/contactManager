package com.pks.contact_manager.Controller;

import com.pks.contact_manager.dao.UserRepository;
import com.pks.contact_manager.entity.Contact;
import com.pks.contact_manager.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserRepository userRepository;

    // principle stores the unique value of a user. In this case email.
    //runs everytime for each and every other functions. Adds common data to response
    @ModelAttribute
    public void addCommonData(Model model, Principal principal) {

        String userName = principal.getName();
        User user = userRepository.getUserByUserName(userName);
        System.out.println("User Name "+userName);
        System.out.println("User  "+user);
        model.addAttribute("user",user);

    }
    //dash board home
    @GetMapping("/index")
    public String dashBoard(Model model, Principal principal){

        return "normal/user_dashboard";
    }

    //open add form handler
    @GetMapping("/add-contact")
    public String addContact(Model model) {

        model.addAttribute("title","Add contact");
        model.addAttribute("contact", new Contact());
        return "normal/add_contact_form";
    }

    @PostMapping("/process-contact")
    public String processContact(@ModelAttribute("contact") Contact contact, @RequestParam("profileImage")MultipartFile file
            , Principal principal) {

        try{
            String name = principal.getName();
            User user = userRepository.getUserByUserName(name);

            //processing and uploading file
            if(file.isEmpty()){

            }else {
                contact.setImage(file.getOriginalFilename());
                File saveFile = new ClassPathResource("static/img").getFile();
                Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+file.getOriginalFilename());
                Files.copy(file.getInputStream(),path, StandardCopyOption.REPLACE_EXISTING);
            }
            contact.setUser(user);
            user.getContacts().add(contact);
            this.userRepository.save(user);
            System.out.println("data"+contact);
        }catch (Exception e) {
            e.printStackTrace();
            System.out.println("ERROR : "+e.getMessage());
        }


        return "normal/add_contact_form";
    }



}
