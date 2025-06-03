package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class MinCapacityTOShipPackages {
    
    private static int findDays(int mid, int[] weights) {
        int count = 0;
        int days = 0;

        for(int i = 0 ; i < weights.length; i++){
            if(weights[i] > mid) return Integer.MAX_VALUE;
            if(count + weights[i] > mid){
                days++;
                count = weights[i];
            }else{
                count += weights[i];
            }
        }

        days++;

        return days;
    }

    public static int shipWithinDays(int[] weights, int days) {

        int lo = Arrays.stream(weights).min().getAsInt();
        int hi = Arrays.stream(weights).sum();
        int ans = 0;

        while(lo <= hi){
            int mid = (lo + hi)/2;

            int temp = findDays(mid, weights);

            if(temp <= days){
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

            System.out.println(shipWithinDays(a, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

