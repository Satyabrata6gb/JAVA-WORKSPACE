package StriverSheet.Recursion.SubsequencePattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class CheckIfSubSeqExistWithSumK {

    public static boolean solve(int[] nums, int i, int sum, int target){
        if(sum == target){
            return true;
        }else if(sum > target) return false;
        if(i >= nums.length) return false;

        if(solve(nums, i + 1, sum + nums[i], target)) return true;
        return solve(nums, i + 1, sum, target);
    }

    public static boolean checkSubsequenceSum(int[] nums, int k) {
        return solve(nums, 0, 0, k);
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(outputFile);

            int testCase = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                try {
                    writer.println("Test Case " + testCase + ":");
                    writer.println("Input: " + line);

                    // Parse the input array
                    int[] nums;
                    if (line.equals("[]") || line.isEmpty()) {
                        nums = new int[0];
                    } else {
                        String[] elements = line.split("\\s+");
                        nums = new int[elements.length];
                        for (int i = 0; i < elements.length; i++) {
                            nums[i] = Integer.parseInt(elements[i]);
                        }
                    }

                    int target = scanner.nextInt();
                    writer.println("Target: " + target);

                    writer.print("Array: [");
                    for (int i = 0; i < nums.length; i++) {
                        writer.print(nums[i]);
                        if (i < nums.length - 1) {
                            writer.print(", ");
                        }
                    }
                    writer.println("]");

                    long startTime = System.currentTimeMillis();
                    boolean result = checkSubsequenceSum(nums, target);
                    long endTime = System.currentTimeMillis();

                    writer.println("Output:");
                    if(result){
                        writer.println("Sub Sequence Exists!!!");
                    }else{
                        writer.println("Sub Sequence Does Not Exist!!!");
                    }

                    writer.println("Execution time: " + (endTime - startTime) + " ms");
                    writer.println();

                    testCase++;

                } catch (NumberFormatException e) {
                    writer.println("Test Case " + testCase + ":");
                    writer.println("Error: Invalid input '" + line + "'. Please enter space-separated integers.");
                    writer.println();
                    testCase++;
                } catch (Exception e) {
                    writer.println("Test Case " + testCase + ":");
                    writer.println("Error processing input: " + e.getMessage());
                    writer.println();
                    testCase++;
                }
            }

            scanner.close();
            writer.close();

            System.out.println("Subset generation completed! Check output.txt for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
