package me.torciv.loltracker.trackers;

import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import me.torciv.loltracker.Champ;
import me.torciv.loltracker.Main;
import me.torciv.loltracker.handlers.SumRegionHandler;
import org.apache.commons.lang3.tuple.MutablePair;
import org.jnativehook.mouse.NativeMouseEvent;
import org.jnativehook.mouse.NativeMouseInputListener;

import java.util.concurrent.TimeUnit;

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


                long millis = System.currentTimeMillis() - Main.currentGame.getCreationTime().getMillis();

                String unlock = String.format("%02d min, %02d sec",
                        TimeUnit.MILLISECONDS.toMinutes(millis),
                        TimeUnit.MILLISECONDS.toSeconds(millis) -
                                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millis))
                );

                System.out.println(unlock);

                MutablePair<SummonerSpell,Float> summoner = champ.getSummoners().get(summ-1);

                if(summoner.getValue() == -1F){
                    double time = summoner.getKey().getCooldowns().get(0);
                    summoner.setValue((float) time);
                    System.out.println("[" + champ.getChampion().getName() + "] "
                            + summoner.getKey().getName() + " : is now in Cooldown!");
                    Main.leagueNotification.notifySummonerUsed(champ.getChampion().getName(), summoner.getKey().getName());
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
