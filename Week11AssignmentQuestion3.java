import java.util.Arrays;

public class Week11AssignmentQuestion3 {

    /**
     * Counts the total number of disorder pairs (inversions) where i < j and roll[i] > roll[j].
     * Runs in O(n log n) time using a modified Merge Sort algorithm.
     */
    public static long countInversions(int[] roll) {
        if (roll == null || roll.length <= 1) {
            return 0;
        }
        int[] temp = new int[roll.length];
        // Clone to avoid mutating original input if needed, or sort in temp
        int[] arr = roll.clone();
        return mergeSortAndCount(arr, temp, 0, arr.length - 1);
    }

    private static long mergeSortAndCount(int[] arr, int[] temp, int left, int right) {
        long count = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            count += mergeSortAndCount(arr, temp, left, mid);
            count += mergeSortAndCount(arr, temp, mid + 1, right);
            count += mergeAndCount(arr, temp, left, mid, right);
        }
        return count;
    }

    private static long mergeAndCount(int[] arr, int[] temp, int left, int mid, int right) {
        int i = left;     // Index for left subarray [left..mid]
        int j = mid + 1;  // Index for right subarray [mid+1..right]
        int k = left;     // Index for merged subarray
        long count = 0;

        while (i <= mid && j <= right) {
            if (arr[i] <= arr[j]) {
                temp[k++] = arr[i++];
            } else {
                // arr[i] > arr[j] means arr[j] is inverted with arr[i..mid]
                temp[k++] = arr[j++];
                count += (mid - i + 1);
            }
        }

        while (i <= mid) {
            temp[k++] = arr[i++];
        }

        while (j <= right) {
            temp[k++] = arr[j++];
        }

        for (int p = left; p <= right; p++) {
            arr[p] = temp[p];
        }

        return count;
    }

    public static void main(String[] args) {
        // Sample 1: roll = [2, 4, 1, 3, 5]
        int[] roll1 = { 2, 4, 1, 3, 5 };
        System.out.println("Sample 1 Input:  " + Arrays.toString(roll1));
        long inv1 = countInversions(roll1);
        System.out.println("Sample 1 Output: " + inv1 + " (Disorder pairs: 2>1, 4>1, 4>3)");

        // Sample 2: roll = [5, 4, 3, 2, 1]
        int[] roll2 = { 5, 4, 3, 2, 1 };
        System.out.println("\nSample 2 Input:  " + Arrays.toString(roll2));
        long inv2 = countInversions(roll2);
        System.out.println("Sample 2 Output: " + inv2);

        System.out.println("\n--- Complexities & Properties ---");
        System.out.println("Time Complexity:  O(n log n) across all cases (Divide and Conquer Merge Sort)");
        System.out.println("Auxiliary Space:  O(n) auxiliary buffer for merging");
        System.out.println("Stability:        Stable (equal elements maintain order; counts strictly greater elements)");
    }
}
