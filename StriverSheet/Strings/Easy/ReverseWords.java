package StriverSheet.Strings.Easy;

import java.io.*;
import java.util.*;

public class ReverseWords {

    public static String reverseWords(String s) {

    // s = s.trim();

    // String[] arr = s.split("\\s+");

    // String res = "";

    // for(int i = arr.length - 1 ; i >= 0 ; i--){
    // if(i != 0){
    // res += arr[i] + " ";
    // }else{
    // res += arr[i];
    // }
    // }

    // return res;

    char[] str = s.toCharArray();
    char[] res = new char[s.length() + 1];

    int n = s.length();
    int i = n-1;
    int j = 0;
    int k = 0;

    while(i >= 0){
        while(i >= 0 && str[i] == ' ') i--;

        if(i < 0) break;

        j = i;

        while(i >= 0 && str[i] != ' ') i--;

        for(int x = i + 1 ; x <= j ; x++){
            res[k++] = str[x];
        }

        res[k++] = ' ';
    }

    return new String(res, 0, k - 1);


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

            System.out.println(reverseWords(s));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

