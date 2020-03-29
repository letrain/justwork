package cn.gnw.cloud.provider.controller;

import cn.gnw.cloud.provider.service.RedisService;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProviderController {
    @Autowired
    private DiscoveryClient client;
    @Autowired
    private RedisService redisService;
    @Value("${oui}")
    private String oui;

    @RequestMapping("/cloud/provider")
    public String test() {
        List<String> instances = client.getServices();
//        redisService.testArticle();
//        redisService.testWeb();
        System.out.println("oui=" + oui);
        return JSON.toJSONString(instances);
    }
}
