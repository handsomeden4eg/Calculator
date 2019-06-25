import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Calculator extends RomanNumerals {

    static final String INVALID_EXPRESSION = "Incorrect mathematical expression";
    static ArrayList<String> userInputArray = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        char mathOperator;

        RomanNumerals Rome = new RomanNumerals();
        String userInput;

        //Checking for empty string
        while(true) {
            try {
                userInput = reader.readLine().toUpperCase();
                if(userInput.isEmpty()) {
                    throw new IncorrectInputException(INVALID_EXPRESSION);
                } else {
                    break;
                }
            } catch(IncorrectInputException e) {
                System.out.println(e.getMessage());
            }
        }

        //Split by metacharacter
        try {
            if (userInput.contains("*")) {
                userInputArray.addAll(Arrays.asList(userInput.replace(" ", "").split("[*]")));
                mathOperator = '*';
            } else if (userInput.contains("/")) {
                userInputArray.addAll(Arrays.asList(userInput.replace(" ", "").split("[/]")));
                mathOperator = '/';
            } else if (userInput.contains("+")) {
                userInputArray.addAll(Arrays.asList(userInput.replace(" ", "").split("[+]")));
                mathOperator = '+';
            } else if (userInput.contains("-")) {
                userInputArray.addAll(Arrays.asList(userInput.replace(" ", "").split("[-]")));
                mathOperator = '-';
            } else {
                throw new IncorrectInputException(INVALID_EXPRESSION);
            }
        } catch (IncorrectInputException e) {
            System.out.println(e.getMessage());
            return;
        }

        //Checking for opposite numerals
        try {
            if (containsOpposite()) {
                throw new IncorrectInputException(INVALID_EXPRESSION);
            }
        } catch (IncorrectInputException e) {
            System.out.println(e.getMessage());
            return;
        }

        //Try to calculate something :)
        for (int i = 0; i < userInputArray.size(); i++) {
            if (mathOperator == '*') {
                total = 1;
                for (int j = 0; j < userInputArray.size(); j++) {
                    try {
                        total *= Integer.parseInt(userInputArray.get(j));
                    } catch (NumberFormatException e) {
                        total *= Rome.getMap().get(userInputArray.get(j));
                    }
                }
            } else if (mathOperator == '/') {
                try {
                    total = Integer.parseInt(userInputArray.get(0));
                } catch (NumberFormatException e) {
                    total = Rome.getMap().get(userInputArray.get(0));
                }

                for (int j = 1; j < userInputArray.size(); j++) {
                    try {
                        total /= Integer.parseInt(userInputArray.get(j));
                    } catch(ArithmeticException e) {
                        System.out.println("Cannot divide by 0");
                        return;
                    } catch (NumberFormatException e) {
                        total /= Rome.getMap().get(userInputArray.get(j));
                    }
                }
            } else if(mathOperator == '+') {
                total=0;
                for (int j = 0; j < userInputArray.size(); j++) {
                    try {
                        total += Integer.parseInt(userInputArray.get(j));
                    } catch (NumberFormatException e) {
                        total += Rome.getMap().get(userInputArray.get(j));
                    }
                }
            } else if(mathOperator == '-') {
                try {
                    total = Integer.parseInt(userInputArray.get(0));
                } catch (NumberFormatException e) {
                    total = Rome.getMap().get(userInputArray.get(0));
                }
                for (int j = 1; j < userInputArray.size(); j++) {
                    try {
                        total -= Integer.parseInt(userInputArray.get(j));
                    } catch (NumberFormatException e) {
                        total -= Rome.getMap().get(userInputArray.get(j));
                    }
                }
            }
        }
        reader.close();
        System.out.println(total);
    }

}
