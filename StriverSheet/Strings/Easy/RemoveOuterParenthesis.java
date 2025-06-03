package StriverSheet.Strings.Easy;

import java.io.*;
import java.util.*;

public class RemoveOuterParenthesis {

    public static String removeOuterParentheses(String s) {

        String result = "";
        int opened = 0;

        for(char c : s.toCharArray()){
            if(c == '(' && opened++ > 0) result += c;
            if(c == ')' && opened-- > 1) result += c;
        }

        return result;
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            // int n = scanner.nextInt();

            // ArrayList<Integer> nums1 = new ArrayList<>();

            // for(int i = 0 ; i < n  ; i++){
            //     // for(int j = 0 ; j < m ; j++){
            //         // a[i] = scanner.nextInt();
            //         nums1.add(scanner.nextInt());
            //     // }
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

            System.out.println(removeOuterParentheses(s));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

