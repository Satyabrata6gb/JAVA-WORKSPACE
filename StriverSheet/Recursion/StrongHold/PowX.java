package StriverSheet.Recursion.StrongHold;

import java.io.*;
import java.util.*;

public class PowX {

    public double solve( double x, int n){
        if(n == 1) return x;

        return x * solve(x, n-1);
    }

    public static double powLogN(double x, long n){
        double ans = 1;

        while(n > 0){
            if(n % 2 == 1){
                ans = ans * x;
                n = n - 1;
            }else{
                x = x * x;
                n = n / 2;
            }
        }

        return ans;
    }

    public static double myPow(double x, int n) {
        if(n == 0) return 1;
        if(x == 0) return 0;

        long nn = n;

        if(n < 0){
            x = 1 / x;
            nn *= -1;
        }

        return powLogN(x, nn);
    }

    public static void main(String[] args) {

        try {
            // Read from input.txt
            Scanner scanner = new Scanner(new File("input.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();

                // Skip empty lines
                if (line.isEmpty()) {
                    continue;
                }

                // Parse the input line
                String[] parts = line.split("\\s+");
                if (parts.length >= 2) {
                    try {
                        double x = Double.parseDouble(parts[0]);
                        int n = Integer.parseInt(parts[1]);

                        // Calculate power using the main instance
                        double result = myPow(x, n);

                        // Write result to output file
                        writer.println(String.format("%.6f", result));

                        // Also print to console for verification
                        System.out.println(x + "^" + n + " = " + String.format("%.6f", result));

                    } catch (NumberFormatException e) {
                        System.err.println("Invalid number format in line: " + line);
                        writer.println("ERROR: Invalid input format");
                    }
                } else {
                    System.err.println("Invalid input format in line: " + line);
                    writer.println("ERROR: Invalid input format");
                }
            }

            scanner.close();
            writer.close();

            System.out.println("Processing complete. Results written to output.txt");

        } catch (FileNotFoundException e) {
            System.err.println("input.txt file not found!");
        } catch (IOException e) {
            System.err.println("Error writing to output.txt: " + e.getMessage());
        }
    }
}