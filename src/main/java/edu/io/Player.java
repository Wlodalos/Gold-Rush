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
        board.placeToken(x, y, new Token("웃"));
    }

    public void move(int dx, int dy, Board board) {
        int newX = x + dx;
        int newY = y + dy;

        if (newX < 0 || newY < 0 || newX >= board.size || newY >= board.size) {
            System.out.println("Nie mozna wyjsc poza plansze!");
            return;
        }

        Token target = board.square(newX, newY);
        if (target != null && "💰︎".equals(target.label)) {
            gold++;
            System.out.println(name + " znalazl zloto! 💰 (" + gold + ")");
        }

        board.placeToken(x, y, new Token("・"));
        x = newX;
        y = newY;
        board.placeToken(x, y, new Token("웃"));
    }

    public int getGold() {
        return gold;
    }

    public String getName() {
        return name;
    }
}
