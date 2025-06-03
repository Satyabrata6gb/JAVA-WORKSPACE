package StriverSheet.LinkedList.Medium_LL;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.util.Scanner;

public class AddOneToLL {
    static class Node {
        int data;
        Node next;

        Node(int x) {
            data = x;
            next = null;
        }
    }

    static Node reverse(Node head) {
        Node prev = null, tmp;
        while (head != null) {
            tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    Node revAndAddOne(Node head) {
        Node prev = null, tmp;
        int rem = 1;
        while (head != null){

            head.data += rem;
            rem = head.data / 10;
            head.data = head.data % 10;

            tmp = head.next;
            head.next = prev;
            prev = head;
            head = tmp;
        }
        return prev;
    }

    public static Node addOne(Node head) {

        head = reverse(head);

        Node temp = head;
        int rem = 1;

        while(temp != null || rem > 0){
            temp.data += rem;
            rem = temp.data / 10;
            temp.data = temp.data % 10;

            if(temp.next == null && rem > 0){
                temp.next = new Node(rem);
                rem = 0;
            }

            temp = temp.next;
        }

        head = reverse(head);

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

    public static Node constructLL(int[] arr) {

        Node head = new Node(arr[0]);

        Node temp = head;

        for(int i = 1 ; i < arr.length ; i++){
            Node newNode = new Node(arr[i]);

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

            Node head = constructLL(a);

            printList(addOne(head));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
