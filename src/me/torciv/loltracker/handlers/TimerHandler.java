package me.torciv.loltracker.handlers;

import me.torciv.loltracker.Champ;

import java.util.Timer;
import java.util.TimerTask;

public class TimerHandler extends TimerTask {

    private Timer timer;
    private float time;
    private int currentTime = 0;
    private Champ champ;
    private int sum;


    public TimerHandler(float time, Champ champ, int sum) {
        this.time = time;
        this.champ = champ;
        this.sum = sum;
        this.start();
    }


    public void start() {
        timer = new Timer();
        timer.schedule(this, 0, 1000L);
    }

    @Override
    public void run() {
        currentTime++;

        if (currentTime == time - 1) {
            timer.cancel();
            timer.purge();
            if (this.sum == 1) {
                this.champ.setS1t(false);
            } else {
                this.champ.setS2t(false);
            }

        }

        if (currentTime % 5 == 0) {
            if(sum == 1){
                System.out.println("[" + champ.getChampion().getName() + "] "
                        + champ.getSummoners().getKey().getName() + " : "
                        + (time - currentTime) + " Seconds left.");
            }else{
                System.out.println("[" + champ.getChampion().getName() + "] "
                        + champ.getSummoners().getValue().getName() + " : "
                        + (time - currentTime) + " Seconds left.");
            }


        }

    }
}
