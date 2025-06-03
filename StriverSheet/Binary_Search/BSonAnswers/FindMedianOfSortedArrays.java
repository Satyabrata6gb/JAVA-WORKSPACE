package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class FindMedianOfSortedArrays {

    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {

        int n1 = nums1.length;
        int n2 = nums2.length;

        if(n1 > n2) return findMedianSortedArrays(nums2, nums1);

        int n = n1+n2;
        int left = (n1+n2+1)/2;

        int lo = 0;
        int hi = n1;

        while(lo <= hi){
            int cut1 = (lo+hi)/2;
            int cut2 = left - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : nums1[cut1 - 1];
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : nums2[cut2 - 1];

            int r1 = cut1 == n1? Integer.MAX_VALUE : nums1[cut1];
            int r2 = cut2 == n2? Integer.MAX_VALUE : nums2[cut2];

            if(l1 <= r2 && l2 <= r1){
                if(n % 2 == 0){
                    return ((double)(Math.max(l1, l2) + Math.min(r1, r2)))/2.0 ;
                }else{
                    return Math.max(l1, l2);
                }
            }else if(l1 > r2){
                hi = cut1 - 1;
            }else{
                lo = cut1 + 1;
            }
        }

        return 0.0;
        
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[] a = new int[n];
            int[] b = new int[m];

            // ArrayList<Integer> nums = new ArrayList<>();

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                    // nums.add(scanner.nextInt());
                // }
            }

            for (int i = 0; i < m; i++) {
                b[i] = scanner.nextInt();
            }

            System.out.println(findMedianSortedArrays(a, b));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
