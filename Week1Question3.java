public class Week1Question3 {
    /**
     * Scans signal reading logs and finds the longest continuous streak of the same color.
     * 
     * @param signalLog String sequence of single-letter readings ('R', 'Y', 'G').
     */
    public static void findLongestStreak(String signalLog) {
        if (signalLog == null || signalLog.isEmpty()) {
            System.out.println("No signal readings found.");
            return;
        }

        char maxChar = signalLog.charAt(0);
        int maxStreak = 1;

        char currentChar = signalLog.charAt(0);
        int currentStreak = 1;

        for (int i = 1; i < signalLog.length(); i++) {
            if (signalLog.charAt(i) == currentChar) {
                currentStreak++;
            } else {
                if (currentStreak > maxStreak) {
                    maxStreak = currentStreak;
                    maxChar = currentChar;
                }
                currentChar = signalLog.charAt(i);
                currentStreak = 1;
            }
        }

        // Final streak check at string boundary
        if (currentStreak > maxStreak) {
            maxStreak = currentStreak;
            maxChar = currentChar;
        }

        System.out.println("Longest Streak: '" + maxChar + "' repeated " + maxStreak + " times");
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        findLongestStreak("RRGGGYRR");

        System.out.println("\n--- Test Case 2 ---");
        findLongestStreak("RRRRYYGG");
    }
}

