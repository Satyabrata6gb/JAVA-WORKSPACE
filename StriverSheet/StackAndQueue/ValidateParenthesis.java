package StriverSheet.StackAndQueue;

import java.io.*;
import java.util.*;

public class ValidateParenthesis {

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
        String s = sc.nextLine().trim();
        boolean result = isValid(s);
        out.println(result);
    }

    public static boolean isValid(String s) {
        Stack<Character> stk = new Stack<>();

        for (char c : s.toCharArray()) {
            if (stk.isEmpty() || c == '(' || c == '{' || c == '[') {
                stk.push(c);
            } else if ((c == ')' && stk.peek() == '(') ||
                    (c == '}' && stk.peek() == '{') ||
                    (c == ']' && stk.peek() == '[')) {
                stk.pop();
            } else {
                stk.push(c);
            }
        }

        return stk.isEmpty();
    }
}