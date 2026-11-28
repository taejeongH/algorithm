import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main{
    static List<int[]>[] g;
    static int[][] parents, min, max;
    static int[] depth;
    static final int LOG = 17;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        g = new List[N+1]; for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
        for (int i=0; i<N-1; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            g[s].add(new int[] {e, c});
            g[e].add(new int[] {s, c});
        }

        parents = new int[N+1][LOG];
        min = new int[N+1][LOG]; for (int i=01; i<=N; i++) Arrays.fill(min[i], INF);
        max = new int[N+1][LOG];
        depth = new int[N+1];

        dfs(1, 1);

        for (int k = 1; k < LOG; k++) {
            for (int node = 1; node <= N; node++) {
                int mid = parents[node][k - 1];

                parents[node][k] = parents[mid][k - 1];

                min[node][k] = Math.min(min[node][k - 1], min[mid][k - 1]);

                max[node][k] = Math.max(max[node][k - 1], max[mid][k - 1]);
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int D = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            int[] ans = lca(D, E);
            sb.append(ans[0]).append(" ").append(ans[1]).append("\n");
        }

        System.out.println(sb);
    }

    static void dfs(int cur, int parent) {
        for (int[] nxt : g[cur]) {
            if (nxt[0] == parent) continue;
            depth[nxt[0]] = depth[cur] + 1;
            parents[nxt[0]][0] = cur;
            min[nxt[0]][0] = nxt[1];
            max[nxt[0]][0] = nxt[1];
            dfs(nxt[0], cur);
        }
    }

    static int[] lca(int a, int b) {
        int minAns = INF;
        int maxAns = 0;

        if (depth[a] < depth[b]) {
            int tmp = a;
            a = b;
            b = tmp;
        }

        int diff = depth[a] - depth[b];

        for (int k=LOG-1; k>=0; k--) {
            if ((diff & (1<<k)) != 0) {
                minAns = Math.min(minAns, min[a][k]);
                maxAns = Math.max(maxAns, max[a][k]);
                
                a = parents[a][k];
            }
        }

        if (a == b) return new int[] {minAns, maxAns};

        for (int k=LOG-1; k>=0; k--) {
            if(parents[a][k] != parents[b][k]) {
                minAns = Math.min(minAns, min[a][k]);
                minAns = Math.min(minAns, min[b][k]);

                maxAns = Math.max(maxAns, max[a][k]);
                maxAns = Math.max(maxAns, max[b][k]);
                a = parents[a][k];
                b = parents[b][k];
            }
        }

        minAns = Math.min(minAns, min[a][0]);
        minAns = Math.min(minAns, min[b][0]);

        maxAns = Math.max(maxAns, max[a][0]);
        maxAns = Math.max(maxAns, max[b][0]);

        return new int[] {minAns, maxAns};
    }

}