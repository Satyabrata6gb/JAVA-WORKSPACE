package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

public class CombinationSum3 {

    public final int[] nums = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public void solve(int i, int k, int target, List<Integer> sum, List<List<Integer>> result) {
        if (target == 0 && k == 0) {
            result.add(new ArrayList<>(sum));
            return;
        } else if (target < 0 || k < 0) return;
        if (i >= 9) return;

        sum.add(nums[i]);
        solve(i + 1, k - 1, target - nums[i], sum, result);
        sum.remove(Integer.valueOf(nums[i]));

        solve(i + 1, k, target, sum, result);
    }

    public List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> sum = new LinkedList<>();

        solve(0, k, n, sum, result);

        return result;
    }

    public static void main(String[] args) {
        CombinationSum3 solution = new CombinationSum3();

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

                String[] parts = line.split("\\s+");

                // Need exactly 2 values: k and n
                if(parts.length < 2) {
                    continue;
                }

                int k = Integer.parseInt(parts[0]);
                int n = Integer.parseInt(parts[1]);

                // Get all valid combinations
                List<List<Integer>> result = solution.combinationSum3(k, n);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("k = " + k + ", n = " + n);
//                writer.println("Find " + k + " unique numbers that sum to " + n);
                writer.println("Total valid combinations: " + result.size());

                if(result.isEmpty()) {
                    writer.println("No valid combinations found.");
                } else {
                    writer.println("All valid combinations:");

                    // Print all combinations in a readable format
                    writer.print("[");
                    for(int i = 0; i < result.size(); i++) {
                        writer.print(result.get(i));
                        if(i < result.size() - 1) {
                            writer.print(", ");
                        }
                    }
                    writer.println("]");

                    // Alternative format - each combination on new line
//                    writer.println("\nCombinations (one per line):");
//                    for(int i = 0; i < result.size(); i++) {
//                        writer.println((i + 1) + ". " + result.get(i));
//                    }
//
//                    // Verification
//                    writer.println("\nVerification:");
//                    for(int i = 0; i < result.size(); i++) {
//                        List<Integer> combo = result.get(i);
//                        int sum = combo.stream().mapToInt(Integer::intValue).sum();
//                        writer.println("Combination " + (i + 1) + ": " + combo +
//                                " -> Size: " + combo.size() + ", Sum: " + sum);
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
