package StriverSheet.Binary_Search.BSon2DArrays;

import java.io.*;
import java.util.*;

public class MatrixMedian {

    private static int countElementLesserThan(int[] row, int mid, int n) {
        int lo = 0;
        int hi = n-1;

        while(lo <= hi){
            int m = (lo+hi) >> 1;

            if(row[m] <= mid){
                lo = m + 1;
            }else{
                hi = m - 1;
            }
        }

        return lo;
    }
    

    public static int findMedian(int matrix[][], int m, int n) {
        int lo = 1;
        int hi = 1000000000;

        while(lo <= hi){
            int mid = (lo + hi) >> 1;
            
            int cnt =  0;

            for(int i = 0 ; i < m; i++){
                cnt += countElementLesserThan(matrix[i], mid, n);
            }


            if(cnt <= (m*n)/2){
                lo = mid + 1;
            }else{
                hi = mid - 1;
            }

        }

        return lo;
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();

            // ArrayList<Integer> nums1 = new ArrayList<>();

            // for(int i = 0 ; i < n  ; i++){
            //     // for(int j = 0 ; j < m ; j++){
            //         // a[i] = scanner.nextInt();
            //         nums1.add(scanner.nextInt());
            //     // }
            // }
            int m = scanner.nextInt();

            // ArrayList<Integer> nums2 = new ArrayList<>();

            // ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

            int[][] matrix = new int[n][m];
            for(int i = 0 ; i < n ; i++){
                // matrix.add(i, new ArrayList<Integer>());
                for(int j = 0 ; j < m ; j++){
                    // matrix.get(i).add(scanner.nextInt());
                    matrix[i][j] = scanner.nextInt();
                }
            }

            // for (int i = 0; i < m; i++) {
                // b[i] = scanner.nextInt();
                // nums2.add(scanner.nextInt());
            // }
            // int k = scanner.nextInt();

            // int target = scanner.nextInt();

            // int[] a = new int[n];
            // int[] b = new int[m];

            System.out.println(findMedian(matrix, n, m));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

