package com.practice.http_servlet.controller;

import com.practice.http_servlet.bean.CommonResponseBody;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.util.Map;

/**
 * 不同传参方式的接收方法
 * @author horizonliu
 * @date 2018/12/12 3:29 PM
 */

@RestController
@RequestMapping(value = "/{version}/diff_params")
public class UploadParamsController {

    @Resource
    private RestTemplate commonRestTemplate;

    @PostMapping(value = "/params/key")
    public CommonResponseBody uploadParams(@PathVariable("version") String version,
                                           @RequestParam("testId") String testId,
                                           @RequestParam("date") String date) {

        Params data = new Params(version, testId, date);
        return new CommonResponseBody(0, data);
    }

    @PostMapping(value = "/params/map")
    public CommonResponseBody uploadParams(@PathVariable("version") String version,
                                           @RequestParam Map<String,String> paramMap ) {

        Params data = new Params();
        data.setVersion(version);
        if (paramMap.containsKey("testId")) {
            data.setTestId(paramMap.get("testId"));
        }
        if (paramMap.containsKey("date")) {
            data.setDate(paramMap.get("date"));
        }
        return new CommonResponseBody(0, data);
    }

    @PostMapping(value = "/headers/key", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponseBody uploadHeader(@PathVariable("version") String version,
                                           @RequestHeader("testId") String testId,
                                           @RequestHeader("date") String date) {
        Params data = new Params(version, testId, date);
        return new CommonResponseBody(0, data);
    }

    @PostMapping(value = "/headers/map", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public CommonResponseBody uploadsHeader(@PathVariable("version") String version,
                                            @RequestHeader Map<String,String> headerMap) {


        Params data = new Params();
        data.setVersion(version);
        if (headerMap.containsKey("testid")) {
            data.setTestId(headerMap.get("testid"));
        }
        if (headerMap.containsKey("date")) {
            data.setDate(headerMap.get("date"));
        }
        return new CommonResponseBody(0, data);
    }

    @PostMapping(value = "/test/send", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public String testSend() {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("hello", "xxxx");
        params.add("xiha", "test");

        // 请求头
        HttpHeaders headers = new HttpHeaders();
        //  请勿轻易改变此提交方式，大部分的情况下，提交方式都是表单提交
        MediaType type = MediaType.parseMediaType("application/x-www-form-urlencoded; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        //我们发起 HTTP 请求还是最好加上"Connection","close" ，有利于程序的健壮性
        headers.set("Connection", "close");

        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params, headers);
        //  执行HTTP请求
        ResponseEntity<String> response = commonRestTemplate.postForEntity("http://localhost:8080/test/recv", requestEntity, String.class);
        return response.getBody();
    }


    @PostMapping(value = "/test/recv", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String testRecv(@RequestBody MultiValueMap params) {

        return params.toString();
    }

    @PostMapping(value = "/test/recvStr", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String testRecvStr(@RequestBody String params) {

        return params;
    }



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class Params {
        private String version;
        private String testId;
        private String date;
    }

}
