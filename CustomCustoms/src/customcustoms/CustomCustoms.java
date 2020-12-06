/*
 */
package customcustoms;

import java.io.FileNotFoundException;
import java.io.File;
import java.util.*;

/**
 *
 * @author bAe
 */
public class CustomCustoms {
    // Pseudocode
    // Part 1; Option 1; O(n x m x k)
    // -------------------------------
    // Part 1; Option 2; O(n)
    // apppend input lines to a string until a blank line is found
    // place each character in a hashset for uniqueness
    // -------------------------------
    // Part 2; O(n x m)
    

    /**
     * Total Unique 'Yes' Per Group
     * @param input
     * @return 
     */
    public static int getYesTotal(String input) {
        // break into groups by spliting at blank space
        String[] query = input.split("\\n\\s+");
        String[] individual;
        ArrayList<List<String>> temp = new ArrayList<>();
        HashSet<String> set = new HashSet<>();
        int totalCount = 0;
        
        for(String group : query) {
            // break groups into individuals
            individual = group.split("\\n");
            // place an individuals answer in a hash set
            int individualSize = individual.length;
            for(int i = 0; i < individualSize; ++i) {
            // place each character in a hashset for uniqueness
            temp.add(Arrays.asList(individual[i].split("")));
            
            }
            // place each character in a hashset for uniqueness
            for(List<String> s : temp) {
                for(String x : s) {
                    set.add(x);
                }
            }
            temp.clear();
            // increament total count by set size
            totalCount += set.size();
            // clear set for next group
            set.clear();
        }
        
        return totalCount;
    }
    
    /**
     * Part 1; Option 2; O(n); Result overshot by 2; Debug Needed
     * @param group - String
     * @return count of unique characters - int
     */    
    public static int yesCount(String group) {
        // place each character in group into hashset for uniqueness
        HashSet<Character> set = new HashSet<>();
        int groupSize = group.length();
        for(int i = 0; i < groupSize; ++i) {
            set.add(group.charAt(i));
        }
        // return size of set 
        return set.size();
    }
    
    /**
     * Common 'Yes' per Group
     * @param input - String
     * @return sum of common yes per group - int
     */
    public static int getCommonSum(String input) {
        int groupSize, commonSum = 0;
        // assign newline for first passenger in a group
        int newlineCount = 1;
        char letter;
        // break input into groups
        String[] query = input.split("\\n\\s+");
        HashMap<Character,Integer> map = new HashMap<>();
        
        // access each group
        for(String group : query){
            // create a map for characters in a group
            groupSize = group.length();
            for(int i = 0; i < groupSize; ++i) {
                letter = group.charAt(i);
                if(letter != '\n') {
                    // if character exists in map, increament key's value
                    if(map.containsKey(letter))
                        map.put(letter,map.get(letter) + 1);
                    // else place character in map with a value of one
                    else
                        map.put(letter,1);
                } else
                    ++newlineCount;
            }
            // check for count values that equal newlines
            for(Integer value : map.values()) {
                if(value == newlineCount) ++commonSum;
            }
            // empty map for next group
            map.clear();
            // reset newline count for next group
            newlineCount = 1;
        }
        return commonSum;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_6.txt"))) {
            int yesTotal = 0;
            int option2 = 0;
            String groupList = "";
            //// Part1 Option 2 Containers ////
            String query = "";
            String input = "";
            //////////////////////////////////
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                // Part 1, Option 1
                groupList += "\n" + input;
                // Part 1, Option 2
                // apppend input lines to a string until a blank line is found
                query += input;
                if(input.matches("s*")) {
                    // place each group in a hashset for uniqueness
                    option2 += yesCount(query);
                    // clear query for next group
                    query="";
                }
            }
            // evaluate last value in stream
            option2 += yesCount(query);
            
            yesTotal = getYesTotal(groupList.trim());
            
            System.out.println("Part 1: " +yesTotal);
            System.out.println("Part 1 II: " +option2);
            System.out.println("Part 2: " +getCommonSum(groupList.trim()));
        } catch(FileNotFoundException e) {
            // uncaught exceptions
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
