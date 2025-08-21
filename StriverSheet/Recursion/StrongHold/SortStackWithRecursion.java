package StriverSheet.Recursion.StrongHold;

import java.io.*;
import java.util.*;

class SortStackWithRecursion {

    public static void sortStack(Stack<Integer> s, int x) {
        if (s.isEmpty() || x > s.peek()) {
            s.push(x);
            return;
        }

        int temp = s.pop();
        sortStack(s, x);
        s.push(temp);
    }

    public static void sort(Stack<Integer> s) {
        if (!s.isEmpty()) {
            int top = s.pop();
            sort(s);
            sortStack(s, top);
        }
    }

    public static void sortStack(Stack<Integer> st) {
        sort(st);
    }

    // Helper method to print stack from bottom to top
    public static void printStack(Stack<Integer> s, PrintWriter writer) {
        if (s.isEmpty()) {
            writer.println("Stack is empty");
            return;
        }

        List<Integer> temp = new ArrayList<>();
        while (!s.isEmpty()) {
            temp.add(s.pop());
        }

        // Print from bottom to top (ascending order after sorting)
        Collections.reverse(temp);
        for (int i = 0; i < temp.size(); i++) {
            if (i > 0) writer.print(" ");
            writer.print(temp.get(i));
        }
        writer.println();

        // Restore stack
        Collections.reverse(temp);
        for (int val : temp) {
            s.push(val);
        }
    }

    public static void main(String[] args) {
        try {
            File inputFile = new File("input.txt");
            File outputFile = new File("output.txt");

            Scanner scanner = new Scanner(inputFile);
            PrintWriter writer = new PrintWriter(outputFile);

            int testCase = 1;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;

                writer.println("Test Case " + testCase + ":");

                Stack<Integer> stack = new Stack<>();
                String[] elements = line.split("\\s+");

                writer.print("Original Stack (bottom to top): ");
                for (String element : elements) {
                    int num = Integer.parseInt(element);
                    stack.push(num);
                    writer.print(num + " ");
                }
                writer.println();

                // Sort the stack
                sortStack(stack);

                writer.print("Sorted Stack (bottom to top): ");
                printStack(stack, writer);
                writer.println();

                testCase++;
            }

            scanner.close();
            writer.close();

            System.out.println("Stack sorting completed! Check output.txt for results.");

        } catch (FileNotFoundException e) {
            System.err.println("Error: input.txt file not found!");
        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        }
    }
}