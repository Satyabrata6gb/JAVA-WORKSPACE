package StriverSheet.StackAndQueue.MonotonicStackQueue;

import java.io.*;
import java.util.*;

public class NextGreaterElement2 {

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

        int[] result = nextGreaterElements(nums);

        for (int val : result) {
            out.print(val + " ");
        }
        out.println();
    }

    public static int[] nextGreaterElements(int[] nums) {
        Stack<Integer> stk = new Stack<>();
        int[] nge = new int[nums.length];
        int n = nums.length;

        for (int i = 2 * n - 1; i >= 0; i--) {
            while (!stk.isEmpty() && nums[i % n] >= stk.peek()) {
                stk.pop();
            }

            if (i < n) {
                nge[i] = stk.isEmpty() ? -1 : stk.peek();
            }

            stk.push(nums[i % n]);
        }

        return nge;
    }
}

