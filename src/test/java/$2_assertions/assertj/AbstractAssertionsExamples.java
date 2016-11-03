
package $2_assertions.assertj;


import $2_assertions.assertj.comparator.AgeComparator;
import fellowshipofthering.Movie;
import fellowshipofthering.Ring;
import fellowshipofthering.TolkienCharacter;
import org.junit.Before;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;

import static com.google.common.collect.Maps.newHashMap;
import static fellowshipofthering.Race.*;
import static org.assertj.core.util.Lists.newArrayList;

/**
 * Init data for assertions assertj.
 */
public abstract class AbstractAssertionsExamples {

    static final String ERROR_MESSAGE_EXAMPLE_FOR_ASSERTION = "{} assertion : {}\n";

    // Some of the Lord of the Rings characters :
    protected final TolkienCharacter frodo = new TolkienCharacter("Frodo", 33, HOBBIT);

    protected final TolkienCharacter sam = new TolkienCharacter("Sam", 38, HOBBIT);

    protected final TolkienCharacter merry = new TolkienCharacter("Merry", 36, HOBBIT);

    protected final TolkienCharacter pippin = new TolkienCharacter("Pippin", 28, HOBBIT);

    protected final TolkienCharacter gandalf = new TolkienCharacter("Gandalf", 2020, MAIA);

    protected final TolkienCharacter gimli = new TolkienCharacter("Gimli", 139, DWARF);

    protected final TolkienCharacter legolas = new TolkienCharacter("Legolas", 1000, ELF);

    protected final TolkienCharacter aragorn = new TolkienCharacter("Aragorn", 87, MAN);

    protected final TolkienCharacter boromir = new TolkienCharacter("Boromir", 37, MAN);

    protected final TolkienCharacter sauron = new TolkienCharacter("Sauron", 50000, MAIA);

    protected final TolkienCharacter galadriel = new TolkienCharacter("Galadriel", 3000, ELF);

    protected final TolkienCharacter elrond = new TolkienCharacter("Elrond", 3000, ELF);

    protected final TolkienCharacter guruk = new TolkienCharacter("Guruk", 20, ORC);

    protected final List<TolkienCharacter> fellowshipOfTheRing = newArrayList();

    protected final List<TolkienCharacter> orcsWithHobbitPrisoners = new ArrayList<TolkienCharacter>();

    protected final Map<Ring, TolkienCharacter> ringBearers = newHashMap();

    protected final Movie theFellowshipOfTheRing = new Movie("the fellowship of the Ring", parse("2001-12-19"), "178 min");

    protected final Movie theTwoTowers = new Movie("the two Towers", parse("2002-12-18"), "179 min");

    protected final Movie theReturnOfTheKing = new Movie("the Return of the King", parse("2003-12-17"), "201 min");

    protected Comparator<TolkienCharacter> ageComparator = new AgeComparator();

    @Before
    public void setup() {
        // let's do some team building :)
        fellowshipOfTheRing.add(frodo);
        fellowshipOfTheRing.add(sam);
        fellowshipOfTheRing.add(merry);
        fellowshipOfTheRing.add(pippin);
        fellowshipOfTheRing.add(gandalf);
        fellowshipOfTheRing.add(legolas);
        fellowshipOfTheRing.add(gimli);
        fellowshipOfTheRing.add(aragorn);
        fellowshipOfTheRing.add(boromir);
        // ring bearers
        ringBearers.put(Ring.nenya, galadriel);
        ringBearers.put(Ring.narya, gandalf);
        ringBearers.put(Ring.vilya, elrond);
        ringBearers.put(Ring.oneRing, frodo);
        orcsWithHobbitPrisoners.add(guruk);
        orcsWithHobbitPrisoners.add(merry);
        orcsWithHobbitPrisoners.add(pippin);
    }

    protected Date parse(String stringDate) {
        LocalDateTime localDateTime = LocalDate.parse(stringDate).atStartOfDay();
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

}
