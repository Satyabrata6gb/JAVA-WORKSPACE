package StriverSheet.LinkedList.Medium_LL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class SOrt012LL {
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

    public static ListNode sortList(ListNode head) {
        if (head == null || head.next == null) return head;

        ListNode zero = new ListNode(0);
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);

        ListNode zeroDummy = zero, oneDummy = one, twoDummy = two;

        while (head != null) {
            if (head.val == 0) {
                zeroDummy.next = head;
                zeroDummy = zeroDummy.next;
            } else if (head.val == 1) {
                oneDummy.next = head;
                oneDummy = oneDummy.next;
            } else if (head.val == 2) {
                twoDummy.next = head;
                twoDummy = twoDummy.next;
            }

            head = head.next;
        }

        zeroDummy.next = (one.next == null) ? two.next : one.next;
        oneDummy.next = two.next;
        twoDummy.next = null;

        return zero.next;
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

            ListNode head = constructLL(a);

            printList(sortList(head));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}