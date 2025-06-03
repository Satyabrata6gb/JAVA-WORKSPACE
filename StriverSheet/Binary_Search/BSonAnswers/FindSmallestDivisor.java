package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class FindSmallestDivisor {
    
    private static int findThreshold(int mid, int[] nums) {
        int ans = 0;

        for(int i = 0 ; i < nums.length; i++){
            ans += (nums[i]-1)/mid + 1;
        }

        return ans;
    }

    public static int smallestDivisor(int[] nums, int threshold) {

        int lo = 1;
        int hi = Arrays.stream(nums).max().getAsInt();
        int ans = 0;

        while(lo <= hi){
            int mid = (lo + hi)/2;

            int temp = findThreshold(mid, nums);

            if(temp <= threshold){
                ans = mid;
                hi = mid - 1;
            }else{
                lo = mid + 1;
            }
        }

        return ans;
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

            System.out.println(smallestDivisor(a, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

