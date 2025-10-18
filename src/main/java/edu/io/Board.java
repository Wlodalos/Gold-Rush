package edu.io;

public class Board {
    public final int size = 8;
    private final Token[][] grid;

    public Board() {
        grid = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                grid[x][y] = new Token("・");
            }
        }
    }

    public boolean placeToken(int x, int y, Token token) {
        if (x < 0 || y < 0 || x >= size || y >= size) return false;
        grid[x][y] = token;
        return true;
    }

    public Token square(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) return null;
        return grid[x][y];
    }

    public void display() {
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                System.out.print(grid[x][y].label);
            }
            System.out.println();
        }
    }
}
