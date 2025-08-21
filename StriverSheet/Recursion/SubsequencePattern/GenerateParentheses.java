package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

class GenerateParentheses {

    public void solve(List<String> ans, String comb, int left, int right) {
//        if (left > right) {
//            return;
//        }

        if (left > 0) {
            solve(ans, comb + "(", left - 1, right);
        }

        if (right > left) {
            solve(ans, comb + ")", left, right - 1);
        }

        if (left == 0 && right == 0) {
            ans.add(comb);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve(ans, "", n, n);
        return ans;
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(outputFile);

            GenerateParentheses solution = new GenerateParentheses();
            int testCase = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                try {
                    int n = Integer.parseInt(line);

                    writer.println("Test Case " + testCase + ":");
                    writer.println("Input: n = " + n);

                    if (n < 0) {
                        writer.println("Error: n must be non-negative");
                        writer.println();
                        testCase++;
                        continue;
                    }

                    if (n > 10) {
                        writer.println("Warning: Large input (n > 10) may generate many combinations");
                    }

                    long startTime = System.currentTimeMillis();
                    List<String> result = solution.generateParenthesis(n);
                    long endTime = System.currentTimeMillis();

                    writer.println("Output:");
                    writer.println("Total valid parentheses combinations: " + result.size());

                    if (result.isEmpty()) {
                        writer.println("Combinations: []");
                    } else {
                        writer.print("Combinations: [");
                        for (int i = 0; i < result.size(); i++) {
                            writer.print("\"" + result.get(i) + "\"");
                            if (i < result.size() - 1) {
                                writer.print(", ");
                            }
                        }
                        writer.println("]");
                    }

                    // Display combinations in a more readable format
                    if (n <= 6 && !result.isEmpty()) {
                        writer.println("Formatted output:");
                        int count = 0;
                        for (String combination : result) {
                            writer.printf("%-" + (2*n + 2) + "s", combination);
                            count++;
                            if (count % 5 == 0) { // 5 combinations per line
                                writer.println();
                            }
                        }
                        if (count % 5 != 0) {
                            writer.println();
                        }
                    }

                    writer.println("Execution time: " + (endTime - startTime) + " ms");

                    // Additional information about the pattern
                    if (n <= 8) {
                        writer.println("Note: Number of combinations follows Catalan number: C(" + n + ") = " + result.size());
                    }

                    writer.println();
                    testCase++;

                } catch (NumberFormatException e) {
                    writer.println("Test Case " + testCase + ":");
                    writer.println("Error: Invalid input '" + line + "'. Please enter a valid integer.");
                    writer.println();
                    testCase++;
                }
            }

            scanner.close();
            writer.close();

            System.out.println("Parentheses generation completed! Check output.txt for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
