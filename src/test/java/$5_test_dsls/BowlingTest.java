package $5_test_dsls;

import bowling.BowlingGame;
import bowling.Roll;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

public class BowlingTest {

    @Test
    public void perfect_game() {
        assertEquals(300, rolls("XXXXXXXXXXXX"));
    }

    @Test
    public void gutter_game() {
        assertEquals(0, rolls("--------------------"));
    }

    @Test
    public void bonus_for_spare_is_next_ball_score() {
        assertEquals(18, rolls("5/4-----------------"));
    }

    @Test
    public void bonus_for_strike_is_next_frame_score() {
        assertEquals(10 + 9 + 9, rolls("X36-----------------"));
    }

    private int rolls(String rollsAsString) {
        return new BowlingGame().rolls(parseRolls(rollsAsString)).score();
    }

    private List<Roll> parseRolls(String framesAsString) {
        return framesAsString.chars().mapToObj(c -> roll((char) c)).collect(Collectors.toList());
    }

    private Roll roll(char c) {
        String sign = String.valueOf(c);
        if (sign.equals("/")) {
            return Roll.SPARE;
        }
        if (sign.equals("X")) {
            return Roll.STRIKE;
        }
        if (sign.equals("-")) {
            return Roll.ZERO;
        }
        return new Roll(Integer.valueOf(sign));
    }

    @Test
    public void parse_gutter() {
        assertThat(parseRolls("-")).containsExactly(Roll.ZERO);
    }

    @Test
    public void parse_pins() {
        assertThat(parseRolls("2")).containsExactly(new Roll(2));
    }

}
