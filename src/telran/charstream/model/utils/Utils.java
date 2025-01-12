package telran.charstream.model.utils;

public class Utils {

    public static String getNameFromString(String source, String sym) {

        int posStart = source.indexOf(sym);
        int posEnd = source.indexOf(sym, posStart + 1);

        if (posStart == -1 || posEnd == -1){
            return "";
        }

        return source.substring(posStart, posEnd);
    }

    public static double roundDoubleValue(double value,int decimal) {
        int div = decimal == 0? 1 : 10 * decimal;
        return (double) Math.round(value * div) / div;
    }

}
