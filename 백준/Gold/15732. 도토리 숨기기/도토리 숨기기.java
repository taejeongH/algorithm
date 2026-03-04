//package BOJ.도토리숨기기;

import java.io.*;
import java.util.*;

public class Main{
	static int K;
	static int[][] order;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		int D = Integer.parseInt(st.nextToken());
		
		order = new int[K][3];
		for (int i=0; i<K; i++) {
			st = new StringTokenizer(br.readLine());
			order[i][0] = Integer.parseInt(st.nextToken());
			order[i][1] = Integer.parseInt(st.nextToken());
			order[i][2] = Integer.parseInt(st.nextToken());
		}
		
		int l = 0;
		int r = N;
		int res = 0;
		while(l<r) {
			int mid = (l+r)/2;
			long count = found(mid);
			
//			System.out.println(mid + " " + count);
			if (count < D) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		System.out.println(r);
	}
	
	static long found(int mid) {
		long cnt = 0;
		for (int i=0; i<K; i++) {
			int s = order[i][0];
			int e = order[i][1];
			int c = order[i][2];
			if (mid < s) continue;
			cnt += (Math.min(e, mid) - s) / c + 1;
		}
		return cnt;
	}
	
}