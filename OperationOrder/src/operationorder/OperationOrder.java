/*
 */
package operationorder;

import java.util.*;
import java.io.FileNotFoundException;
import java.io.File;

/**
 *
 * @author bAe
 */
public class OperationOrder {
    // Pseudocode - O(n x m)
    // nest entire input string in brackets
    // evaluate from nested-brackets out
    // get last occurance of (,
    // get first occurance of )
    // indeces will act as markers for a substring
    // evaluate substring from left to right
    // take out recently resolved nested parenthesis
    // stop when all brackets are resolved
    // -------------------------------
    // place parenthensis round values with *
    // call Part 1
    
    /**
     * Get sum of expressions following defined rules
     * @param input - String
     * @return result - long
     */
    public static long compute(String input) {
        long result = 0;
        
        // nest entire input string in brackets
        input = "(" +input +")";
        
        // evaluate from nested-brackets out
        int start, end;
        String sub;
        // stop when all brackets are resolved
        while(input.charAt(0) == '(') {
            // trim down to bracket
            start = input.lastIndexOf('(') + 1;
            sub = input.substring(start);
            end = sub.indexOf(')');
            sub = sub.substring(0, end);
            
            // break substring at white spaces
            String[] inputArray = sub.split(" ");
            
            // evaluate substring from left to right
            String operator;
            long operand;
            // result initialized to first operand within brackets
            result = Long.valueOf(inputArray[0]);
            int span = inputArray.length-1;
            for(int j = 1; j < span; j+=2) {
                operator = inputArray[j];
                // get second operand
                operand = Long.valueOf(inputArray[j+1]);
                // resolve operator
                if(operator.equals("*"))
                    result *= operand;
                else if(operator.equals("+"))
                    result += operand;
            }
            // take out recently resolved nested parenthesis
            sub = "(" +sub + ")";
            input = input.replace(sub, ""+result);
        }
        return result;
    }
    
    // loop through input string characters
    // when charcater == *
    // get char before and after *
    // replace expression with parenthesis in string
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_18.txt"))) {
            String input;
            long sum = 0;
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                sum += compute(input);
            }
            System.out.println("Part 1: " +sum);
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
