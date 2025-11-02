package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

public class LetterCombinations {

    public List<String> letterCombinations(String digits) {

        LinkedList<String> ans = new LinkedList<String>();
        if(digits.isEmpty()) return ans;

        String[] map = new String[] {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};

        ans.add("");

        for(int i = 0 ; i < digits.length(); i++){
            int x = Character.getNumericValue(digits.charAt(i));
            while(ans.get(0).length() == i){
                String temp = ans.remove(0);
                for(char a : map[x].toCharArray()){
                    ans.add(temp+a);
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        LetterCombinations solution = new LetterCombinations();

        try {
            // Read input from file
            Scanner scanner = new Scanner(new File("input.txt"));

            // Write output to file
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            int testCase = 1;

            // Process multiple test cases
            while(scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if(line.isEmpty()) {
                    continue;
                }

                String digits = line;

                // Get all letter combinations
                List<String> result = solution.letterCombinations(digits);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("Input digits: \"" + digits + "\"");

                if(result.isEmpty()) {
                    writer.println("No combinations possible (empty input)");
                } else {
                    writer.println("Total combinations: " + result.size());
                    writer.println("All combinations:");

                    // Print all combinations in a readable format
                    writer.print("[");
                    for(int i = 0; i < result.size(); i++) {
                        writer.print("\"" + result.get(i) + "\"");
                        if(i < result.size() - 1) {
                            writer.print(", ");
                        }
                    }
                    writer.println("]");

//                    // Alternative format - grouped by length for readability
//                    if(result.size() <= 50) { // Only for smaller outputs
//                        writer.println("\nCombinations (grouped format):");
//                        int itemsPerLine = Math.min(10, result.size());
//                        for(int i = 0; i < result.size(); i++) {
//                            if(i % itemsPerLine == 0 && i > 0) {
//                                writer.println();
//                            }
//                            writer.print(result.get(i));
//                            if(i < result.size() - 1) {
//                                writer.print(" ");
//                            }
//                        }
//                        writer.println();
//                    }

//                    // Show mapping for reference
//                    writer.println("\nDigit to letters mapping used:");
//                    String[] map = {"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
//                    for(char c : digits.toCharArray()) {
//                        if(Character.isDigit(c)) {
//                            int digit = Character.getNumericValue(c);
//                            if(digit >= 2 && digit <= 9) {
//                                writer.println(digit + " -> " + map[digit]);
//                            }
//                        }
//                    }
                }

                writer.println(); // Empty line for separation
                testCase++;
            }

            scanner.close();
            writer.close();

            System.out.println("Program executed successfully!");
            System.out.println("Processed " + (testCase - 1) + " test case(s).");
            System.out.println("Check output.txt for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
        } catch (IOException e) {
            System.err.println("Error writing to output.txt!");
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
