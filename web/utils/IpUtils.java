package com.cp.web.utils;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 获取Ip的工具类
 */
public class IpUtils {
    private static final Logger log = LoggerFactory.getLogger(IpUtils.class);
    private static String X_FORWARDED = "x-forwarded-for";
    private static String PROXY_CLIENT = "Proxy-Client-IP";
    private static String WL_PROXY_CLIENT = "WL-Proxy-Client-IP";
    private static String LOCALHOST = "127.0.0.1";
    private static String UNKNOW = "unknown";

    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        try {
            ipAddress = request.getHeader(X_FORWARDED);
            //判断是否是x-forwarded方式，不是则选择下一个
            if(StringUtils.isEmpty(ipAddress) || ipAddress.equalsIgnoreCase(UNKNOW)){
                ipAddress = request.getHeader(PROXY_CLIENT);
            }
            if(StringUtils.isEmpty(ipAddress) || ipAddress.equalsIgnoreCase(UNKNOW)){
                ipAddress = request.getHeader(WL_PROXY_CLIENT);

            }
            if(StringUtils.isEmpty(ipAddress) || ipAddress.equalsIgnoreCase(UNKNOW)){
                ipAddress = request.getRemoteAddr();
                //若是本机，
                if(ipAddress.equals(LOCALHOST) || ipAddress.equals("0:0:0:0:0:0:0:1")){
                    //根据网卡取本机配置的IP
                    InetAddress inetAddr = null;
                    try {
                        inetAddr = InetAddress.getLocalHost();
                    } catch (UnknownHostException e) {
                        log.error("【获取localhost】异常");
                    }
                    ipAddress = inetAddr.getHostAddress();
                }
            }
            //对于通过多个代理的情况 第一个IP 为客户端的真实IP，多个IP按照 ',' 分割
            if(!StringUtils.isEmpty(ipAddress) && ipAddress.length()>15){
                if(ipAddress.indexOf(",") > 0){
                    ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("【获取IP】出现异常");
            ipAddress = "";
        }
        return ipAddress;
    }
}
