package gameol.simulation;

import gameol.simulation.LifeFrameAction;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class GlobalTimer extends Thread {
    private long interval;

    private boolean suspend;

    private LifeFrameAction lifeFrameAction;

    public GlobalTimer(long interval, LifeFrameAction lifeFrameAction) {
        this.interval = interval;
        this.lifeFrameAction = lifeFrameAction;
        this.suspend = false;
        lifeFrameAction.performFrame();
    }

    @Override
    public void run() {
        while (true) {
            try {
                lifeFrameAction.performFrame();
                Thread.sleep(interval);
                synchronized (this) {
                    while (suspend) {
                        wait();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void stopGlobalTimer() {
        this.interrupt();
    }

    public void pause() {
        this.suspend = true;
    }

    public synchronized void wakeup() {
        this.suspend = false;
        notify();
    }
}
