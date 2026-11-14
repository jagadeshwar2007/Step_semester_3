public class Week12AssignmentQuestion1 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Finds the middle seat in a single pass without counting total length first.
     * For even number of seats, returns the second of the two middle seats.
     */
    public static int middle(Node head) {
        if (head == null) {
            throw new IllegalArgumentException("List is empty");
        }

        Node slow = head;
        Node fast = head;
        int fastVisits = 0;

        // Move slow by 1 step, fast by 2 steps
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            fastVisits++;
        }

        return slow.data;
    }

    public static Node buildList(int[] seats) {
        if (seats == null || seats.length == 0) return null;
        Node head = new Node(seats[0]);
        Node current = head;
        for (int i = 1; i < seats.length; i++) {
            current.next = new Node(seats[i]);
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
        // Sample 1: seats = 11 -> 22 -> 33 -> 44 -> 55 (Odd count)
        Node sample1 = buildList(new int[] { 11, 22, 33, 44, 55 });
        System.out.print("Sample 1 seats: ");
        printList(sample1);
        System.out.println("Middle: " + middle(sample1));

        // Sample 2: seats = 11 -> 22 -> 33 -> 44 -> 55 -> 66 (Even count)
        Node sample2 = buildList(new int[] { 11, 22, 33, 44, 55, 66 });
        System.out.print("\nSample 2 seats: ");
        printList(sample2);
        System.out.println("Middle: " + middle(sample2));

        System.out.println("\n--- Complexities & Analysis ---");
        System.out.println("Time Complexity:        O(n), traverses the list in a single pass.");
        System.out.println("Auxiliary Space:        O(1), only two node pointers (slow and fast) are maintained.");
        System.out.println("Fast Pointer Traversal: Fast pointer visits ceil(n / 2) step-pairs, jumping 2 nodes per iteration.");
    }
}
