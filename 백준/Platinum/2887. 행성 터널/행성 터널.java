//package BOJ.행성터널;

import java.io.*;
import java.util.*;

public class Main{
	static int[] parent;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[][] xMap = new int[N][4];
		int[][] yMap = new int[N][4];
		int[][] zMap = new int[N][4];
		parent = new int[N+1];
		for (int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			int[] now = new int[] {i, x, y, z};
			xMap[i] = yMap[i] = zMap[i] = now;
			parent[i] = i;
		}
		PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[2]-o2[2]);
		
		Arrays.sort(xMap, (o1, o2) -> o1[1]-o2[1]);
		for (int i=0; i<N-1; i++) {
			pq.add(new int[] {xMap[i][0], xMap[i+1][0], Math.abs(xMap[i][1] - xMap[i+1][1])});
		}
		
		Arrays.sort(yMap, (o1, o2) -> o1[2]-o2[2]);
		for (int i=0; i<N-1; i++) {
			pq.add(new int[] {yMap[i][0], yMap[i+1][0], Math.abs(yMap[i][2] - yMap[i+1][2])});
		}
			
		Arrays.sort(zMap, (o1, o2) -> o1[3]-o2[3]);
		for (int i=0; i<N-1; i++) {
			pq.add(new int[] {zMap[i][0], zMap[i+1][0], Math.abs(zMap[i][3] - zMap[i+1][3])});
		}
		
		int res = 0;
		while(!pq.isEmpty()) {
			int[] now = pq.poll();
			
			int s = now[0];
			int e = now[1];
			
			if(union(s, e)) {
				res += now[2];
			}
		}
		
		System.out.println(res);
	}
	static boolean union(int x, int y) {
		int xRoot = find(x);
		int yRoot = find(y);
		
		if (xRoot==yRoot) return false;
		
		parent[xRoot] = yRoot;
		return true;
	}
	
	static int find(int x) {
		if (parent[x] == x) return x;
		
		int rootX = find(parent[x]);
		return parent[x] = rootX;
	}
	
}