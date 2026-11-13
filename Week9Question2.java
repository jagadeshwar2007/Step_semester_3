public class Week9Question2 {

    static String warehouseSummary(int[][] grid) {
        int total = 0;
        int maxValue = -1;
        int maxRow = 0;
        int maxCol = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                total += grid[i][j];
                if (grid[i][j] > maxValue) {
                    maxValue = grid[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }
        return "(" + total + ", (" + maxRow + ", " + maxCol + "))";
    }

    public static void main(String[] args) {
        int[][] grid = { { 4, 9, 2 }, { 7, 1, 6 }, { 3, 12, 5 } };
        System.out.println(warehouseSummary(grid));
        System.out.println("Time: O(m * n), Space: O(1)");
    }
}
