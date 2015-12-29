package atoi;

public class Atoi {
    public static int atoi(String s) {
        if (s == null || s.trim().isEmpty())
            return 0;

        int index = 0;
        int sign = 1;

        String clean = s.trim();
        if (clean.startsWith("-")) {
            sign = -1;
            index++;
        }

        int number = 0;
        for (;index < clean.length(); index++) {
            number = number * 10 + clean.charAt(index) - '0';
        }

        return number * sign;
    }
}
