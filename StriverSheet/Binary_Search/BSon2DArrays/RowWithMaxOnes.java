package StriverSheet.Binary_Search.BSon2DArrays;

import java.io.*;
import java.util.*;

public class RowWithMaxOnes {

    public static int lowerBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;

        int ans = nums.length;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(nums[mid] >= target){
                high = mid - 1;
                ans = mid;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    public static int maximumOnesRow(ArrayList<ArrayList<Integer>> matrix, int n, int m)
    {
        int count =  0;
        int index = 0;

        for(int i = 0  ; i < n ; i++){
            int ones = m - lowerBound(matrix.get(i).stream().mapToInt(Integer::intValue).toArray(), 1);

            if(ones > count){
                count = ones;
                index = i;
            }
        }

        return index;
        //	  Write your code here.
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

            ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

            for(int i = 0 ; i < n ; i++){
                matrix.add(i, new ArrayList<Integer>());
                for(int j = 0 ; j < m ; j++){
                    matrix.get(i).add(scanner.nextInt());
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

            System.out.println(maximumOnesRow(matrix, n, m));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

