package tlu.cse.android.ht63.quanlychitieu.Other;

import java.text.DecimalFormat;
public class CustomNumber {
    public static String formatNumber(int number) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###");
        return decimalFormat.format(number);
    }
}
