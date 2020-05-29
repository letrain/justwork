package com.ylzinfo.print_recipe.service.print.core;

import com.ylzinfo.print_recipe.service.print.base.Clibrary;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class WebMvcConfig {
    @Bean
    public Clibrary getClibraryBean(){
        return  Clibrary.newClibrary();
    }
}
