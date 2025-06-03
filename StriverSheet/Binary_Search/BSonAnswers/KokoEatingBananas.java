package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class KokoEatingBananas {

    public static int findTotalHours(int m, int[] piles){
        int ans = 0;

        for(int i = 0 ; i < piles.length; i++){
            ans += (piles[i] - 1)/m + 1;
         }

        return ans;
    }

    public static int minEatingSpeed(int[] piles, int h) {

        int lo = 0;
        int hi = Arrays.stream(piles).max().getAsInt();

        while(lo <= hi){
            int mid = (lo + hi)/2;

            int hrs = findTotalHours(mid, piles);
            if(hrs > h){
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
            int m = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                // }
            }

            System.out.println(minEatingSpeed(a, m));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

