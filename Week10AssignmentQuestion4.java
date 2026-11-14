public class Week10AssignmentQuestion4 {
    static class Node {
        String name;
        Node prev;
        Node next;

        Node(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static class TrainDoublyLinkedList {
        Node head;
        Node tail;

        // Time Complexity: O(1)
        public void addLast(String name) {
            Node newNode = new Node(name);
            if (head == null) {
                head = tail = newNode;
            } else {
                tail.next = newNode;
                newNode.prev = tail;
                tail = newNode;
            }
        }

        // Time Complexity: O(n) to find, O(1) to unlink once found
        public boolean remove(String name) {
            Node current = head;
            while (current != null) {
                if (current.name.equals(name)) {
                    if (current == head && current == tail) {
                        head = tail = null;
                    } else if (current == head) {
                        head = head.next;
                        head.prev = null;
                    } else if (current == tail) {
                        tail = tail.prev;
                        tail.next = null;
                    } else {
                        current.prev.next = current.next;
                        current.next.prev = current.prev;
                    }
                    return true;
                }
                current = current.next;
            }
            return false;
        }

        public void printForward() {
            System.out.print("Forward: ");
            Node current = head;
            while (current != null) {
                System.out.print(current.name);
                if (current.next != null) {
                    System.out.print(" <-> ");
                }
                current = current.next;
            }
            System.out.println();
        }

        public void printBackward() {
            System.out.print("Backward: ");
            Node current = tail;
            while (current != null) {
                System.out.print(current.name);
                if (current.prev != null) {
                    System.out.print(" <-> ");
                }
                current = current.prev;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        TrainDoublyLinkedList train = new TrainDoublyLinkedList();
        String[] initialCoaches = { "Engine", "A1", "B1", "B2", "Guard" };
        for (String coach : initialCoaches) {
            train.addLast(coach);
        }

        System.out.println("Before detachment:");
        train.printForward();
        train.printBackward();

        System.out.println("\nDetaching coach 'B1'...");
        train.remove("B1");

        System.out.println("\nAfter detachment:");
        train.printForward();
        train.printBackward();

        System.out.println("\n--- Complexities ---");
        System.out.println("Finding the coach: Time O(n), scanning up to n coaches.");
        System.out.println("Unlinking the coach once found: Time O(1), constant-time pointer reassignment.");
        System.out.println("Space Complexity: O(1) auxiliary space.");
    }
}
