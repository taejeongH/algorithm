import java.util.*;
class Solution {
    final int INF = 1_000_000_000;
    int[][] matrix_sizes;
    int[][] dp;
    public int solution(int[][] matrix_sizes) {
        int answer = 0;
        this.matrix_sizes = matrix_sizes;
        int N = matrix_sizes.length;
        dp = new int[N][N];
        for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);
        return dfs(0, N-1);
    }
    
    int dfs(int l, int r) {
        if (dp[l][r] != -1) return dp[l][r];
        if (l == r) return 0;
        if (l == r-1) {
            return matrix_sizes[l][0] * matrix_sizes[l][1] * matrix_sizes[r][1];
        }
        
        int res = INF;
        for (int mid=l; mid<r; mid++) {
            res = Math.min(dfs(l, mid) + dfs(mid+1, r) + matrix_sizes[l][0] * matrix_sizes[mid][1] * matrix_sizes[r][1], res);
        }
        
        return dp[l][r] = res;
    }
}