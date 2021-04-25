package com.practice.http_servlet.future;

/**
 * @author horizonliu
 * @date 2021/4/23 10:58 上午
 */
public class FutureData implements Data {

    protected RealData realData = null;
    protected boolean isReady = false;


    public synchronized void setRealData(RealData realData) {
        if (isReady) {
            return;
        }
        this.realData = realData;
        isReady = true;
        notifyAll();
    }

    @Override
    public synchronized String getResult() {
        while (!isReady) {
            try {
                wait();
            } catch (Exception ex) {
                System.out.println("exception:" + ex.getMessage());
            }
        }
        return realData.getResult();
    }
}
