package com.practice.http_servlet.controller;

import com.practice.http_servlet.bean.CommonResponseBody;
import com.practice.http_servlet.config.ParamConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author horizonliu
 * @date 2018/12/17 2:19 PM
 */
@RestController
@RequestMapping(value = "/config")
public class ConfigController {
    @Autowired
    private ParamConfig paramConfig;

    @PostMapping(value = "/map")
    public CommonResponseBody getMapParams() {

        return new CommonResponseBody(0, paramConfig);
    }
}
