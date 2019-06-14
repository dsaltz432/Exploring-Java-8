import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;

public class TestingFunctionalInterfaces {

    public static void main(String[] args){

        TestingFunctionalInterfaces instance = new TestingFunctionalInterfaces();

        // concat - 1 line lambda expression
        // because the lambda function is only 1 line, we can exclude a few things:
            // parentheses around the input parameter
            // return statement
            // curly braces around code
        Function<String,String> concat = input -> input + " with trailing text";
        System.out.println("\nTesting concat with blueberries: " + concat.apply("blueberries"));
        System.out.println("Testing concat with strawberries: " + concat.apply("strawberries"));


        // test new feature of method reference
        Function<String,String> makeLowerCase = String::toLowerCase;
        System.out.println("\nTesting makeLowerCase with HelloOOO: " + makeLowerCase.apply("HelloOOO"));


        // countDistinctChars - multiple lined lambda expression
        Function<String,Integer> countDistinctChars = (word) -> {
            Set<Character> chars = new HashSet<>();
            for (char c : word.toCharArray()){
                chars.add(c);
            }
            return chars.size();
        };

        System.out.println("\nTesting countDistinctChars:");
        System.out.println("Number of distinct chars in blueberries: " + countDistinctChars.apply("blueberries"));
        System.out.println("Number of distinct chars in strawberries: " + countDistinctChars.apply("strawberries"));
        System.out.println("Number of distinct chars in berrrrrry: " + countDistinctChars.apply("berrrrrry"));


        // countTotalDistinctChars - function that takes in a List as a parameter
        Function<List<String>,Integer> countTotalDistinctChars = (wordList) -> {
            Set<Character> chars = new HashSet<>();
            for (String word : wordList){
                for (char c : word.toCharArray()){
                    chars.add(c);
                }
            }
            return chars.size();
        };

        System.out.println("\nTesting countTotalDistinctChars:");
        List<String> wordList = Arrays.asList("blueberries", "strawberries", "berrrrrry");
        System.out.println(String.format("Total number of distinct chars in %s: %d",
                wordList, countTotalDistinctChars.apply(wordList)));


        // Create a new FunctionalInterface to perform basic math operations
        MathOperation addition = (a, b) -> a + b;
        MathOperation subtraction = (a, b) -> a - b;
        MathOperation multiplication = (a, b) -> a * b;
        MathOperation division = (a, b) -> a / b;

        System.out.println("\nUsing a custom FunctionalInterface to perform math operations:");
        System.out.println("10 + 5 = " + instance.operate(10, 5, addition));
        System.out.println("10 - 5 = " + instance.operate(10, 5, subtraction));
        System.out.println("10 x 5 = " + instance.operate(10, 5, multiplication));
        System.out.println("10 / 5 = " + instance.operate(10, 5, division));
    }


    /*
        Created FunctionalInterface to perform a single math operation. Then create an implementation of that interface
        for each type of basic math operation. We can pass that function as a parameter into a method to perform
        the desired operation.
     */

    @FunctionalInterface
    interface MathOperation {
        int operation(int a, int b);
    }

    private int operate(int a, int b, MathOperation mathOperation) {
        return mathOperation.operation(a, b);
    }

}
