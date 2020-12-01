/*
 */
package reportrepair;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author bAe
 */
public class ReportRepair {

    /**
     * @param args the command line arguments
     */
    // Pseudocode - O(n)
    // place all values in a hash set
    // access each value
    // sub 2020 from each value
    // check if difference is in set
    // if yes, mutiply value by difference
    // else move on
    // -----------------------------------
    // Part 2 - O(n x m)
    // sub 2020 from each key and store result in value
    // find two keys that add up to a value  
    // sub value from key
    // check if difference is in key
    
    /**
     * Repair Report - Part 1
     * @param map - HashMap<Integer, Integer>
     * @return result - int
     */
    public static int repair(HashMap<Integer, Integer> map) {
        int value;
        int difference;
        // access each value
        for(Map.Entry entry : map.entrySet()) {
            value = (Integer)entry.getKey();
            // sub 2020 from each value
            difference = 2020 - value;
            // check if difference is in set
            // if yes, mutiply value by difference
            if(map.containsKey(difference))
                return value * difference;
        }
        return 0;
    }
    
    /**
     * Product of Three that Sum to 2020 - Part 2
     * @param map - HashMap<Integer, Integer>
     * @return result - int
     */
    public static int threeSum(HashMap<Integer, Integer> map) {
        // sub 2020 from each key and store result in value
        for(Map.Entry entry : map.entrySet()) { // O(n)
            map.put((Integer)entry.getKey(),2020 - (Integer)entry.getKey());//O(1)
        }
        return 0;
    }
    
    // Driver code
    public static void main(String[] args) {
        // TODO code application logic here
        // hash ser
        HashMap<Integer,Integer> map = new HashMap<>();
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_1.txt"))){
            while(scanner.hasNext()) {
                // place all values in a hash set
                map.put(scanner.nextInt(), 0);
                // skip to end of line
                scanner.nextLine();
            }
            // debug
            System.out.println("Part One: " +repair(map));
            //System.out.println("Morph: " +threeSum(map));
        } catch(FileNotFoundException e) {
            // uncaught exceptions
            System.out.println(e.getClass().getName() + " :" + e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() + " :" + e.getMessage());
        }
    }
    
}
