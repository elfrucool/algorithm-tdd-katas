package reverse;

public class ReverseWords {
    public static String reverse(String s) {
        if (s == null || s.trim().isEmpty())
            return "";
        return reverseNonEmpty(s.trim());
    }

    private static String reverseNonEmpty(String s) {
        int spaceIndex = s.indexOf(" ");
        if (spaceIndex < 0)
            return s;
        return reverse(s.substring(spaceIndex).trim()) + " " + s.substring(0, spaceIndex);
    }
}
