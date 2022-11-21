import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HorseTest {

    public static final String NAME_CANNOT_BE_NULL = "Name cannot be null.";

    public static final String NAME_CANNOT_BE_BLANK = "Name cannot be blank.";

    public static final String SPEED_CANNOT_BE_NEGATIVE = "Speed cannot be negative.";

    public static final String DISTANCE_CANNOT_BE_NEGATIVE = "Distance cannot be negative.";

    private final String TEST_NAME = "HorseName";
    private final double TEST_SPEED = 1.0;
    private final double TEST_DISTANCE = 2.5;

    /**
     * Exercise 1: Check that when passing to the constructor, the first parameter is null, will
     * throw IllegalArgumentException.
     * Exercise 2: Check that when passing to the constructor, the first parameter is null,
     * the exception thrown will contain the message "Name cannot be null."
     */
    @Test
    void testHorseExceptionDueToInvalidNameNullArgument(){

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Horse(null, TEST_SPEED, TEST_DISTANCE));

        assertEquals(NAME_CANNOT_BE_NULL, thrown.getMessage());
    }

    /**
     * Exercise 3: Check that when passing to the constructor as the first parameter an empty string or string
     * containing only whitespace characters (space, tab, etc.),
     * an IllegalArgumentException will be thrown.
     */
    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "\r"})
    void testHorseExceptionDueToInvalidNameArgument(String string){

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Horse(string, TEST_SPEED, TEST_DISTANCE));

        assertEquals(NAME_CANNOT_BE_BLANK, thrown.getMessage());
    }

    /**
     * Exercise 4: Check that when passing a negative number as the second parameter to the constructor,
     * will throw IllegalArgumentException.
     * Exercise 5: Check that when passing a negative number as the second parameter to the constructor,
     * the thrown exception will contain the message "Speed cannot be negative.".
     */
    @Test
    void testHorseExceptionDueToInvalidSpeedArgument(){

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Horse(TEST_NAME, -2, TEST_DISTANCE));

        assertEquals(SPEED_CANNOT_BE_NEGATIVE, thrown.getMessage());
    }

    /**
     * Exercise 6: Check that when passing a negative number as the third parameter to the constructor,
     * will throw IllegalArgumentException.
     * Exercise 7: Check that when passing a negative number as the third parameter to the constructor,
     * the thrown exception will contain the message "Distance cannot be negative.".
     */
    @Test
    void testHorseExceptionDueToInvalidDistanceArgument(){

        IllegalArgumentException thrown = assertThrows(IllegalArgumentException.class, () ->
            new Horse(TEST_NAME, TEST_SPEED, -2));

        assertEquals(DISTANCE_CANNOT_BE_NEGATIVE, thrown.getMessage());
    }

    /**
     * Check that the method returns the string that was passed as the first parameter to the constructor.
     */
    @Test
    void testGetName(){

        Horse horse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        assertEquals(TEST_NAME, horse.getName());
    }

    /**
     * Check that the method returns the number that was passed as the second parameter to the constructor.
     */
    @Test
    void testGetSpeed(){

        Horse horse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        assertEquals(TEST_SPEED, horse.getSpeed());
    }

    /**
     * Check that the method returns the number that was passed as the third parameter to the constructor.
     */
    @Test
    void testGetDistance(){

        Horse horse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
        assertEquals(TEST_DISTANCE, horse.getDistance());
    }

    /**
     * Check that the method returns null if the object was created using a constructor with two parameters.
     */
    @Test
    public void testGetDistanceDefaultValue() {

        Horse horse = new Horse(TEST_NAME, TEST_SPEED);
        assertEquals(0, horse.getDistance());
    }

    /**
     * Check that the move method calls inside the getRandomDouble method with parameters 0.2 and 0.9.
     * To do this, you need to use MockedStatic and its verify method
     */
    @Test
    void testGetRandomDouble(){

        try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            mockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    /**
     * Check that the method assigns the distance value calculated by the formula:
     * distance + speed * getRandomDouble(0.2, 0.9). To do this, you need to lock getRandomDouble,
     * so that it returns certain values that need to be set by parameterizing the test.
     */
    @ParameterizedTest
    @ValueSource(doubles = {1.0, 5.0, 0.5, 15.0, 10.0, 0.8})
    void testMethodsGetRandomDoubleThatItWorksOnTheFormula(double random) {

         try (MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse(TEST_NAME, TEST_SPEED, TEST_DISTANCE);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(random);

            horse.move();

            assertEquals(TEST_DISTANCE + TEST_SPEED * random, horse.getDistance());
        }
    }
}
