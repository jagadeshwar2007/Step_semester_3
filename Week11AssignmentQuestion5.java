public class Week11AssignmentQuestion5 {

    public static class Entry {
        String name;
        int marks;

        public Entry(String name, int marks) {
            this.name = name;
            this.marks = marks;
        }

        @Override
        public String toString() {
            return name + " " + marks;
        }
    }

    /**
     * Produces a descending leaderboard using Counting Sort in O(n + 101) time.
     * Preserves original submission order for equal marks by iterating right-to-left.
     */
    public static Entry[] leaderboard(Entry[] entries) {
        if (entries == null || entries.length == 0) {
            return new Entry[0];
        }

        int n = entries.length;
        final int MAX_MARKS = 100;
        // Key transformation: key = 100 - marks, where 100 becomes key 0, 99 becomes key 1, etc.
        // key ranges from 0 to 100 (size 101).
        int[] count = new int[MAX_MARKS + 1];

        // Step 1: Count frequency of each key
        for (int i = 0; i < n; i++) {
            int key = MAX_MARKS - entries[i].marks;
            count[key]++;
        }

        // Step 2: Transform counts into cumulative positions
        for (int i = 1; i <= MAX_MARKS; i++) {
            count[i] += count[i - 1];
        }

        // Step 3: Build output array walking right to left to maintain stability
        Entry[] output = new Entry[n];
        for (int i = n - 1; i >= 0; i--) {
            int key = MAX_MARKS - entries[i].marks;
            int position = count[key] - 1;
            output[position] = entries[i];
            count[key]--;
        }

        return output;
    }

    public static void printLeaderboard(Entry[] entries) {
        for (int i = 0; i < entries.length; i++) {
            System.out.print(entries[i]);
            if (i < entries.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample Input: Asha 90, Ravi 75, Neha 90, Kiran 100, Meera 75
        Entry[] entries = {
            new Entry("Asha", 90),
            new Entry("Ravi", 75),
            new Entry("Neha", 90),
            new Entry("Kiran", 100),
            new Entry("Meera", 75)
        };

        System.out.print("Input:  ");
        printLeaderboard(entries);

        Entry[] result = leaderboard(entries);

        System.out.print("Output: ");
        printLeaderboard(result);

        System.out.println("\n--- Complexities & Properties ---");
        System.out.println("Time Complexity:  O(n + K) where K = 101, non-comparison based linear time.");
        System.out.println("Auxiliary Space:  O(n + K) for output array and count table.");
        System.out.println("Stability:        Stable (walking right-to-left preserves original submission order for equal marks).");
    }
}
