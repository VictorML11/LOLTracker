package me.torciv.loltracker.trackers;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.NativeKeyListener;

public class TabTracker implements NativeKeyListener {

    private boolean showingStats;

    public TabTracker() {
        this.showingStats = false;
    }

    @Override
    public void nativeKeyTyped(NativeKeyEvent e) {

    }

    @Override
    public void nativeKeyPressed(NativeKeyEvent e) {
        if(e.getKeyCode() == NativeKeyEvent.VC_TAB && !showingStats){
            System.out.println("Pulsado tabulador!");
            this.showingStats = true;
        }
    }

    @Override
    public void nativeKeyReleased(NativeKeyEvent e) {
        if(e.getKeyCode() == NativeKeyEvent.VC_TAB &&showingStats){
            System.out.println("Despulsado tabulador!");
            this.showingStats = false;
        }
    }

    public boolean isShowingStats() {
        return showingStats;
    }

    public void setShowingStats(boolean showingStats) {
        this.showingStats = showingStats;
    }
}
