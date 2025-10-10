package edu.io;

public class Board {
    private final int size;
    private final Token[][] board;

    public Board(int size) {
        this.size = size;
        this.board = new Token[size][size];
        clean();
    }

    public void clean() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = Token.EMPTY;
            }
        }
    }

    public boolean placeToken(int x, int y, Token token) {
        if (x < 0 || y < 0 || x >= size || y >= size) return false;
        board[x][y] = token;
        return true;
    }

    public Token square(int x, int y) {
        if (x < 0 || y < 0 || x >= size || y >= size) return null;
        return board[x][y];
    }

    public void display() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%-3s", board[i][j].toString());
            }
            System.out.println();
        }
        System.out.println();
    }

    public int getSize() {
        return size;
    }
}
