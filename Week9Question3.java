import java.util.HashSet;

public class Week9Question3 {

    static boolean bruteForce(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    return true;
                }
            }
        }
        return false;
    }

    static boolean hasPairWithSum(int[] nums, int target) {
        HashSet<Integer> seen = new HashSet<>();
        for (int num : nums) {
            if (seen.contains(target - num)) {
                return true;
            }
            seen.add(num);
        }
        return false;
    }

    public static void main(String[] args) {
        int[] first = { 2, 7, 11, 15 };
        int[] second = { 3, 4, 6 };
        System.out.println(hasPairWithSum(first, 9));
        System.out.println(hasPairWithSum(second, 20));
        System.out.println(bruteForce(first, 9));
        System.out.println(bruteForce(second, 20));
        System.out.println("Brute force: Time O(n^2), Space O(1)");
        System.out.println("Hash set: Time O(n), Space O(n)");
    }
}
