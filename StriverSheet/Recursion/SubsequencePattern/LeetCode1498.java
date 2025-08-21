package StriverSheet.Recursion.SubsequencePattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;

public class LeetCode1498 {
    static int mod = 1000000007;

    public static void solve(int[] nums, TreeSet<Integer> numbers, int i, int target, AtomicInteger count){
        if(i >= nums.length){
            if(!numbers.isEmpty() && (numbers.first() + numbers.last()) <= target){
                count.set((count.get() + 1) % mod);
            }
            return;
        }

        solve(nums, numbers, i + 1, target, count);

        if(target > nums[i]){
            numbers.add(nums[i]);
            solve(nums, numbers, i + 1, target, count);
            numbers.remove(nums[i]);
        }
    }
    public static int numSubseq(int[] nums, int target) {
        TreeSet<Integer> numbers = new TreeSet<>();

        AtomicInteger count = new AtomicInteger(0);

        solve(nums, numbers, 0, target, count);

        return count.get();
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

//                    writer.print("Array: [");
//                    for (int i = 0; i < nums.length; i++) {
//                        writer.print(nums[i]);
//                        if (i < nums.length - 1) {
//                            writer.print(", ");
//                        }
//                    }
//                    writer.println("]");

                    long startTime = System.currentTimeMillis();
                    int result = numSubseq(nums, target);
                    long endTime = System.currentTimeMillis();

                    writer.println("Output:");
                    writer.println("Total subsets: " + result);

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
