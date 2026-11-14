public class Week12AssignmentQuestion2 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Finds the k-th node from the end in a single pass using two pointers (lead and trail).
     */
    public static String kthFromEnd(Node head, int k) {
        if (head == null || k <= 0) {
            return "Invalid k";
        }

        Node lead = head;
        Node trail = head;

        // Advance lead pointer k steps ahead
        for (int i = 0; i < k; i++) {
            if (lead == null) {
                return "Invalid k"; // k is greater than the total number of nodes
            }
            lead = lead.next;
        }

        // Advance both lead and trail until lead reaches the end (null)
        while (lead != null) {
            lead = lead.next;
            trail = trail.next;
        }

        return k + " from the end: " + trail.data;
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
        Node current = head;
        while (current != null) {
            System.out.print(current.data);
            if (current.next != null) {
                System.out.print(" -> ");
            }
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample 1: announcements = 301 -> 302 -> 303 -> 304 -> 305 -> 306, k = 2
        Node sample1 = buildList(new int[] { 301, 302, 303, 304, 305, 306 });
        int k1 = 2;
        System.out.print("Sample 1: ");
        printList(sample1);
        System.out.println("Result:   " + kthFromEnd(sample1, k1));

        // Sample 2: announcements = 301 -> 302 -> 303, k = 5
        Node sample2 = buildList(new int[] { 301, 302, 303 });
        int k2 = 5;
        System.out.print("\nSample 2: ");
        printList(sample2);
        System.out.println("Result:   " + kthFromEnd(sample2, k2));

        System.out.println("\n--- Complexities & Two-Pass Comparison ---");
        System.out.println("Time Complexity:          O(n), visits list nodes in a single forward pass.");
        System.out.println("Auxiliary Space:          O(1), only two pointers (lead and trail).");
        System.out.println("Comparison with 2-pass:   A two-pass solution first counts length N (N steps) and then moves (N-k) steps,");
        System.out.println("                          taking 2N - k operations. The single-pass approach uses exactly N pointer steps");
        System.out.println("                          and works efficiently even in streaming/piped data scenarios.");
    }
}
