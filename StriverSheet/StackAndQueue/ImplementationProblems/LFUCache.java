package StriverSheet.StackAndQueue.ImplementationProblems;

import java.io.*;
import java.util.*;

public class LFUCache {

    final int capacity;
    int curSize;
    int minFrequency;

    Map<Integer, DLLNode> cache;
    Map<Integer, DoubleLinkedList> freqMap;

    public LFUCache(int capacity) {
        this.capacity = capacity;
        this.curSize = 0;
        this.minFrequency = 0;
        this.cache = new HashMap<>();
        this.freqMap = new HashMap<>();
    }

    public int get(int key) {
        DLLNode node = cache.get(key);
        if (node == null) return -1;

        updateNode(node);
        return node.val;
    }

    public void put(int key, int value) {
        if (capacity == 0) return;

        if (cache.containsKey(key)) {
            DLLNode node = cache.get(key);
            node.val = value;
            updateNode(node);
            return;
        }

        curSize++;
        if (curSize > capacity) {
            DoubleLinkedList minList = freqMap.get(minFrequency);
            DLLNode evict = minList.removeLast();
            cache.remove(evict.key);
            curSize--;
        }

        minFrequency = 1;

        DLLNode newNode = new DLLNode(key, value);
        DoubleLinkedList list = freqMap.getOrDefault(1, new DoubleLinkedList());
        list.addNode(newNode);
        freqMap.put(1, list);
        cache.put(key, newNode);
    }

    private void updateNode(DLLNode node) {
        int freq = node.frequency;
        DoubleLinkedList oldList = freqMap.get(freq);
        oldList.removeNode(node);

        if (freq == minFrequency && oldList.listSize == 0) {
            minFrequency++;
        }

        node.frequency++;

        DoubleLinkedList newList = freqMap.getOrDefault(node.frequency, new DoubleLinkedList());
        newList.addNode(node);
        freqMap.put(node.frequency, newList);
    }


    // ================================
    // -------- RUNNER CODE -----------
    // ================================
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

        List<String> results = new ArrayList<>();

        LFUCache cache = null;

        for (int i = 0; i < commands.length; i++) {
            String cmd = commands[i].trim();
            String[] params = paramLines[i].trim().split(",");

            if (cmd.equals("LFUCache")) {
                int cap = Integer.parseInt(params[0].trim());
                cache = new LFUCache(cap);
                results.add("null");
            }
            else if (cmd.equals("put")) {
                int key = Integer.parseInt(params[0].trim());
                int val = Integer.parseInt(params[1].trim());
                cache.put(key, val);
                results.add("null");
            }
            else if (cmd.equals("get")) {
                int key = Integer.parseInt(params[0].trim());
                int val = cache.get(key);
                results.add(String.valueOf(val));
            }
        }

        out.println(results);

        sc.close();
        out.close();
    }
}


// ===========================
// DLLNode
// ===========================
class DLLNode {
    int key;
    int val;
    int frequency;
    DLLNode prev;
    DLLNode next;

    public DLLNode(int key, int val) {
        this.key = key;
        this.val = val;
        this.frequency = 1;
    }
}


// ===========================
// Double Linked List
// ===========================
class DoubleLinkedList {
    int listSize;
    DLLNode head;
    DLLNode tail;

    public DoubleLinkedList() {
        this.listSize = 0;
        head = new DLLNode(0, 0);
        tail = new DLLNode(0, 0);
        head.next = tail;
        tail.prev = head;
    }

    public void addNode(DLLNode node) {
        DLLNode nextNode = head.next;
        head.next = node;
        node.prev = head;
        node.next = nextNode;
        nextNode.prev = node;
        listSize++;
    }

    public void removeNode(DLLNode node) {
        DLLNode prevNode = node.prev;
        DLLNode nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        listSize--;
    }

    public DLLNode removeLast() {
        if (listSize > 0) {
            DLLNode last = tail.prev;
            removeNode(last);
            return last;
        }
        return null;
    }
}

//["LFUCache","put","put","get","put","get","get"]
//[[2],[1,1],[2,2],[1],[3,3],[2],[3]]