import java.io.*;
import java.util.*;

public class Main{
    static int N, K;
    static int[][] walk, bicycle;
    static final int INF = 1_000_000_000;
    static int[][] dp;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        walk = new int[N][2]; //{시간, 금액}
        bicycle = new int[N][2];

        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());

            walk[i][0] = Integer.parseInt(st.nextToken());
            walk[i][1] = Integer.parseInt(st.nextToken());
            bicycle[i][0] = Integer.parseInt(st.nextToken());
            bicycle[i][1] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][K+1];
        for (int i=0; i<N; i++) Arrays.fill(dp[i], -1);

        System.out.println(dfs(0, 0));
    }

    static int dfs(int cur, int time) {
        if (cur == N) return 0;
        if(dp[cur][time] != -1) return dp[cur][time];

        int res = -INF;
        if (time + walk[cur][0] <= K) {
            res = Math.max(dfs(cur+1, time + walk[cur][0])+walk[cur][1], res);
        }

        if (time+bicycle[cur][0] <= K) {
            res = Math.max(dfs(cur+1, time+bicycle[cur][0]) + bicycle[cur][1], res);
        }

        return dp[cur][time] = res;
    }

}