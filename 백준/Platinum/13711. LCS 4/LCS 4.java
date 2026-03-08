//package BOJ.LCS4;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		
		int N = Integer.parseInt(br.readLine());
		
		int[] A = new int[N+1];
		int[] Bposition = new int[N+1];
		ArrayList<Integer> lis = new ArrayList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i=0; i<N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			int num = Integer.parseInt(st.nextToken());
			Bposition[num] = i;
		}
		
		for (int i=0; i<N; i++) {
			int pos = Bposition[A[i]];
			int idx = binarySearch(lis, pos);
			
			if (idx == lis.size()) {
				lis.add(pos);
			} else {
				lis.set(idx, pos);
			}
//			System.out.println(lis);
		}
		System.out.println(lis.size());
	}
	
	static int binarySearch(ArrayList<Integer> arr, int num) {
		int l = 0;
		int r = arr.size();
		
		while(l < r) {
			int mid = (l+r)/2;
			
			if (arr.get(mid) < num) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return r;
	}
}
