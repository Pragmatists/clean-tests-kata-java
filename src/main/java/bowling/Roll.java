package bowling;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Roll {
    public static final Roll ZERO = new Roll(0);
    public static final Roll SPARE = new SpareRoll();
    public static final Roll STRIKE = new StrikeRoll();
    public final int pins;

    public Roll(int pins) {
        this.pins = pins;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Roll roll = (Roll) o;

        return new EqualsBuilder()
                .append(pins, roll.pins)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(pins)
                .toHashCode();
    }

    public boolean isStrike() {
        return false;
    }

    public boolean isSpare() {
        return false;
    }

    private static class SpareRoll extends Roll {
        public SpareRoll() {
            super(0);
        }

        @Override
        public boolean isSpare() {
            return true;
        }
    }

    private static class StrikeRoll extends Roll {
        public StrikeRoll() {
            super(10);
        }

        @Override
        public boolean isStrike() {
            return true;
        }
    }
}
