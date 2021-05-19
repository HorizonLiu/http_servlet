package com.practice.http_servlet.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horizonliu
 * @date 2018/12/11 4:39 PM
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBody {

    private int code = 0;
    private String msg;

    public ResponseBody(int code) {
        this.code = code;
        this.msg = "success";
    }
}
