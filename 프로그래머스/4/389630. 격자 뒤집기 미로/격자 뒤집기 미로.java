import java.util.*;
class Solution {
    int N, M, K, res;
    int[][] visible, hidden;
    
    public int solution(int[][] visible, int[][] hidden, int k) {
        this.N = visible.length;
        this.M = visible[0].length;
        this.K = k;
        this.visible = visible;
        this.hidden = hidden;
        res = 0;
        dfs(0, 0, new boolean[N]);
        return res;
    }
    
    void dfs(int depth, int start, boolean[] reverseY) {
        res = Math.max(calSum(reverseY) - depth*K, res);
        if (depth == N) return;
        for (int i=start; i<N; i++) {
            if (reverseY[i]) continue;
            reverseY[i] = true;
            dfs(depth+1, i+1, reverseY);
            reverseY[i] = false;
        }
    }
    
    int calSum(boolean[] reverseY) {
        int minBlack = Integer.MAX_VALUE;
        int sum = 0;

        for (int j = 0; j < M; j++) {
            int sum0 = 0;
            int sum1 = -K;

            // 각 열에 대해 min도 따로 관리
            int min0 = Integer.MAX_VALUE;
            int min1 = Integer.MAX_VALUE;

            for (int i = 0; i < N; i++) {
                boolean isBlack = ((i + j) % 2 == 1);

                int v0, v1;

                if (reverseY[i]) {
                    v0 = hidden[i][j];
                    v1 = visible[i][j];
                } else {
                    v0 = visible[i][j];
                    v1 = hidden[i][j];
                }

                sum0 += v0;
                sum1 += v1;

                if (isBlack) {
                    min0 = Math.min(min0, v0);
                    min1 = Math.min(min1, v1);
                }
            }

            if (sum0 >= sum1) {
                sum += sum0;
                minBlack = Math.min(minBlack, min0);
            } else {
                sum += sum1;
                minBlack = Math.min(minBlack, min1);
            }
        }
        
        if (N%2==0 && M%2==0) {
            sum -= minBlack;
        }
        
        return sum;
    }
}