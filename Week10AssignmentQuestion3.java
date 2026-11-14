public class Week10AssignmentQuestion3 {
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // In-place reversal: Time O(n), Auxiliary Space O(1)
    public static Node reverse(Node head) {
        Node previous = null;
        Node current = head;
        Node next = null;

        while (current != null) {
            next = current.next;       // store next node
            current.next = previous;   // reverse pointer
            previous = current;        // move previous forward
            current = next;            // move current forward
        }

        return previous; // new head
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
        // Sample 1: queue = 1 -> 2 -> 3 -> 4
        Node sample1 = buildList(new int[] { 1, 2, 3, 4 });
        System.out.print("Sample 1 Input:  ");
        printList(sample1);
        sample1 = reverse(sample1);
        System.out.print("Sample 1 Output: ");
        printList(sample1);

        // Sample 2: queue = 9
        Node sample2 = buildList(new int[] { 9 });
        System.out.print("\nSample 2 Input:  ");
        printList(sample2);
        sample2 = reverse(sample2);
        System.out.print("Sample 2 Output: ");
        printList(sample2);

        System.out.println("\n--- Complexities ---");
        System.out.println("Time Complexity: O(n), where n is the number of nodes.");
        System.out.println("Auxiliary Space Complexity: O(1), in-place pointer reversal without allocating any new nodes.");
    }
}
