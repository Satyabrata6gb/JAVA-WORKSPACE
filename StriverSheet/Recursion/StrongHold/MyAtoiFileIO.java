package StriverSheet.Recursion.StrongHold;

import java.io.*;
import java.util.*;

class Solution {

    public int atoi(String s, int i, int sign, long result){
        if(sign*result > Integer.MAX_VALUE){
            return Integer.MAX_VALUE;
        }if(sign*result < Integer.MIN_VALUE){
            return Integer.MIN_VALUE;
        }if(i >= s.length() || s.charAt(i) < '0' || s.charAt(i) > '9'){
            return (int)(sign * result);
        }

        result = atoi(s, i+1, sign, (result*10 + (s.charAt(i) - '0')));

        return (int)result;
    }

    public int myAtoi(String s) {
        int i = 0;

        int sign = 1;

        int n = s.length();

        while(i < n && s.charAt(i) == ' ') i++;

        if(i < n && s.charAt(i) == '-'){
            sign = -1;
            i++;
        }else if(i < n &&  s.charAt(i) == '+'){
            i++;
        }

        return atoi(s, i, sign, 0);

    }
}

public class MyAtoiFileIO {

    public static void main(String[] args) {
        Solution solution = new Solution();

        try {
            // Read input from input.txt
            BufferedReader reader = new BufferedReader(new FileReader("input.txt"));
            PrintStream originalOut = System.out;
            PrintStream fileOut = new PrintStream(new FileOutputStream("output.txt"));
            System.setOut(fileOut);

            String line;
            int testCaseNumber = 1;

            System.out.println("Processing input.txt...");

            while ((line = reader.readLine()) != null) {
                // Process each line as a test case
                String input = line.trim();

                // Call your myAtoi function
                int result = solution.myAtoi(input);


                // Also print to console for verification
                System.out.println("Test Case " + testCaseNumber + ": \"" + input + "\" -> " + result);

                testCaseNumber++;
            }

            // Close the reader and file output stream
            reader.close();
            fileOut.close();

            // Restore original System.out for final message
            System.setOut(originalOut);
            System.out.println("Processing complete! Results written to output.txt");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
            System.err.println("Please create an input.txt file with test cases (one per line)");
            System.err.println("Example input.txt content:");
            System.err.println("42");
            System.err.println("   -42");
            System.err.println("4193 with words");
            System.err.println("words and 987");
            System.err.println("-91283472332");

        } catch (IOException e) {
            System.err.println("Error reading/writing files: " + e.getMessage());
        }
    }
}

/*

inputs

42
   -42
4193 with words
words and 987
-91283472332
91283472332
+1
+-12
   +0 123
-000000000000001
2147483647
-2147483648
2147483648
-2147483649


 */