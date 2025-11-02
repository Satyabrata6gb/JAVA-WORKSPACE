package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

public class SubsetSum {

    public void solve(int[] nums, int index, int sum, ArrayList<Integer> result){
        if(index >= nums.length){
            result.add(sum);
            return;
        }

        solve(nums, index + 1, sum, result);
        solve(nums, index + 1, sum + nums[index], result);
    }

    public ArrayList<Integer> subsetSums(int[] arr) {
        ArrayList<Integer> result = new ArrayList<>();
        solve(arr, 0, 0, result);
        return result;
    }

    public static void main(String[] args) {
        SubsetSum solution = new SubsetSum();

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

                // Get all subset sums
                ArrayList<Integer> result = solution.subsetSums(arr);

                // Sort the result for better readability (optional)
                Collections.sort(result);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("Array: " + Arrays.toString(arr));
                writer.println("Total subset sums: " + result.size());
                writer.println("All subset sums:");

                // Print all sums in a readable format
                for(int i = 0; i < result.size(); i++) {
                    writer.print(result.get(i));
                    if(i < result.size() - 1) {
                        writer.print(" ");
                    }
                }
                writer.println();
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
