package StriverSheet.LinkedList.Hard_LL;

public class FlattenALL {

    static class Node {
        int data;
        Node next;
        Node child;

        // Constructors to initialize the
        // data, next, and child pointers
        Node() {
            this.data = 0;
            this.next = null;
            this.child = null;
        }

        Node(int x) {
            this.data = x;
            this.next = null;
            this.child = null;
        }

        Node(int x, Node nextNode, Node childNode) {
            this.data = x;
            this.next = nextNode;
            this.child = childNode;
        }
    }

    public static class FlattenLinkedList {
        // Merges two linked lists in a particular
        // order based on the data value
        public static Node merge(Node list1, Node list2) {
            Node dummyNode = new Node(-1);
            Node temp = dummyNode;

            while(list1 != null && list2 != null) {
                if(list1.data < list2.data) {
                    temp.child = list1;
                    temp = list1;
                    list1 = list1.child;
                }else{
                    temp.child = list2;
                    temp = list2;
                    list2 = list2.child;
                }
                temp.next = null;
            }

            if(list1 != null) {
                temp.child = list1;
            }
            if(list2 != null) {
                temp.child = list2;
            }

            if (dummyNode.child != null) {
                dummyNode.child.next = null;
            }

            return dummyNode.child;
        }

        // Flattens a linked list with child pointers
        public static Node flattenLinkedList(Node head) {
            if(head == null || head.next == null) return head;

            Node mergedHead = flattenLinkedList(head.next);
            head = merge(head, mergedHead);
            return head;
        }

        // Print the linked list by
        // traversing through child pointers
        public static void printLinkedList(Node head) {
            while (head != null) {
                System.out.print(head.data + " ");
                head = head.child;
            }
            System.out.println();
        }

        // Print the linked list
        // in a grid-like structure
        public static void printOriginalLinkedList(Node head, int depth) {
            while (head != null) {
                System.out.print(head.data);

                // If child exists, recursively
                // print it with indentation
                if (head.child != null) {
                    System.out.print(" -> ");
                    printOriginalLinkedList(head.child, depth + 1);
                }

                // Add vertical bars
                // for each level in the grid
                if (head.next != null) {
                    System.out.println();
                    for (int i = 0; i < depth; ++i) {
                        System.out.print("| ");
                    }
                }
                head = head.next;
            }
        }

        public static void main(String[] args) {
            // Create a linked list with child pointers
            Node head = new Node(5);
            head.child = new Node(14);

            head.next = new Node(4);
            head.next.child = new Node(10);

            head.next.next = new Node(12);
            head.next.next.child = new Node(13);
            head.next.next.child.child = new Node(20);

            head.next.next.next = new Node(7);
            head.next.next.next.child = new Node(17);

            // Print the original
            // linked list structure
            System.out.println("Original linked list:");
            printOriginalLinkedList(head, 0);

            // Flatten the linked list
            // and print the flattened list
            Node flattened = flattenLinkedList(head);
            System.out.print("\nFlattened linked list: ");
            printLinkedList(flattened);
        }
    }

}
