import java.util.*;

class Solution {
    int[][] problems;
    int N, alMax, coMax;
    final int INF = 1_000_000_000;
    int[][] dp;

    public int solution(int alp, int cop, int[][] problems) {
        this.problems = problems;
        N = problems.length;
        alMax = 0;
        coMax = 0;

        for (int i=0; i<N; i++) {
            alMax = Math.max(alMax, problems[i][0]);
            coMax = Math.max(coMax, problems[i][1]);
        }

        dp = new int[alMax+1][coMax+1];
        for (int[] row : dp) Arrays.fill(row, -1);

        return dfs(alp, cop);
    }

    int dfs(int al, int co) {
        al = Math.min(al, alMax);
        co = Math.min(co, coMax);

        if (al == alMax && co == coMax) return 0;
        if (dp[al][co] != -1) return dp[al][co];

        int res = INF;

        if (al < alMax) res = Math.min(dfs(al+1, co)+1, res);
        if (co < coMax) res = Math.min(dfs(al, co+1)+1, res);

        for (int i=0; i<N; i++) {
            if (problems[i][0] <= al && problems[i][1] <= co) {
                int na = Math.min(al+problems[i][2], alMax);
                int nc = Math.min(co+problems[i][3], coMax);

                if (na == al && nc == co) continue;

                res = Math.min(dfs(na, nc)+problems[i][4], res);
            }
        }

        return dp[al][co] = res;
    }
}