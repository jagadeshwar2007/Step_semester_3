public class Week12AssignmentQuestion4 {

    static class TabNode {
        String name;
        TabNode prev;
        TabNode next;

        TabNode(String name) {
            this.name = name;
            this.prev = null;
            this.next = null;
        }
    }

    static class BrowserTabStrip {
        TabNode head;
        TabNode current;

        // Time Complexity: O(1)
        public void open(String name) {
            TabNode newTab = new TabNode(name);
            if (head == null) {
                head = newTab;
                current = newTab;
            } else {
                newTab.prev = current;
                newTab.next = current.next;
                if (current.next != null) {
                    current.next.prev = newTab;
                }
                current.next = newTab;
                current = newTab;
            }
            System.out.println("OPEN " + name + ": " + getForwardString());
        }

        // Time Complexity: O(1)
        public void close() {
            if (current == null) {
                System.out.println("CLOSE: no open tabs");
                return;
            }

            String closedName = current.name;
            TabNode prevNode = current.prev;
            TabNode nextNode = current.next;

            if (prevNode != null) {
                prevNode.next = nextNode;
            } else {
                head = nextNode; // removing head
            }

            if (nextNode != null) {
                nextNode.prev = prevNode;
            }

            // Next tab becomes current, or the previous one if no next tab exists
            if (nextNode != null) {
                current = nextNode;
            } else {
                current = prevNode;
            }

            System.out.println("CLOSE " + closedName + ": " + getForwardString());
        }

        // Time Complexity: O(1)
        public void back() {
            if (current != null && current.prev != null) {
                current = current.prev;
                System.out.println("BACK: " + getForwardString());
            } else {
                System.out.println("BACK: already at first tab");
            }
        }

        // Time Complexity: O(1)
        public void forward() {
            if (current != null && current.next != null) {
                current = current.next;
                System.out.println("FORWARD: " + getForwardString());
            } else {
                System.out.println("FORWARD: already at last tab");
            }
        }

        public String getForwardString() {
            if (head == null) return "empty";
            StringBuilder sb = new StringBuilder();
            TabNode temp = head;
            while (temp != null) {
                if (temp == current) {
                    sb.append("[").append(temp.name).append("]");
                } else {
                    sb.append(temp.name);
                }
                if (temp.next != null) {
                    sb.append(" <-> ");
                }
                temp = temp.next;
            }
            return sb.toString();
        }

        public void printBackward() {
            if (head == null) {
                System.out.println("Backward: empty");
                return;
            }
            TabNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            System.out.print("Backward: ");
            while (temp != null) {
                System.out.print(temp.name);
                if (temp.prev != null) {
                    System.out.print(" <-> ");
                }
                temp = temp.prev;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        BrowserTabStrip browser = new BrowserTabStrip();

        // Sequence: OPEN Mail, OPEN Docs, OPEN Maps, BACK, BACK, BACK, OPEN Drive, CLOSE, FORWARD, CLOSE, PRINT BACKWARD
        browser.open("Mail");
        browser.open("Docs");
        browser.open("Maps");
        browser.back();
        browser.back();
        browser.back();
        browser.open("Drive");
        browser.close();
        browser.forward();
        browser.close();
        browser.printBackward();

        System.out.println("\n--- Complexities ---");
        System.out.println("open(name):       Time O(1)");
        System.out.println("close():          Time O(1)");
        System.out.println("back():           Time O(1)");
        System.out.println("forward():        Time O(1)");
        System.out.println("printBackward():  Time O(T), where T is number of open tabs");
        System.out.println("Auxiliary Space:  O(1) per pointer modification");
    }
}
