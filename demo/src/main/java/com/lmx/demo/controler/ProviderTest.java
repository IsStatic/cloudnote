package com.lmx.demo.controler;

import com.lmx.demo.rpc.Provider;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/provider")
public class ProviderTest {

    @Resource
    private Provider provider;

    @RequestMapping("/")
    public String login(){
        return "forward:/hello";
    }

    @RequestMapping("forwardTest")
    public String forwardTest(){
        return "forward:/test";
    }
    @ResponseBody
    @RequestMapping("/test")
    public String test(){
        return "test";
    }
    @ResponseBody
    @RequestMapping("/hello")
    public String he() {
        return provider.hei();
    }
    @ResponseBody
    @RequestMapping("/timeout")
    public String timeout() {
        //feign默认等待1秒
        return provider.timeout();
    }

    @ResponseBody
    @RequestMapping("/hello2")
    public String he2() {
        return provider.hei2();
    }
    @ResponseBody
    @RequestMapping("/timeout2")
    public String timeout2() {
        //feign默认等待1秒
        return provider.timeout2();
    }
}
