package StriverSheet.StackAndQueue;

import java.io.*;
import java.util.*;

public class SumOfSubArrayRanges {

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
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        long result = subArrayRanges(nums);
        out.println(result);
    }

    public static long sumSubarrayMins(int[] arr) {
        long res = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);

        for (int i2 = 0; i2 < arr.length + 1; i2++) {
            int currVal = (i2 < arr.length) ? arr[i2] : -1000000007;

            while (stack.peek() != -1 && currVal < arr[stack.peek()]) {
                int index = stack.pop();
                int i1 = stack.peek();
                int left = index - i1;
                int right = i2 - index;
                long add = (long) (left * right * (long) arr[index]);
                res += add;
            }

            stack.push(i2);
        }

        return res;
    }

    public static long sumSubarrayMaxs(int[] arr) {
        long res = 0;
        Stack<Integer> stack = new Stack<Integer>();
        stack.push(-1);

        for (int i2 = 0; i2 < arr.length + 1; i2++) {
            int currVal = (i2 < arr.length) ? arr[i2] : 1000000007;

            while (stack.peek() != -1 && currVal > arr[stack.peek()]) {
                int index = stack.pop();
                int i1 = stack.peek();
                int left = index - i1;
                int right = i2 - index;
                long add = (long) (left * right * (long) arr[index]);
                res += add;
            }

            stack.push(i2);
        }

        return res;
    }

    public static long subArrayRanges(int[] nums) {
        long maxSum = sumSubarrayMaxs(nums);
        long minSum = sumSubarrayMins(nums);
        return maxSum - minSum;
    }
}

