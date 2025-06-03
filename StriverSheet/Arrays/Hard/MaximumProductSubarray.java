package StriverSheet.Arrays.Hard;
import java.io.*;
import java.util.*;

public class MaximumProductSubarray {

    public static int maxProduct(int[] nums) {
        int left = 0;
        int right = 0;
        int answer = Integer.MIN_VALUE;
        
        int n = nums.length;

        for(int i = 0 , j = n-1; i < n && j >= 0 ; i++, j--){
            if(left == 0) left = nums[i];
            else left = left * nums[i];

            if(right == 0) right = nums[j];
            else right = right * nums[j];

            answer = Math.max(answer, Math.max(left, right));
        }

        return answer;
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();
            // int m = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                // }
            }

            System.out.println(maxProduct(a));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
