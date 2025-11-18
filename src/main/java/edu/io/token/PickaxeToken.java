package edu.io.token;

import edu.io.player.Repairable;
import edu.io.player.Tool;

public class PickaxeToken extends Token implements Tool, Repairable {
    private final double gainFactor;
    private int durability;
    private final int initialDurability;

    private Token withToken;

    private static final double DEFAULT_GAIN = 1.5;
    private static final int DEFAULT_DURABILITY = 3;

    public PickaxeToken() {
        this(DEFAULT_GAIN, DEFAULT_DURABILITY);
    }

    public PickaxeToken(double gainFactor) {
        this(gainFactor, DEFAULT_DURABILITY);
    }

    public PickaxeToken(double gainFactor, int durability) {
        super(Label.PICKAXE_TOKEN_LABEL);
        if (gainFactor <= 0) {
            throw new IllegalArgumentException("Gain factor must be positive");
        }
        if (durability <= 0) {
            throw new IllegalArgumentException("Durability must be positive");
        }
        this.gainFactor = gainFactor;
        this.durability = durability;
        this.initialDurability = durability;
    }

    public double gainFactor() {
        return gainFactor;
    }

    public int durability() {
        return durability;
    }

    public void use() {
        if (durability > 0) {
            durability--;
        }
    }

    @Override
    public boolean isBroken() {
        return durability <= 0;
    }

    @Override
    public void repair() {
        this.durability = initialDurability;
    }

    @Override
    public Tool useWith(Token token) {
        this.withToken = token;
        if (!isBroken() && token instanceof GoldToken) {
            use();
        }
        return this;
    }

    @Override
    public Tool ifWorking(Runnable action) {
        if (!isBroken() && withToken instanceof GoldToken) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifBroken(Runnable action) {
        if (isBroken() && withToken instanceof GoldToken) {
            action.run();
        }
        return this;
    }

    @Override
    public Tool ifIdle(Runnable action) {
        if (!isBroken() && !(withToken instanceof GoldToken)) {
            action.run();
        }
        return this;
    }
}