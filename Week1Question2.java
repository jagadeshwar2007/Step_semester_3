public class Week1Question2 {
    /**
     * Compares typed text against original passage and calculates accuracy & first mismatch position.
     * 
     * @param original The expected original string passage.
     * @param typed The user's typed string passage.
     */
    public static void checkTypingAccuracy(String original, String typed) {
        int total = original.length();
        int matched = 0;
        int firstMismatchPos = -1;
        char origChar = ' ';
        char typedChar = ' ';

        for (int i = 0; i < total; i++) {
            if (original.charAt(i) == typed.charAt(i)) {
                matched++;
            } else if (firstMismatchPos == -1) {
                firstMismatchPos = i + 1; // 1-indexed position
                origChar = original.charAt(i);
                typedChar = typed.charAt(i);
            }
        }

        double accuracy = ((double) matched / total) * 100;

        StringBuilder output = new StringBuilder();
        output.append(String.format("Matched: %d/%d | Accuracy: %.2f%% | ", matched, total, accuracy));

        if (firstMismatchPos == -1) {
            output.append("No Mismatches");
        } else {
            output.append(String.format("First Mismatch at position %d ('%c' vs '%c')", firstMismatchPos, origChar, typedChar));
        }

        System.out.println(output.toString());
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        checkTypingAccuracy("hello world", "hello worlt");

        System.out.println("\n--- Test Case 2 ---");
        checkTypingAccuracy("coding", "coding");
    }
}

