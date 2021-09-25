package pl.trollcraft.prison;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import pl.trollcraft.prison.utility.NumericUtility;

public class NumericUtilityTest {

    @Test
    void shouldConvertStringToTinyDouble() {
        String numStr = "0.01";
        double num = NumericUtility.stringToDouble(numStr, 0D);

        assertEquals(0.01D, num);
    }

    @Test
    void shouldConvertStringToTinyDoubleAndRound() {
        String numStr = "2.32638123819";
        double num = NumericUtility.stringToDouble(numStr, 0D);

        double rounded = NumericUtility.round(num, 2);
        System.out.println(rounded);

        assertEquals(2.32638123819, num);
        assertEquals(2.32, rounded);
    }

    @Test
    void shouldConvertStringTo0() {
        String numStr = "0.000000001";
        double num = NumericUtility.stringToDouble(numStr, 0D);

        double rounded = NumericUtility.round(num, 2);
        System.out.println(rounded);

        assertEquals(0.000000001, num);
        assertEquals(0, rounded);
    }

}
