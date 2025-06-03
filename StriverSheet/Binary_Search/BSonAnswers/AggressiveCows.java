package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class AggressiveCows {
    
    private static int FindNoOfCowsPlaced(int mid, int[] stalls) {

        int total_placed = 1;
        int last_cow_placed = stalls[0];

        for(int i = 1 ; i < stalls.length;  i++){
            if(stalls[i] - last_cow_placed >= mid){
                total_placed++;
                last_cow_placed = stalls[i];
            }
        }

        return total_placed;
    }
    
    public static int aggressiveCows(int []stalls, int k) {
        int lo = 0;
        int hi = Arrays.stream(stalls).max().getAsInt();

        Arrays.sort(stalls);

        while(lo <= hi){
            int mid = (lo + hi)/2;

            int placed = FindNoOfCowsPlaced(mid, stalls);

            if(placed >= k){
                lo = mid+1;
            }else{
                hi = mid - 1;
            }
        }

        return hi;
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

            System.out.println(aggressiveCows(a, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

