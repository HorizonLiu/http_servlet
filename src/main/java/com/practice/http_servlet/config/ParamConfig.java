package com.practice.http_servlet.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import scala.Tuple2;

import java.util.Map;

/**
 * @author horizonliu
 * @date 2018/12/17 2:16 PM
 */

@Configuration
@ConfigurationProperties
@Data
public class ParamConfig {
    private Map<Integer, Tuple2<String, String>> errMsg;
}
