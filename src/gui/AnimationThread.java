/*
 * Copyright (C) 2018 Szysz
 */
package gui;

/**
 *
 * @author Szysz
 */
public class AnimationThread extends Thread {

    GoAFXMLController controller;

    boolean isStopped = false;
    int speed = 1;

    public AnimationThread(GoAFXMLController goa) {
        controller = goa;
    }

    @Override
    public synchronized void run() {
        while (!isStopped) {
            controller.getGc().clearRect(0, 0, controller.getCanvas().getWidth(), controller.getCanvas().getHeight());
            controller.view();
            controller.getCa().nextIteration();
            try {
                Thread.sleep(500/speed);
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
