package com.lmx.demo.rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "USER")
public interface User {

    @GetMapping("/user/hello")
    public String hello();

    @GetMapping("user/hello")
    public String hello2();
}
