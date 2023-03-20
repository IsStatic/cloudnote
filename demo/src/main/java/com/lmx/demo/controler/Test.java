package com.lmx.demo.controler;


import com.lmx.demo.rpc.User;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class Test {

    @Resource
    private User user;

    @GetMapping("/hello")
    public String hello(){
        return user.hello();
    }

    @GetMapping("hello2")
    public String hello2(){
        return user.hello2();
    }

}
