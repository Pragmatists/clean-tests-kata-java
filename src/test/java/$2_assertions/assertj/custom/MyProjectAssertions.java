package $2_assertions.assertj.custom;

import fellowshipofthering.TolkienCharacter;
import org.assertj.core.api.Assertions;

public class MyProjectAssertions extends Assertions {

    public static TolkienCharacterAssert assertThat(TolkienCharacter actual) {
        return new TolkienCharacterAssert(actual);
    }
}
