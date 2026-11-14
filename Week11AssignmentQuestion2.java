public class Week11AssignmentQuestion2 {

    public static class Team {
        String name;
        int size;

        public Team(String name, int size) {
            this.name = name;
            this.size = size;
        }

        @Override
        public String toString() {
            return name + "(" + size + ")";
        }
    }

    /**
     * Sorts teams by team size in ascending order using stable Insertion Sort.
     * Preserves relative registration order for teams of equal size.
     */
    public static void sortTeams(Team[] teams) {
        int n = teams.length;
        for (int i = 1; i < n; i++) {
            Team key = teams[i];
            int j = i - 1;

            // Shift strictly greater elements to the right.
            // Using '>' rather than '>=' preserves stability (registration order for ties).
            while (j >= 0 && teams[j].size > key.size) {
                teams[j + 1] = teams[j];
                j--;
            }
            teams[j + 1] = key;
        }
    }

    public static void printTeams(Team[] teams) {
        for (int i = 0; i < teams.length; i++) {
            System.out.print(teams[i]);
            if (i < teams.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Sample Input: Pixel(4), Byte(2), Nova(4), Echo(3), Atom(2)
        Team[] teams = {
            new Team("Pixel", 4),
            new Team("Byte", 2),
            new Team("Nova", 4),
            new Team("Echo", 3),
            new Team("Atom", 2)
        };

        System.out.print("Input:  ");
        printTeams(teams);

        sortTeams(teams);

        System.out.print("Output: ");
        printTeams(teams);

        System.out.println("\n--- Complexities & Properties ---");
        System.out.println("Time Complexity:  Best Case O(n) (already sorted), Average/Worst Case O(n^2)");
        System.out.println("Auxiliary Space:  O(1) (in-place)");
        System.out.println("Stability:        Stable (strict inequality prevents reordering equal elements)");
    }
}
