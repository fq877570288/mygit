package cn.cucsi.bsd.ucc.rest.adapter;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by lanfa on 2017/10/19.
 */
@Configuration
public class UccConfigurerAdapter extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*").allowedMethods(
                "GET", "HEAD", "PUT", "DELETE", "POST", "OPTIONS"
        );
    }


}
