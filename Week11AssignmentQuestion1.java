import java.util.Arrays;

public class Week11AssignmentQuestion1 {

    /**
     * Sorts the scores using optimized Bubble Sort with early stopping.
     * 
     * @param scores The array of test scores to sort.
     * @return int[] {passes, swaps}: total sweeps performed and total swaps made.
     */
    public static int[] bubbleStats(int[] scores) {
        int n = scores.length;
        int passes = 0;
        int swaps = 0;

        for (int i = 0; i < n - 1; i++) {
            boolean swapped = false;
            passes++; // Count this pass as soon as it begins

            for (int j = 0; j < n - 1 - i; j++) {
                if (scores[j] > scores[j + 1]) {
                    // Swap neighbouring elements
                    int temp = scores[j];
                    scores[j] = scores[j + 1];
                    scores[j + 1] = temp;
                    swaps++;
                    swapped = true;
                }
            }

            // Early exit if no elements were swapped during this pass
            if (!swapped) {
                break;
            }
        }

        // Edge case: if array has length <= 1, exactly 0 passes or at most 1 pass needed
        if (n <= 1) {
            passes = (n == 1) ? 1 : 0;
        }

        return new int[] { passes, swaps };
    }

    public static void main(String[] args) {
        // Sample 1: scores = [5, 1, 4, 2, 8]
        int[] scores1 = { 5, 1, 4, 2, 8 };
        System.out.println("Sample 1 Input:  " + Arrays.toString(scores1));
        int[] stats1 = bubbleStats(scores1);
        System.out.println("Sample 1 Output: " + Arrays.toString(scores1) + " passes = " + stats1[0] + ", swaps = " + stats1[1]);

        // Sample 2: scores = [1, 2, 3, 4]
        int[] scores2 = { 1, 2, 3, 4 };
        System.out.println("\nSample 2 Input:  " + Arrays.toString(scores2));
        int[] stats2 = bubbleStats(scores2);
        System.out.println("Sample 2 Output: " + Arrays.toString(scores2) + " passes = " + stats2[0] + ", swaps = " + stats2[1]);

        System.out.println("\n--- Why an already-sorted board needs only 1 pass ---");
        System.out.println("In the first sweep, every adjacent pair is already in non-decreasing order (scores[j] <= scores[j+1]).");
        System.out.println("Therefore, no swaps occur, the 'swapped' flag remains false, and the algorithm terminates immediately after 1 pass.");

        System.out.println("\n--- Complexities & Properties ---");
        System.out.println("Time Complexity:  Best Case O(n) (already sorted), Average/Worst Case O(n^2)");
        System.out.println("Auxiliary Space:  O(1) (in-place)");
        System.out.println("Stability:        Stable (adjacent equal elements are never swapped)");
    }
}
