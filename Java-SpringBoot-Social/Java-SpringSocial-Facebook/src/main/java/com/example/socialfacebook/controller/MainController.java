package com.example.socialfacebook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {

//    private Facebook facebook;
//    private ConnectionRepository connectionRepository;
//
//    @Autowired
//    public MainController(Facebook facebook, ConnectionRepository connectionRepository) {
//        this.facebook = facebook;
//        this.connectionRepository = connectionRepository;
//    }
//
//    @RequestMapping(value = "feed", method = RequestMethod.GET)
//    public String feed(Model model) {
//
//        if (connectionRepository.findPrimaryConnection(Facebook.class) == null) {
//            return "redirect:/connect/facebook";
//        }
//
//        User userProfile = facebook.userOperations().getUserProfile();
//        model.addAttribute("userProfile", userProfile);
//        PagedList<Post> userFeed = facebook.feedOperations().getFeed();
//        model.addAttribute("userFeed", userFeed);
//        return "feed";
//    }

    @RequestMapping(
        value = "/",
        method = RequestMethod.GET
    )
    public String index() {
        return "index";
    }

}