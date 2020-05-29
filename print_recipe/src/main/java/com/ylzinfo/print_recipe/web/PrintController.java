package com.ylzinfo.print_recipe.web;

import com.ylzinfo.print_recipe.service.print.PrintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author zengxiang
 * @create 2019-12-15 16:35
 * @desc
 **/
@Controller
public class PrintController {

    @Autowired
    private PrintService printService;

    @RequestMapping(value = "/doPrint",method = RequestMethod.POST)
    @ResponseBody
    public String doPrint(@RequestBody String printInfo){
        return printService.doPrint(printInfo);
    }

    @RequestMapping("/index")
    @ResponseBody
    public String index(String printInfo){
        return "你好，打印机！";
    }
}