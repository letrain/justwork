package com.cp.web.service;

public interface IpAddrService {
    /**
     * 初始化
     */
    void init();

    String getSubCity(String ipAddress);
}
