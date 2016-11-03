package $3_test_structure;

import hero.Hero;
import org.junit.Test;

import static com.google.common.collect.Lists.newArrayList;
import static org.assertj.core.api.Assertions.assertThat;

public class HeroesFightTest {

    @Test
    public void testFight1() throws Exception {
        //given
        Hero superman = new Hero().alias("Superman").power(5).symbols(newArrayList("blue suit", "red cape"));
        Hero lexLuthor = new Hero().realFirstName("Lex").realLastName("Luthor").power(4)
                .symbols(newArrayList("bald head", "cigar")).health(10);
        assertThat(superman.numberOfFightsWon).isEqualTo(0);
        assertThat(lexLuthor.numberOfFightsWon).isEqualTo(0);

        //when
        boolean supermanWon = superman.fight(lexLuthor);

        //then
        assertThat(supermanWon).isTrue();
        assertThat(superman.numberOfFightsWon).isEqualTo(1);
        assertThat(lexLuthor.numberOfFightsWon).isEqualTo(0);

        lexLuthor.inflictDamage(500);

        assertThat(lexLuthor.getHealth()).isEqualTo(9);
    }

}
