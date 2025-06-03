package LeetCode;

import java.io.*;
import java.util.*;

public class leetcode3196 {
    public static long maximumTotalCost(int[] nums) {
        int n = nums.length;

        long[][] dp = new long[n][2];

        dp[0][0] = nums[0];
        dp[0][1] = nums[0];

        for(int i = 1 ; i < n ; i++){
            dp[i][0] = Math.max(dp[i-1][0], dp[i-1][1]) + nums[i];
            dp[i][1] = dp[i-1][0] - nums[i];
        }

        return Math.max(dp[n-1][0], dp[n-1][1]);

    }

    public static void main(String[] args) {
        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            int n = scanner.nextInt();

            int[] arr = new int[n];

            for(int i = 0 ; i < n ; i++){
                arr[i] = scanner.nextInt();
            }

            System.out.println(maximumTotalCost(arr));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
