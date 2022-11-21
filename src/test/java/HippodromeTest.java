import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {

    public static final String HORSES_CANNOT_BE_NULL = "Horses cannot be null.";
    public static final String HORSE_CANNOT_BE_EMPTY = "Horses cannot be empty.";

    private final String TEST_NAME = "HorseName";

    private final double TEST_SPEED = 1.0;

    private final double TEST_DISTANCE = 2.5;

    private final int STARTING_POINT = 1;

    /**
     * Exercise 1: Check that when passing null to the constructor, an IllegalArgumentException will be thrown.
     * Exercise 2: Check that when passing to the constructor is null,
     * the exception thrown will contain the message "Horses cannot be null.".
     */
    @Test
    void testHorseExceptionDueToInvalidNameNullArgument(){

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));

        assertEquals(HORSES_CANNOT_BE_NULL, thrown.getMessage());
    }

    /**
     * Exercise 3: Check that when passing null to the constructor, an IllegalArgumentException will be thrown.
     * Exercise 4: Check that when passing an empty list to the constructor,
     * the exception thrown will contain the message "Horses cannot be empty.".
     */
    @Test
    public void testHippodromeThrowsIllegalArgumentExceptionWhenGetEmptyList() {

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
                new Hippodrome(new ArrayList<>()));

        assertEquals(HORSE_CANNOT_BE_EMPTY, thrown.getMessage());
    }

    /**
     * Exercise 5: Check that the getHorses method returns a list,
     * which contains the same objects and in the same sequence,
     * which is the list that was passed to the constructor.
     * When creating a Hippodrome object, pass a list of 30 different horses to the constructor
     */
    @Test
    public void testGetHorses() {

        int numberOfHorses = 30;

        List<Horse> horses = new ArrayList<>();
        for (int horseNum = STARTING_POINT; horseNum <= numberOfHorses; horseNum++) {
            horses.add(new Horse(TEST_NAME + horseNum, TEST_SPEED, TEST_DISTANCE));
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }

    /**
     * Exercise 5: Check that the method calls the move method on all horses.
     * When creating a Hippodrome object, pass a list to the constructor
     * out of 50 mock horses and use the verify method.
     */
    @Test
    public void testMove() {

        int numberOfHorses = 50;

        List<Horse> horses = new ArrayList<>();
        for (int horseNum = STARTING_POINT; horseNum <= numberOfHorses; horseNum++) {
            horses.add(mock(Horse.class));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();
        for (Horse horse : horses) {
            verify(horse).move();
        }
    }

    /**
     * Check that the method returns the horse with the largest distance value.
     */
    @Test
    public void getWinner() {

        int increase = 1;

        Horse horse1 = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        Horse horse2 = new Horse(TEST_NAME + increase + increase, TEST_SPEED, TEST_DISTANCE + increase);
        Horse horse3 = new Horse(TEST_NAME + increase, TEST_SPEED, TEST_DISTANCE + increase + increase);

        Hippodrome hippodrome = new Hippodrome(List.of(horse1, horse2, horse3));

        assertSame(horse3, hippodrome.getWinner());
    }
}
