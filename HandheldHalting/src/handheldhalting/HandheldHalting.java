/*
 */
package handheldhalting;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author bAe
 */
// Pseudocode - O(n)
// make bootcode instruction class
// each code line instance will have a name and weight
// a static variable will hold the accumulator
// a set will keep track of the instruction instances
// once an instruction is revisited, 
// the accumulator is displayed
// -------------------------------------------------
// Part 2 - O
// continue until the set size equals the list size
// when u see a nop, make it a jump and vice versa
// compute and see if set size equals list size
// if not, swap the next nop or jump encountered
class BootCode {
    private String instruction;
    private int action;
    private static int accumulator;

    BootCode(String instruction, int action) {
        this.instruction = instruction;
        this.action = action;
    }
    
    // Getters & Setters - Notice setAccumulator increament
    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public static int getAccumulator() {
        return accumulator;
    }

    public static void setAccumulator(int accumulator) {
        BootCode.accumulator += accumulator;
    }

    @Override
    // following the equals contract - Dead code left for future reference
    public boolean equals(Object obj) {
        // check if its thesame instance,
        // save time if pointing to same heap location,
        // and if equals comparism is costly in constly
        // if(this == obj) return true;
        
        return(this == obj); // only object's of same instance are equal
        
//        // ensure object is not null,
//        // and is an instances of the same class
//        // Note; instanceof would print ture for subclasses, - unless the class is final (no subclasses exist for final class)
//        // While; getClass ensures same class comparism
//        if(obj == null || this.getClass() != obj.getClass())
//            return false;
//        
//        // make object BootCode at this point
//        BootCode other = (BootCode)obj;
//        // return true if properties are equal
//        return(this.action == other.action && this.instruction.equals(other.instruction));
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.instruction);
        hash = 53 * hash + this.action;
        return hash;
    }
}
public class HandheldHalting {
    //
    private static int getAccumulation(ArrayList<BootCode> codeList) {
        HashSet<BootCode> set = new HashSet<>();
        // start from first operation
        int step = 0;
        int listSize = codeList.size();
        BootCode line = codeList.get(step);
        // move until end of list or bootcode is encountered again
        while(!set.contains(line) && step < listSize) {
            set.add(line);
            if (line.getInstruction().equals("acc")) {
                BootCode.setAccumulator(line.getAction());
                // move to next line
                line = codeList.get(step+1);
            } else if (line.getInstruction().equals("jmp")) {
                // jump to new location
                line = codeList.get(step+line.getAction());
            } else {
                // no operation, move to next line
                line = codeList.get(step+1);
            }
            // use current list location as reference point
            step = codeList.indexOf(line);
        }
        return BootCode.getAccumulator();
    }
    
    /**Driver code
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_8.txt"))) {
            String instruction;
            int action, instructionEnd, accumulation;
            ArrayList<BootCode> codeList = new ArrayList<>();
            BootCode line;
            while(scanner.hasNext()) {
                String input = scanner.nextLine();
                // make sense of input
                instructionEnd = input.indexOf(" ");
                instruction = input.substring(0, instructionEnd);
                action = Integer.valueOf(input.substring(instructionEnd+1));
                // boot code instance
                line = new BootCode(instruction, action);
                // add object to list
                codeList.add(line);
            } 

            accumulation = getAccumulation(codeList);
            System.out.println("Part 1: " +accumulation);
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
