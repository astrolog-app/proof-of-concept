package utils;

public class Enums {
    public static <T extends Enum<T>> String enumToString(T en) {
        String s = en.toString().toLowerCase();
        char[] cList = s.toCharArray();

        for (int i = 0; i < cList.length; i++) {
            if (i == 0) {
                cList[0] = Character.toUpperCase(cList[0]);
            }
            if (cList[i] == '_') {
                cList[i] = ' ';
                try {
                    cList[i + 1] = Character.toUpperCase(cList[i + 1]);
                } catch (Exception e) {
                    System.out.println(" ");
                }
            }
        }

        return new String(cList).trim();
    }

    public static <T extends Enum<T>> T getEnumFromString(String string, Class<T> enumClass) {
        return T.valueOf(enumClass, string.toUpperCase().replace(" ", "_").trim());
    }
}
