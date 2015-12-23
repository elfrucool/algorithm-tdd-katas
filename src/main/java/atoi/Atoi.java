package atoi;

public class Atoi {
    public static int atoi(String s) {
        if (s == null || s.trim().isEmpty())
            return 0;
        return atoiNotEmpty(s.trim());
    }

    private static int atoiNotEmpty(String s) {
        int index = 0;
        int sign = 1;

        if (s.charAt(0) == '-') {
            sign = -1;
            index++;
        }

        int number = 0;

        while (index < s.length())
            number = number * 10 + s.charAt(index++) - '0';

        return number * sign;
    }
}
