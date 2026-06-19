import java.util.*;
class Solution {
    final int INF = 1_000_000_000;
    String numbers;
    int[][][] dp;
    int[][] g = {{1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
                 {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
                 {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
                 {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
                 {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
                 {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
                 {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
                 {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
                 {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
                 {3, 6, 5, 4, 5, 3, 2, 4, 2, 1}};
    public int solution(String numbers) {
        int answer = 0;
        this.numbers = numbers;
        dp = new int[10][10][numbers.length()];
        
        for (int i=0; i<10; i++)
            for (int j=0; j<10; j++)
                Arrays.fill(dp[i][j], -1);
        return dfs(4, 6, 0);
    }
    
    int dfs(int left, int right, int idx) {
        if (left == right) return INF;
        if (idx == numbers.length()) return 0;
        if (dp[left][right][idx] != -1) return dp[left][right][idx];
        int nxt = numbers.charAt(idx) - '0';
        
        return dp[left][right][idx]=Math.min(dfs(nxt, right, idx+1)+g[left][nxt], dfs(left, nxt, idx+1)+g[right][nxt]);
    }
}