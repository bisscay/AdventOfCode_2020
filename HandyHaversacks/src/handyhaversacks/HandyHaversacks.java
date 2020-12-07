/*
 */
package handyhaversacks;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

/**
 *
 * @author prof.bissallahekele
 */
public class HandyHaversacks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try(Scanner scanner = new Scanner(new File("../PuzzleInput/input_7.txt"))) {
            String input;
            while(scanner.hasNext()) {
                input = scanner.nextLine();
                System.out.println(input);
            }
        } catch(FileNotFoundException e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        } catch(Exception e) {
            System.out.println(e.getClass().getName() +" : " +e.getMessage());
        }
    }
    
}
