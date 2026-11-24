//package BOJ.전구;

import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int[][] capacity, flow;
    static boolean[] v;
    static final int INF = 1_000_000_000;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        Map<Character, Integer> idxs = new HashMap<>();

        N = Integer.parseInt(br.readLine());

        capacity = new int[26*2][26*2];
        flow = new int[26*2][26*2];

        int idx = 0;
        for (int i=0; i<N; i++) {
            st = new StringTokenizer(br.readLine());
            char s = st.nextToken().charAt(0);
            char e = st.nextToken().charAt(0);
            int cost = Integer.parseInt(st.nextToken());

            int sIdx = s>=97 ? s-'a' + 26 : s-'A';
            int eIdx = e>=97 ? e-'a' + 26 : e-'A';

            capacity[sIdx][eIdx] += cost;
            capacity[eIdx][sIdx] += cost;
        }

        int total = 0;
        while (true) {
            v = new boolean[26*2];
            int f = dfs(0, 25, INF);

            if (f==0) break;

            total += f;
        }

        System.out.println(total);
    }

    static int dfs(int cur, int T, int amount) {
        if (cur == T) return amount;

        v[cur] = true;

        for (int nxt = 0; nxt < 26*2; nxt++) {
            if (v[nxt]) continue;

            int remain = capacity[cur][nxt] - flow[cur][nxt];

            if (remain <= 0) continue;

            int f = dfs(nxt, T, Math.min(amount, remain));

            if (f > 0) {
                flow[cur][nxt] += f;
                flow[nxt][cur] -= f;

                return f;
            }
        }

        return 0;
    }

}