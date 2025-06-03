package StriverSheet.Binary_Search.OneDArray;

import java.io.*;
import java.util.*;

public class SingleELementInASortedArray {

    public static int singleNonDuplicate(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;

        while(lo < hi){
            int mid = (lo + hi)/2;

            if(mid % 2 == 1){
                mid--;
            }

            if(nums[mid] != nums[mid+1]){
                hi = mid;
            }else{
                lo = mid + 2;
            }
        }

        return nums[lo];
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

            System.out.println(singleNonDuplicate(a));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
