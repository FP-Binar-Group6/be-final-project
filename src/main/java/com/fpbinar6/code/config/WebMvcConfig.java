package com.fpbinar6.code.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fpbinar6.code.utils.TimestampConverter;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping("/**")
        .allowedMethods("*")
        .allowedOrigins("*")
        .allowedHeaders("*")
        .allowCredentials(false)
        .maxAge(-1);
  }

  @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new TimestampConverter());
    }
}