package reversewords;

public class ReverseWords {
    public static String reverse(String s) {
        if (s == null)
            return "";
        return reverseNotNull(s.trim());
    }

    protected static String reverseNotNull(String s) {
        int indexOfSpace = s.indexOf(" ");

        if (indexOfSpace == -1)
            return s;

        return reverse(s.substring(indexOfSpace)) + " " + s.substring(0, indexOfSpace);
    }
}
