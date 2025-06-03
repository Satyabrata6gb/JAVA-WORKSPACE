package StriverSheet.LinkedList.Medium_LL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class AddTwoLL {
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

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode temp1 = l1, temp2 = l2;
        int carry = 0;

        ListNode result = new ListNode(0);
        ListNode temp = result;

        while(temp1 != null || temp2 != null){
            int val1 = temp1 == null ? 0 : temp1.val;
            int val2 = temp2 == null ? 0 : temp2.val;

            int res = val1 + val2 + carry;
            carry = res / 10;
            res = res % 10;

            temp.next = new ListNode(res, null);
            temp = temp.next;

            temp1 = (temp1 != null) ? temp1.next : temp1;
            temp2 = (temp2 != null) ? temp2.next : temp2;
        }

        if(carry > 0){
            temp.next = new ListNode(carry, null);
        }

        return result.next;
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

            int m = scanner.nextInt();

            int[] b = new int[n];

            for(int i = 0 ; i < n ; i++){
                b[i] = scanner.nextInt();
            }

            ListNode headA = constructLL(a);
            ListNode headB = constructLL(b);

            printList(addTwoNumbers(headA, headB));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
