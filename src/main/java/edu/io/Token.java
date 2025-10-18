package edu.io;

public class Token {
    public final String label;

    public Token(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }

    public static final Token EMPTY = new Token("・");
    public static final Token PLAYER = new Token("웃");
    public static final Token GOLD = new Token("💰︎");
}
