package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

public class CombinationSum2 {
    public static void solve(int[] nums, int i, int target,List<Integer> sum, List<List<Integer>> result){
        if(target == 0){
            result.add(new ArrayList<>(sum));
            return;
        }

        for(int ind = i; ind < nums.length; ind++){
            if(ind > i && nums[ind] == nums[ind-1]) continue;
            if(nums[ind] > target) break;

            sum.add(nums[ind]);
            solve(nums, ind + 1, target - nums[ind], sum, result);
            sum.remove(Integer.valueOf(nums[ind]));
        }

    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new LinkedList<>();
        List<Integer> sum = new LinkedList<>();
        Arrays.sort(candidates);

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
                List<List<Integer>> result = combinationSum2(candidates, target);

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
