package edu.io.player;

public class Gold {
    private double amount;

    public Gold() {
        this(0.0);
    }

    public Gold(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot create gold with negative amount");
        }
        this.amount = amount;
    }

    public double amount() {
        return amount;
    }

    public void gain(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot gain negative amount");
        }
        this.amount += amount;
    }

    public void lose(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Cannot lose negative amount");
        }
        if (this.amount - amount < 0) {
            throw new IllegalArgumentException("Cannot lose more gold than you have");
        }
        this.amount -= amount;
    }
}