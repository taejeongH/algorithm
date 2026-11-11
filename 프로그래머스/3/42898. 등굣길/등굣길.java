import java.util.*;
class Solution {
    int[] dx = {1, 0};
    int[] dy = {0, 1};
    int[][] map;
    int n, m;
    int[][] dp;
    final int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        this.n = n;
        this.m = m;
        
        map = new int[n+1][m+1];
        dp = new int[n+1][m+1];
        for (int i=0; i<puddles.length; i++) {
            map[puddles[i][1]][puddles[i][0]] = 1;
        }
        for (int i=0; i<=n; i++) Arrays.fill(dp[i], -1);
        return dfs(1, 1) % MOD;
    }
    
    int dfs(int y, int x) {
        if(y == n && x == m) return 1;
        if(dp[y][x] != -1) return dp[y][x];
        int res = 0;
        for (int i=0; i<2; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            
            if(ny < 1 || ny >n || nx<1 || nx>m || map[ny][nx]==1) continue;
            res += dfs(ny, nx);
        }
        
        return dp[y][x] = res % MOD;
    }
}