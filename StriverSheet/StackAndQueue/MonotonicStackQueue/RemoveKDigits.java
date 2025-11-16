package StriverSheet.StackAndQueue.MonotonicStackQueue;

import java.io.*;
import java.util.*;

public class RemoveKDigits {

    public static void main(String[] args) throws Exception {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        Scanner sc = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        solve(sc, out);

        sc.close();
        out.close();
    }

    public static void solve(Scanner sc, PrintWriter out) {
        String num = sc.next();
        int k = sc.nextInt();

        String result = removeKdigits(num, k);
        out.println(result);
    }

    public static String removeKdigits(String num, int k) {
        if(k == num.length()) return "0";
        Stack<Character> stk = new Stack<>();

        char[] nums = num.toCharArray();

        for (int i =  0; i <= nums.length ; i++) {
            char c = (i == nums.length) ? 'a' : nums[i];
            while (!stk.isEmpty() && k > 0 && c < stk.peek()) {
                stk.pop();
                k--;
            }

            if(i < nums.length) stk.push(nums[i]);
        }

        StringBuilder sb = new StringBuilder();

        while(!stk.isEmpty()){
            sb.append(stk.pop());
        }

        int number = Integer.parseInt(sb.reverse().toString());

        // Now convert the integer back to a string/StringBuilder if needed
        // The resulting string will be "200"
        return String.valueOf(number);
    }
}

