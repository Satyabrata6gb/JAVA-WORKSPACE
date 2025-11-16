package StriverSheet.StackAndQueue.ImplementationProblems;

import java.io.*;
import java.util.*;

public class OnlineStockSpan {

    // ---------- Custom Pair class ----------
    static class Pair<K, V> {
        K key;
        V value;

        Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    // ---------- StockSpanner Implementation ----------
    static class StockSpanner {

        Stack<Pair<Integer,Integer>> stk;
        int i;

        public StockSpanner() {
            stk = new Stack<>();
            i = -1;
        }

        public int next(int price) {
            while(!stk.isEmpty() && stk.peek().getKey() <= price){
                stk.pop();
            }

            int start = stk.isEmpty() ? -1 : stk.peek().getValue();

            stk.push(new Pair(price, ++i));

            return i - start;
        }
    }

    // -------------- Runner Code (LeetCode Style) -----------------
    public static void main(String[] args) throws Exception {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        Scanner sc = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        // Read commands
        String[] commands = sc.nextLine()
                .replaceAll("[\\[\\]\"]", "")
                .split(",\\s*");

        // Read parameters
        String[] paramLines = sc.nextLine()
                .replaceAll("\\[\\[|\\]\\]", "")
                .split("\\],\\s*\\[");

        StockSpanner spanner = null;
        List<String> results = new ArrayList<>();

        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i].trim();
            String params = paramLines[i].trim();

            if (cmd.equals("StockSpanner")) {
                spanner = new StockSpanner();
                results.add("null");
            }
            else if (cmd.equals("next")) {
                int price = Integer.parseInt(params);
                int val = spanner.next(price);
                results.add(String.valueOf(val));
            }
        }

        out.println(results);

        sc.close();
        out.close();
    }
}


//["StockSpanner","next","next","next","next","next","next","next"]
//        [[ ],[100],[80],[60],[70],[60],[75],[85]]
