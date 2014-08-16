package gameol.utils;

import gameol.LifeFrameAction;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class GlobalTimer extends Thread {
    private boolean running;
    private long interval;

    private LifeFrameAction action;

    public GlobalTimer(long interval, LifeFrameAction action) {
        this.interval = interval;
        this.action = action;
    }

    @Override
    public void run() {
        while (true) {
            try {
                action.performFrame();
                Thread.sleep(interval);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopGlobalTimer() {
        this.interrupt();
    }
}
