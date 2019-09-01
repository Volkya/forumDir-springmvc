package com.store.freak.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    // Static routes
    @GetMapping("/register")
    public String register(){
        return "register";
    }

    @GetMapping("/new-product")
    public String newProduct(){
        return "newProduct";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/top")
    public String top(){
        return "top";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @GetMapping("/locations")
    public String locations(){
        return "locations";
    }

    @GetMapping("/work")
    public String work(){
        return "work";
    }

}
