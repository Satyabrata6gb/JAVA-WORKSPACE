package StriverSheet.Recursion.StrongHold;

import java.io.*;
import java.util.*;

public class CountGoodNumbers {
    static int mod = 1000000007;

    public static int powLogN(int x, long n) {
        long ans = 1;
        long base = x;

        while (n > 0) {
            if (n % 2 == 1) {
                ans = (ans * base) % mod;
                n = n - 1;
            } else {
                base = (base * base) % mod;
                n = n / 2;
            }
        }

        return (int)(ans % mod);
    }

    public static int countGoodNumbers(long n) {
        if (n % 2 == 0) {
            int five = powLogN(5, n / 2);
            int four = powLogN(4, n / 2);

            return (int)((long)five * four % mod);
        } else {
            int five = powLogN(5, (n / 2) + 1);  // Fixed: should be +1 for odd positions
            int four = powLogN(4, n / 2);

            return (int)((long)five * four % mod);
        }
    }

    public static void main(String[] args) {
        try {
            // Read from input.txt
            Scanner scanner = new Scanner(new File("input.txt"));
            PrintWriter writer = new PrintWriter(new FileWriter("output.txt"));

            while (scanner.hasNextLong()) {
                long n = scanner.nextLong();
                int result = countGoodNumbers(n);
                writer.println(result);
            }

            scanner.close();
            writer.close();

            System.out.println("Processing completed. Results written to output.txt");

        } catch (FileNotFoundException e) {
            System.err.println("input.txt file not found: " + e.getMessage());
        } catch (IOException e) {
            System.err.println("Error writing to output.txt: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("An error occurred: " + e.getMessage());
        }
    }
}
