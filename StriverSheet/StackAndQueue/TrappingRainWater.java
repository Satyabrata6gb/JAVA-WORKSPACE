package StriverSheet.StackAndQueue;

import java.io.*;
import java.util.*;

public class TrappingRainWater {

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
        int[] height = new int[n];
        for (int i = 0; i < n; i++) {
            height[i] = sc.nextInt();
        }

        int result = trap(height);
        out.println(result);
    }

    public static int trap(int[] height) {
        int lMax = 0, rMax = 0, i = 0;
        int j = height.length - 1;
        int res = 0;

        while (i < j) {
            if (height[i] <= height[j]) {
                if (lMax > height[i]) {
                    res += (lMax - height[i]);
                } else {
                    lMax = height[i];
                }
                i++;
            } else {
                if (rMax > height[j]) {
                    res += (rMax - height[j]);
                } else {
                    rMax = height[j];
                }
                j--;
            }
        }

        return res;
    }
}

