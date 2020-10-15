package com.practice.http_servlet.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author horizonliu
 * @date 2018/12/11 4:41 PM
 */

@Data
@AllArgsConstructor
public class CommonResponseBody extends ResponseBody {
    private Object data;

    public CommonResponseBody() {
        super(0);
    }

    public CommonResponseBody(int code, Object data) {
        super(code);
        this.data = data;
    }

    public CommonResponseBody(int code, String msg, Object data) {
        super(code, msg);
        this.data = data;
    }

}
