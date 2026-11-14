public class Week10AssignmentQuestion5 {
    static class Node {
        String name;
        Node next;

        Node(String name) {
            this.name = name;
            this.next = null;
        }
    }

    static class CircularLinkedList {
        Node tail; // in a circular singly linked list, tail.next is head
        int size = 0;

        // Time Complexity: O(1)
        public void addLast(String name) {
            Node newNode = new Node(name);
            if (tail == null) {
                tail = newNode;
                tail.next = tail; // circular link to itself
            } else {
                newNode.next = tail.next; // new node points to head
                tail.next = newNode;      // old tail points to new node
                tail = newNode;           // update tail
            }
            size++;
        }

        // Time Complexity: O(turns)
        public void printTurns(int turns) {
            if (tail == null || turns <= 0) return;

            Node current = tail.next; // start at head
            for (int i = 0; i < turns; i++) {
                System.out.print(current.name);
                if (i < turns - 1) {
                    System.out.print(" ");
                }
                current = current.next; // circularly wraps around
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        CircularLinkedList game = new CircularLinkedList();
        String[] players = { "Asha", "Ravi", "Neha" };
        for (String player : players) {
            game.addLast(player);
        }

        int turns = 7;
        System.out.println("Players: Asha, Ravi, Neha | Turns: " + turns);
        System.out.print("Output:  ");
        game.printTurns(turns);

        System.out.println("\n--- Complexities ---");
        System.out.println("Adding a player (addLast): Time O(1) using tail reference, Space O(1).");
        System.out.println("Printing turns: Time O(T), where T is the number of turns requested.");
        System.out.println("Auxiliary Space: O(1).");
    }
}
