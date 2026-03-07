//package BOJ.주식;

import java.io.*;
import java.util.*;

public class Main {
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for (int tc=1; tc<=T; tc++) {
			st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int K = Integer.parseInt(st.nextToken());
			
			ArrayList<Integer> lis = new ArrayList<>();
			st = new StringTokenizer(br.readLine());
			boolean res = false;
			for (int i=0; i<N; i++) {
				int num = Integer.parseInt(st.nextToken());
				
				int idx = binarySearch(lis, num);
				
				if (idx == lis.size()) {
					lis.add(num);
					if (lis.size() == K) {
						res = true;
						break;
					}
				} else {
					lis.set(idx, num);
				}
			}
			sb.append("Case #").append(tc).append("\n");
			sb.append(res?1:0).append("\n");
			
		}
		System.out.print(sb);
	}
	
	static int binarySearch(ArrayList<Integer> arr, int key) {
		int l = 0;
		int r = arr.size();
		
		while(l<r) {
			int mid = (l+r)/2;
			if (arr.get(mid) < key) {
				l = mid+1;
			} else {
				r = mid;
			}
		}
		return r;
	}
}
