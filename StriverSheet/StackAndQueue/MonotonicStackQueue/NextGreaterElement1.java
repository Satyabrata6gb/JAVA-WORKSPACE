package StriverSheet.StackAndQueue.MonotonicStackQueue;

import java.io.*;
import java.util.*;

public class NextGreaterElement1 {

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
        int n1 = sc.nextInt();
        int[] nums1 = new int[n1];
        for (int i = 0; i < n1; i++) {
            nums1[i] = sc.nextInt();
        }

        int n2 = sc.nextInt();
        int[] nums2 = new int[n2];
        for (int i = 0; i < n2; i++) {
            nums2[i] = sc.nextInt();
        }

        int[] result = nextGreaterElement(nums1, nums2);

        for (int val : result) {
            out.print(val + " ");
        }
        out.println();
    }

    public static int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stk = new Stack<>();
        Map<Integer, Integer> ngeMap = new LinkedHashMap<>();

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && nums2[i] > stk.peek()) {
                stk.pop();
            }

            if (stk.isEmpty()) {
                ngeMap.put(nums2[i], -1);
            } else {
                ngeMap.put(nums2[i], stk.peek());
            }

            stk.push(nums2[i]);
        }

        int[] res = new int[nums1.length];
        for (int i = 0; i < nums1.length; i++) {
            res[i] = ngeMap.get(nums1[i]);
        }

        return res;
    }
}

