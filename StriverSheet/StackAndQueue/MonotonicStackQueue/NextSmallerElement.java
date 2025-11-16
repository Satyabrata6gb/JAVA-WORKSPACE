package StriverSheet.StackAndQueue.MonotonicStackQueue;

import java.io.*;
import java.util.*;

public class NextSmallerElement {

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

        int[] result = nextSmallerElements(arr);

        for (int val : result) {
            out.print(val + " ");
        }
        out.println();
    }

    public static int[] nextSmallerElements(int[] arr) {
        Stack<Integer> stk = new Stack<>();
        int[] res = new int[arr.length];

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stk.isEmpty() && arr[i] < stk.peek()) {
                stk.pop();
            }

            if (stk.isEmpty()) {
                res[i] = -1;
            } else {
                res[i] = stk.peek();
            }

            stk.push(arr[i]);
        }

        return res;
    }
}
