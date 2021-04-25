package com.practice.http_servlet.future;

/**
 * @author horizonliu
 * @date 2021/4/23 11:05 上午
 */
public class Client {

    public Data request(final String queryStr) {
        final FutureData futureData = new FutureData();
        new Thread() {
            @Override
            public void run() {
                try {
                    RealData realData = new RealData(queryStr);
                    futureData.setRealData(realData);
                } catch (Exception ex) {

                }

            }
        }.start();
        return futureData;
    }

}
