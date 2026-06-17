import java.util.*;
class Solution {
    int aMax, cMax, N;
    int[][] problems;
    final int INF = 1_000_000_000;
    int[][] dp;
    public int solution(int alp, int cop, int[][] problems) {
        this.problems = problems;
        N = problems.length;
        
        for (int i=0; i<N; i++) {
            aMax = Math.max(problems[i][0], aMax);
            cMax = Math.max(problems[i][1], cMax);
        }
        dp = new int[aMax+500][cMax+500];
        for (int i=0; i<aMax+500; i++) Arrays.fill(dp[i], -1);
        
        return dfs(alp, cop);
    }
    
    int dfs(int algo, int code) {
        if (algo >= aMax && code >= cMax) return 0;
        if(dp[algo][code] != -1) return dp[algo][code];
        
        int res = INF;
        
        if (algo + 1 <= aMax) res = Math.min(dfs(algo+1, code)+1, res);
        if (code + 1 <= cMax) res = Math.min(dfs(algo, code+1)+1, res);
        
        for (int i=0; i<N; i++) {
            int algoReq = problems[i][0];
            int codeReq = problems[i][1];
            int algoInc = problems[i][2];
            int codeInc = problems[i][3];
            int cost = problems[i][4];
            
            if (algoReq <= algo && codeReq <= code && algo+algoInc<aMax+500 && code+codeInc<cMax+500) 
                res = Math.min(dfs(algo+algoInc, code+codeInc)+cost, res);
        }
        
        return dp[algo][code]=res;
    }
    
}