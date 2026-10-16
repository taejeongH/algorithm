import java.util.*;

class Solution {
    public int solution(int[] money) {
        int N = money.length;
        int[][] dp = new int[N + 2][2];

        for (int i = N - 1; i >= 0; i--) {
            for (int type = 0; type < 2; type++) {
                if (i == N - 1 && type == 1) {
                    continue;
                }

                dp[i][type] = Math.max(
                    dp[i + 1][type],
                    dp[i + 2][type] + money[i]
                );
            }
        }

        int a = dp[2][1] + money[0];
        int b = dp[1][0];

        return Math.max(a, b);
    }
}