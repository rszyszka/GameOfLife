/*
 * Copyright (C) 2018 Szysz
 */
package gui;

import java.util.concurrent.TimeUnit;
import javafx.application.Platform;

/**
 *
 * @author Szysz
 */
public class AnimationThread extends Thread {

    final GoAFXMLController controller;

    boolean isStopped = false;
    int speed = 1;

    public AnimationThread(GoAFXMLController goa) {
        controller = goa;
    }

    @Override
    public synchronized void run() {
        while (!isStopped) {
            if (controller.getCa() != null) {
                controller.getCa().nextIteration();
            }
            Platform.runLater(() -> {
                controller.getGc().clearRect(0, 0, controller.getCanvas().getWidth(), controller.getCanvas().getHeight());
                controller.view();
            });
            try {
                TimeUnit.MILLISECONDS.sleep(500 / speed);
            } catch (InterruptedException ex) {
            }
        }
    }

    public boolean isIsStopped() {
        return isStopped;
    }

    public void setIsStopped(boolean isStopped) {
        this.isStopped = isStopped;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

}
