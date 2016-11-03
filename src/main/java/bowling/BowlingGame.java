package bowling;

import java.util.List;

public class BowlingGame {

    private List<Roll> rolls;

    public BowlingGame rolls(List<Roll> rolls) {
        this.rolls = rolls;
        return this;
    }

    public int score() {
        int score = 0;
        int frameIndex = 0;
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfBallsInFrame(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private int sumOfBallsInFrame(int frameIndex) {
        return rolls.get(frameIndex).pins + rolls.get(frameIndex + 1).pins;
    }

    private int spareBonus(int frameIndex) {
        return rolls.get(frameIndex + 2).pins;
    }

    private boolean isSpare(int frameIndex) {
        return rolls.get(frameIndex + 1).isSpare();
    }

    private int strikeBonus(int frameIndex) {
        return sumOfBallsInFrame(frameIndex + 1);
    }

    private boolean isStrike(int frameIndex) {
        return rolls.get(frameIndex).isStrike();
    }

}
