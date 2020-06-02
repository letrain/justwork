package com.ylzinfo.print_recipe.service.print.base;

import com.sun.jna.Native;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author zengxiang
 * @create 2019-12-17 13:02
 * @desc
 **/
public class Clibrary  {
    private IClibrary INSTANCE;

    private Clibrary(){
        Logger logger = LoggerFactory.getLogger(Clibrary.class);
        logger.info("加载dll文件=====》开始");
//        INSTANCE = Native.load("/jgyy/PrinterTool32.dll", IClibrary.class);
        INSTANCE = Native.load("/seyy/PrinterTool64.dll", IClibrary.class);
        logger.info("加载dll文件=====》成功");
    }
    public IClibrary getIClibrary(){
        return INSTANCE;
    }

    public static Clibrary newClibrary(){
        return new  Clibrary();
    }

}