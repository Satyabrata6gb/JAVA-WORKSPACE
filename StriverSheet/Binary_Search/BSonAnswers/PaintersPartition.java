package StriverSheet.Binary_Search.BSonAnswers;

import java.io.*;
import java.util.*;

public class PaintersPartition {

    private static int countSum(int mid, ArrayList<Integer> boards) {
        int count = 1;
        int sum = 0;

        for(int i = 0 ; i < boards.size() ; i++){
            if(sum + boards.get(i) > mid){
                count++;
                sum = boards.get(i);
            }else{
                sum += boards.get(i);
            }
        }

        return count;
    }
    
    public static int findLargestMinDistance(ArrayList<Integer> boards, int k)
    {
        int lo = boards.stream().max((i,j) -> i.compareTo(j)).get();
        int hi = boards.stream().mapToInt(Integer::intValue).sum();

        while(lo <= hi){
            int mid = (lo + hi)/2;

            int painters = countSum(mid, boards);
            if(painters > k){
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

            System.out.println(findLargestMinDistance(nums, target));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}

