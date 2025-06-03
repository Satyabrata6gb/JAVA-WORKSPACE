package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class KthElementof2SortedArray {

    public static int kthElement(ArrayList<Integer> arr1, ArrayList<Integer> arr2, int n, int m, int k) {

        if(n > m) return kthElement(arr2, arr1, m, n, k);

        int lo = Math.max(0, k-m);
        int hi = Math.min(k, n);

        while(lo <= hi){
            int cut1 = (lo+hi)/2;
            int cut2 = k - cut1;

            int l1 = cut1 == 0 ? Integer.MIN_VALUE : arr1.get(cut1 - 1);
            int l2 = cut2 == 0 ? Integer.MIN_VALUE : arr2.get(cut2 - 1);

            int r1 = cut1 == n? Integer.MAX_VALUE : arr1.get(cut1);
            int r2 = cut2 == m? Integer.MAX_VALUE : arr2.get(cut2);

            if(l1 <= r2 && l2 <= r1){
                return Math.max(l1, l2);
            }else if(l1 > r2){
                hi = cut1 - 1;
            }else{
                lo = cut1 + 1;
            }
        }

        return 0;
        
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();

            ArrayList<Integer> nums1 = new ArrayList<>();

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    // a[i] = scanner.nextInt();
                    nums1.add(scanner.nextInt());
                // }
            }
            int m = scanner.nextInt();

            ArrayList<Integer> nums2 = new ArrayList<>();

            for (int i = 0; i < m; i++) {
                // b[i] = scanner.nextInt();
                nums2.add(scanner.nextInt());
            }
            int k = scanner.nextInt();

            // int target = scanner.nextInt();

            // int[] a = new int[n];
            // int[] b = new int[m];

            System.out.println(kthElement(nums1, nums2, n, m, k));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

