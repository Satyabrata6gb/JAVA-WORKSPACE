package StriverSheet.Binary_Search.OneDArray;

import java.io.*;
import java.util.*;

public class SearchInARotatedSortedArray2 {

    public static int search(int[] nums, int target) {

        int lo = 0;
        int  hi = nums.length-1;
        // int n = nums.length;

        while(lo < hi){
            int mid = (lo + hi)/2;

            if(nums[mid] == target) return mid;

            if(nums[lo] <= nums[mid]){
                if(target >= nums[lo] && nums[mid] > target){
                    hi = mid - 1;
                }else {
                    lo = mid + 1;
                }
            }else{
                if(target > nums[mid] && target <= nums[hi]) {
                    lo = mid + 1;
                }else{
                    hi = mid - 1;
                }
            }
        }

        return nums[lo] == target ? lo : -1;
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

            System.out.println(search(a, target ));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
