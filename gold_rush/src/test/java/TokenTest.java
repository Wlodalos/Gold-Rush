import edu.io.Token;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

    public class TokenTest {
        @Test
        public void token_has_label() {
            Assertions.assertEquals(
                    "*",
                    new Token("*").label
            );
        }
    }


