package StriverSheet.Strings.Medium;

import java.io.*;
import java.util.*;

public class LongestPallindromicString {

    public static String longestPalindrome(String s) {
        if(s.length() <= 1) return s;
        
        String maxStr = s.substring(0, 1);

        for(int i = 0 ; i < s.length() - 1 ; i++){
            String odd = expandFromMid(s, i, i);
            String even = expandFromMid(s,  i, i+1);

            if(odd.length() > maxStr.length()){
                maxStr = odd;
            }if(even.length() > maxStr.length()){
                maxStr = even;
            }
        }

        return maxStr;
    }

    private static String expandFromMid(String s, int left, int right) {
        while(left >= 0 && right < s.length() && (s.charAt(left) == s.charAt(right))){
            left--;
            right++;
        }

        return s.substring(left+1, right);
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);
            // int n = scanner.nextInt();

            // ArrayList<Integer> nums1 = new ArrayList<>();

            // for(int i = 0 ; i < n ; i++){
            // // for(int j = 0 ; j < m ; j++){
            // // a[i] = scanner.nextInt();
            // nums1.add(scanner.nextInt());
            // // }
            // }
            // int m = scanner.nextInt();

            // ArrayList<Integer> nums2 = new ArrayList<>();

            // ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

            // int[][] matrix = new int[n][m];
            // for(int i = 0 ; i < n ; i++){
            // matrix.add(i, new ArrayList<Integer>());
            // for(int j = 0 ; j < m ; j++){
            // matrix.get(i).add(scanner.nextInt());
            // matrix[i][j] = scanner.nextInt();
            // }
            // }

            // for (int i = 0; i < m; i++) {
            // b[i] = scanner.nextInt();
            // nums2.add(scanner.nextInt());
            // }
            // int k = scanner.nextInt();

            // int target = scanner.nextInt();

            // int[] a = new int[n];
            // int[] b = new int[m];

            String s = scanner.nextLine();

            // String t = scanner.nextLine();

            // String[] arr = new String[n];

            // for(int i = 0 ; i < n ; i++){
            //     arr[i] = scanner.nextLine();
            // }

            System.out.println(longestPalindrome(s));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

