package me.torciv.loltracker.summoners;

public abstract class Summ {

    private long cooldown;

    public Summ(long cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isInCooldown(){
        return false;
    }

    public boolean expectedTime(){
        return false;
    }
}
