import java.util.*;

class Solution {
    int aMax, cMax, N;
    int[][] problems;
    final int INF = 1_000_000_000;
    int[][] dp;

    public int solution(int alp, int cop, int[][] problems) {
        this.problems = problems;
        N = problems.length;

        for (int[] p : problems) {
            aMax = Math.max(aMax, p[0]);
            cMax = Math.max(cMax, p[1]);
        }

        alp = Math.min(alp, aMax);
        cop = Math.min(cop, cMax);

        dp = new int[aMax + 1][cMax + 1];

        for (int i = 0; i <= aMax; i++) {
            Arrays.fill(dp[i], -1);
        }

        return dfs(alp, cop);
    }

    int dfs(int algo, int code) {
        if (algo == aMax && code == cMax) return 0;

        if (dp[algo][code] != -1) return dp[algo][code];

        int res = INF;

        // 알고력 1 올리기
        if (algo < aMax) {
            res = Math.min(res, dfs(algo + 1, code) + 1);
        }

        // 코딩력 1 올리기
        if (code < cMax) {
            res = Math.min(res, dfs(algo, code + 1) + 1);
        }

        // 문제 풀기
        for (int[] p : problems) {
            int algoReq = p[0];
            int codeReq = p[1];
            int algoInc = p[2];
            int codeInc = p[3];
            int cost = p[4];

            if (algo >= algoReq && code >= codeReq) {
                int nextAlgo = Math.min(aMax, algo + algoInc);
                int nextCode = Math.min(cMax, code + codeInc);

                // 보상이 0,0인 이상한 문제 방어
                if (nextAlgo == algo && nextCode == code) continue;

                res = Math.min(res, dfs(nextAlgo, nextCode) + cost);
            }
        }

        return dp[algo][code] = res;
    }
}