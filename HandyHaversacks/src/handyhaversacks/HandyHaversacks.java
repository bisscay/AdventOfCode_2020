/*
 */
package handyhaversacks;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * Lol, Damn 
 * @author bAe
 */
// for each set, check if the value string contains shiny gold
// if it does, add that key to bag set
// for every bag in set, check if map value string contains a bag,
// if it does, add that bag to set
// ??? stopping condition ???? Use trees
// ---------------------------------
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
    
    public static Node insert(Node root, Node child) {
        for(Node node : root.next) { 
            if(child.color.equals(node.color)) {
                node.next = child.next;
            }
        }
        return root;
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
    //
    private static Node getGoldDad(Node dad) {
        HashSet<Node> set = new HashSet<>();
        if(dad.next.contains(new Node(0, "shiny gold"))) {
            set.add(dad);
            return dad;
        }
        else if (dad.next != null) {
            // return the dad's child
            //return dad.next;
//            // find a forest where it's child is a root
//            for(Node forest : forestList) {
//                if(dad.next.contains(forest))
//                    set.add(getGoldDad(forest));
//            }

        }
        return null;    
    } 
        
    /**
     * Option 2
     * @param forestList
     * @param map
     * @return 
     */
    private static int goldBagContainerCount(List<Node> forestList,HashMap<String, Integer> map) {
        // make a tree
        HashSet<Node> tree = new HashSet<>();
        String query = "";
        // pick the lowest map value(s) i.e value == 0
        for(Map.Entry entry : map.entrySet()) {
            if((Integer)entry.getValue() == 0) query = (String)entry.getKey();
            for(Node node : forestList) {
                if(query == null ? (node.color) == null : query.equals(node.color)) tree.add(node);
            }
        }
        // extend the forest with low map value by setting it's children to corresponding forests
        for(Node root : tree) {
            for(Node child : forestList) {
                if(!root.equals(child)) { // exclude comparism with root instance in map
                    root = Node.insert(root,child);
                }
            }
        }
        return 0;
    }
    
    /**
     * Option 1
     * @param forestList
     * @return 
     */
    private static int getParent(List<Node> forestList,HashMap<String, Integer> map) {
        // hold the roots
        HashSet<Node> tree = new HashSet<>();
        String query = "";
        // pick the lowest map value(s) i.e value == 0
        for(Map.Entry entry : map.entrySet()) {
            if((Integer)entry.getValue() == 0) query = (String)entry.getKey();
            for(Node node : forestList) {
                if(query == null ? (node.color) == null : query.equals(node.color)) tree.add(node);
            }
        }
        
        HashSet<String> set = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();
        HashSet<String> setSum = new HashSet<>();
        // for each set, check if the value string contains shiny gold
        for (Node forestNode : forestList) {
            for (Node node : forestNode.next) {
                // if it does, add that key to bag set
                if (node != null && node.color.equals("shiny gold")) {
                    set.add(forestNode.color);
                }
            }
        }
        // find every node that has shiny gold as a child
        // find every node that has that parent as a child
        // continue until the parent is a root
        
        // for every bag in set, check if map value string contains a bag,
        //for (Node root : tree) {
            // if set does not contain  root
            //while (!setSum.contains(root.color)) {
                for (Node forestNode : forestList) {
                    for (Node node : forestNode.next) { 
                        if(node != null && set.contains(node.color)){
                                set2.add(forestNode.color);
                        }
                    }
                }
                for(String s : set) {
                    setSum.add(s);
                }
                for(String s : set2) {
                    setSum.add(s);
                }
            //}
        //}
        
//        for (Node root : tree) {
//            // if set does not contain  root
//            while (!setSum.contains(root.color)) {
//                for (Node forestNode : forestList) {
//                    for (Node node : forestNode.next) {
//                        if (node != null && setSum.contains(node.color)) {
//                            setSum.add(forestNode.color);
//                        }
//                    }
//                }
//            }
//        }
//        System.out.println(set);
//        System.out.println(set2);
        for (Node root : tree)
        System.out.println(root.color);
        return setSum.size();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_7.txt"))) {
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
                        countData = -1; // leaf node marker
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
            }
            
            // debug
            System.out.println(getParent(forestList,map));
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
