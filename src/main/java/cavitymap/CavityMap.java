package cavitymap;

public class CavityMap {
    public static char[][] find(char[][] inputMap) {
        char[][] outputMap = copyOf(inputMap);
        if (inputMap.length > 2)
            findWhenLengthGreaterThanTwo(inputMap, outputMap);
        return outputMap;
    }

    protected static void findWhenLengthGreaterThanTwo(char[][] inputMap, char[][] outputMap) {
        for (int row = 1; row + 1 < inputMap.length; row++)
            for (int column = 1; column + 1 < inputMap.length; column++)
                if (isCavity(inputMap, row, column))
                    outputMap[row][column] = 'X';
    }

    protected static char[][] copyOf(char[][] inputMap) {
        char[][] outputMap = new char[inputMap.length][];
        System.arraycopy(inputMap, 0, outputMap, 0, inputMap.length);
        return outputMap;
    }

    private static boolean isCavity(char[][] map, int row, int column) {
        char cell = map[row][column];
        return cell > map[row - 1][column] &&
                cell > map[row + 1][column] &&
                cell > map[row][column - 1] &&
                cell > map[row][column + 1];
    }

    public static String[] find(String[] intputMap) {
        return toStringArray(find(toCharArrays(intputMap)));
    }

    public static char[][] toCharArrays(String[] strings) {
        char[][] chars = new char[strings.length][];
        for (int i = 0; i < strings.length; i++)
            chars[i] = strings[i].toCharArray();
        return chars;
    }

    public static String[] toStringArray(char[][] chars) {
        String[] strings = new String[chars.length];
        for (int i = 0; i < chars.length; i++)
            strings[i] = new String(chars[i]);
        return strings;
    }
}
