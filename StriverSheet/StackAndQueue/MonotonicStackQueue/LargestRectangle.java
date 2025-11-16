package StriverSheet.StackAndQueue.MonotonicStackQueue;

import java.io.*;
import java.util.*;

public class LargestRectangle {

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
        int[] heights = new int[n];
        for (int i = 0; i < n; i++) {
            heights[i] = sc.nextInt();
        }

        int result = largestRectangleArea(heights);
        out.println(result);
    }

    public static int largestRectangleArea(int[] heights) {
        Stack<Integer> stk = new Stack<>();
        int area = 0;

        for (int i = 0; i <= heights.length; i++) {
            int current = i == heights.length ? -1 : heights[i];

            while (!stk.isEmpty() && current < heights[stk.peek()]) {
                int ele = heights[stk.pop()];
                int pse = stk.isEmpty() ? -1 : stk.peek();

                area = Math.max(area, ele * (i - pse - 1));
            }

            stk.push(i);
        }

        return area;
    }
}

