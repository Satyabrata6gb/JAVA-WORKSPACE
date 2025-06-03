package StriverSheet.LinkedList.Medium_DLL;

import StriverSheet.LinkedList.Medium_LL.AddOneToLL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class DeleteAllNodeWithTargetVal {
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

    static Node deleteAllOccurOfX(Node head, int v) {

        Node temp = head;

        while(temp != null && temp.data == v){
            Node pre = temp;
            temp = temp.next;

            pre.next = null;
            temp.prev = null;
            head = temp;
        }

        while(temp != null){

            while(temp.next != null && temp.next.data == v){
                temp.next = temp.next.next;
                if(temp.next != null) temp.next.prev = temp;
            }
            temp = temp.next;
        }

        return head;
    }

    public static void printList(Node node){

        while(node != null){
            if(node.next == null){
                System.out.print(node.data);
            }else{
                System.out.print(node.data + "->" );
            }

            node = node.next;
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

            printList(deleteAllOccurOfX(head, target));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
