/*
 */
package binaryboarding;

import java.util.*;
import java.io.*;

/**
 *
 * @author bAe
 */
public class BinaryBoarding {

    /**
     * Get seatID for each passenger 
     * @param input
     * @return seatID - int
     */
    public static int getID(String input) {
        int size = input.length();
        int start = 0;
        float end = 127.0f;
        int left = 0;
        float right = 7.0f;
        float span, spanLR;
        char character;

        for (int i = 0; i < size; ++i) {
            character = input.charAt(i);
            switch (character) {
                case 'F':
                    span = (end - start) / 2;
                    end -= Math.ceil(span);
                    break;
                case 'B':
                    span = (end - start) / 2;
                    start += Math.ceil(span);
                    break;
                case 'L':
                    spanLR = (right - left) / 2;
                    right -= Math.ceil(spanLR);
                    break;
                default:
                    spanLR = (right - left) / 2;
                    left += Math.ceil(spanLR);
                    break;
            }
        }
        return start * 8 + left;
    }

    // Part 2
    // back
    // 127 * 8 + 0 = 1016
    // 127  * 8 + 7 = 1023
    // front
    // 0 * 8 + 0 = 0
    // 0 * 8 + 7 = 7
    // span
    // 8 - 1015
    // find missing ID in span

    /**Driver code
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try (Scanner scanner = new Scanner(new File("../PuzzleInput/input_5.txt"))) {
            int highestID = 0;
            int seatID;
            int foundSeat = 0;
            HashSet<Integer> set = new HashSet<>();
            
            while (scanner.hasNext()) {
                String input = scanner.nextLine();
                seatID = getID(input);
                highestID = Math.max(highestID, seatID);
                // Set of all IDs
                set.add(seatID);
            }
            // find empty seat
            for(int i = 54; i < 930; i++) {
                if(!set.contains(i))
                    foundSeat = i;
            }
            System.out.println("Part 1: " +highestID);
            System.out.println("Part 2: " +foundSeat);
        } catch (FileNotFoundException e) {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        }
    }

}
