package com.cp.web.controller;

import com.cp.web.utils.IpUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/website")
public class HttpController {

    @GetMapping("/test")
    public String getIp(HttpServletRequest request){
        return IpUtils.getIpAddr(request);
    }

}
