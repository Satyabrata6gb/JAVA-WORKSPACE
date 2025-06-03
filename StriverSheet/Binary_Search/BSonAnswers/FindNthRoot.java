package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class FindNthRoot {

    public static int findRoot(int mid, int n , int m){
        long ans = 1;

        for(int i = 0 ; i < n ; i++){
            ans = ans * mid;

            if(ans > m) return 2;
        }

        if(ans == m) return 0;

        else return 1;
    }

    public static int NthRoot(int n, int m) {

        int lo = 0;
        int hi = m;

        while(lo <= hi){
            int mid = (lo + hi)/2;

            int midN = findRoot(mid,n, m);

            if(midN == 0) return mid;

            if(midN == 1){
                lo = mid + 1;
            }else{
                hi = mid - 1;
            }
        }

        return -1;
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

            // int[] a = new int[n];

            // for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    // a[i] = scanner.nextInt();
                // }
            // }

            System.out.println(NthRoot(n, m));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

