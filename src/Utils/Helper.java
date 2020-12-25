package Utils;

import java.util.Random;

public class Helper {
    public static String RandomString(int limit){
        Random random = new Random();
        int leftLimit = 48; // digit '0'
        int rightLimit = 57; // digit '9'
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(limit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
    public static boolean IsOnlyDigit(String str){
        return str.matches("[0-9]+");
    }

}
