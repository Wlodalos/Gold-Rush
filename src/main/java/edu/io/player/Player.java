package edu.io.player;

import edu.io.token.*;

import java.util.Objects;

public class Player {
    private PlayerToken token;
    public final Gold gold = new Gold();
    private final Shed shed = new Shed();
    public final Vitals vitals = new Vitals();

    public void assignToken(PlayerToken token) {
        this.token = Objects.requireNonNull(token);
    }

    public PlayerToken token() {
        return token;
    }

    public void interactWithToken(Token token) {
        Objects.requireNonNull(token);

        if (!vitals.isAlive()) {
            throw new IllegalStateException("Player is dead");
        }

        switch (token) {
            case GoldToken goldToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_GOLD);

                Tool tool = shed.getTool();
                double baseAmount = goldToken.amount();

                tool.useWith(goldToken)
                        .ifWorking(() -> {
                            if (tool instanceof PickaxeToken pickaxe) {
                                gold.gain(baseAmount * pickaxe.gainFactor());
                            }
                        })
                        .ifBroken(() -> {
                            gold.gain(baseAmount);
                            shed.dropTool();
                        })
                        .ifIdle(() -> {
                            gold.gain(baseAmount);
                        });
            }
            case PickaxeToken pickaxeToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
                shed.add(pickaxeToken);
            }
            case AnvilToken anvilToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_ANVIL);

                Tool tool = shed.getTool();
                if (tool instanceof Repairable repairableTool) {
                    repairableTool.repair();
                }
            }
            case WaterToken waterToken -> {
                vitals.hydrate(waterToken.amount());
            }
            case EmptyToken emptyToken -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
            }
            default -> {
                vitals.dehydrate(VitalsValues.DEHYDRATION_MOVE);
            }
        }
    }
}