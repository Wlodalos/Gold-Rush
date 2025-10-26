package edu.io;

import edu.io.token.GoldToken;
import edu.io.token.PlayerToken;
import edu.io.token.Token;

public class Player {
    private PlayerToken token;
    private double gold;

    public Player() {
        this.gold = 0.0;
    }

    public void assignToken(PlayerToken token) {
        this.token = token;
    }

    public PlayerToken token() {
        return token;
    }

    public double gold() {
        return gold;
    }

    public void gainGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot gain negative amount of gold");
        }
        this.gold += amount;
    }

    public void loseGold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot lose negative amount of gold");
        }
        if (this.gold - amount < 0) {
            throw new IllegalArgumentException("Cannot lose more gold than you have");
        }
        this.gold -= amount;
    }

    public void interactWithToken(Token token) {
        if (token instanceof GoldToken goldToken) {
            System.out.println("GOLD!");
            gainGold(goldToken.amount());
        }
    }
}