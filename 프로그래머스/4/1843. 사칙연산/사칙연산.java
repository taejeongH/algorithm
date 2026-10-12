import java.util.*;

class Solution {
    String[] arr;
    int[][] maxDp, minDp;
    boolean[][] visited;

    public int solution(String[] arr) {
        this.arr = arr;
        int N = arr.length;

        maxDp = new int[N][N];
        minDp = new int[N][N];
        visited = new boolean[N][N];

        dfs(0, N - 1);

        return maxDp[0][N - 1];
    }

    void dfs(int l, int r) {
        if (visited[l][r]) return;
        visited[l][r] = true;

        if (l == r) {
            maxDp[l][r] = Integer.parseInt(arr[l]);
            minDp[l][r] = Integer.parseInt(arr[l]);
            return;
        }

        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;

        for (int mid = l + 2; mid <= r; mid += 2) {
            dfs(l, mid - 2);
            dfs(mid, r);

            if (arr[mid - 1].equals("+")) {
                max = Math.max(max,
                    maxDp[l][mid - 2] + maxDp[mid][r]);

                min = Math.min(min,
                    minDp[l][mid - 2] + minDp[mid][r]);
            } else {
                max = Math.max(max,
                    maxDp[l][mid - 2] - minDp[mid][r]);

                min = Math.min(min,
                    minDp[l][mid - 2] - maxDp[mid][r]);
            }
        }

        maxDp[l][r] = max;
        minDp[l][r] = min;
    }
}