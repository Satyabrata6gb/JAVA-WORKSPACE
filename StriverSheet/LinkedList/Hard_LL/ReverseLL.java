package StriverSheet.LinkedList.Hard_LL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class ReverseLL {
    static class ListNode {
        int val;
        ListNode next;

        ListNode(int data1) {
            val = data1;
            next = null;
        }

        ListNode(int data1, ListNode next1) {
            val = data1;
            next = next1;
        }
    }

    public static ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null) return head;

        ListNode newH , tail;

        newH = tail = head;

        int len = 1;

        while(tail.next != null){
            len++;
            tail = tail.next;
        }

        k = k % len;

        tail.next = head;

        for(int i = 0 ; i < len - k ; i++){
            tail = tail.next;
        }

        newH = tail.next;
        tail.next = null;

        return newH;
    }

    public static void printList(ListNode node){

        while(node != null){
            if(node.next == null){
                System.out.print(node.val);
            }else{
                System.out.print(node.val + "->" );
            }

            node = node.next;
        }
    }

    public static ListNode constructLL(int []arr) {

        ListNode head = new ListNode(arr[0]);

        ListNode temp = head;

        for(int i = 1 ; i < arr.length ; i++){
            ListNode newNode = new ListNode(arr[i]);

            temp.next = newNode;

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

            int k = scanner.nextInt();

            ListNode headA = constructLL(a);

            printList(rotateRight(headA, k));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
