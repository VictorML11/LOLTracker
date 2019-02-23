package me.torciv.loltracker.summoners;

public abstract class Summoner {

    private long cooldown;

    public Summoner(long cooldown) {
        this.cooldown = cooldown;
    }

    public boolean isInCooldown(){
        return false;
    }

    public boolean expectedTime(){
        return false;
    }
}
