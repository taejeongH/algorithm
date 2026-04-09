//package BOJ.개미;

import java.io.*;
import java.util.*;

public class Main {
	static final int LOG = 30;
	static boolean[] v;
	static int[][] parent, cost;
	static List<int[]>[] g;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] energy = new int[N+1];
		for (int i=1; i<=N; i++) {
			energy[i] = Integer.parseInt(br.readLine());
		}
		g = new List[N+1]; for(int i=1; i<=N; i++) g[i] = new ArrayList<>();
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			int c = Integer.parseInt(st.nextToken());
			
			g[s].add(new int[] {e, c});
			g[e].add(new int[] {s, c});
		}
		parent = new int[N+1][LOG+1];
		cost = new int[N+1][LOG+1];
		v = new boolean[N+1];
		
		v[1] = true;
		dfs(1);
		
		for (int k=1; k<=LOG; k++) {
			for (int i=1; i<=N; i++) {
				int mid = parent[i][k-1];
				parent[i][k] = parent[mid][k-1];
				cost[i][k] = cost[i][k-1] + cost[mid][k-1];
			}
		}
		
		StringBuilder sb = new StringBuilder();

        for (int i = 1; i <= N; i++) {
            int now = i;
            long remain = energy[i];

            for (int k = LOG; k >= 0; k--) {
                if (parent[now][k] != 0 && cost[now][k] <= remain) {
                    remain -= cost[now][k];
                    now = parent[now][k];
                }
            }

            sb.append(now).append('\n');
        }

        System.out.print(sb);
	}
	
	static void dfs(int node) {
		
		for (int[] nxt : g[node]) {
			if (!v[nxt[0]]) {
				v[nxt[0]] = true;
				parent[nxt[0]][0] = node;
				cost[nxt[0]][0] = nxt[1];
				dfs(nxt[0]);
			}
		}
		
	}
}
