/*
 */
package handyhaversacks;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author bAe
 */
// build single depth forests for each input line
// add forests to a list
// keep map of forest color -
// when a color is a key, map value will be increamented by 0
// when a color is in the value array, map value will be increased by 1
// pick the lowest map value(s) i.e value == 0
// extend the forest with low map value by setting it's children to corresponding forests
// on each next modification, increament child counts based on corresponding forest
// make a set of every parent of shiny gold bags
// return size of set
class Node{
    int count;
    String color;
    ArrayList<Node> next;
    Node(int count, String color) {
        this.count = count;
        this.color = color;
        next = null;
    }
    Node(int count, String color, Node[] next) {
        this.count = count;
        this.color = color;
        this.next = new ArrayList<>(Arrays.asList(next));
    }

    @Override
    public String toString() {
        if(next != null)
            return "Parent: " +color +" " +count +"\nChildren: " +next;
        else
            return "Parent: " +color +" " +count +"\nNo Children";
    }
}
public class HandyHaversacks {
    //// for each set, check is the value string contains shiny gold
        // if it does, add that key to bag set
        // for every bag in set, check if map value string contains a bag,
        // if it does, add that bag to set
        // ??? stopping condition ???? Use trees
    public static int goldBagContainerCount(HashMap<String, String> map) {
        
        return 0;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/test.txt"))) {
            String input, key, value, bag, colorData;
            int keyEnd, valueStart, valueEnd, childCount, countEnd, colorEnd;
            // child param
            int countData = 0;
            Node child;
            // forest declaration
            Node forest;
            List<Node> forestList = new ArrayList<>();
            String[] valueArray;
            // keep map of forest color -
            HashMap<String, Integer> map = new HashMap<>();
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                // get first occurance of bags,
                // to act as key for other bags in map
                keyEnd = input.indexOf("bags");
                // store key
                key = input.substring(0, keyEnd).trim();
                // get first number occurance to serve as map value
                valueStart = input.indexOf("contain") + "contian ".length();
                valueEnd = input.length() - 1; // ignore fullstop
                // store value
                value = input.substring(valueStart, valueEnd);
                
                // when a color is a key, map value will be increamented by 0
                if(!map.containsKey(key)) map.put(key, 0);
                
                // split values at comma
                valueArray = value.split(", ");
                // derive children data
                childCount = valueArray.length;
                Node[] children = new Node[childCount];
                for(int i = 0; i < childCount; ++i) {
                    bag = valueArray[i];
                    // account for leaf nodes
                    if(bag.charAt(0) != 'n') {
                        countEnd = bag.indexOf(" ");
                        countData = Integer.valueOf(bag.substring(0, countEnd));
                        colorEnd = bag.indexOf(" bag");
                        colorData = bag.substring(countEnd+1, colorEnd);
                        // create child node
                        child = new Node(countData,colorData);
                        children[i] = child;
                        // when a color is in the value array, map value will be increased by 1
                        if(!map.containsKey(colorData)) map.put(colorData, 1);
                        else map.put(colorData,map.get(colorData) + 1);
                    } else {
                        countData = 0; // could use colorData
                    }
                }
                // create a forest
                if(countData != 0) {
                    forest = new Node(0,key,children);
                } else { // a leaf
                    forest = new Node(0,key);
                }
                // add forests to list
                forestList.add(forest);
                
//                //debug
                System.out.println(key);
//                //for(String s : valueArray) System.out.println(s);
//                System.out.println("------");
            }
            // debug
            System.out.println(map);
            //System.out.println(forestList);
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
