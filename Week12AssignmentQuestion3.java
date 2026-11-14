public class Week12AssignmentQuestion3 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Merges two sorted singly linked lists by relinking existing nodes.
     * Uses a single dummy node and a tail reference.
     */
    public static Node merge(Node first, Node second) {
        Node dummy = new Node(-1);
        Node tail = dummy;

        while (first != null && second != null) {
            if (first.data <= second.data) {
                tail.next = first;
                first = first.next;
            } else {
                tail.next = second;
                second = second.next;
            }
            tail = tail.next;
        }

        // Attach whatever remains of the non-empty list in one step
        if (first != null) {
            tail.next = first;
        } else if (second != null) {
            tail.next = second;
        }

        return dummy.next;
    }

    public static Node buildList(int[] arr) {
        if (arr == null || arr.length == 0) return null;
        Node head = new Node(arr[0]);
        Node current = head;
        for (int i = 1; i < arr.length; i++) {
            current.next = new Node(arr[i]);
            current = current.next;
        }
        return head;
    }

    public static void printList(Node head) {
        if (head == null) {
            System.out.println("null");
            return;
        }
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        // Sample 1: first = 3 -> 8 -> 15, second = 5 -> 8 -> 20 -> 25
        Node first1 = buildList(new int[] { 3, 8, 15 });
        Node second1 = buildList(new int[] { 5, 8, 20, 25 });
        System.out.print("Sample 1 - List 1: ");
        printList(first1);
        System.out.print("Sample 1 - List 2: ");
        printList(second1);
        Node merged1 = merge(first1, second1);
        System.out.print("Merged Output:     ");
        printList(merged1);

        // Sample 2: first = (empty), second = 4 -> 9
        Node first2 = null;
        Node second2 = buildList(new int[] { 4, 9 });
        System.out.print("\nSample 2 - List 1: (empty)\nSample 2 - List 2: ");
        printList(second2);
        Node merged2 = merge(first2, second2);
        System.out.print("Merged Output:     ");
        printList(merged2);

        System.out.println("\n--- Complexities & Analysis ---");
        System.out.println("Time Complexity:  O(N + M), where N and M are the lengths of the two lists.");
        System.out.println("Auxiliary Space:  O(1), relinks existing nodes with only 1 dummy node and pointers.");
    }
}
