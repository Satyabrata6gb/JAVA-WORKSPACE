package StriverSheet.LinkedList.Hard_LL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class ReverseNodeInKGrp {
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

    public static ListNode reverseKGroup(ListNode head, int k) {
        int count = 0;
        ListNode temp = head;

        while(temp != null && count < k){
            count++;
            temp = temp.next;
        }

        if(count == k){

            temp = reverseKGroup(temp, k);

            while(count > 0){
                ListNode next = head.next;
                head.next = temp;
                temp = head;
                head = next;
                count--;
            }

            head = temp;
        }

        return head;
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

            printList(reverseKGroup(headA, k));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
