import java.util.HashMap;


public class RomanNumerals {

    private static HashMap<String, Integer> map = new HashMap<>();

    static {
        map.put("I", 1);
        map.put("II", 2);
        map.put("III", 3);
        map.put("IV", 4);
        map.put("V", 5);
        map.put("VI", 6);
        map.put("VII", 7);
        map.put("VIII", 8);
        map.put("IX", 9);
        map.put("X", 10);
    }

    public HashMap<String, Integer> getMap() {
        return map;
    }

    public static boolean isNumber(String s) {
        if (s.length() == 0) return false;

        char[] chars = s.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if ((i != 0 && c == '-') // String contains '-'
                    || (!Character.isDigit(c) && c != '-') // Or not a number and not beginning with '-'
                    || (chars.length == 1 && c == '-')) // Or single character '-'
            {
                return false;
            }
        }
        return true;
    }

    //Does given array have an opposite number?
    public static boolean containsOpposite() {
        String temporaryElement = Calculator.userInputArray.get(0);
        boolean checked = isNumber(temporaryElement);

        for (int i = 1; i < Calculator.userInputArray.size(); i++) {
            if (isNumber(Calculator.userInputArray.get(i)) && !checked) {
                return true;
            } else if (!isNumber(Calculator.userInputArray.get(i)) && checked) {
                return true;
            }
        }
        return false;
    }

}
