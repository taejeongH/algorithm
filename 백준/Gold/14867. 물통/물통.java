//package BOJ.물통;

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int maxA = Integer.parseInt(st.nextToken());
		int maxB = Integer.parseInt(st.nextToken());
		int endA = Integer.parseInt(st.nextToken());
		int endB = Integer.parseInt(st.nextToken());
		
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		que.add(new int[] {0, 0, 0});
		
		Set<Long> v = new HashSet<>();
		int res = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int a = now[0];
			int b = now[1];
			int dis = now[2];
			
		    long key = ((long)a << 32) | b;
		    if (v.contains(key)) continue;
		    v.add(key);
//			System.out.println(a + " " + b + " " + dis);
			
			if (a==endA && b == endB) {
				res = dis;
				break;
			}
			
			//Fill
			if (a != maxA) {
				que.add(new int[] {maxA, b, dis+1});
			}
			if (b != maxB) {
				que.add(new int[] {a, maxB, dis+1});
			}
			
			//Empty
			if (a != 0) {
				que.add(new int[] {0, b, dis+1});
			}
			if(b != 0) {
				que.add(new int[] {a, 0, dis+1});
			}
			
			//Move
			if (a != maxA && b != 0) {
				int remain = Math.min((maxA - a), b);
				que.add(new int[] {a + remain, b - remain, dis+1});
			}
			
			if (b != maxB && a != 0) {
				int remain = Math.min((maxB - b), a);
				que.add(new int[] {a - remain, b + remain, dis+1});
			}
		}
		
		System.out.println(res);
	}
	
}