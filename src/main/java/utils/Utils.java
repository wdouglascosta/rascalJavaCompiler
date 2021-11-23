package utils;

import core.LexerToken;

public class Utils {

    public static Boolean isAnInteger(LexerToken var) {

        try {
            Integer.valueOf(var.getVal());
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }

    }
}
