package StriverSheet.Arrays.Hard;

import java.io.*;
import java.util.*;

public class findmissingandrepeatingnum {

    public static int[] findMissingRepeatingNumbers(int []a) {
        // Write your code here
        //4 1 4 2
        //-4 -1     2

        int n = a.length;
        List<Integer> ans = new ArrayList<>();
        int sum = 0;

        for(int i = 0 ; i < a.length ; i++){
            sum += Math.abs(a[i]);
            int index = Math.abs(a[i])-1;
            
            a[index] *= -1;
        }

        for(int i = 0 ; i < a.length ; i++){
            if(a[i] > 0){
                ans.add(i + 1);
            }
        }

        if(sum > (n*(n+1)/2) ){
            Collections.swap(ans, 0, 1);
        }

        return ans.stream().mapToInt(Integer::intValue).toArray();
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

            System.out.println(Arrays.toString(findMissingRepeatingNumbers(a)));

        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
    
}
