import edu.io.Board;
import edu.io.token.EmptyToken;
import edu.io.token.PlayerToken;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PlayerTest {
    Board board;
    PlayerToken token;

    @BeforeEach
    void setUp() {
        board = new Board();
        token = new PlayerToken(board);
    }

    @Test
    void new_PlayerToken_is_placed_on_the_board() {
        PlayerToken.Position pos = token.pos();
        Assertions.assertEquals(token, board.peekToken(pos.col(), pos.row()));
    }

    @Test
    void stay_inside_and_throws_when_go_too_far_left() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            while (true) token.move(PlayerToken.Move.LEFT);
        });
        PlayerToken.Position pos = token.pos();
        Assertions.assertEquals(0, pos.col());
    }
    @Test
    void stay_inside_and_throws_when_go_too_far_right() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            while (true) token.move(PlayerToken.Move.RIGHT);
        });
        PlayerToken.Position pos = token.pos();
        Assertions.assertEquals(board.size()-1, pos.col());
    }
    @Test
    void stay_inside_throws_when_go_too_far_up() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            while (true) token.move(PlayerToken.Move.UP);
        });
        PlayerToken.Position pos = token.pos();
        Assertions.assertEquals(0, pos.row());
    }
    @Test
    void stay_inside_throws_when_go_too_far_down() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            while (true) token.move(PlayerToken.Move.DOWN);
        });
        PlayerToken.Position pos = token.pos();
        Assertions.assertEquals(board.size()-1, pos.row());
    }

    @Test
    void move_moves_token() {
        PlayerToken.Position pos = token.pos();
        token.move(PlayerToken.Move.DOWN);
        Assertions.assertEquals(
                token,
                board.peekToken(pos.col(), pos.row()+1));
    }

    @Test
    void after_move_prev_square_is_empty() {
        PlayerToken.Position pos = token.pos();
        token.move(PlayerToken.Move.RIGHT);
        Assertions.assertInstanceOf(
                EmptyToken.class,
                board.peekToken(pos.col(), pos.row()));
    }

    @Test
    void Move_NONE_doesnt_move_token() {
        PlayerToken.Position pos = token.pos();
        token.move(PlayerToken.Move.NONE);
        Assertions.assertEquals(pos, token.pos());
    }
}
