package com.lmx.demo.rpc;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

@Component
@FeignClient(value = "RPCDEMO-PROVIDER")
//@RequestMapping("/provider/")
public interface Provider {

    @GetMapping("hei")
    public String hei();

    @GetMapping("timeout")
    public String timeout();

    @GetMapping("/provider/hei")
    public String hei2();

    @GetMapping("/provider/timeout")
    public String timeout2();
}
