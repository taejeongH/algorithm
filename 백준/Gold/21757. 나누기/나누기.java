// BOJ.나누기;

import java.io.*;
import java.util.*;

public class Main{
	static int N, sum;
	static int[] sumArr;
	static long[][] dp;

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		N = Integer.parseInt(br.readLine());
		sumArr = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			sumArr[i] = sumArr[i-1] + (Integer.parseInt(st.nextToken()));
		}
		sum = sumArr[N]/4;
		dp = new long[4][N+1];
		for (int i=0; i<4; i++) Arrays.fill(dp[i], -1);
		System.out.println(dfs(0, 0));
//		for (int i=0; i<4; i++) System.out.println(Arrays.toString(dp[i]));
		
	}

	static long dfs(int depth, int start) {
		if (dp[depth][start]!=-1) return dp[depth][start];
		if (depth == 3) {
			if (sumArr[N] - sumArr[start] == sum) return dp[depth][start]=1;
			else return dp[depth][start]=0;
		}
		
		long res = 0;
		for (int i=start+1; i<N; i++) {
			if (sumArr[i] - sumArr[start] == sum) {
				res += dfs(depth+1, i);
			}
		}
		
		return dp[depth][start]=res;
	}
	
}