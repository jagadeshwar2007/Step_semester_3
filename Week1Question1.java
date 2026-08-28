public class Week1Question1 {
    /**
     * Scans seat numbers to flag any duplicates using basic arrays and loops.
     * 
     * @param seatNumbers Array of seat numbers assigned to students.
     */
    public static void checkDuplicateSeats(int[] seatNumbers) {
        boolean duplicateFound = false;

        for (int i = 0; i < seatNumbers.length; i++) {
            // Avoid re-printing if duplicate was already flagged in earlier iteration
            boolean alreadyReported = false;
            for (int k = 0; k < i; k++) {
                if (seatNumbers[k] == seatNumbers[i]) {
                    alreadyReported = true;
                    break;
                }
            }
            if (alreadyReported) continue;

            // Check against remaining seat numbers
            boolean isDuplicate = false;
            for (int j = i + 1; j < seatNumbers.length; j++) {
                if (seatNumbers[i] == seatNumbers[j]) {
                    isDuplicate = true;
                    break;
                }
            }

            if (isDuplicate) {
                duplicateFound = true;
                System.out.println("Duplicate Seat Number Found: " + seatNumbers[i]);
            }
        }

        if (!duplicateFound) {
            System.out.println("No Duplicate Seats Found");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- Test Case 1 ---");
        int[] input1 = {101, 102, 103, 102, 105};
        checkDuplicateSeats(input1);

        System.out.println("\n--- Test Case 2 ---");
        int[] input2 = {101, 102, 103, 104, 105};
        checkDuplicateSeats(input2);
    }
}

