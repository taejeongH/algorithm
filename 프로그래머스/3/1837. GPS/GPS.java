import java.util.*;
class Solution {
    final int INF = 1_000_000_000;
    int[] gps_log;
    int[][] dis;
    int k;
    int[] dp;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        
        this.k = k;
        this.gps_log = gps_log;
        dis = new int[n+1][n+1];
        for (int i=1; i<=n; i++) Arrays.fill(dis[i], INF);
        for (int i=0; i<m; i++) {
            int s = edge_list[i][0];
            int e = edge_list[i][1];
            
            dis[s][e] = 1;
            dis[e][s] = 1;
        }
        
        for (int l=1; l<=n; l++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (i==j) continue;
                    if (dis[i][l] == INF || dis[l][j] == INF) continue;
                    dis[i][j] = Math.min(dis[i][j], dis[i][l] + dis[l][j]);
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            dis[i][i] = 0;
        }
        
        dp = new int[k];
        Arrays.fill(dp, -1);
        int answer = dfs(0);
        return answer==INF?-1:answer;
    }
    
    int dfs(int depth) {
        if (depth == k-1) return 0;
        if(dp[depth] != -1) return dp[depth];
        
        int res = INF;
        int cur = gps_log[depth];
        int t = 1;
        for (int i=depth+1; i<k; i++) {
            int nxt = gps_log[i];
            
            if (dis[cur][nxt] <= t) {
                res = Math.min(dfs(i) + t-1, res);
            }
            t++;
        }
        
        return dp[depth]=res;
    }
}