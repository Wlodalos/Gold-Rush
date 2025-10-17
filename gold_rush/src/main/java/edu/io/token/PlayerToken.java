package edu.io.token;

import edu.io.Board;

public class PlayerToken extends Token {
    private final Board board;
    private int col;
    private int row;

    public enum Move {
        NONE, UP, DOWN, LEFT, RIGHT
    }

    public PlayerToken(Board board) {
        this(board, board.size() / 2, board.size() / 2);
    }

    public PlayerToken(Board board, int col, int row) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.board = board;
        this.col = col;
        this.row = row;
        board.placeToken(this.col, this.row, this);
    }

    public void move(Move dir) {
        if (dir == Move.NONE) {
            return;
        }

        int newCol = col;
        int newRow = row;

        switch (dir) {
            case UP:    newRow--; break;
            case DOWN:  newRow++; break;
            case LEFT:  newCol--; break;
            case RIGHT: newCol++; break;
        }

        if (!board.isValidPosition(newCol, newRow)) {
            throw new IllegalArgumentException("Cannot move outside the board");
        }

        board.placeToken(col, row, new EmptyToken());
        this.col = newCol;
        this.row = newRow;
        board.placeToken(this.col, this.row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }
}