package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

public class SubsetSum2 {

    public void solve(int[] nums, int i, List<Integer> subsets, List<List<Integer>> result, boolean skipped) {
        if (i >= nums.length) {
            result.add(new ArrayList<>(subsets));
            return;
        }

        solve(nums, i + 1, subsets, result, true);

        if (skipped && i > 0 && nums[i] == nums[i - 1])
            return;

        subsets.add(nums[i]);
        solve(nums, i + 1, subsets, result, false);
        subsets.remove(Integer.valueOf(nums[i]));
    }

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> subsets = new LinkedList<>();
        Arrays.sort(nums);

        solve(nums, 0, subsets, result, false);

        return result;
    }

    public static void main(String[] args) {
        SubsetSum2 solution = new SubsetSum2();

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

                int n = Integer.parseInt(line);

                // Check if there's a next line for array elements
                if(!scanner.hasNextLine()) {
                    break;
                }

                String[] elements = scanner.nextLine().trim().split("\\s+");
                int[] arr = new int[n];

                for(int i = 0; i < n && i < elements.length; i++) {
                    arr[i] = Integer.parseInt(elements[i]);
                }

                // Get all unique subsets
                List<List<Integer>> result = solution.subsetsWithDup(arr);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("Input Array: " + Arrays.toString(arr));
                writer.println("Total unique subsets: " + result.size());
                writer.println("All unique subsets:");

                // Print all subsets in a readable format
                writer.print("[");
                for(int i = 0; i < result.size(); i++) {
                    writer.print(result.get(i));
                    if(i < result.size() - 1) {
                        writer.print(", ");
                    }
                }
                writer.println("]");

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
