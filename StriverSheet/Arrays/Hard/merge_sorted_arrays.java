package StriverSheet.Arrays.Hard;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class merge_sorted_arrays {
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int i = m-1;
        int j = n-1;
        int k = m+n-1;

        while(i > 0 || j > 0){
            if(j > 0 && nums1[i] < nums2[j]){
                nums1[k] = nums2[j];
                k--;
                j--;
            }else if(i > 0 && nums1[i] >= nums2[j]){
                nums1[k] = nums1[i];
                k--;
                i--;
            }
        }
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();
            int x = scanner.nextInt();
            int m = scanner.nextInt();
            int y = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[] a = new int[n];
            int[] b = new int[m];

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    a[i] = scanner.nextInt();
                // }
            }

            for(int i = 0 ; i < m  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    b[i] = scanner.nextInt();
                // }
            }

            merge( a, x, b, y);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
