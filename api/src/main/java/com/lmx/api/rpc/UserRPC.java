package com.lmx.api.rpc;

import com.lmx.common.entitys.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Component
@FeignClient(value = "USER")
public interface UserRPC {

    @PostMapping("/user/login")
    public LoginResponse login(LoginEntity login);

    @GetMapping("/user/hello")
    public String hello();

    @GetMapping("user/hello")
    public String hello2();

    @PostMapping("/user/register")
    public RespBean register(LoginEntity login);

    @PostMapping("/user/update")
    public RespBean update(UpdateEntity update);

    @PostMapping("/user/updatePass")
    public RespBean updatePass(PassWordEntity passWord);
}
