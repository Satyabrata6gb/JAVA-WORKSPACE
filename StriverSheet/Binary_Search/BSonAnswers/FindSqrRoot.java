package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class FindSqrRoot {

    public static int sqrtN(long N) {

        long lo = 0;
        long hi = N;

        while(lo <= hi){
            long mid = (hi + lo)/2;

			long ans = mid * mid;

            if(ans <= N){
                lo = mid + 1;
            }else{
                hi = mid - 1;
            }
        }

        return (int)hi;
	}

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            long n = scanner.nextInt();
            // int m = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            // int[] a = new int[n];

            // for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    // a[i] = scanner.nextInt();
                // }
            // }

            System.out.println(sqrtN(n));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
