//package BOJ.그림교환;

import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int[][] map;
	static int[][][] dp;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<N; j++) {
				map[i][j] = input.charAt(j)-'0';
			}
		}
		dp = new int[N][10][(1<<N)+1];
		for (int i=0; i<N; i++) {
			for (int j=0; j<10; j++) {
				Arrays.fill(dp[i][j], -1);
			}
		}
		
		System.out.println(dfs(0, 0, 1)+1);
	}
	
	static int dfs(int cur, int price, int mask) {
		if(dp[cur][price][mask]!=-1)return dp[cur][price][mask];
		int res = 0;
		for (int i=0; i<N; i++) {
			if (price > map[cur][i]) continue;
			int key = 1<<i;
			if ((mask & key) == key) continue;
			res = Math.max(dfs(i, map[cur][i], mask | key)+1, res);
		}
		return dp[cur][price][mask]=res;
	}
}
