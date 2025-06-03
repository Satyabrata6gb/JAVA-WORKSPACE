package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class MinimizeMaxDistanceToGasStation {

    private static boolean getGasCount(int[] arr, double mid, int k) {
        int count = 0;

        for(int i = 0 ; i < arr.length-1 ; i++){
            int gasStationplaced = (int) ((arr[i+1] - arr[i])/mid);
            if((arr[i+1] - arr[i]) == (mid * gasStationplaced) ){
                gasStationplaced--;
            }

            count += gasStationplaced;
        }

        return (count > k);
    }

    public static double MinimiseMaxDistance(int []arr, int K){
        double lo = 0;
        double hi = 0;

        for(int i = 0 ; i < arr.length-1; i++){
            hi = Math.max(hi, (double) arr[i+1] - arr[i]);
        }

        double diff = 1e-6;

        while((hi - lo) > diff){
            double mid = (hi + lo)/2;

            if(getGasCount(arr, mid, K)){
                lo = mid;
            }else{
                hi = mid;
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

            // ArrayList<Integer> nums = new ArrayList<>();

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                    // nums.add(scanner.nextInt());
                // }
            }

            System.out.println(MinimiseMaxDistance(a, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

