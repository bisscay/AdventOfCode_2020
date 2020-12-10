/*
 */
package adapterarray;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author bAe
 */
public class AdapterArray {
    // Pseudocode
    // Part 1, Option 1 - O(nlogn)
    // read stream as list of integers
    // sort list - O(nlogn)
    // declear variables for one and three count
    // if the difference between first and next is one, - O(n)
    // increament one
    // if the difference is three, increament three
    // ---------------------------------------------------
    // Part 1, Option 2 - O(n)
    // find minimum and max in set - O(n)
    // increase minimum by 1 or 2 or 3 until maximum
    // keep count for 1 and 3
    // when at maximum, return product of 1 and 3 count
    // ---------------------------------------------------
    // Part 2
    
    /**
     * Get product of jolt 1 and 3 counts; Option 2
     * @param set - Set<Integer>
     * @return product - int
     */
    public static int joltBound(Set<Integer> set) {
        // consider bounded conditions
        // charge outlet has effective volt of zero,
        // hence account for first step,
        // first step can be 1, 2, 3 depending on min in set
        int oneCount;
        // rated in built in adapter is 3 higher than set highest
        int threeCount = 1;
        
        // find minimum value in set
        // initialize minmum and maximum to first
        Iterator sit = set.iterator();
        int min = (Integer)sit.next();
        int max = min;
        int current;
        
        // loop through set until minimum and maximum is found - O(n)
        while(sit.hasNext()) {
            current = (Integer)sit.next();
            min = Math.min(min, current);
            max = Math.max(max, current);
        }
        
        // if min is one, increament one counter for first step
        // if two, ignore, if three increament three counter
        if(min == 1) oneCount = 1;
        else if(min == 3) oneCount = 3;
        else oneCount = 0;
        
        // continue until min equals max, min will also hold steps
        while(min < max) {
            if(set.contains(min + 1)) {
                // if set contains one increament,
                // store step by one in min an increase one counter
                ++min;
                ++oneCount;
            }
            else if(set.contains(min + 2)) {
                // if set contains two increament,
                // store step by two in min
                min += 2;
            }
            else if(set.contains(min + 3)) {
                // if set contains three increament,
                // store step by three in min an increase three counter
                min += 3;
                ++threeCount;
            }
        }
        
        // return one count x three count
        return oneCount * threeCount;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_10.txt"))) {
            int input;
            Set<Integer> set = new HashSet<>();
            while (scanner.hasNext()) {
                input = scanner.nextInt();
                set.add(input);
            }

            System.out.println("Part 1: " +joltBound(set));
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        }
    }
}
