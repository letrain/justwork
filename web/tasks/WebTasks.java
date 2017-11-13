package com.cp.web.tasks;

import com.cp.dao.WebsiteMasterMapper;
import com.cp.web.aspect.HttpAspect;
import com.cp.web.entity.WebsiteMaster;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Component
@EnableScheduling
public class WebTasks {

    private final Logger log = LoggerFactory.getLogger(WebTasks.class);

    @Autowired
    private WebsiteMasterMapper websiteMasterMapper;

    @Autowired
    private HttpAspect httpAspect;

    /**
     * 每20min 将websiteMasters保存到数据库中
     */
    @Scheduled(fixedRate = 1000 *60*20,initialDelay = 1000*60*20)
    @Transactional
    public void timerSave() {
        log.info("*********网站浏览信息定时保存*************");
        Set<WebsiteMaster> websiteMasterSet = httpAspect.getWebsiteMasterSet();
        if(websiteMasterSet.size()<=0){
            log.info("*********暂时无用户浏览网站***************");
        }else {
            for (WebsiteMaster wm : websiteMasterSet){
                try {
                    websiteMasterMapper.save(wm);
                }catch (Exception e){
                    log.error("【保存网站信息】出现异常，msg={}",e.getMessage());
                    throw new RuntimeException("【保存网站信息】");
                }
                //把内容清除
                httpAspect.setWebsiteMasterSet();
            }
        }
    }

    @Scheduled(fixedRate = 1000*60*1,initialDelay = 1000*60*1)
    public void timedupdate(){

    }
}
