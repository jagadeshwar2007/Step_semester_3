public class Week12AssignmentQuestion5 {

    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    /**
     * Partitions the list so that nodes with data < passMark come first,
     * followed by nodes with data >= passMark.
     * Preserves original relative order within both partitions in O(n) time and O(1) space.
     */
    public static Node partition(Node head, int passMark) {
        if (head == null || head.next == null) {
            return head;
        }

        Node belowHead = null, belowTail = null;
        Node restHead = null, restTail = null;

        Node current = head;
        while (current != null) {
            Node nextNode = current.next;
            current.next = null; // isolate current node

            if (current.data < passMark) {
                if (belowHead == null) {
                    belowHead = belowTail = current;
                } else {
                    belowTail.next = current;
                    belowTail = current;
                }
            } else {
                if (restHead == null) {
                    restHead = restTail = current;
                } else {
                    restTail.next = current;
                    restTail = current;
                }
            }
            current = nextNode;
        }

        // If no elements were below passMark, return restHead
        if (belowHead == null) {
            return restHead;
        }

        // Connect the tail of below chain to the head of rest chain
        belowTail.next = restHead;
        return belowHead;
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
        // Sample 1: marks = 55 -> 32 -> 78 -> 20 -> 40 -> 35, passMark = 40
        Node sample1 = buildList(new int[] { 55, 32, 78, 20, 40, 35 });
        int passMark1 = 40;
        System.out.print("Sample 1 Input:  ");
        printList(sample1);
        Node result1 = partition(sample1, passMark1);
        System.out.print("Sample 1 Output: ");
        printList(result1);

        // Sample 2: marks = 60 -> 70, passMark = 40
        Node sample2 = buildList(new int[] { 60, 70 });
        int passMark2 = 40;
        System.out.print("\nSample 2 Input:  ");
        printList(sample2);
        Node result2 = partition(sample2, passMark2);
        System.out.print("Sample 2 Output: ");
        printList(result2);

        System.out.println("\n--- Complexities & Analysis ---");
        System.out.println("Time Complexity:  O(n), traverses the list once in a single forward pass.");
        System.out.println("Auxiliary Space:  O(1), relinks existing node references in-place without creating new nodes.");
        System.out.println("Stability:        Stable (both partitions preserve original input order).");
    }
}
