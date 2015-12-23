package reverse;

public class ReverseWords {
    public static String reverse(String s) {
        if (s == null)
            return "";
        return reverseNonNull(s.trim());
    }

    private static String reverseNonNull(String s) {
        int indexOfSpace = s.indexOf(" ");

        if (indexOfSpace < 0)
            return s;

        return reverse(s.substring(indexOfSpace).trim()) + " " + s.substring(0, indexOfSpace);
    }
}
