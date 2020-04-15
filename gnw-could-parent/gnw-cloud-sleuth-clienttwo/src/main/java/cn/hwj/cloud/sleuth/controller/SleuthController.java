package cn.hwj.cloud.sleuth.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class SleuthController {
    @GetMapping("/sleuth2")
    public String trace(HttpServletRequest request) {

        log.info("====<call trace-2>====, TraceId={}, spanId={}",
                request.getHeader("X-B3-TraceId"), request.getHeader("X-B3-SpanId"));
        return "trace2";
    }
}
