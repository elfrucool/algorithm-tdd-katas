package angryprofessor;

import java.util.Scanner;

public class AngryProfessor {
    public static void main(String[] args) {
        System.out.println(angry(new Scanner(System.in)));
    }

    public static String angry(Scanner scanner) {
        if (!scanner.hasNextInt())
            return "";

        int numberOfTests = scanner.nextInt();

        String output = "";
        while (numberOfTests-- > 0)
            output += singleAngry(scanner) + "\n";

        return output.trim();
    }

    protected static String singleAngry(Scanner scanner) {
        int studentsInClass = scanner.nextInt();
        int attendanceThreshold = scanner.nextInt();

        int attendingStudents = studentsInClass;

        for (int studentsCount = studentsInClass; studentsCount-- > 0; )
            if (scanner.nextInt() > 0)
                attendingStudents--;

        return attendingStudents < attendanceThreshold ? "YES" : "NO";
    }
}
