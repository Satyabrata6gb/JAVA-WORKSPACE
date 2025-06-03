package StriverSheet.Strings.Medium;

import java.io.*;
import java.util.*;

public class Atoi {

    public static int myAtoi(String s) {
        int answer = 0;
        int i = 0;
        int flag = 1;
        int n = s.length();

        if(s == "") return 0;
        
        while(i < n && s.charAt(i) == ' ') i++;

        if(i < n && (s.charAt(i) == '+' || s.charAt(i) == '-')){
            if(s.charAt(i) == '-') flag = -1;
            i++;
        }

        while(i < n && Character.isDigit(s.charAt(i))){
            if(answer > Integer.MAX_VALUE/10 || answer == Integer.MAX_VALUE/10 && ((s.charAt(i) - '0') > Integer.MAX_VALUE%10)){
                if(flag == 1) return Integer.MAX_VALUE;
                else return Integer.MIN_VALUE;
            }

            answer = answer * 10 + Integer.parseInt(String.valueOf(s.charAt(i)));
            i++;
        }

        return answer * flag;
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

            System.out.println(myAtoi(s));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

