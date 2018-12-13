package com.practice.http_servlet.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * 通用的请求透传接口
 *
 * @author horizonliu
 * @date 2018/12/11 4:38 PM
 */

@Service
public class BasicService {

    @Resource
    private RestTemplate interceptorRestTemplate;

    private static final String URI_FORMAT = "http://localhost:18080/%s/%s/%s";

    /**
     * 通用post透传接口
     *
     * @param version      版本号
     * @param cmd          一级命令
     * @param subcmd       二级命令
     * @param headers      请求头
     * @param body         请求体
     * @param responseType 返回类型
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> postProxy(String version, String cmd, String subcmd, HttpHeaders headers, Object body, Class<T> responseType) {
        String uri = String.format(URI_FORMAT, version, cmd, subcmd);
        HttpEntity request = new HttpEntity<>(body, headers);
        ResponseEntity<T> responseEntity = interceptorRestTemplate.postForEntity(uri, request, responseType);
        return responseEntity;
    }

    /**
     * 通用get透传接口
     *
     * @param version 版本号
     * @param cmd 一级命令
     * @param subcmd 二级命令
     * @param headers 请求头
     * @param responseType 返回类型
     * @param <T>
     * @return
     */
    public <T> ResponseEntity<T> getProxy(String version, String cmd, String subcmd, HttpHeaders headers, Class<T> responseType) {
        String uri = String.format(URI_FORMAT, version, cmd, subcmd);
        HttpEntity request = new HttpEntity<>(headers);
        ResponseEntity<T> responseEntity = interceptorRestTemplate.exchange(uri, HttpMethod.GET, request, responseType);
        return responseEntity;
    }

}
