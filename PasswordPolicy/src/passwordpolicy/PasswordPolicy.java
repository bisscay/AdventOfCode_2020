/*
 */
package passwordpolicy;

import java.util.*;
import java.io.*;

/**
 *
 * @author bAe
 */
public class PasswordPolicy {
    // Pseudocode - O(n)
    // make sense of input string
    // while password has character
        // increase count if character equals query
        // if count is greater return false
    // if count is less return false
    // else return true
    
    /**
     * Check is a password meets the policy
     * @param input - String
     * @return true/false - Boolean
     */
    public static boolean isValid(String input) {
        // make sense of input string
        String[] inputArray = input.split(" ");
        String[] range = inputArray[0].split("-");
        int start = Integer.valueOf(range[0]);
        int end = Integer.valueOf(range[1]);
        char query = inputArray[1].charAt(0);
        String passphrase = inputArray[2];
        int passLength = passphrase.length();
        
        int count = 0;
        // while password has character
        for(int i = 0; i < passLength; ++i) {
            // increase count if character equals query
            if(passphrase.charAt(i) == query)
                ++count;
            // if count is greater return false
            if(count > end) return false;
        }
        // if count is less return false
        return !(count < start);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here -178
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_2.txt"))) {
            // valid counter
            int valid = 0;
            while(scanner.hasNext()) {
                if(isValid(scanner.nextLine()))
                    ++valid;
            }
            System.out.println("Part 1: " +valid +" valid passphrases.");
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
