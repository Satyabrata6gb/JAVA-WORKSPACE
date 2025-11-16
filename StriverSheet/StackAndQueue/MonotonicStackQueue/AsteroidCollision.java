package StriverSheet.StackAndQueue.MonotonicStackQueue;

import java.io.*;
import java.util.*;

public class AsteroidCollision {

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
        int n = sc.nextInt();
        int[] asteroids = new int[n];
        for (int i = 0; i < n; i++) {
            asteroids[i] = sc.nextInt();
        }

        int[] result = asteroidCollision(asteroids);

        for (int val : result) {
            out.print(val + " ");
        }
        out.println();
    }

    public static int[] asteroidCollision(int[] asteroids) {
        LinkedList<Integer> stk = new LinkedList<>();

        for (int i = 0; i < asteroids.length; i++) {
            if (asteroids[i] < 0) {
                while (!stk.isEmpty() && stk.getLast() > 0 && -asteroids[i] > stk.getLast()) {
                    stk.pollLast();
                }
                if (!stk.isEmpty() && -asteroids[i] == stk.getLast()) {
                    stk.pollLast();
                } else if (stk.isEmpty() || stk.getLast() < 0) {
                    stk.add(asteroids[i]);
                }
            } else {
                stk.add(asteroids[i]);
            }
        }

        return stk.stream().mapToInt(i -> i).toArray();
    }
}

