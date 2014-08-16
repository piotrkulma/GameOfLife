package gameol.simulation;

import gameol.simulation.LifeFrameAction;

/**
 * Created by Piotr Kulma on 16.08.14.
 */
public class GlobalTimer extends Thread {
    private boolean running;
    private long interval;

    private LifeFrameAction lifeFrameAction;

    public GlobalTimer(long interval, LifeFrameAction lifeFrameAction) {
        this.interval = interval;
        this.lifeFrameAction = lifeFrameAction;
    }

    @Override
    public void run() {
        while (true) {
            try {
                lifeFrameAction.performFrame();
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
