import java.util.HashSet;

public class Week9Question4 {

    static boolean checkAllPairs(int[] nums, int target) {
        for (int i = 0; i < nums.length - 1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean checkWithSet(int[] nums, int target) {
        HashSet<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (set.contains(target - nums[i])) {
                return true;
            }
            set.add(nums[i]);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] first = { 2, 7, 11, 15 };
        int[] second = { 3, 4, 6 };
        System.out.println(checkWithSet(first, 9));
        System.out.println(checkWithSet(second, 20));
        System.out.println(checkAllPairs(first, 9));
        System.out.println(checkAllPairs(second, 20));
        System.out.println("All pairs: Time O(n^2), Space O(1)");
        System.out.println("Hash set: Time O(n), Space O(n)");
    }
}
