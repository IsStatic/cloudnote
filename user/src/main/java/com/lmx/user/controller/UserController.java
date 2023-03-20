package com.lmx.user.controller;

import com.lmx.common.entitys.*;
import com.lmx.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


/**
 * 
 *
 * @author liang
 * @date 2023-03-12
 */
@RestController
@RequestMapping("/user")
//@Slf4j
public class UserController {

    @Autowired
    private IUserService userService;
    @GetMapping("/hello")
    public String hello(){
        return "hello";
    }


    /**
     * 登录
     *由于Feign辉使用HTTP请求的方式调用，所以必须要接受参数，即添加@RequestBody或@PathVariable等
     * @param login 登录
     * @return {@link RespBean}
     */
    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginEntity login){
        return userService.login(login);
    }
    public UserInfoEntity userInfo(UserInfoRequest userInfoRequest){
        if (null == userInfoRequest){
            return null;
        }
        return null;
    }

    @PostMapping("/register")
    public RespBean register(@RequestBody LoginEntity login){
        return userService.register(login);
    }

    @PostMapping("/update")
    public RespBean update(@RequestBody UpdateEntity update){
        return userService.update(update);
    }

    @PostMapping("/updatePass")
    public RespBean updatePass(@RequestBody PassWordEntity passWord){
        return userService.updatePassWord(passWord);
    }
}
