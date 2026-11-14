public class Week10AssignmentQuestion2 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Time Complexity: O(n)
    // Auxiliary Space Complexity: O(1)
    public static Node removeAll(Node head, int code) {
        // Remove matching nodes from the front
        while (head != null && head.data == code) {
            head = head.next;
        }

        if (head == null) {
            return null;
        }

        // Walk with a reference and skip matching nodes
        Node current = head;
        while (current.next != null) {
            if (current.next.data == code) {
                current.next = current.next.next;
            } else {
                current = current.next;
            }
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

    public static Node buildList(int[] values) {
        if (values == null || values.length == 0) return null;
        Node head = new Node(values[0]);
        Node current = head;
        for (int i = 1; i < values.length; i++) {
            current.next = new Node(values[i]);
            current = current.next;
        }
        return head;
    }

    public static void main(String[] args) {
        // Sample 1: orders = 5 -> 3 -> 5 -> 8 -> 5, code = 5
        Node sample1 = buildList(new int[] { 5, 3, 5, 8, 5 });
        System.out.print("Sample 1 Input:  ");
        printList(sample1);
        sample1 = removeAll(sample1, 5);
        System.out.print("Sample 1 Output: ");
        printList(sample1);

        // Sample 2: orders = 5 -> 5 -> 5, code = 5
        Node sample2 = buildList(new int[] { 5, 5, 5 });
        System.out.print("\nSample 2 Input:  ");
        printList(sample2);
        sample2 = removeAll(sample2, 5);
        System.out.print("Sample 2 Output: ");
        printList(sample2);

        System.out.println("\n--- Complexities ---");
        System.out.println("Time Complexity: O(n), where n is the number of nodes in the list.");
        System.out.println("Auxiliary Space Complexity: O(1), in-place removal with no extra nodes created.");
    }
}
