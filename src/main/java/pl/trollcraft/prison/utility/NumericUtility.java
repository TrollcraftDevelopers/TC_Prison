package pl.trollcraft.prison.utility;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class NumericUtility {

    private NumericUtility(){}

    public static double stringToDouble(String num, double errorNum) {
        try {
            return Double.parseDouble(num);
        } catch (NumberFormatException e) {
            return errorNum;
        }
    }

    public static double round(double num, int places) {
        if (places < 0) throw new IllegalArgumentException();
        BigDecimal bd = BigDecimal.valueOf(num);
        bd = bd.setScale(places, RoundingMode.FLOOR);
        return bd.doubleValue();
    }

}
