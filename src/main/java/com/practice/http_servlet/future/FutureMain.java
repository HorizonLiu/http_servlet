package com.practice.http_servlet.future;

/**
 * @author horizonliu
 * @date 2021/4/23 11:04 上午
 */
public class FutureMain {

    public static void main(String[] args) {

        Client client = new Client();
        Data data = client.request("测试测试");

        System.out.println("数据 = " + data.getResult());

        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {

        }
        System.out.println("数据 = " + data.getResult());
    }

}
