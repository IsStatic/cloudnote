package com.lmx.api.controller;

import com.lmx.common.entitys.UserInfoEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {

    @RequestMapping("/")
    public String toHome(){
        return "redirect:/home";
    }

    @RequestMapping("/hello")
    public String hello(Model model, UserInfoEntity userInfoEntity){
        model.addAttribute("user",userInfoEntity);
        return "hello";
    }

    @RequestMapping("/home")
    public String home(UserInfoEntity userInfoEntity){
        if (userInfoEntity == null) return "login";
        return "home";
    }

    @RequestMapping("/docs")
    public String docs(){
        return "docs";
    }
}
