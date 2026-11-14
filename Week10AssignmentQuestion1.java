class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class TokenLinkedList {
    Node head;
    Node tail;
    int size = 0;

    // Time Complexity: O(1)
    public void addFirst(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }
        size++;
    }

    // Time Complexity: O(1) using tail reference (O(n) if traversing from head)
    public void addLast(int token) {
        Node newNode = new Node(token);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }

    // Time Complexity: O(index) <= O(n)
    public void insertAt(int index, int token) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index: " + index + ", Current size: " + size);
        }
        if (index == 0) {
            addFirst(token);
            return;
        }
        if (index == size) {
            addLast(token);
            return;
        }

        Node newNode = new Node(token);
        Node current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
        size++;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }
}

public class Week10AssignmentQuestion1 {
    public static void main(String[] args) {
        TokenLinkedList list = new TokenLinkedList();

        // Starting list: 101 -> 102 -> 103
        list.addLast(101);
        list.addLast(102);
        list.addLast(103);

        System.out.print("Initial List: ");
        list.printList();

        // Operations: addFirst(100), addLast(104), insertAt(2, 150)
        list.addFirst(100);
        list.addLast(104);
        list.insertAt(2, 150);

        System.out.print("Final List: ");
        list.printList();

        System.out.println("\n--- Complexities ---");
        System.out.println("addFirst(token): Time O(1), Space O(1)");
        System.out.println("addLast(token):  Time O(1) with tail reference, Space O(1)");
        System.out.println("insertAt(index, token): Time O(k) where k is index (O(n) worst case), Space O(1)");
    }
}
