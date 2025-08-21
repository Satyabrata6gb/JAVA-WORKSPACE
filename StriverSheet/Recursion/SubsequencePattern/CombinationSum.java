package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

public class CombinationSum {

    public static void solve(int[] nums, int i, int target, List<Integer> sum, List<List<Integer>> result) {
        if (target == 0) {
            result.add(new ArrayList<>(sum));
            return;
        } else if (target < 0) return;
        if (i >= nums.length) return;

        sum.add(nums[i]);
        solve(nums, i, target - nums[i], sum, result);
        sum.remove(Integer.valueOf(nums[i]));

        solve(nums, i + 1, target, sum, result);
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> sum = new LinkedList<>();

        solve(candidates, 0, target, sum, result);

        return result;
    }

    public static void main(String[] args) {

        try {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");

            Scanner fileScanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(new FileWriter(outputFile));

            int testCase = 1;

            while (fileScanner.hasNextLine()) {
                // Read candidates line
                String candidateLine = fileScanner.nextLine().trim();
                if (candidateLine.isEmpty()) continue;

                // Read target line
                if (!fileScanner.hasNextLine()) break;
                String targetLine = fileScanner.nextLine().trim();
                if (targetLine.isEmpty()) continue;

                // Parse candidates
                String[] candidateTokens = candidateLine.split("\\s+");
                int[] candidates = new int[candidateTokens.length];
                for (int i = 0; i < candidateTokens.length; i++) {
                    candidates[i] = Integer.parseInt(candidateTokens[i]);
                }

                // Parse target
                int target = Integer.parseInt(targetLine);

                // Solve problem
                List<List<Integer>> result = combinationSum(candidates, target);

                // Write output
                writer.println("Test Case " + testCase + ":");
                writer.println("Input: candidates = " + Arrays.toString(candidates) + ", target = " + target);
                writer.print("Output: [");
                for (int i = 0; i < result.size(); i++) {
                    writer.print(result.get(i));
                    if (i < result.size() - 1) writer.print(",");
                }
                writer.println("]");
                writer.println("Number of combinations: " + result.size());
                writer.println();

                System.out.println("Processed test case " + testCase);
                testCase++;
            }

            fileScanner.close();
            writer.close();

            System.out.println("Processing complete! Results written to " + outputFile);

        } catch (FileNotFoundException e) {
            System.out.println("Input file not found: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("Error writing to output file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error parsing numbers: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }


    }

}
