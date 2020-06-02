package com.ylzinfo.print_recipe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class PrintRecipeApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrintRecipeApplication.class, args);
        /*System.out.println("加载dll文件");
        IClibrary INSTANCE = (IClibrary) Native.loadLibrary(("E:\\job\\互联网医院接口\\福建省机关医院\\PrinterTestV2019121601\\PrinterTool64.dll"),IClibrary.class);*/
        System.out.println("启动成功！");
    }

}
