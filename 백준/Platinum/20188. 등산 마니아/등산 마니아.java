//package BOJ.등산마니아;

import java.io.*;
import java.util.*;

public class Main{
	static List<Integer>[] g;
	static int[] subTree;
	static boolean[] v;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		
		g = new List[N+1];  for (int i=1; i<=N; i++) g[i] = new ArrayList<>();
		
		for (int i=0; i<N-1; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s].add(e);
			g[e].add(s);
		}
		
		v = new boolean[N+1];
		subTree = new int[N+1];
		
		dfs(1);
		
		long res = 0;
		for (int i=2; i<=N; i++) {
			int cnt = subTree[i];
			res += 1L * (cnt * (2 * (N-1) + ((cnt-1) * -1)))/2;
		}
		System.out.println(res);
	}
	
	static int dfs(int cur) {
		v[cur] = true;
		subTree[cur]++;
		for (int nxt : g[cur]) {
			if (v[nxt]) continue;
			subTree[cur] += dfs(nxt);
		}
		return subTree[cur];
	}
	
}

//최소 공통 부모 찾기 -> 부모와의 거리 찾기? 

//subtree의 개수를 구하고