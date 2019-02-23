package me.torciv.loltracker;

import javafx.util.Pair;
import me.torciv.loltracker.summoners.Summoner;

public class Player {

    private Role role;
    private Pair<Summoner, Summoner> summoners;

    public Player(Role role, Pair<Summoner, Summoner> summoners) {
        this.role = role;
        this.summoners = summoners;
    }
}
