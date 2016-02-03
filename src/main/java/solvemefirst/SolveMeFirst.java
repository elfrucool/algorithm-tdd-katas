package solvemefirst;

import java.util.Scanner;

public class SolveMeFirst {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        System.out.println(solveMeFirst(a, b));
    }
    public static int solveMeFirst(int a, int b) {
        return a + b;
    }
}
