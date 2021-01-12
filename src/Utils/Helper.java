package Utils;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

public class Helper {
    public static String RandomNumberOnly(int limit){
        Random random = new Random();
        int leftLimit = 48; // digit '0'
        int rightLimit = 57; // digit '9'
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .limit(limit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
    public static String RandomCharacterNumber(int limit){
        Random random = new Random();
        int leftLimit = 48; // digit '0'
        int rightLimit = 90; // letter 'Z'
        String generatedString = random.ints(leftLimit, rightLimit + 1)
                .filter(i -> (i <= 57 || i >= 65 && i <= 90))
                .limit(limit)
                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                .toString();
        return generatedString;
    }
    public static String GetCurrentDate(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = simpleDateFormat.format(new Date(System.currentTimeMillis()));
        return date;
    }
    public static boolean IsOnlyDigit(String str){
        return str.matches("[0-9]+");
    }


}
