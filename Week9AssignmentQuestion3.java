import java.util.HashMap;

public class Week9AssignmentQuestion3 {

    static String mostPopular(String[] orders) {
        HashMap<String, Integer> counts = new HashMap<>();
        for (String item : orders) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }
        String bestItem = orders[0];
        int bestCount = 0;
        for (String item : orders) {
            int count = counts.get(item);
            if (count > bestCount) {
                bestCount = count;
                bestItem = item;
            }
        }
        return "(\"" + bestItem + "\", " + bestCount + ")";
    }

    public static void main(String[] args) {
        String[] first = { "dosa", "idli", "vada", "dosa", "idli", "dosa", "tea" };
        String[] second = { "tea", "coffee", "coffee", "tea" };
        System.out.println(mostPopular(first));
        System.out.println(mostPopular(second));
        System.out.println("Rescan for every item: Time O(n^2), Space O(1)");
        System.out.println("Hash map: Time O(n), Space O(k)");
    }
}
