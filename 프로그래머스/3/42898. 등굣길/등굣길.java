import java.util.*;
class Solution {
    int N, M;
    int[][] map, dp;
    int[] dx = {1, 0};
    int[] dy = {0, 1};
    final int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        N = n;
        M = m;
        
        map = new int[N][M];
        dp = new int[N][M];
        for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
        
        for (int i=0; i<puddles.length; i++) {
            int x = puddles[i][0]-1;
            int y = puddles[i][1]-1;
            map[y][x] = 1;
        }
        return dfs(0, 0);
    }
    
    int dfs (int y, int x) {
        if (y == N-1 && x == M-1) return 1;
        if (dp[y][x] != -1) return dp[y][x];
        
        long res = 0;
        for (int i=0; i<2; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx] == 1) continue;
            res += dfs(ny, nx);
        }
        
        return dp[y][x] = (int) (res%MOD);
    }
}