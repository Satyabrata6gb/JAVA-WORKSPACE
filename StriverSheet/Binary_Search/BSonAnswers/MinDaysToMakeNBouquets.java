package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class MinDaysToMakeNBouquets {

    public static boolean findTotalFlowers(int day, int[] bloomDay, int m, int k){
        int ans = 0;
        int count = 0;

        for(int i = 0 ; i < bloomDay.length ; i++){
            if(day >= bloomDay[i]) ans++;
            else{
                count += ans/k;
                ans = 0;
            }
        }

        count += ans/k;

        return count >= m;
    }

    public static int minDays(int[] bloomDay, int m, int k) {

        if(bloomDay.length < m*k) return -1;

        int lo = Arrays.stream(bloomDay).min().getAsInt();
        int hi = Arrays.stream(bloomDay).max().getAsInt();

        while(lo <= hi){
            int mid = (lo + hi)/2;

            if(findTotalFlowers(mid, bloomDay, m, k)){
                hi = mid - 1;
            }else{
                lo = mid + 1;
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
            int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                // }
            }

            System.out.println(minDays(a, m, k));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
