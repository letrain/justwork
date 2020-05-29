package com.ylzinfo.print_recipe.service.print.base;

import com.sun.jna.Native;

/**
 * @author zengxiang
 * @create 2019-12-17 13:02
 * @desc
 **/
public class Clibrary  {

    private IClibrary INSTANCE;

    private Clibrary(){
        System.out.println("加载dll文件");
        //INSTANCE = (IClibrary) Native.loadLibrary(("E:\\job\\互联网医院接口\\福建省机关医院\\PrinterTestV2019121601\\PrinterTool.dll"), IClibrary.class);
        INSTANCE = (IClibrary) Native.loadLibrary(("/seyy/PrinterTool.dll"), IClibrary.class);
    }
    public IClibrary getIClibrary(){
        return INSTANCE;
    }
    public static Clibrary newClibrary(){
        return new  Clibrary();
    }

}