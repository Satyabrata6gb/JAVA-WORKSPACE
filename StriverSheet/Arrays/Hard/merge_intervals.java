package StriverSheet.Arrays.Hard;

import java.io.*;
import java.util.*;

public class merge_intervals {

    public static int[][] merge(int[][] intervals) {
        List<List<Integer>> res = new ArrayList<>();

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        res.add(Arrays.asList(Arrays.stream(intervals[0]).boxed().toArray(Integer[]::new)));

        int j = 0;

        for(int i = 1 ; i < intervals.length ; i++){
            if(res.get(j).get(0) < intervals[i][0] && res.get(j).get(1) > intervals[i][1]){
                continue;
            }else if(res.get(j).get(0) < intervals[i][0] && res.get(j).get(1) > intervals[i][0]){

                List<Integer> temp = new ArrayList<>(Arrays.asList(res.get(j).get(0), intervals[i][1]));
                res.remove(j);
                res.add(j, temp);

            }else{
                res.add(Arrays.asList(Arrays.stream(intervals[i]).boxed().toArray(Integer[]::new)));
                j++;
            }
        }

        int[][] ans = res.stream()
                .map(innerList -> innerList.stream().mapToInt(Integer::intValue).toArray())
                .toArray(int[][]::new);

        return ans;
    }

    public static void main(String[] args) {
        
        File file = new File("input.txt");

        try(Scanner scanner = new Scanner(new FileReader(file));
        PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);){
            
            System.setOut(out);
            int n = scanner.nextInt();
            int m = scanner.nextInt();
            // int k = scanner.nextInt();
            // int target = scanner.nextInt();

            int[][] a = new int[n][m];

            for(int i = 0 ; i < n  ; i++){
                for(int j = 0 ; j < m ; j++){
                    a[i][j] = scanner.nextInt();
                }
            }

            System.out.println(Arrays.deepToString(merge(a)));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}

