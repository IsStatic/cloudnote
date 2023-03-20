package com.lmx.api.controller;


import com.lmx.api.rpc.UserRPC;
import com.lmx.api.utils.CookieUtil;
import com.lmx.common.entitys.*;
import com.lmx.common.validators.isPhone;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {

    @Resource
    private UserRPC userRPC;

    @RequestMapping("/check")
    public String checkUser(Model model, UserInfoEntity userInfoEntity){
        if (userInfoEntity == null) return "login";
        model.addAttribute("user", userInfoEntity);
        model.addAttribute("userName", userInfoEntity.getId());
        return "hello";
    }
    @RequestMapping("/info")
    public String info(Model model,UserInfoEntity userInfoEntity){
        if (userInfoEntity == null) return "login";
        model.addAttribute("user", userInfoEntity);
        return "userInfo";
    }

    @PostMapping("/register")
    @ResponseBody
    public RespBean register(@Valid LoginEntity login){
        log.info("开始注册,log:\t"+login.toString());
        return userRPC.register(login);
    }

    @PostMapping("/update")
    @ResponseBody
    public RespBean update(@RequestParam(value = "phone") @isPhone String phone, @RequestParam(value = "userName") String userName, HttpServletRequest request){
        UpdateEntity update = new UpdateEntity();
        update.setUserName(userName);
        update.setNewPhone(phone);
        update.setTicket(CookieUtil.getCookieValue(request,"userTicket"));
        return userRPC.update(update);
    }

    @PostMapping("/updatePass")
    @ResponseBody
    public RespBean updatePass(@RequestParam(value = "password") String password,@RequestParam(value = "newPassword") String newPassword,HttpServletRequest request){
        System.out.println("开始修改密码：元密码："+password+"\t新密码："+newPassword);
        PassWordEntity passWord = new PassWordEntity();
        passWord.setPassWord(password);
        passWord.setNewPass(newPassword);
        passWord.setTicket(CookieUtil.getCookieValue(request,"userTicket"));
        return userRPC.updatePass(passWord);
    }

    @RequestMapping("/toPassWordHome")
    public String toPassWordHome(UserInfoEntity userInfoEntity){
        return "updatePassword";
    }
}
