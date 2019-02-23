package me.torciv.loltracker.trackers;

import me.torciv.loltracker.handlers.SumRegionHandler;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

public class MouseTracker implements NativeMouseInputListener {

    private TabTracker tabTracker;
    private SumRegionHandler SumRegionHandler;

    public MouseTracker(TabTracker tabTracker, SumRegionHandler SumRegionHandler) {
        this.tabTracker = tabTracker;
        this.SumRegionHandler = SumRegionHandler;
    }

    @Override
    public void nativeMouseClicked(NativeMouseEvent e) {
        if(tabTracker.isShowingStats()){
            int x = e.getX();
            int y = e.getY();
            int summ = this.SumRegionHandler.isSummoner(x, y);
            if(summ != -1){
                System.out.println("x: " + e.getX() + " y: " + e.getY());
                System.out.println("Summ number: " + summ);
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
