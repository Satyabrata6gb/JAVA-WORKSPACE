package StriverSheet.Binary_Search.OneDArray;

import java.io.*;
import java.util.*;

public class CountOccurencesOfANumber {
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

    public static int upperBound(int[] nums, int target) {
        int low = 0;
        int high = nums.length-1;

        int ans = nums.length;

        while(low <= high){
            int mid = low + (high-low)/2;

            if(nums[mid] > target){
                high = mid - 1;
                ans = mid;
            }else{
                low = mid + 1;
            }
        }

        return ans;
    }

    public static int[] searchRange(int[] nums, int target) {

        int lb = lowerBound(nums, target);

        if (lb == nums.length || nums[lb] != target) return new int[] { -1, -1};

        int ub = upperBound(nums, target);

        return new int[] {lb, ub-1};
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();
            // int m = scanner.nextInt();
            // int k = scanner.nextInt();
            int target = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                // }
            }

            System.out.println(Arrays.toString(searchRange(a, target )));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
