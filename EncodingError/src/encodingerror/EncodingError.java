/*
 */
package encodingerror;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author bAe
 */
public class EncodingError {
    // Pseudocode O(n)
    // read from stream and place in preamble list
    // when at preamble size plus one in stream - xmas starts,
    // sub current input from first num in preamble,
    // search for difference in remaining num in preamble -
    // if it exits, move to next stream
    // else move to next num in preample until end of list
    // if difference exists, move to next stream
    // else return stream value as result
    // ------------------------------------------------
    // Part 2 - O(n x m)
    // for each value in list
    // sum until result equals input
    // if result is greater
    // shift start of sum to next index
    
    /**
     * Get the point where an error occurs
     * @param list - List<Long>
     * @param input - long
     * @return true/false - boolean
     */
    public static boolean errorCheck(List<Long> list, long input) {
        // moving from left to right
        // subtract input from preamble set
        // return true once difference is in rest of set
        // else refurn false
        
        long difference;
        Set<Long> setSub = new HashSet<>();
        List<Long> listSub = new LinkedList<>();
        
        // moving from left to right
        int listSize = list.size();
        for(int i = 0; i < listSize; ++i) {
            // subtract input from preamble set
            difference = Math.abs(list.get(i) - input);
            // add every value after 
            listSub.addAll(list.subList(i+1, listSize)); // O(?)
            // add every value before
            listSub.addAll(list.subList(0, i));
            
            setSub = new HashSet(listSub);
            
            // return true once difference is in rest of set
            if(setSub.contains(difference)) {
                return true;
            }
            // reset set for next value in list preamble
            setSub.clear();
        }
        return false;
    }
    
    /**
     * Get the sum of the least and greatest contiguous values 
     * for all inputs before error
     * @param list - List<Long>
     * @param input - long
     * @return sum - long
     */
    public static long getWeakness(List<Long> list, long input) {
        long smallest = 0;
        long largest = 0;
        long current, sum = 0;
        // for each value in list
        int listSize = list.size();
        for(int i = 0; i < listSize; ++i) {
            // at first current will be the smallest and larget
            smallest = list.get(i);
            largest = list.get(i);
            
            // if result is greater
            // shift start of sum to next index
            for(int j = i; j < listSize; ++j) {
                current = list.get(j);
                smallest = Math.min(smallest, current);
                largest = Math.max(largest, current);
                sum += current;
                // sum until result equals input
                if(sum == input) {
                    return smallest + largest;
                } else if (sum > input) { 
                    // result overshoots no need to check for rest
                    break;
                }
            }
            // reset sum for next contiguous set
            sum = 0;
        }
        return sum;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_9.txt"))) {
            // initialize preamble to 25 (5 test)
            int preamble = 25;
            int xmasStart = 0; // xmas starts at preamble
            List<Long> list = new LinkedList<>();
            // keeps track of every removed input
            List<Long> listCopy = new LinkedList<>();
            long input, part2;
            
            while(scanner.hasNext()) {
                // read from stream and place in preamble map
                input = scanner.nextLong();
                if(xmasStart < preamble)
                    list.add(input);
                ++xmasStart; // xmas starts at preamble + 1
                if(xmasStart > preamble) { // == preamble+1
                    // if error exists, console out and end stream
                    if(!errorCheck(list,input)) {
                        System.out.println("Part 1: " +input);
                        // pass removed inputs and current preamble
                        listCopy.addAll(list);
                        part2 = getWeakness(listCopy,input);
                        System.out.println("Part 2: " + part2);
                        break; // no need to access stream further
                    } // else
                    else { // check next preamble set
                        // store input to be removed for part 2
                        listCopy.add(list.get(0));
                        // remove first thing in map
                        list.remove(list.get(0));
                        // and append in current input
                        list.add(input);
                    }
                }
            }
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
