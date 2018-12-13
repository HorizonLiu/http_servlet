package com.practice.http_servlet.bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * @author horizonliu
 * @date 2018/12/11 5:38 PM
 */
@Configuration
public class RestTemplateConfig {
    @Autowired
    private RestTemplateInterceptor restTemplateInterceptor;

    @Bean(name = "interceptorRestTemplate")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Arrays.asList(restTemplateInterceptor));
        return restTemplate;
    }

    @Bean(name = "commonRestTemplate")
    public RestTemplate getRestTemplate () {
        return new RestTemplate();
    }
}
