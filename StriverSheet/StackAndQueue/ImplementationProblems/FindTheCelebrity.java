package StriverSheet.StackAndQueue.ImplementationProblems;

import java.io.*;
import java.util.*;

class Relation {
    static int[][] MATRIX;

    public boolean knows(int a, int b) {
        return MATRIX[a][b] == 1;
    }
}

public class FindTheCelebrity extends Relation {

    public static void main(String[] args) throws Exception {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        Scanner sc = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        int n = sc.nextInt();
        MATRIX = new int[n][n];

        // Read adjacency matrix
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                MATRIX[i][j] = sc.nextInt();
            }
        }

        Solution sol = new Solution();
        int result = sol.findCelebrity(n);

        out.println(result);

        sc.close();
        out.close();
    }
}

class Solution extends Relation {

    public int findCelebrity(int n) {
        int top = 0;
        int bottom = n - 1;

        while (top < bottom) {
            if (knows(top, bottom)) {
                top++;
            } else if (knows(bottom, top)) {
                bottom--;
            } else {
                top++;
                bottom--;
            }
        }

        if (top > bottom) return -1;

        for (int i = 0; i < n; i++) {
            if (i == top) continue;
            if (!knows(i, top) || knows(top, i)) {
                return -1;
            }
        }
        return top;
    }
}

