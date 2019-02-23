package me.torciv.loltracker;

import com.merakianalytics.orianna.types.core.staticdata.Champion;
import com.merakianalytics.orianna.types.core.staticdata.SummonerSpell;
import javafx.util.Pair;
import lombok.Getter;
import lombok.Setter;

public class Champ {

    @Getter @Setter
    private Champion champion;
    @Getter @Setter
    private Pair<SummonerSpell,SummonerSpell> summoners;
    @Getter @Setter
    private boolean insight;

    @Getter @Setter
    private boolean s1t = false;
    @Getter @Setter
    private boolean s2t = false;

    public Champ(Champion champion, Pair<SummonerSpell, SummonerSpell> summoners, boolean insight) {
        this.champion = champion;
        this.summoners = summoners;
        this.insight = insight;
        this.s1t = false;
        this.s2t = false;
    }

    public Champ(Champion champion, SummonerSpell summonerSpell1, SummonerSpell summonerSpell2, boolean insight) {
        this.champion = champion;
        this.summoners = new Pair<>(summonerSpell1,summonerSpell2);
        this.insight = insight;
        this.s1t = false;
        this.s2t = false;

    }


}
