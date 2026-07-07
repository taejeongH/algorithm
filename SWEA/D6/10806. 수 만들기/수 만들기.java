import java.io.*;
import java.util.*;


public class Solution {
	static int N, K;
	static int[] arr;
	static Map<Integer, Integer> map;
	
	static int dfs(int num) {
		if (num == 0) return 0;
		if (map.containsKey(num)) return map.get(num);
		
		int res = num;
		for (int i=0; i<N; i++) {
			int count = num % arr[i];
			int nxt = num / arr[i];
			
			res = Math.min(res, dfs(nxt) + count);
		}
		
		map.put(num, res);
		return res;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=tc; t++) {
			N = Integer.parseInt(br.readLine());
			arr = new int[N];
			map = new HashMap<>();

			st = new StringTokenizer(br.readLine());
			for (int i=0; i<N; i++) {
				arr[i] = Integer.parseInt(st.nextToken());
			}
			K = Integer.parseInt(br.readLine());
			
			sb.append("#").append(t).append(" ").append(dfs(K)).append("\n");
		}
		
		System.out.println(sb);
	}
}
