package StriverSheet.Strings.Easy;

import java.io.*;
import java.util.*;

public class AnagramString {

    public static boolean isAnagram(String s, String t) {
        // Map<Character, Integer> map = new HashMap<>();

        if(s.length() != t.length()) return false;

        // for(int i = 0  ; i < s.length(); i++){
        //     map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        // }

        // for(int i = 0 ; i < t.length() ; i++){
        //     if(map.getOrDefault(t.charAt(i), 0) == 0) return false;

        //     if(map.get(t.charAt(i)) == 1){
        //         map.remove(t.charAt(i));
        //     }else{
        //         map.put(t.charAt(i), map.get(t.charAt(i)) - 1);
        //     }
        // }

        // if(map.size() != 0){
        //     return false;
        // }else{
        //     return true;
        // }

        int[] array = new int[26];

        for(char c : s.toCharArray()){
            array[c - 'a']++;
        }

        for(char c : t.toCharArray()){
            array[c - 'a']--;
        }

        for(int i = 0 ; i < 26 ; i++){
            if(array[i] != 0) return false;
        }

        return true;
    
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

            String t = scanner.nextLine();

            // String[] arr = new String[n];

            // for(int i = 0 ; i < n ; i++){
            //     arr[i] = scanner.nextLine();
            // }

            System.out.println(isAnagram(s, t));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

