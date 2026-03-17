//package BOJ.대표선수;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[N][M];
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
		int max = 0;
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j=0; j<M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(map[i]);
		}
		
		for (int i=0; i<N; i++) {
			pq.add(new int[] {i, 0, map[i][0]});
			max = Math.max(map[i][0], max);
		}
		
		int res = Integer.MAX_VALUE;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			int i = now[0];
			int j = now[1];
			int min = now[2];
			res = Math.min(max-min, res);
			
			if(++j == M) break;
			int nxt = map[i][j];
			if (nxt > max) {
				max = nxt;
			}
			
			pq.add(new int[] {i, j, nxt});
		}
		
		System.out.println(res);
	}
}
