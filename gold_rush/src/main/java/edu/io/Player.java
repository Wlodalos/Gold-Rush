package edu.io;

public class Player {
    private final String name;
    private int x;
    private int y;
    private int gold;

    public Player(String name, int startX, int startY) {
        this.name = name;
        this.x = startX;
        this.y = startY;
        this.gold = 0;
    }

    public void placeOnBoard(Board board) {
        board.placeToken(x, y, Token.PLAYER);
    }

    public void move(int dx, int dy, Board board) {
        int newX = x + dx;
        int newY = y + dy;

        if (newX < 0 || newY < 0 || newX >= board.getSize() || newY >= board.getSize()) {
            System.out.println("Nie mozna wyjsc poza plansze!");
            return;
        }

        Token target = board.square(newX, newY);
        if (target == Token.GOLD) {
            gold++;
            System.out.println(name + " znalazl zloto! 💰 (" + gold + ")");
        }

        board.placeToken(x, y, Token.EMPTY);
        x = newX;
        y = newY;
        board.placeToken(x, y, Token.PLAYER);
    }

    public int getGold() {
        return gold;
    }

    public String getName() {
        return name;
    }
}
