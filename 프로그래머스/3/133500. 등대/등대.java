import java.util.*;
class Solution {
    List<Integer>[] g;
    int[][] dp;
    boolean[] v;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;
        g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        v = new boolean[n+1];
        
        for (int i=0; i<lighthouse.length; i++) {
            int s = lighthouse[i][0];
            int e = lighthouse[i][1];
            g[s].add(e);
            g[e].add(s);
        }
        
        dp = new int[n+1][2];
        
        dfs(1);
        return Math.min(dp[1][0], dp[1][1]);
    }
    
    void dfs(int cur) {
        v[cur] = true;
        
        for (int nxt : g[cur]) {
            if(v[nxt]) continue;
            dfs(nxt);
        }
        
        for (int nxt : g[cur]) {
            dp[cur][0] += Math.min(dp[nxt][0], dp[nxt][1]);
            dp[cur][1] += dp[nxt][0];
        }
        dp[cur][0]++;       
    }
}