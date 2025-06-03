package StriverSheet.LinkedList.Learn_1D_LinkedList;

import java.io.*;
import java.util.*;

public class SearchElement {
    
    public static int searchInLinkedList(Node head, int k)
    {
        while(head != null){
            if(head.data == k) return 1;
            head = head.next;
        }

        return 0;
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

    public static Node constructLL(int []arr) {

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

            int value = scanner.nextInt();

            int[] a = new int[n];

            for(int i = 0 ; i < n ; i++){
                a[i] = scanner.nextInt();
            }

            Node head = constructLL(a);

            System.out.println(searchInLinkedList(head, value));

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}

