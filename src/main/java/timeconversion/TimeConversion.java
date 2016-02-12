package timeconversion;

import java.util.Scanner;

public class TimeConversion {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(convert(scanner.nextLine()));
    }

    public static String convert(String amPmTime) {
        int length = amPmTime.length();

        String timePart = amPmTime.substring(0, length - 2);
        int hour = Integer.parseInt(timePart.substring(0, 2));

        if (shouldModifyHour(amPmTime.substring(length - 2, length), hour))
            return timePart;

        return String.format("%02d%s", (hour + 12) % 24, timePart.substring(2));
    }

    protected static boolean shouldModifyHour(String typeOfTime, int hour) {
        return hour != 12 && "AM".equals(typeOfTime) || hour == 12 && "PM".equals(typeOfTime);
    }
}
