package me.torciv.loltracker;

import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.tuple.MutablePair;

import java.util.ArrayList;
import java.util.Arrays;

public class Champ {

    @Getter @Setter
    private Champion champion;
    @Getter @Setter
    private ArrayList<MutablePair<SummonerSpell,Float>> summoners;
    @Getter @Setter
    private boolean insight;


    public Champ(Champion champion, ArrayList<MutablePair<SummonerSpell, Float>> summoners, boolean insight) {
        this.champion = champion;
        this.summoners = summoners;
        this.insight = insight;
    }

    public Champ(Champion champion, SummonerSpell s1, SummonerSpell s2, boolean insight) {
        this.champion = champion;
        this.insight = insight;
        this.summoners = new ArrayList<>(Arrays.asList(new MutablePair<>(s1, -1f),new MutablePair<>(s2,-1f)));
    }
}
