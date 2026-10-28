import java.util.*;

class Solution {
    int[][] dp;

    public int[] solution(int target) {
        dp = new int[target + 1][2];

        for (int i = 0; i <= target; i++) {
            dp[i][0] = -1;
        }

        return dfs(target);
    }

    int[] dfs(int target) {
        if (target == 0) {
            return new int[]{0, 0};
        }

        if (dp[target][0] != -1) {
            return dp[target];
        }

        int min = Integer.MAX_VALUE;
        int max = 0;

        // 불 (50점)
        if (target >= 50) {
            int[] next = dfs(target - 50);

            min = next[0] + 1;
            max = next[1] + 1;
        }

        // 싱글 (1~20점)
        for (int i = 1; i <= 20; i++) {
            if (target < i) break;

            int[] next = dfs(target - i);

            int dis = next[0] + 1;
            int sum = next[1] + 1;

            if (dis < min || (dis == min && sum > max)) {
                min = dis;
                max = sum;
            }
        }

        // 더블, 트리플
        for (int i = 1; i <= 20; i++) {
            for (int j = 2; j <= 3; j++) {
                int score = i * j;

                if (target < score) continue;

                int[] next = dfs(target - score);

                int dis = next[0] + 1;
                int sum = next[1];

                if (dis < min || (dis == min && sum > max)) {
                    min = dis;
                    max = sum;
                }
            }
        }

        return dp[target] = new int[]{min, max};
    }
}