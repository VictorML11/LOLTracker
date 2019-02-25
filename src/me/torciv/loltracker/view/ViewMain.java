package me.torciv.loltracker.view;

import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import me.torciv.loltracker.Champ;
import me.torciv.loltracker.Main;
import org.apache.commons.lang3.tuple.MutablePair;
import org.msgpack.jackson.dataformat.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

public class ViewMain extends JFrame implements ActionListener{


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

    private Timer timer;


    private ArrayList<JLabel> champs = new ArrayList<>(Arrays.asList(topChamp, jungleChamp, midChamp, adcChamp, suppChamp));
    private ArrayList<ArrayList<JLabel>> summChamps = new ArrayList<>(Arrays.asList(
            new ArrayList<>(Arrays.asList(topS1, topS2)),
            new ArrayList<>(Arrays.asList(jungleS1, jungleS2)),
            new ArrayList<>(Arrays.asList(midS1, midS2)),
            new ArrayList<>(Arrays.asList(adcS1, adcS2)),
            new ArrayList<>(Arrays.asList(suppS1, suppS2))));

    public ViewMain() {
        for(int i = 0; i <  Main.enemyTeam.size(); i++){
            ImageIcon icon = new ImageIcon( Main.enemyTeam.get(i).getChampion().getImage().get());
            String name =  Main.enemyTeam.get(i).getChampion().getName();
            champs.get(i).setIcon(icon);
            champs.get(i).setText(name);
        }

        for(int i = 0; i < summChamps.size(); i++){
            for(int j = 0; j <  Main.enemyTeam.get(i).getSummoners().size(); j++){
                ImageIcon sicon = new ImageIcon( Main.enemyTeam.get(i).getSummoners().get(j).getKey().getImage().get());
                summChamps.get(i).get(j).setIcon(sicon);
                summChamps.get(i).get(j).setText("UP!");
            }

        }

        timer = new Timer(1000, this);
        timer.setRepeats(true);
        timer.start();


    }

    public static void initFrame(){
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("LOL-Tracker");
                frame.setContentPane(new ViewMain().mainPanel); // Notice we do a NEW here!
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);

            }
        });
    }


    @Override
    public void actionPerformed(ActionEvent e) {

        for(int i = 0; i < Main.enemyTeam.size(); i++){
            Champ c =  Main.enemyTeam.get(i);
            for(int j = 0; j < c.getSummoners().size(); j++){

                MutablePair<SummonerSpell, Float> champPair = c.getSummoners().get(j);
                float currentTime = champPair.getValue();
                float nextTime = currentTime - 1F;
                if(nextTime > 0.0F){
                    System.out.println("[" + c.getChampion().getName() + "] "
                            + champPair.getKey().getName() + " has " + nextTime +" sec");
                    champPair.setValue(nextTime);
                    summChamps.get(i).get(j).setText(nextTime + " Seconds");

                }else if (champPair.getValue() != -1){
                    champPair.setValue(-1F);
                    System.out.println("[" + c.getChampion().getName() + "] "
                            + champPair.getKey().getName() + " : is now UP!");
                    summChamps.get(i).get(j).setText("UP!");

                }
            }
        }
    }

}
