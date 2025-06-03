package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class FindKthPosMissingNum {
    
    public static int findKthPositive(int[] arr, int k) {
        int lo = 0;
        int hi = arr.length;

        while(lo < hi){
            int mid = (lo+hi)/2;

            if(arr[mid] - 1 - mid < k){
                lo = mid + 1;
            }else{
                hi = mid ;
            }
        }

        return lo+k;
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

            System.out.println(findKthPositive(a, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
