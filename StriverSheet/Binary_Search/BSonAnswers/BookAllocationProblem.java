package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class BookAllocationProblem {

    private static int countStudents(int mid, ArrayList<Integer> arr) {

        int count = 1;
        int sum = 0;

        for(int i = 0 ; i < arr.size(); i++){
            if(sum + arr.get(i) > mid){
                count++;
                sum = arr.get(i);
            }else{
                sum += arr.get(i);
            }
        }

        return count;
    }
    
    public static int findPages(ArrayList<Integer> arr, int n, int m) {
        int lo = arr.stream().max((i,j) -> i.compareTo(j)).get();
        int hi = arr.stream().mapToInt(Integer::intValue).sum();

        while(lo <= hi){
            int mid = (lo + hi)/2;
            
            int students = countStudents(mid, arr);

            if(students >  m){
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
            // int m = scanner.nextInt();
            // int k = scanner.nextInt();
            int target = scanner.nextInt();

            // int[] a = new int[n];

            ArrayList<Integer> nums = new ArrayList<>();

            for(int i = 0 ; i < n  ; i++){
                // for(int j = 0 ; j < m ; j++){
                    // a[i] = scanner.nextInt();
                    nums.add(scanner.nextInt());
                // }
            }

            System.out.println(findPages(nums,n, target));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}