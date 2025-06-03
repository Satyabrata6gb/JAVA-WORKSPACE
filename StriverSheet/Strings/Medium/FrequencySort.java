package StriverSheet.Strings.Medium;

import java.io.*;
import java.util.*;

public class FrequencySort {

    public static String frequencySort(String s) {
        Map<Character, Integer> hm = new HashMap<>();
        
        for (char c : s.toCharArray()) {
            hm.put(c, hm.getOrDefault(c, 0) + 1);
        }
        
        PriorityQueue<Map.Entry<Character, Integer>> pq = new PriorityQueue<>(
            (a, b) -> b.getValue() - a.getValue()
        );
        
        pq.addAll(hm.entrySet());
        
        StringBuilder result = new StringBuilder();
        while (!pq.isEmpty()) {
            Map.Entry<Character, Integer> entry = pq.poll();
            result.append(String.valueOf(entry.getKey()).repeat(entry.getValue()));
        }
        
        return result.toString();
    }

    public static void main(String[] args) {

        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
                PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);
            // int n = scanner.nextInt();

            // ArrayList<Integer> nums1 = new ArrayList<>();

            // for(int i = 0 ; i < n ; i++){
            // // for(int j = 0 ; j < m ; j++){
            // // a[i] = scanner.nextInt();
            // nums1.add(scanner.nextInt());
            // // }
            // }
            // int m = scanner.nextInt();

            // ArrayList<Integer> nums2 = new ArrayList<>();

            // ArrayList<ArrayList<Integer>> matrix = new ArrayList<ArrayList<Integer>>();

            // int[][] matrix = new int[n][m];
            // for(int i = 0 ; i < n ; i++){
            // matrix.add(i, new ArrayList<Integer>());
            // for(int j = 0 ; j < m ; j++){
            // matrix.get(i).add(scanner.nextInt());
            // matrix[i][j] = scanner.nextInt();
            // }
            // }

            // for (int i = 0; i < m; i++) {
            // b[i] = scanner.nextInt();
            // nums2.add(scanner.nextInt());
            // }
            // int k = scanner.nextInt();

            // int target = scanner.nextInt();

            // int[] a = new int[n];
            // int[] b = new int[m];

            String s = scanner.nextLine();

            // String t = scanner.nextLine();

            // String[] arr = new String[n];

            // for(int i = 0 ; i < n ; i++){
            //     arr[i] = scanner.nextLine();
            // }

            System.out.println(frequencySort(s));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

