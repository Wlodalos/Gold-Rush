package edu.io;

public class Token {
    public final String label;

    public static final Token EMPTY = new Token("\u30FB");
    public static final Token GOLD = new Token("\uD83D\uDCB0");
    public static final Token PLAYER = new Token("\uC6C3");

    public Token(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
