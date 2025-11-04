package edu.io.token;

import edu.io.Board;
import edu.io.player.Player;

public class PlayerToken extends Token {
    private final Player player;
    private final Board board;
    private int col;
    private int row;

    public enum Move {
        NONE, UP, DOWN, LEFT, RIGHT
    }

    public PlayerToken(Player player, Board board) {
        super(Label.PLAYER_TOKEN_LABEL);
        this.player = player;
        this.board = board;

        Board.Coords startPos = board.getAvailableSquare();
        this.col = startPos.col();
        this.row = startPos.row();

        board.placeToken(this.col, this.row, this);
    }

    public void move(Move dir) {
        if (dir == Move.NONE) {
            return;
        }

        int pCol = col;
        int pRow = row;

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

        Token targetToken = board.peekToken(newCol, newRow);
        player.interactWithToken(targetToken);

        board.placeToken(pCol, pRow, new EmptyToken());
        this.col = newCol;
        this.row = newRow;
        board.placeToken(this.col, this.row, this);
    }

    public Board.Coords pos() {
        return new Board.Coords(col, row);
    }
}