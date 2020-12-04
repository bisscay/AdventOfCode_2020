/*
 */
package passportprocessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author bAe
 */
public class PassportProcessing {
    // Pseudocode - O(n x m)
    // make sense of entry
    // check if entry contains required fields

    /**
     * Number of credentials with required fields
     *
     * @param input - String
     * @return count - int
     */
    public static int validKeyCount(String input) {
        int valid = 0;
        // split at blank line
        String[] inputArray = input.trim().split("\\n\\s+");
        
        int inputSize = inputArray.length;

        // holds each passengers credential -
        // split at space or newline for each passenger's credentials
        String[] credential;
        // hold each credentials key
        HashSet<String> set = new HashSet<>();

        for (int i = 0; i < inputSize; ++i) {
            // store split values in array
            credential = inputArray[i].split("[\\s+ | \\n]");
            // place values in a hashset - no two credential keys are equal
            // access just keys, values not needed
            int credentialSize = credential.length;
            for (int j = 0; j < credentialSize; ++j) {
                int end = credential[j].indexOf(':');
                set.add(credential[j].substring(0, end));
            }

            // check if each entry in set contains required keys
            if (set.contains("byr") && set.contains("iyr") && set.contains("eyr")
                    && set.contains("hgt") && set.contains("hcl")
                    && set.contains("ecl") && set.contains("pid")) {
                ++valid;
            }

            // empty set for next passenger
            set.clear();
        }
        return valid;
    }

    /**
     * Method too long consider splitting
     *
     * @param input
     * @return
     */
    public static int validValueCount(String input) {
        int valid = 0;
        // split at blank line
        String[] inputArray = input.trim().split("\\n\\s+");
        // split at space or newline for each passenger's credentials
        int inputSize = inputArray.length;

        // hold each passengers credential
        String[] credential;
        // hold each credentials key and value
        HashMap<String, String> map = new HashMap<>();

        for (int i = 0; i < inputSize; ++i) {
            // store split values in an array
            credential = inputArray[i].split("[\\s+ | \\n]");
            // place values in a hashmap
            int credentialSize = credential.length;
            for (int j = 0; j < credentialSize; ++j) {
                String string = credential[j];
                int keyEnd = string.indexOf(':');
                int valueStart = keyEnd + 1;
                map.put(string.substring(0, keyEnd).trim(), string.substring(valueStart).trim());
            }
            // check if each value in entry contains required keys
            if (map.containsKey("byr") && map.containsKey("iyr") && map.containsKey("eyr")
                    && map.containsKey("hgt") && map.containsKey("hcl")
                    && map.containsKey("ecl") && map.containsKey("pid")) {
                // check if key values are valid
                String byr = map.get("byr");
                int byrSize = byr.length();
                int byrInt = Integer.valueOf(byr);

                String iyr = map.get("iyr");
                int iyrSize = iyr.length();
                int iyrInt = Integer.valueOf(iyr);

                String eyr = map.get("eyr");
                int eyrSize = eyr.length();
                int eyrInt = Integer.valueOf(eyr);

                String hgt = map.get("hgt");
                int spanEnd = hgt.length() - 2;
                String hgtFigure = hgt.substring(0, spanEnd);
                int hgtInt = Integer.valueOf(hgtFigure);

                String hcl = map.get("hcl");
                String ecl = map.get("ecl");

                String pid = map.get("pid");
                int pidSize = pid.length();

                if ((byrSize == 4 && byrInt >= 1920 && byrInt <= 2002)
                        && (iyrSize == 4 && iyrInt >= 2010 && iyrInt <= 2020)
                        && (eyrSize == 4 && eyrInt >= 2020 && eyrInt <= 2030)
                        && ((hgt.matches("\\d+cm$") && hgtInt >= 150 && hgtInt <= 193)
                        || (hgt.matches("\\d+in$") && hgtInt >= 59 && hgtInt <= 76))
                        && (hcl.matches("#[\\d|a-f]{6}"))
                        && (ecl.matches("(amb)|(blu)|(brn)|(gry)|(grn)|(hzl)|(oth)"))
                        && (pidSize == 9 && pid.matches("^0*\\d+"))) {
                    ++valid;
                }
            }

            // empty map for next passenger
            map.clear();
        }
        return valid;
    }

    /**
     * Driver Code
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        try (Scanner scanner = new Scanner(new File("../PuzzleInput/input_4.txt"))) {
            // valid counter
            String input = "";
            while (scanner.hasNext()) {
                input += "\n" + scanner.nextLine();
            }
            System.out.println("Part 1: " + PassportProcessing.validKeyCount(input));
            System.out.println("Part 2: " + validValueCount(input));
        } catch (FileNotFoundException e) {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        } catch (Exception e) {
            System.out.println(e.getClass().getName() + " : " + e.getMessage());
        }
    }

}
