package utils;

public class Temp {
    public static Double toFahrenheit(Double t) {
        if (t == null)
            return null;

        return (t * 9/5) + 32;
    }

    public static Double toCelsius(Double t) {
        if (t == null)
            return null;

        return (t - 32) * 5/9;
    }
}
