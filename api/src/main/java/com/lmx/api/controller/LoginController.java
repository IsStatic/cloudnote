package com.lmx.api.controller;


import com.lmx.common.entitys.LoginEntity;
import com.lmx.common.entitys.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/login")
@RestController
@Slf4j
public class LoginController {

    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginEntity login){
        log.info(login.toString());
        return RespBean.success(login);
    }

    @GetMapping("/hello")
    @ResponseBody
    public String hello(){
        return "hello";
    }
}
