package StriverSheet.Recursion.Hard;

import java.io.*;
import java.util.*;

public class PalindromePartitioning {

    public static boolean isPalindrome(String str) {
        int left = 0, right = str.length() - 1;
        while (left < right) {
            if (str.charAt(left) != str.charAt(right)) {
                return false;
            }
            left++;
            right--;
        }
        return true;
    }

    public void solve(int i, String s, List<String> substr, List<List<String>> result){
        if(i >= s.length()){
            result.add(new ArrayList<>(substr));
            return;
        }

        for(int x = i; x < s.length(); x++ ){
            String temp = s.substring(i, x+1);
            if(isPalindrome(temp)){
                substr.add(temp);
                solve(x+1, s, substr, result);
                substr.remove(substr.size() - 1);
            }
        }
    }

    public List<List<String>> partition(String s) {
        List<List<String>> result = new LinkedList<>();
        List<String> substr = new LinkedList<>();

        solve(0, s, substr, result);

        return result;
    }

    public static void main(String[] args) {
        PalindromePartitioning solution = new PalindromePartitioning();

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

                String s = line;

                // Get all palindrome partitions
                List<List<String>> result = solution.partition(s);

                // Write output for this test case
                writer.println("Test Case " + testCase + ":");
                writer.println("Input string: \"" + s + "\"");
                writer.println("String length: " + s.length());

                if(result.isEmpty()) {
                    writer.println("No valid partitions found.");
                } else {
                    writer.println("Total palindrome partitions: " + result.size());
                    writer.println("All palindrome partitions:");

                    // Print all partitions in a readable format
                    writer.print("[");
                    for(int i = 0; i < result.size(); i++) {
                        writer.print("[");
                        for(int j = 0; j < result.get(i).size(); j++) {
                            writer.print("\"" + result.get(i).get(j) + "\"");
                            if(j < result.get(i).size() - 1) {
                                writer.print(", ");
                            }
                        }
                        writer.print("]");
                        if(i < result.size() - 1) {
                            writer.print(", ");
                        }
                    }
                    writer.println("]");

                    // Alternative format - each partition on new line
                    writer.println("\nPartitions (one per line):");
                    for(int i = 0; i < result.size(); i++) {
                        writer.print((i + 1) + ". [");
                        for(int j = 0; j < result.get(i).size(); j++) {
                            writer.print("\"" + result.get(i).get(j) + "\"");
                            if(j < result.get(i).size() - 1) {
                                writer.print(", ");
                            }
                        }
                        writer.println("]");
                    }

                    // Show verification - check each partition
                    writer.println("\nVerification (palindrome check):");
                    for(int i = 0; i < result.size(); i++) {
                        writer.print("Partition " + (i + 1) + ": ");
                        List<String> partition = result.get(i);
                        boolean allPalindromes = true;
                        writer.print("[");
                        for(int j = 0; j < partition.size(); j++) {
                            String part = partition.get(j);
                            boolean isPalin = isPalindrome(part);
                            writer.print(part + "(" + (isPalin ? "✓" : "✗") + ")");
                            if(j < partition.size() - 1) {
                                writer.print(", ");
                            }
                            if(!isPalin) allPalindromes = false;
                        }
                        writer.print("] -> ");

                        // Check if partition recreates original string
                        String reconstructed = String.join("", partition);
                        boolean validPartition = reconstructed.equals(s) && allPalindromes;
                        writer.println(validPartition ? "Valid ✓" : "Invalid ✗");
                    }

                    // Show some interesting statistics
                    writer.println("\nStatistics:");
                    int minParts = result.isEmpty() ? 0 : result.get(0).size();
                    int maxParts = 0;
                    for(List<String> partition : result) {
                        minParts = Math.min(minParts, partition.size());
                        maxParts = Math.max(maxParts, partition.size());
                    }
                    writer.println("Minimum parts in any partition: " + minParts);
                    writer.println("Maximum parts in any partition: " + maxParts);

                    // Count single character partitions
                    long singleCharPartitions = result.stream()
                            .filter(partition -> partition.size() == s.length())
                            .count();
                    if(singleCharPartitions > 0) {
                        writer.println("Single character partition exists: Yes");
                    }
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
