package com.practice.http_servlet.controller;

import com.practice.http_servlet.bean.CommonResponseBody;
import com.practice.http_servlet.service.BasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * restTemplate拦截器 测试 调用接口
 * @author horizonliu
 * @date 2018/12/11 9:20 PM
 */

@RestController
@RequestMapping(value = "/{version}/interceptor")
public class InterceperTestController {
    @Autowired
    private BasicService basicService;

    /**
     * 被调方
     */
    @PostMapping(value = "/server", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponseBody beCalled(@PathVariable("version") String version,
                                       @RequestHeader("testId") String testId) {
        return new CommonResponseBody(0, (Object)testId);
    }

    /**
     * 调用方
     */
    @PostMapping(value = "/client")
    public CommonResponseBody call(@PathVariable("version") String version) {
        return basicService.postProxy(version, "interceptor", "server", null, null, CommonResponseBody.class).getBody();
    }

}
