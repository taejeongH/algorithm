//package BOJ.회의준비;

import java.io.*;
import java.util.*;

public class Main{
	static final int INF = 1_000_000;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		int N = Integer.parseInt(br.readLine());
		int[][] g = new int[N+1][N+1];
		for (int i=1; i<=N; i++) Arrays.fill(g[i], INF);
		int M = Integer.parseInt(br.readLine());
		for (int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken());
			int e = Integer.parseInt(st.nextToken());
			g[s][e] = 1;
			g[e][s] = 1;
		}
		
		for (int k=1; k<=N; k++) {
			for (int i=1; i<=N; i++) {
				for (int j=1; j<=N; j++) {
					if (g[i][k] == INF || g[k][j] == INF) continue; 
					g[i][j] = Math.min(g[i][j], g[i][k] + g[k][j]);
				}
			}
		}
		
		boolean[] v = new boolean[N+1];

		
		int[] group = new int[N+1];
		int groupIdx = 1;
		for (int i=1; i<=N; i++) {
			if (group[i]!=0) continue;
			group[i] = groupIdx;
			for (int j=1; j<=N; j++) {
				if (g[i][j]==INF) continue;
				group[j] = groupIdx;
			}
			groupIdx++;
		}
		
		
		int[] minVal = new int[groupIdx];
		Arrays.fill(minVal, INF);
		int[] head = new int[groupIdx];
		for (int i=1; i<=N; i++) {
			int maxVal = 0;
			int groupId = group[i];
			for (int j=1; j<=N; j++) {
				if (g[i][j]==INF || i==j) continue;
				maxVal = Math.max(maxVal, g[i][j]);
			}
			if (maxVal < minVal[groupId]) {
				head[groupId] = i;
				minVal[groupId] = maxVal;
			}
		}
		
		System.out.println(groupIdx-1);
		Arrays.sort(head);
		for (int i=1; i<groupIdx; i++) {
			System.out.println(head[i]);
		}
	}
	
}