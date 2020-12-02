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
    // ------------------------------------
    // Part 2 : Policy Update - O(1)
    // make sense of input string - DRY
    // check if index search is within bound
    // check if letter is in only one of either index
     
    /**
     * Check is a password meets the policy
     * @param input - String
     * @return true/false - boolean
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
     * Policy Update
     * @param input - String
     * @return true/false - boolean
     */
    public static boolean isIndexValid(String input) {
        // make sense of input string
        String[] inputArray = input.split(" ");
        String[] range = inputArray[0].split("-");
        int first = Integer.valueOf(range[0])-1; // 1-indexing
        int second = Integer.valueOf(range[1])-1; // 1-indexing
        char query = inputArray[1].charAt(0);
        String passphrase = inputArray[2];
        int passLength = passphrase.length();
        
        // check if index is within bound
        if(first >= 0 && first < passLength 
                && second >= 0 && second < passLength) {
            // check for first
            if(passphrase.charAt(first) == query 
                    && passphrase.charAt(second) != query) return true;
            // check for second
            else if (passphrase.charAt(first) != query 
                    && passphrase.charAt(second) == query) return true;
        } 
        // check for first only if second is out of bound
        else if(first >= 0 && first < passLength) {
            return (passphrase.charAt(first) == query);
        } 
        // check for second only if first is out of bound
        else if (second >= 0 && second < passLength) {
            return (passphrase.charAt(second) == query);
        }
        // else both indices are out of bound
        return false;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here -178
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_2.txt"))) {
            // valid counter
            int valid = 0;
            int validUpdate = 0;
            while(scanner.hasNext()) {
                String s = scanner.nextLine();
                // Part 1
                if(isValid(s))
                    ++valid; 
                // Part 2
                if(isIndexValid(s))
                    ++validUpdate;
            }
            System.out.println("Part 1: " +valid +" valid passphrases.");
            System.out.println("Part 2: " +validUpdate +" valid passphrases.");
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
