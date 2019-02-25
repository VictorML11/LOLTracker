package me.torciv.loltracker;


import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.staticdata.ReforgedRunes;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import lombok.Getter;
import me.torciv.loltracker.handlers.LeagueNotification;
import me.torciv.loltracker.handlers.SumRegionHandler;
import me.torciv.loltracker.trackers.MouseTracker;
import me.torciv.loltracker.trackers.TabTracker;
import me.torciv.loltracker.view.ViewMain;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final String APIKEY = "RGAPI-bb6fb957-739f-4d38-863d-24806d2621b2";
    public static final String SUMMONER_NAME = "Tanu";
    public static final String SUMMONER_NAME3 = "Deloron";
    public static final String SUMMONER_NAME2 = "WhirLind7";
    public static CurrentMatch currentGame;


    public static ArrayList<Champ> enemyTeam = new ArrayList<>();
    public static LeagueNotification leagueNotification;

    @Getter
    private static Main instance;

    public Main() {
        instance = this;
        leagueNotification = new LeagueNotification();
    }

    //Cosmic Insight 8347
    public static void main(String [] args) {
        Orianna.setRiotAPIKey(APIKEY);
        Orianna.setDefaultRegion(Region.EUROPE_WEST);


        Summoner summoner = Summoner.named(SUMMONER_NAME3).withRegion(Region.EUROPE_WEST).get();
        currentGame = summoner.getCurrentMatch();

        Side mySide = null;

        if (currentGame.exists()) {

            for (Player p : currentGame.getParticipants()) {
                if (p.getSummoner().getName().equalsIgnoreCase(SUMMONER_NAME3)) {
                    mySide = p.getTeam().getSide();
                    break;
                }
            }
            SumRegionHandler rh = (mySide == Side.BLUE) ? SumRegionHandler.RED : SumRegionHandler.BLUE;

            System.out.println(rh.name());


            for (Player p : currentGame.getParticipants()) {
                if (p.getTeam().getSide() != mySide) {
                    boolean insight = false;
                    if (p.getRunes().contains(ReforgedRunes.withIds(8347))) {
                        insight = true;
                    }
                    enemyTeam.add(new Champ(p.getChampion(), p.getSummonerSpellD(), p.getSummonerSpellF(), insight));

                }
            }

            System.out.println(currentGame.getCreationTime().toLocalTime());
            System.out.println(currentGame.getCreationTime().getMillis());
            System.out.println(TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis() - currentGame.getCreationTime().getMillis()));


            try {
                GlobalScreen.registerNativeHook();
                // Get the logger for "org.jnativehook" and set the level to warning.
                Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                logger.setLevel(Level.WARNING);

                // Don't forget to disable the parent handlers.
                logger.setUseParentHandlers(false);
            } catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());

                System.exit(1);
            }

            TabTracker tt = new TabTracker();
            GlobalScreen.addNativeKeyListener(tt);
            GlobalScreen.addNativeMouseListener(new MouseTracker(tt, rh));

            ViewMain.initFrame();



        }



    }


}
