package com.lmx.api.controller;


import com.lmx.api.rpc.UserRPC;
import com.lmx.api.utils.CookieUtil;
import com.lmx.api.utils.UUIDUtil;
import com.lmx.common.entitys.LoginEntity;
import com.lmx.common.entitys.LoginResponse;
import com.lmx.common.entitys.RespBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.concurrent.TimeUnit;

@RequestMapping("/login")
@Controller
@Slf4j
public class LoginController {

    @Resource
    private UserRPC userRPC;

    @Autowired
    private RedisTemplate redisTemplate;

//    @GetMapping("/hello")
//    @ResponseBody
//    public String hello(){
//        return userRPC.hello();
//    }

    @RequestMapping("/hello")
    public String hello(){
        return "hello";
    }



    @RequestMapping(value = "/toLogin")
    public String toLogin(){
        return "login";
    }

    @PostMapping("/doLogin")
    @ResponseBody
    public RespBean doLogin(@Valid LoginEntity login, HttpServletRequest request, HttpServletResponse response){
        LoginResponse loginResponse = userRPC.login(login);
        RespBean respBean = loginResponse.getRespBean();
        if (respBean.getCode()!=200){
            return respBean;
        }
        String ticket = UUIDUtil.uuid();
        redisTemplate.opsForValue().set("user="+ticket,loginResponse.getUserInfoEntity(),60*24, TimeUnit.MINUTES);
        CookieUtil.setCookie(request,response,"userTicket",ticket);
        return RespBean.success(ticket);
    }
}
