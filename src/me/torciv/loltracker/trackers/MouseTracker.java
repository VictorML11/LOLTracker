package me.torciv.loltracker.trackers;

import me.torciv.loltracker.Champ;
import me.torciv.loltracker.Main;
import me.torciv.loltracker.handlers.SumRegionHandler;
import me.torciv.loltracker.handlers.TimerHandler;
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
        if (tabTracker.isShowingStats()) {
            int x = e.getX();
            int y = e.getY();
            int summ = this.SumRegionHandler.isSummoner(x, y);
            if (summ != -1) {
                System.out.println("x: " + e.getX() + " y: " + e.getY());
                System.out.println("Summ number: " + summ);

                int target = (int) Math.ceil((summ / 2.0D));

                Champ champ = Main.enemyTeam.get(target-1);
                double t;
                if (summ % 2 == 0) {
                    if (!champ.isS2t()) {
                        t = champ.getSummoners().getValue().getCooldowns().get(0);
                        if (champ.isInsight()) {
                            t *= 0.95f;
                        }
                        TimerHandler timerHandler = new TimerHandler((float) t, champ, 2);
                        champ.setS2t(true);
                    }

                } else {
                    if (!champ.isS1t()) {
                        t = champ.getSummoners().getKey().getCooldowns().get(0);
                        if (champ.isInsight()) {
                            t *= 0.95f;
                        }
                        TimerHandler timerHandler = new TimerHandler((float) t, champ, 1);
                        champ.setS1t(true);
                    }
                }

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
