package me.torciv.loltracker;


import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.common.Side;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import me.torciv.loltracker.handlers.SumRegionHandler;
import me.torciv.loltracker.trackers.MouseTracker;
import me.torciv.loltracker.trackers.TabTracker;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {

    public static final String APIKEY = "RGAPI-6e6d5a4b-d015-40dd-894d-77aeb5b8ea84";
    public static final String SUMMONER_NAME = "sergislabgg";
    public static final String SUMMONER_NAME2 = "Best OTP Syndra";

    public static void main(String[] args) {

        Orianna.setRiotAPIKey(APIKEY);
        Orianna.setDefaultRegion(Region.EUROPE_WEST);


        Summoner summoner = Summoner.named(SUMMONER_NAME).withRegion(Region.EUROPE_WEST).get();
        CurrentMatch currentGame = summoner.getCurrentMatch();

        Side mySide = null;

        if (currentGame.exists()) {

            for (Player p : currentGame.getParticipants()) {
                if (p.getSummoner().getName().equalsIgnoreCase(SUMMONER_NAME)) {
                    mySide = p.getTeam().getSide();
                    break;
                }
            }
            SumRegionHandler rh = (mySide == Side.BLUE) ? SumRegionHandler.RED : SumRegionHandler.BLUE;


            for (Player p : currentGame.getParticipants()) {
                if (p.getTeam().getSide() != mySide) {
                    System.out.println("-----------------------------");

                    System.out.println(p.getChampion().getName());

                    System.out.println(p.getSummonerSpellD().getName());
                    System.out.println(p.getSummonerSpellD().getCooldowns());
                    System.out.println(p.getSummonerSpellF().getName());
                    System.out.println(p.getSummonerSpellF().getCooldowns());

                }
            }


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

        }


    }


}
