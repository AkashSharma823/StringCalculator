import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class StringCalculatorTest {

    private final StringCalculator calculator = new StringCalculator();

    @Test
    public void testEmptyString() {
        assertEquals(0, calculator.add(""));
    }

    @Test
    public void testSingleNumber() {
        assertEquals(5, calculator.add("5"));
    }

    @Test
    public void testTwoNumbersSeparatedByComma() {
        assertEquals(3, calculator.add("1,2"));
    }

    @Test
    public void testNumbersSeparatedByNewline() {
        assertEquals(6, calculator.add("1\n2,3"));
    }

    @Test
    public void testSupportsCustomDelimiter() {
        assertEquals(3, calculator.add("//;\n1;2"));
    }

    @Test
    public void testThrowsErrorForNegativeNumbers() {
        IllegalArgumentException thrown = assertThrows(
            IllegalArgumentException.class,
            () -> calculator.add("1,-2,3")
        );
        assertEquals("negatives not allowed: -2", thrown.getMessage());
    }
}
