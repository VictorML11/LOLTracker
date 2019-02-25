package me.torciv.loltracker.handlers;

import java.awt.*;
import java.awt.event.KeyEvent;

public class LeagueNotification {

    private Robot robot;

    public LeagueNotification() {
        try {
            this.robot = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }
    }

    public void notifySummonerUp(String champion, String summoner){
        String[] msg = new String[1];
        msg[0] = champion + " has " + summoner + " up!";

        typeWord(msg);
    }

    public void notifyTimeLeft(String champion, String summoner, int time){
        String[] msg = new String[1];
        msg[0] = champion + " : " + summoner + " up in " + time + " secs";
        typeWord(msg);
    }

    public void notifySummonerUsed(String champion, String summoner){
        String[] msg = new String[1];
        msg[0] = champion + " no " + summoner;
        typeWord(msg);
    }


    private void pressEnter(int delay){
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.delay(delay);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }

    private void startNewRow(Robot robot){
        robot.keyPress(KeyEvent.VK_SHIFT);
        robot.delay(50);
        pressEnter(50);
        robot.delay(10);
        robot.keyRelease(KeyEvent.VK_SHIFT);
    }


    private void typeWord(String[] wordChars){
        //Loop through each array
        for(int i = 0; i < wordChars.length; i++){
            //Loop through each character of the string
            for(int j = 0; j < wordChars[i].length(); j++){
                char character = wordChars[i].charAt(j);

                int key = KeyEvent.getExtendedKeyCodeForChar(character);
                if (KeyEvent.CHAR_UNDEFINED == key) {
                    throw new RuntimeException(
                            "Key code not found for character '" + character + "'");
                }

                if (j == 0){
                    startNewRow(robot);
                }

                robot.keyPress(key);
                robot.delay(1);
                robot.keyRelease(key);

                if (j == wordChars[i].length() - 1){
                    pressEnter(50);
                }
            }
        }
    }
}
