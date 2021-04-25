package com.practice.http_servlet.future;

/**
 * @author horizonliu
 * @date 2021/4/23 10:58 上午
 */
public class RealData implements Data {

    protected String res;

    public RealData(String res) {
        this.res = res;
    }

    @Override
    public String getResult() {
        return res;
    }
}
