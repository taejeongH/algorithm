//package BOJ.이진탐색트리;

import java.io.*;
import java.util.*;

public class Main{
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		
		int[] depth = new int[N+1];
		TreeSet<Integer> set = new TreeSet<>();
		int head = Integer.parseInt(br.readLine());
		set.add(head);
		long cnt = 0;
		sb.append(cnt).append("\n");
		for (int i=1; i<N; i++) {
			int num = Integer.parseInt(br.readLine());
			
			Integer lower = set.lower(num);
			Integer higher = set.higher(num);
			
			int depthL = 0;
			int depthR = 0;
			if(lower!=null) depthL = depth[lower];
			if(higher!=null) depthR = depth[higher];
			
			depth[num] = Math.max(depthL, depthR) + 1;
			set.add(num);
			cnt += depth[num];
			sb.append(cnt).append("\n");
		}
		System.out.print(sb);
	}
	
	
}