package com.ylzinfo.print_recipe.service.print;

import com.ylzinfo.print_recipe.service.print.base.Clibrary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zengxiang
 * @create 2019-12-15 16:32
 * @desc
 **/

@Service
public class PrintService {

    @Autowired
    private Clibrary INSTANCE;

    public String doPrint(String input) {
        String err = "";

        System.out.println("Input:\n" + input);
        try {
            char e = INSTANCE.getIClibrary().PrintRecipe(2,input, err);
            System.out.println("Output:\n" + err);
            System.out.println("e:\n" + e);
        } catch (Exception e) {
            System.out.println("打印失败！");
            return "false";
        }
        return "true";
    }
}