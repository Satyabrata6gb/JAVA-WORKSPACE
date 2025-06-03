package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class SplitArrayLargestSum {

    private static int countSum(int mid, int[] arr) {
        int count = 1;
        int sum = 0;

        for(int i = 0 ; i < arr.length; i++){
            if(sum + arr[i] > mid){
                count++;
                sum = arr[i];
            }else{
                sum += arr[i];
            }
        }

        return count;
    }
    
    public static int splitArray(int[] nums, int k) {
        int lo = Arrays.stream(nums).max().getAsInt();
        int hi = Arrays.stream(nums).sum();

        while(lo <= hi){
            int mid = (lo + hi)/2;
            
            int splits = countSum(mid, nums);

            if(splits >  k){
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
            // int m = scanner.nextInt();
            // int k = scanner.nextInt();
            int target = scanner.nextInt();

            int[] a = new int[n];

            // ArrayList<Integer> nums = new ArrayList<>();

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                    // nums.add(scanner.nextInt());
                // }
            }

            System.out.println(splitArray(a, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
