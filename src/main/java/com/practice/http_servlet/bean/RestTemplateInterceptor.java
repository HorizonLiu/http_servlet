package com.practice.http_servlet.bean;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.MediaType;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author horizonliu
 * @date 2018/12/11 4:48 PM
 */

@Component
public class RestTemplateInterceptor implements ClientHttpRequestInterceptor {
    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        addRequestHeader(request);
        ClientHttpResponse response = execution.execute(request, body);
        return response;
    }

    private void addRequestHeader(HttpRequest request) {
        HttpHeaders headers = request.getHeaders();

        // 设置请求类型
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("testId","add by interceptor");
    }
}
