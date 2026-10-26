import java.util.*;
class Solution {
    final int INF = Integer.MAX_VALUE;
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] g = new int [n+1][n+1];
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i==j) continue;
                g[i][j] = INF;
            }
        }
        
        for (int i=0; i< fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int c = fares[i][2];
            g[start][end] = c;
            g[end][start] = c;
        }
        
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (g[i][k] == INF || g[k][j] == INF) continue;
                    g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }
        
        int answer = g[s][a] + g[s][b];
        for (int i=1; i<=n; i++) {
            answer = Math.min(answer, g[s][i] + g[i][a] + g[i][b]);
        }
        
        //for (int i=1; i<=n; i++) System.out.println(Arrays.toString(g[i]));
        return answer;
    }
}