package StriverSheet.Recursion.SubsequencePattern;

import java.io.*;
import java.util.*;

class GenerateBinStringsWithoutConsecutive1s {

    public static void generateRecursively(int len, Boolean isOneinPrev, String s, List<String> result) {
        if (len == 0) {
            result.add(s);
            return;
        }

        generateRecursively(len - 1, false, s + "0", result);
        if (!isOneinPrev) {
            generateRecursively(len - 1, true, s + "1", result);
        }
    }

    public static List<String> generateBinaryStrings(int n) {
        List<String> result = new LinkedList<>();
        generateRecursively(n, false, "", result);
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
                    int n = Integer.parseInt(line);

                    writer.println("Test Case " + testCase + ":");
                    writer.println("Input: n = " + n);

                    if (n < 0) {
                        writer.println("Error: n must be non-negative");
                        writer.println();
                        testCase++;
                        continue;
                    }

                    List<String> result = generateBinaryStrings(n);

                    writer.println("Output:");
                    writer.println("Total binary strings without consecutive 1s: " + result.size());
                    writer.print("Binary strings: ");

                    if (result.isEmpty()) {
                        writer.println("[]");
                    } else {
                        writer.print("[");
                        for (int i = 0; i < result.size(); i++) {
                            writer.print("\"" + result.get(i) + "\"");
                            if (i < result.size() - 1) {
                                writer.print(", ");
                            }
                        }
                        writer.println("]");
                    }

                    // Show strings in a more readable format for larger n
                    if (n <= 5 && !result.isEmpty()) {
                        writer.println("Formatted output:");
                        int count = 0;
                        for (String str : result) {
                            writer.print(str + " ");
                            count++;
                            if (count % 10 == 0) { // 10 strings per line
                                writer.println();
                            }
                        }
                        if (count % 10 != 0) {
                            writer.println();
                        }
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

            System.out.println("Binary string generation completed! Check output.txt for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}
