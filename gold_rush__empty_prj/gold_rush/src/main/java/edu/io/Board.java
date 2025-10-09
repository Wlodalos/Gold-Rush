package edu.io;

public class Board {
    private final int size;
    private final Token[][] grid;

    public Board(int size) {
        this.size = size;
        this.grid = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = new Token(".");
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
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%-2s", grid[i][j].label);
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }
}
