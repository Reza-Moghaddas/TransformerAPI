package com.equilibrium.transformerapi.handler;

import com.equilibrium.transformerapi.model.Transformer;

public class BattleHandler {

    // This method returns the one on one battle result based on the rules defined
    public static String oneOnOneBattle(Transformer autobot, Transformer decepticon){

        if (autobot.getName().equals("Optimus Prime") && decepticon.getName().equals("Predaking"))
            return "End Game";
        if (autobot.getName().equals("Optimus Prime"))
            return "Autobot Wins";
        if (autobot.getName().equals("Predaking"))
            return "Decepticon Wins";
        if ((autobot.getCourage() >= decepticon.getCourage() + 4 && autobot.getStrength() >= decepticon.getStrength() + 3) |
                (autobot.getSkill() >= decepticon.getSkill()))
            return "Autobot Wins";
        if ((decepticon.getCourage() >= autobot.getCourage() + 4 && decepticon.getStrength() >= autobot.getStrength() + 3) |
                (decepticon.getSkill() >= autobot.getSkill()))
            return "Decepticon Wins";
        if (autobot.getSkill() - decepticon.getSkill() >= 3)
            return "Autobot Wins";

        if (decepticon.getSkill() - autobot.getSkill() >= 3)
            return "Decepticon Wins";

        if (autobot.getOverallRate() == decepticon.getOverallRate())
            return "Tie";
        if(autobot.getOverallRate() > decepticon.getOverallRate())
            return "Autobot Wins";
        else
            return "Decepticon Wins";
    }
}
