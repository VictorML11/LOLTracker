package me.torciv.loltracker;

import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseTracker implements NativeMouseInputListener {

    private TabTracker tabTracker;
    private Side side;

    public MouseTracker(TabTracker tabTracker, Side side) {
        this.tabTracker = tabTracker;
        this.side = side;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        if(tabTracker.isShowingStats()){
            int x = e.getX();
            int y = e.getY();
            int summ = this.side.isSummoner(x, y);
            if(summ != -1){
                System.out.println("x: " + e.getX() + " y: " + e.getY());
                System.out.println("Summoner number: " + summ);
            }

        }

    }

    @Override
    public void nativeMousePressed(NativeMouseEvent e) {

    }

    @Override
    public void nativeMouseReleased(NativeMouseEvent e) {

    }

    @Override
    public void nativeMouseMoved(NativeMouseEvent e) {

    }

    @Override
    public void nativeMouseDragged(NativeMouseEvent e) {

    }
}
