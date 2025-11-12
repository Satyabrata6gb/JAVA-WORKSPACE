package StriverSheet.StackAndQueue;

import java.io.*;
import java.util.*;

public class SumOfSubarrayMinimum {

    public static void main(String[] args) throws Exception {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        Scanner sc = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        solve(sc, out);

        sc.close();
        out.close();
    }

    public static void solve(Scanner sc, PrintWriter out) {
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        int result = sumSubarrayMins(arr);
        out.println(result);
    }

    private static int[] findNSE(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stk = new Stack<>();
        int n = arr.length;

        for (int i = n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && arr[stk.peek()] >= arr[i]) {
                stk.pop();
            }
            res[i] = stk.isEmpty() ? n : stk.peek();
            stk.push(i);
        }

        return res;
    }

    private static int[] findPSE(int[] arr) {
        int[] res = new int[arr.length];
        Stack<Integer> stk = new Stack<>();
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            while (!stk.isEmpty() && arr[stk.peek()] > arr[i]) {
                stk.pop();
            }
            res[i] = stk.isEmpty() ? -1 : stk.peek();
            stk.push(i);
        }

        return res;
    }

    public static int sumSubarrayMins(int[] arr) {
        int[] nse = findNSE(arr);
        int[] pse = findPSE(arr);

        long res = 0L;
        long mod = 1_000_000_007L;
        int n = arr.length;

        for (int i = 0; i < n; i++) {
            long r = nse[i] - i;
            long l = i - pse[i];

            long contrib = (r * l) % mod;
            contrib = (contrib * arr[i]) % mod;

            res = (res + contrib) % mod;
        }

        return (int) res;
    }
}

