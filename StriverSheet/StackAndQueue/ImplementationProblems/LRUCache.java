package StriverSheet.StackAndQueue.ImplementationProblems;

import java.io.*;
import java.util.*;

public class LRUCache {

    // ================= Node Class =================
    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
            next = prev = null;
        }
    }

    // ================= LRU Cache Fields =================
    static Node[] map; // array mapping key → Node
    static int capacity;
    static int count;
    static Node head;
    static Node tail;

    // ================= Constructor =================
    public LRUCache(int capacity_) {
        map = new Node[10005];     // fixed array for keys up to 10^4
        capacity = capacity_;
        count = 0;

        head = new Node(-1, -1);
        tail = new Node(-1, -1);
        head.next = tail;
        tail.prev = head;
    }

    // ================= Helper: Insert at Head =================
    public static void insertAtHead(Node node) {
        Node currHead = head.next;
        head.next = node;
        node.prev = head;
        node.next = currHead;
        currHead.prev = node;
    }

    // ================= Helper: Delete Node =================
    public static void deleteNode(Node node) {
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
    }

    // ================= GET =================
    public static int get(int key) {
        if (key >= map.length || map[key] == null) return -1;

        Node node = map[key];

        deleteNode(node);
        insertAtHead(node);

        return node.value;
    }

    // ================= PUT =================
    public static void put(int key, int value) {
        if (key >= map.length) return;

        if (map[key] != null) {
            Node existingNode = map[key];
            existingNode.value = value;

            deleteNode(existingNode);
            insertAtHead(existingNode);
            return;
        }

        Node node = new Node(key, value);

        if (count == capacity) {
            Node lastNode = tail.prev;

            deleteNode(lastNode);
            map[lastNode.key] = null;
            count--;
        }

        insertAtHead(node);
        map[key] = node;
        count++;
    }

    // ================= RUNNER (For input.txt) =================
    public static void main(String[] args) throws Exception {
        File inputFile = new File("input.txt");
        File outputFile = new File("output.txt");

        Scanner sc = new Scanner(inputFile);
        PrintWriter out = new PrintWriter(new FileWriter(outputFile));

        // Read commands array
        String[] commands = sc.nextLine()
                .replaceAll("[\\[\\]\"]", "")
                .split(",\\s*");

        // Read parameters array
        String[] paramLines = sc.nextLine()
                .replaceAll("\\[\\[|\\]\\]", "")
                .split("\\],\\s*\\[");

        List<String> results = new ArrayList<>();

        LRUCache cache = null;

        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i].trim();
            String[] params = paramLines[i].trim().split(",");

            if (cmd.equals("LRUCache")) {
                int cap = Integer.parseInt(params[0].trim());
                cache = new LRUCache(cap);
                results.add("null");
            }
            else if (cmd.equals("put")) {
                int key = Integer.parseInt(params[0].trim());
                int value = Integer.parseInt(params[1].trim());
                put(key, value);
                results.add("null");
            }
            else if (cmd.equals("get")) {
                int key = Integer.parseInt(params[0].trim());
                results.add(String.valueOf(get(key)));
            }
        }

        out.println(results);

        sc.close();
        out.close();
    }
}

//["LRUCache","put","put","get","put","get","put","get","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[4,4],[1],[3],[4]]