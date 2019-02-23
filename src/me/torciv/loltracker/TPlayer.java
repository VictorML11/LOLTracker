package me.torciv.loltracker;

import javafx.util.Pair;
import me.torciv.loltracker.summoners.Summ;

public class TPlayer {

    private Role role;
    private Pair<Summ, Summ> summoners;

    public TPlayer(Role role, Pair<Summ, Summ> summoners) {
        this.role = role;
        this.summoners = summoners;
    }
}
