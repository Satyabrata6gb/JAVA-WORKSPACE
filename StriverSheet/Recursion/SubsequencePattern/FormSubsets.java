package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

class FormSubsets {

    public static void formSubsets(List<List<Integer>> result, int index, int[] nums, List<Integer> subset) {
        if (index >= nums.length) {
            result.add(new ArrayList<>(subset)); // Create a copy of the subset
            return;
        }

        // Exclude current element
        formSubsets(result, index + 1, nums, subset);

        // Include current element
        subset.add(nums[index]);
        formSubsets(result, index + 1, nums, subset);
        subset.remove(Integer.valueOf(nums[index]));
    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        formSubsets(result, 0, nums, new LinkedList<Integer>());
        return result;
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

                    writer.print("Array: [");
                    for (int i = 0; i < nums.length; i++) {
                        writer.print(nums[i]);
                        if (i < nums.length - 1) {
                            writer.print(", ");
                        }
                    }
                    writer.println("]");

                    long startTime = System.currentTimeMillis();
                    List<List<Integer>> result = subsets(nums);
                    long endTime = System.currentTimeMillis();

                    writer.println("Output:");
                    writer.println("Total subsets: " + result.size());
                    writer.println("All subsets:");

                    // Display subsets in a formatted way
                    writer.print("[");
                    for (int i = 0; i < result.size(); i++) {
                        writer.print("[");
                        List<Integer> subset = result.get(i);
                        for (int j = 0; j < subset.size(); j++) {
                            writer.print(subset.get(j));
                            if (j < subset.size() - 1) {
                                writer.print(", ");
                            }
                        }
                        writer.print("]");
                        if (i < result.size() - 1) {
                            writer.print(", ");
                        }
                    }
                    writer.println("]");

//                    // Show subsets in a more readable format for smaller arrays
//                    if (nums.length <= 5) {
//                        writer.println("Readable format:");
//                        int count = 0;
//                        for (List<Integer> subset : result) {
//                            writer.print("{");
//                            if (subset.isEmpty()) {
//                                writer.print("∅"); // Empty set symbol
//                            } else {
//                                for (int j = 0; j < subset.size(); j++) {
//                                    writer.print(subset.get(j));
//                                    if (j < subset.size() - 1) {
//                                        writer.print(", ");
//                                    }
//                                }
//                            }
//                            writer.print("} ");
//                            count++;
//                            if (count % 6 == 0) { // 6 subsets per line
//                                writer.println();
//                            }
//                        }
//                        if (count % 6 != 0) {
//                            writer.println();
//                        }
//                    }

                    writer.println("Execution time: " + (endTime - startTime) + " ms");
                    writer.println("Note: Total subsets = 2^n = 2^" + nums.length + " = " + result.size());
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