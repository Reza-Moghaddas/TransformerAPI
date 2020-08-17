package com.equilibrium.transformerapi.model;

import java.util.List;

public class TransformerBattleResult {

    Integer battleCount;
    List<Transformer> WinnerTeam;
    List<Transformer> LoserSurvival;

    public  Integer getBattleCount() {
        return battleCount;
    }

    public  void setBattleCount(Integer battleCount) {
        this.battleCount = battleCount;
    }

    public  List<Transformer> getWinnerTeam() {
        return WinnerTeam;
    }

    public  void setWinnerTeam(List<Transformer> winnerTeam) {
        WinnerTeam = winnerTeam;
    }

    public  List<Transformer> getLoserSurvival() {
        return LoserSurvival;
    }

    public  void setLoserSurvival(List<Transformer> loserSurvival) {
        LoserSurvival = loserSurvival;
    }
}
