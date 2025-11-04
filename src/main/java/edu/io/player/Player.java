package edu.io.player;

import edu.io.token.*;

public class Player {
    private PlayerToken token;
    public final Gold gold = new Gold();
    private final Shed shed = new Shed();

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
        switch (token) {
            case GoldToken goldToken -> {
                Tool tool = shed.getTool();
                double baseAmount = goldToken.amount();

                if (tool instanceof PickaxeToken pickaxe) {
                    if (pickaxe.isBroken()) {
                        gold.gain(baseAmount);
                        shed.dropTool();
                    } else {
                        gold.gain(baseAmount * pickaxe.gainFactor());
                        pickaxe.use();
                    }
                } else {
                    gold.gain(baseAmount);
                }
            }
            case PickaxeToken pickaxeToken -> {
                shed.add(pickaxeToken);
            }
            case AnvilToken anvilToken -> {
                Tool tool = shed.getTool();
                if (tool instanceof Repairable repairableTool) {
                    repairableTool.repair();
                }
            }
            case null, default -> {
            }
        }
    }
}