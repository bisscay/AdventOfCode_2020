/*
 */
package dockingdata;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author bAe
 */
public class DockingData {
    // Pseudocode
    // make sense of input
    // combine mask to memory location's variable
    // increase sum by combined result
    // ---------------------
    // Generate a list of every combination for an input
    // Place list in map to store latest location assignments
    // sum values of map
    
    //
    public static long memAssignment(String mask, int value) {
        String result = "";
        
        String binaryValue = Integer.toString(value, 2);
        int valueSize = binaryValue.length();
        int maskSize = mask.length();
        // ensure value and mask are of same length
        while(valueSize < maskSize) {
            binaryValue = "0"+binaryValue;
            valueSize = binaryValue.length();
        }
        char maskBit, valueBit;
        for(int i = 0; i < maskSize; ++i) {
            maskBit = mask.charAt(i);
            valueBit = binaryValue.charAt(i);
            if(maskBit == 'X') {
                result += valueBit;
            } else if(maskBit == '1') {
                result += maskBit;
            } else {
                result += '0';
            }
        }
        // convert bit string to decimal - result is larger than an int
        long resultLong = Long.parseLong(result, 2);
        return resultLong;
    }
    
    // value is actually location
    public static List<Long> decoderVersion2(String mask, long value) {
        List<Long> sum = new ArrayList<>();
        // queue to compute all bits
        Queue<String> queue = new LinkedList<>();
        // list of complete bits
        List<String> list = new ArrayList<>();
        
        String binaryValue = Long.toString(value, 2);
        int valueSize = binaryValue.length();
        int maskSize = mask.length();
        // ensure value and mask are of same length
        while(valueSize < maskSize) {
            binaryValue = "0"+binaryValue;
            valueSize = binaryValue.length();
        }
        
        char maskBit, valueBit;
        String result = "";
        for(int i = 0; i < maskSize; ++i) {
            maskBit = mask.charAt(i);
            valueBit = binaryValue.charAt(i);
            if(maskBit == 'X') {
                // make a copy of current string
                String resultCopy = result;
                // append zero to string
                result += '0';
                // append one to string copy
                resultCopy += '1';
                // add string to queue
                queue.add(result);
                // add string copy to queue
                queue.add(resultCopy);
                // plsce each copy in queue
                break;
            } else if(maskBit == '1') {
                result += maskBit;
            } else {
                result += valueBit;
            }
        }
        // while string copies exist in queue,
        while(!queue.isEmpty()) {
            // pick first entry in queue
            String query = queue.poll();
            result = query;
            // store complete bits in an array
            if(result.length() == maskSize) {
                list.add(result);
            }
            int queryStart = query.length();
            // compare mask to value starting at queue entries length
            for(int i = queryStart; i < maskSize ; ++i) {
                maskBit = mask.charAt(i);
                valueBit = binaryValue.charAt(i);
                if (maskBit == 'X') {
                    // make a copy of current string
                    String resultCopy = result;
                    // append zero to string
                    result += '0';
                    // append one to string copy
                    resultCopy += '1';
                    // add string to queue
                    queue.add(result);
                    // add string copy to queue
                    queue.add(resultCopy);
                    // address each copy in queue
                    break;
                } else if (maskBit == '1') {
                    result += maskBit;
                } else {
                    result += valueBit;
                }
            }
        }
        for(String s : list) {
            // convert bit string to decimal - result is larger than an int
            sum.add(Long.parseLong(s, 2));
        }
        
        return sum;
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_14.txt"))) {
            // map of memory location to memory value to track most recent assignment
            HashMap<String, Long> map = new HashMap<>();
            HashMap<Long, Long> map2 = new HashMap<>();
            int locationStart, locationEnd, valueStart;
            String memLocation, memValue;
            long valueUpdate;
            //List<Long> locationList;
            // bit mask to combine values
            String mask = "";
            int maskStart;
            // stream input
            String input;
            Long sum = 0l;
            Long sum2 = 0l;
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                maskStart = input.indexOf("= ")+2; 
                // extract mask until next update
                if(input.matches("^mask.+")) {
                    mask = input.substring(maskStart);
                } else { // extract mem map
                    locationStart = input.indexOf("[")+1;
                    locationEnd = input.indexOf("]");
                    valueStart = maskStart;
                    memLocation = input.substring(locationStart, locationEnd);
                    memValue = input.substring(valueStart);
                    // map is always updated with latest location parameters
                    valueUpdate = memAssignment(mask,Integer.valueOf(memValue));
                    map.put(memLocation,valueUpdate);
                    // version 2
                    List<Long> locationList = decoderVersion2(mask,Long.valueOf(memLocation));
                    for(Long location : locationList) {
                        map2.put(location, Long.valueOf(memValue));
                    }
                }
            }
            for(Long value : map.values()) {
                sum += value;
            }
            for(Long value : map2.values()) {
                sum2 += value;
            }
            System.out.println("Part 1: " +sum);
            System.out.println("Part 2: " +sum2); // 693104161208 -Result Incorrect
            
        } catch(FileNotFoundException e){
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
