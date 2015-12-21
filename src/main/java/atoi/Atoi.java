package atoi;

public class Atoi {
    public static int atoi(String s) {
        if (s == null || s.trim().isEmpty())
            return 0;

        String trimmed = s.trim();
        int index = 0;
        int sign = 1;

        if (trimmed.charAt(0) == '-') {
            index++;
            sign = -1;
        }

        int number = 0;
        while (index < trimmed.length())
            number = number * 10 + trimmed.charAt(index++) - '0';

        return number * sign;
    }
}
