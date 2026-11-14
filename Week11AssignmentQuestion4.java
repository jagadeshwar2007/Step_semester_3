import java.util.Arrays;

public class Week11AssignmentQuestion4 {

    /**
     * Finds the k-th highest mark using QuickSelect with the last element as pivot.
     * Target position in an ascending array is index (n - k).
     * Runs in average O(n) time.
     */
    public static int kthHighest(int[] marks, int k) {
        if (marks == null || k < 1 || k > marks.length) {
            throw new IllegalArgumentException("Invalid value for k: " + k);
        }

        int[] arr = marks.clone();
        int targetIndex = arr.length - k;
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int pivotIndex = partition(arr, left, right);
            if (pivotIndex == targetIndex) {
                return arr[pivotIndex];
            } else if (pivotIndex < targetIndex) {
                left = pivotIndex + 1;
            } else {
                right = pivotIndex - 1;
            }
        }

        return arr[targetIndex];
    }

    /**
     * Standard Lomuto partition scheme using the last element as pivot.
     */
    private static int partition(int[] arr, int left, int right) {
        int pivot = arr[right];
        int i = left;

        for (int j = left; j < right; j++) {
            if (arr[j] <= pivot) {
                swap(arr, i, j);
                i++;
            }
        }
        swap(arr, i, right);
        return i;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static void main(String[] args) {
        // Sample 1: marks = [72, 95, 60, 88, 79], k = 2
        int[] marks1 = { 72, 95, 60, 88, 79 };
        int k1 = 2;
        System.out.println("Sample 1 Input:  marks = " + Arrays.toString(marks1) + ", k = " + k1);
        System.out.println("Sample 1 Output: " + kthHighest(marks1, k1) + " (2nd highest mark)");

        // Sample 2: marks = [50, 90, 90, 70], k = 2
        int[] marks2 = { 50, 90, 90, 70 };
        int k2 = 2;
        System.out.println("\nSample 2 Input:  marks = " + Arrays.toString(marks2) + ", k = " + k2);
        System.out.println("Sample 2 Output: " + kthHighest(marks2, k2) + " (2nd highest mark, duplicates counted separately)");

        System.out.println("\n--- Complexities & Properties ---");
        System.out.println("Time Complexity:  Average Case O(n), Worst Case O(n^2)");
        System.out.println("Auxiliary Space:  O(1) (iterative in-place QuickSelect)");
        System.out.println("Stability:        Unstable (partitioning involves non-adjacent element swaps)");
    }
}
