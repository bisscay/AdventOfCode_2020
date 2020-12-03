/*
 */
package toboggantrajectory;

import java.util.*;
import java.io.*;

/**
 *
 * @author bAe
 */
public class TobogganTrajectory {
    // Pseudocode - O(n)
    // mirror pool until leftIndex is bounded
    // check if coordinate is blocked
    
    /**
     * Find open squares
     * @param input - String
     * @param leftIndex - int
     * @return true/false - boolean
     */
    public static boolean isBlocked(String input, int leftIndex) {
        // store input's signature
        String sign = input;
        
        // mirror pool until leftIndex is bounded
        String pool = sign;
        int poolLength = pool.length();
        while(leftIndex >= poolLength) {
            pool += sign;
            poolLength = pool.length();
        }
        
        // check if coordinate is blocked
        return (pool.charAt(leftIndex) == '#');
    }

    /**Driver code
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_3.txt"))) {
            // right steps
            int rightBy1 = 0;
            int rightBy3 = 0;
            int rightBy5 = 0;
            int rightBy7 = 0;
            int rightByOne = 0;
            // down steps
            int skip = 0;
           
            // obstructions per slop
            int treeFoundSlope1 = 0;
            int treeFoundSlope2 = 0;
            int treeFoundSlope3 = 0;
            int treeFoundSlope4 = 0;
            int treeFoundSlope5 = 0;
            
            while(scanner.hasNext()) {
                // read scanner by line
                String input = scanner.nextLine();
                
                // Part 1
                if(isBlocked(input, rightBy3))
                    ++treeFoundSlope2;
                // follow trajectory
                rightBy3+=3;
                
                // Part 2
                // Slope 1
                if(isBlocked(input, rightBy1))
                    ++treeFoundSlope1;
                // follow trajectory
                rightBy1+=1;
                
                // Slope 3
                if(isBlocked(input, rightBy5))
                    ++treeFoundSlope3;
                // follow trajectory
                rightBy5+=5;
                
                // Slope 4
                if(isBlocked(input, rightBy7))
                    ++treeFoundSlope4;
                // follow trajectory
                rightBy7+=7;
                
                // Slope 5
                if(skip == 0 || skip%2 == 0) { // even steps
                    if(isBlocked(input, rightByOne))
                        ++treeFoundSlope5;
                    // follow trajectory
                    rightByOne+=1;
                }
                // down two steps
                ++skip;
            }
            
            System.out.println("Part 1: " +treeFoundSlope2 +" trees found.");
            
            // output without long - 2124702224
            long result = (long)treeFoundSlope2 * treeFoundSlope1 * treeFoundSlope3 * 
                    treeFoundSlope4 * treeFoundSlope5;
            
            System.out.println("Part 2: " +result);
            
        } catch(FileNotFoundException e) {
            // uncaught exception
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
