package com.ylzinfo.print_recipe.service.print.base;

import com.sun.jna.Library;
import com.sun.jna.Native;

/**
 * @author zengxiang
 * @create 2019-12-14 15:21
 * @desc 调用动态库
 **/
public interface IClibrary extends Library {
    char PrintRecipe(int type,  String pindata, String poutdata);
}