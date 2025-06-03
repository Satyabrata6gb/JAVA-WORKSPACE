package StriverSheet.Binary_Search.OneDArray;

import java.io.*;
import java.util.*;

public class FindPeakElement {

    public static int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while(lo < hi){
            int mid = lo + (hi - lo)/2;

            if(nums[mid] <= nums[mid+1]){
                lo = mid + 1;
            }else{
                hi = mid;
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
            // int m = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                // }
            }

            System.out.println(findPeakElement(a));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

