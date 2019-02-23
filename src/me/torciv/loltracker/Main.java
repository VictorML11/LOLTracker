package me.torciv.loltracker;


import com.merakianalytics.orianna.Orianna;
import com.merakianalytics.orianna.types.common.Region;
import com.merakianalytics.orianna.types.core.spectator.CurrentMatch;
import com.merakianalytics.orianna.types.core.spectator.Player;
import com.merakianalytics.orianna.types.core.summoner.Summoner;
import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main  {

    public static final String APIKEY = "RGAPI-6e6d5a4b-d015-40dd-894d-77aeb5b8ea84";
    public static final String SUMMONER_NAME = "sergislabgg";

    public static void main(String[] args) {
            Side side = null;

            while(side == null){
                System.out.println("Selected a side: ");
                System.out.println("1. BLUE");
                System.out.println("2. RED");
                Scanner scanner = new Scanner(System.in);
                int s = scanner.nextInt();
                switch (s){
                    case 1:
                        side = Side.BLUE;
                        break;

                    case 2:
                        side = Side.RED;
                        break;

                    default:
                        System.out.println("ERROR! Selecting a team use 1 or 2");
                }

            }

            System.out.println(side.name().toLowerCase() + " team selected!");


            try {
                GlobalScreen.registerNativeHook();
                // Get the logger for "org.jnativehook" and set the level to warning.
                Logger logger = Logger.getLogger(GlobalScreen.class.getPackage().getName());
                logger.setLevel(Level.WARNING);

                // Don't forget to disable the parent handlers.
                logger.setUseParentHandlers (false);
            }
            catch (NativeHookException ex) {
                System.err.println("There was a problem registering the native hook.");
                System.err.println(ex.getMessage());

                System.exit(1);
            }

            TabTracker tt = new TabTracker();
            GlobalScreen.addNativeKeyListener(tt);
            GlobalScreen.addNativeMouseListener(new MouseTracker(tt, side));


            Orianna.setRiotAPIKey(APIKEY);
            Orianna.setDefaultRegion(Region.EUROPE_WEST);

            Summoner summoner = Summoner.named(SUMMONER_NAME).withRegion(Region.EUROPE_WEST).get();
            CurrentMatch currentGame = summoner.getCurrentMatch();



            if(currentGame != null){
                System.out.println(currentGame.getMap());
                for(Player player : currentGame.getParticipants()) {
                    System.out.println(player.getSummoner().getName());
                }
            }



            /*Orianna.Configuration config = new Orianna.Configuration();
            config.setDefaultPlatform(Platform.EUROPE_WEST);
            Orianna.setDefaultLocale("es_ES");
            config.setCurrentVersionExpiration(ExpirationPeriod.create(6, TimeUnit.HOURS));*/

    }


}
