//package BOJ.발전소;

import java.io.*;
import java.util.*;

public class Main{
	static final int INF = 1_000_000_000;
	static int N, P;
	static int[][] g, dp;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		N = Integer.parseInt(br.readLine());
		
		g = new int[N][N];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<N; j++) {
				g[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		String input = br.readLine();
		
		int mask = 0;
		int cnt = 0;
		int n = 1;
		for (int i=0; i<N; i++) {
			if (input.charAt(i)=='Y') {
				mask = mask | n;
				cnt++;
			}
			n = n<<1;
		}
		
		P = Integer.parseInt(br.readLine());
		dp = new int[P+1][1<<N];
		for (int i=0; i<=P; i++) Arrays.fill(dp[i], -1);
		
		int res = dfs(cnt, mask);
		System.out.println(res==INF?-1:res);
	}
	
	static int dfs(int cnt, int mask) {
		if (cnt >= P) return 0;
		if(dp[cnt][mask] != -1) return dp[cnt][mask];
		
		int res = INF;
		int n = 1;
		for (int i=0; i<N; i++) {
			if ((n & mask) == n) {
				int n2 = 1;
				for (int j=0; j<N; j++) {
					if ((n2 & mask) != n2) {
						res = Math.min(dfs(cnt+1, mask | n2)+g[i][j], res);
					}
					n2 = n2<<1;
				}
			}
			n = n<<1;
		}
		return dp[cnt][mask]=res;
	}
}