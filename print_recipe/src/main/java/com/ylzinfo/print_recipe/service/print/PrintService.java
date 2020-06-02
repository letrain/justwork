package com.ylzinfo.print_recipe.service.print;

import com.ylzinfo.print_recipe.service.print.base.Clibrary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

/**
 * @author zengxiang
 * @create 2019-12-15 16:32
 * @desc
 **/

@Service
public class PrintService {
    private Logger logger = LoggerFactory.getLogger(PrintService.class);

    @Autowired
    private Clibrary INSTANCE;
    @Value("${recipeType}")
    private int recipeType;

    public String doPrint(String input) {
        String output = "";
        logger.info("Input:{}", input);
        try {
            char res = INSTANCE.getIClibrary().PrintRecipe(recipeType, input, output);
            logger.info("Output:{}",output);
            logger.info("res:{}", res);
        } catch (Exception e) {
            logger.info("打印失败！", e);
            return "false";
        }
        return "true";
    }
}