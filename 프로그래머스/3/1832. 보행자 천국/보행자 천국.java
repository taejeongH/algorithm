import java.util.*;

class Solution {
    final int MOD = 20170805;
    int N, M;
    int[][] map;
    int[][][] dp;
    int[] dy = {0, 1};
    int[] dx = {1, 0};

    public int solution(int m, int n, int[][] cityMap) {
        N = m;
        M = n;
        map = cityMap;

        dp = new int[N][M][2];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                Arrays.fill(dp[i][j], -1);
            }
        }

        return dfs(0, 0, 0);
    }

    int dfs(int y, int x, int dir) {
        if (y == N - 1 && x == M - 1) return 1;

        if (dp[y][x][dir] != -1) {
            return dp[y][x][dir];
        }

        long res = 0;

        for (int i = 0; i < 2; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || ny >= N || nx < 0 || nx >= M) continue;
            if (map[ny][nx] == 1) continue;
            if (map[y][x] == 2 && dir != i) continue;

            res += dfs(ny, nx, i);
        }

        return dp[y][x][dir] = (int)(res % MOD);
    }
}