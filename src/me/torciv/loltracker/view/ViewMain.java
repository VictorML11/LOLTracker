package me.torciv.loltracker.view;

import me.torciv.loltracker.Main;

import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;

public class ViewMain {


    private JPanel mainPanel;

    private JPanel topPanel;
    private JPanel junglePanel;
    private JPanel midPanel;
    private JPanel adcPanel;
    private JPanel suppPanel;

    private JLabel topChamp;
    private JLabel jungleChamp;
    private JLabel midChamp;
    private JLabel adcChamp;
    private JLabel suppChamp;

    private JLabel topS1;
    private JLabel topS2;
    private JLabel jungleS1;
    private JLabel jungleS2;
    private JLabel midS1;
    private JLabel midS2;
    private JLabel adcS1;
    private JLabel adcS2;
    private JLabel suppS1;
    private JLabel suppS2;


    private ArrayList<JLabel> champs = new ArrayList<>(Arrays.asList(topChamp, jungleChamp, midChamp, adcChamp, suppChamp));
    private ArrayList<JLabel> summChamps = new ArrayList<>(Arrays.asList(topS1,topS2,jungleS1,jungleS2,midS1,midS2,adcS1,adcS2,suppS1,suppS2));

    public ViewMain() {
        setup();
    }

    public void initFrame(){
        JFrame frame = new JFrame("LOL-Tracker");
        frame.setContentPane(new ViewMain().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    private void setup(){

        for(int i = 0; i < Main.enemyTeam.size(); i++){
            ImageIcon icon = new ImageIcon(Main.enemyTeam.get(i).getChampion().getImage().get());
            String name = Main.enemyTeam.get(i).getChampion().getName();
            champs.get(i).setIcon(icon);
            champs.get(i).setText(name);
        }

        int j = 0;
        for(int i = 0; i < summChamps.size(); i++){
            ImageIcon sicon;
            if((i+1) % 2 != 0){
                sicon = new ImageIcon(Main.enemyTeam.get(j).getSummoners().getKey().getImage().get());
            }else{
                sicon = new ImageIcon(Main.enemyTeam.get(j).getSummoners().getValue().getImage().get());
                j++;
            }

            summChamps.get(i).setIcon(sicon);
        }



    }


}
