package edu.io;

import edu.io.token.EmptyToken;
import edu.io.token.Token;

public class Board {
    private final int size;
    private final Token[][] grid;
    private static final int DEFAULT_SIZE = 8;

    public record Coords(int col, int row) {}

    public Board() {
        this(DEFAULT_SIZE);
    }

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    public int size() {
        return size;
    }

    public final void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new EmptyToken();
            }
        }
    }

    public void placeToken(int col, int row, Token token) {
        if (isValidPosition(col, row)) {
            grid[row][col] = token;
        }
    }

    public Token peekToken(int col, int row) {
        if (isValidPosition(col, row)) {
            return grid[row][col];
        }
        return null;
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].label() + "  ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public boolean isValidPosition(int col, int row) {
        return col >= 0 && col < size && row >= 0 && row < size;
    }
}