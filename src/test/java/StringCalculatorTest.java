import com.ultragenius.assement.NegativesNotAllowedException;
import com.ultragenius.assement.StringCalculator;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;



public class StringCalculatorTest {
    private StringCalculator sc = new StringCalculator();

    @BeforeAll
    public static void setUp() {
        System.out.println("Starting  Calculator Test...");
    }
    @Test
    public void emptyString() {
        assertEquals(sc.add(""), 0);
    }
    @Test
    public void emptyStringWithSpaces() {
        assertEquals(sc.add(" "), 0);
    }
    @Test
    public void oneNumber() {
        assertEquals(sc.add("1"), 1);
    }
    @Test
    public void multipleNumbers() {
        assertEquals(sc.add("1, 5, 9, 10"), 25);
    }
    @Test
    public void handleNewLines() {
        assertEquals(sc.add("1, 2\n 3"), 6);
    }
    @Test
    public void customDelimiter() {
        assertEquals(sc.add("//;\\n1;4"), 5);
    }
    @Test
    public void checkNegative() {
        try {
            sc.add("-11,-18,10");
        } catch (NegativesNotAllowedException e) {
            assertEquals(e.getMessage(), "Negatives Not Allowed. Numbers are: -11 -18 ");
        }
    }
    @Test
    public void checkNegativeInCustomDelimiter() {
        try {
            sc.add("//%\\n1%-8%-1%-12%26");
        } catch (NegativesNotAllowedException e) {
            assertEquals(e.getMessage(), "Negatives Not Allowed. Numbers are: -8 -1 -12 ");
        }
    }
    @Test
    public void greaterThan1000() {
        assertEquals(sc.add("//-\\n2000-6-3-1-1001"), 10);
    }
    @Test
    public void longLengthDelimiterWithoutBraces() {
        assertEquals(sc.add("//%%%\\n1%%%3"), 4);
    }
    @Test
    public void longLengthDelimiterWithBraces() {
        assertEquals(sc.add("//[^^]\\n1^^2^^3"), 6);
    }
    @Test
    public void longLengthMultipleDelimiters() {
        assertEquals(sc.add("//[**][%%%][^^]\\n1**2%%%3^^6"), 12);
    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Completed");
    }
}