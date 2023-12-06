package utils;

import models.settings.LoggerColumns;

public class Enums {
    public static String enumToString(LoggerColumns e) {
        String s = e.toString().toLowerCase();
        char[] cList = s.toCharArray();

        for (int i = 0; i < cList.length; i++) {
            if (i == 0) {
                cList[0] = Character.toUpperCase(cList[0]);
            }

            if (cList[i] == '_') {
                cList[i] = ' ';
                cList[i + 1] = Character.toUpperCase(cList[i + 1]);
            }
        }

        return new String(cList);
    }
}
