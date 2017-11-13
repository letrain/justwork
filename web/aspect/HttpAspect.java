package com.cp.web.aspect;

import com.cp.dao.WebsiteMasterMapper;
import com.cp.pojo.Log;
import com.cp.web.entity.WebsiteMaster;
import com.cp.web.service.IpAddrService;
import com.cp.web.utils.IpUtils;
import com.jfinal.template.ext.directive.Str;
import com.sun.org.apache.regexp.internal.RE;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@Aspect
@Component
public class HttpAspect {

    private static final Logger log = LoggerFactory.getLogger(HttpAspect.class);

    @Autowired
    private WebsiteMasterMapper websiteMasterMapper;

    @Autowired
    private IpAddrService ipAddrService;

    private static WebsiteMaster websiteMaster;

    //保存访问量
    private static Map<String,Integer> ipMap;

    //使用set
    private static Set<WebsiteMaster> websiteMasterSet;

    private static String content;


    static {
        websiteMaster = new WebsiteMaster();
        ipMap = new HashMap<>();
        websiteMasterSet = new HashSet<>();
    }

//    @Pointcut("execution(public * com.cp.web.controller.*.*(..))")
    @Pointcut("execution(public * com.cp.controller.CPController.*(..))")
    public void webcon(){}

    @Before("webcon()")
    public void doBefore(){

        ServletRequestAttributes attributes = (ServletRequestAttributes)
                RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String ipaddr = IpUtils.getIpAddr(request);
        //不管ip是否相同都统计到访问量
        if(ipMap.size()==0){
            ipMap.put("ipaddr",1);
        }else {
            Integer count = ipMap.get("ipaddr");
            ipMap.put("ipaddr",count+1);
        }
        log.info("访问量={}",ipMap.get("ipaddr"));

        // 判断ip是否已保存到数据库
        WebsiteMaster result = websiteMasterMapper.findByIpAddr(ipaddr);
        // 更新浏览内容
        if(result != null){
            String content = result.getVisitContent();
            //TODO 如果此网页未访问 则添加
            if(!content.equals(request.getRequestURL().toString())){
                content = content + request.getRequestURL().toString();
                //TODO  怎么判断
//                websiteMasterMapper.updateByIp(ipaddr,content);
        }
        }else {
            setWebsiteMaster(ipaddr,request);
            // 使用TimerTask任务 定时 20min 统一保存到数据库
            websiteMasterSet.add(websiteMaster);
//        websiteMasterMapper.save(websiteMaster);
        }


    }

    private void setWebsiteMaster(String ipaddr, HttpServletRequest request){
        websiteMaster.setIp(ipaddr);
        websiteMaster.setCity(ipAddrService.getSubCity(ipaddr));
        websiteMaster.setVisitTime(new Date());
        websiteMaster.setStayTime("5555s");//todo
        websiteMaster.setVisitContent(request.getRequestURL().toString());
    }

    public  Set<WebsiteMaster> getWebsiteMasterSet() {
        return websiteMasterSet;
    }

    public static void setWebsiteMasterSet() {
        HttpAspect.websiteMasterSet = new HashSet<>();
    }
}
