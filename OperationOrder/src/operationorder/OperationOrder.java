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
    // Part 2 - O(n x m)
    // evaluate from nested-brackets out
    // resolve + expressions
    // resolve * expressions
    
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
    
    /**
     * Get expression sum following new precedence
     * @param input - String
     * @return result - long
     */
    public static long computeV3(String input) {
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
            
            // evaluate substring from + to *
            int span = inputArray.length-1;
            long before, after;
            
            // resolve + expressions
            for(int j = 1; j < span; j+=2) {
                // when charcater == +
                if(inputArray[j].equals("+")) {
                    // get operand before and after +
                    before = Long.valueOf(inputArray[j-1]);
                    after = Long.valueOf(inputArray[j+1]);
                    result = before + after;
                    // update input array with result
                    inputArray[j-1] = "0";
                    inputArray[j] = "0";
                    inputArray[j+1] = Long.toString(result);
                }
            }
            
            // resolve * expressions
            long temp = 1;
            int inputArraySize = inputArray.length;
            for(int j = 0; j < inputArraySize; ++j) {
                // all non zero values will be multiplied
                if(!(inputArray[j].equals("0") || inputArray[j].equals("*"))) {
                    temp *= Long.valueOf(inputArray[j]);
                }
            }
            // only update result when * exists within parenthesis
            if(temp > 1) result = temp;
            
            // take out recently resolved nested parenthesis
            sub = "(" +sub + ")";
            input = input.replace(sub, ""+result);
        }
        return result;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_18.txt"))) {
            String input;
            long sum = 0, sumV2 = 0;
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                sum += compute(input);
                sumV2 += computeV3(input);
            }
            System.out.println("Part 1: " +sum);
            System.out.println("Part 2: " +sumV2);
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
