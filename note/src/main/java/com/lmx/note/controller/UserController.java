package com.lmx.note.controller;

import com.lmx.common.entitys.RespBean;
import com.lmx.user.service.IUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;

    @PostMapping("/login")
    public RespBean login(String phone, String password, HttpServletRequest request, HttpServletResponse response){
        return userService.login(phone,password,request,response);
    }
}
