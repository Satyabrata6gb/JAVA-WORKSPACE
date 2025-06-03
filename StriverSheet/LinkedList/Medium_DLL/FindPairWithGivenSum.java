package StriverSheet.LinkedList.Medium_DLL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class FindPairWithGivenSum {
    static class Node
    {
        int data;
        Node next;
        Node prev;
        Node(int data)
        {
            this.data = data;
            next = prev = null;
        }
    }

    public static ArrayList<ArrayList<Integer>> findPairsWithGivenSum(int target, Node head) {
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();

        Node tail = head;

        while(tail.next != null) tail = tail.next;

        while(head.data < tail.data ){
            if(head.data + tail.data == target){
                result.add(new ArrayList(Arrays.asList(head.data, tail.data)));
                head = head.next;
                tail = tail.prev;
            }

            if(head.data + tail.data > target){
                tail = tail.prev;
            }

            if(head.data + tail.data < target){
                head = head.next;
            }
        }


        return result;
    }

    public static void printList(ArrayList<ArrayList<Integer>> node){

        for(ArrayList<Integer> data : node){
            System.out.println("(" + data.get(0) + " " + data.get(1) + ")");
        }
    }

    public static Node constructDLL(int[] arr) {
        if (arr == null || arr.length == 0) return null;

        Node head = new Node(arr[0]);
        Node temp = head;

        for (int i = 1; i < arr.length; i++) {
            Node newNode = new Node(arr[i]);
            temp.next = newNode;
            newNode.prev = temp;
            temp = newNode;
        }

        return head;
    }


    public static void main(String[] args) {
        File file = new File("input.txt");

        try (Scanner scanner = new Scanner(new FileReader(file));
             PrintStream out = new PrintStream(new FileOutputStream("output.txt", false), true);) {

            System.setOut(out);

            int n = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n ; i++){
                a[i] = scanner.nextInt();
            }

            Node head = constructDLL(a);

            int target = scanner.nextInt();

            printList(findPairsWithGivenSum(target, head));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
