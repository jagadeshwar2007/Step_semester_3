import java.util.Arrays;

public class Week9AssignmentQuestion2 {

    static int[] mergeTokens(int[] counterA, int[] counterB) {
        int[] result = new int[counterA.length + counterB.length];
        int i = 0;
        int j = 0;
        int k = 0;
        while (i < counterA.length && j < counterB.length) {
            if (counterA[i] <= counterB[j]) {
                result[k++] = counterA[i++];
            } else {
                result[k++] = counterB[j++];
            }
        }
        while (i < counterA.length) {
            result[k++] = counterA[i++];
        }
        while (j < counterB.length) {
            result[k++] = counterB[j++];
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(mergeTokens(new int[] { 3, 8, 15, 20 }, new int[] { 5, 8, 12 })));
        System.out.println(Arrays.toString(mergeTokens(new int[] {}, new int[] { 4, 9 })));
        System.out.println("Time: O(m + n), Space: O(m + n)");
        System.out.println("Join and sort: O((m + n) log(m + n))");
    }
}
