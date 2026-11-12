import java.util.*;
class Solution {
    List<Integer>[] g;
    int k;
    int[] gps_log;
    int[][] dp;
    final int INF = 1_000_000;
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        this.k = k;
        this.gps_log = gps_log;
        g = new List[n+1];
        for (int i=1; i<=n; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<m; i++) {
            int s = edge_list[i][0];
            int e = edge_list[i][1];
            g[s].add(e);
            g[e].add(s);
        }
        
        dp = new int[k][n+1];
        for (int i=0; i<k; i++) Arrays.fill(dp[i], -1);
        
        int answer = dfs(0, gps_log[0]);

        return answer >= INF ? -1 : answer;
    }
    
    int dfs(int idx, int node) {
        if (idx == k - 1) {
            return node == gps_log[idx] ? 0 : INF;
        }

        if (dp[idx][node] != -1) return dp[idx][node];

        int res = INF;

        // 1. 현재 정점에 머무르기
        res = Math.min(res, dfs(idx + 1, node) + (node == gps_log[idx + 1] ? 0 : 1));

        // 2. 인접 정점으로 이동하기
        for (int nxt : g[node]) {
            res = Math.min(res, dfs(idx + 1, nxt) + (nxt == gps_log[idx + 1] ? 0 : 1));
        }

        return dp[idx][node] = res;
    }
}