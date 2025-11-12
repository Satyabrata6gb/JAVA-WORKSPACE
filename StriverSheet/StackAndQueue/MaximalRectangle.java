package StriverSheet.StackAndQueue;

import java.io.*;
import java.util.*;

public class MaximalRectangle {

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
        int m = sc.nextInt();
        char[][] matrix = new char[n][m];

        // read input as integers (0/1)
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.next().charAt(0);
            }
        }

        int result = maximalRectangle(matrix);
        out.println(result);
    }

    public static int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] left = new int[n];
        int[] right = new int[n];
        int maxSize = 0;

        left[0] = -1;
        for (int i = 1; i < n; i++) {
            int prev = i - 1;
            while (prev >= 0 && heights[prev] >= heights[i]) {
                prev = left[prev];
            }
            left[i] = prev;
        }

        right[n - 1] = n;
        for (int i = n - 2; i >= 0; i--) {
            int next = i + 1;
            while (next < n && heights[next] >= heights[i]) {
                next = right[next];
            }
            right[i] = next;
        }

        for (int i = 0; i < n; i++) {
            int width = right[i] - left[i] - 1;
            int currentSize = heights[i] * width;
            if (currentSize > maxSize) {
                maxSize = currentSize;
            }
        }

        return maxSize;
    }

    public static int maximalRectangle(char[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        int maxArea = 0;

        int[][] prefixArr = new int[n][m];

        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                if (matrix[i][j] == '1') {
                    sum += 1;
                } else {
                    sum = 0;
                }
                prefixArr[i][j] = sum;
            }
        }

        for (int i = 0; i < n; i++) {
            maxArea = Math.max(maxArea, largestRectangleArea(prefixArr[i]));
        }

        return maxArea;
    }
}
