//package BOJ.연세워터파크;

import java.io.*;
import java.util.*;

public class Main{
	static int N, D;
	static final long MIN = -1_000_000_000_000_000L;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		int[] map = new int[N+1];
		st = new StringTokenizer(br.readLine());
		for (int i=1; i<=N; i++) {
			map[i] = Integer.parseInt(st.nextToken());
		}

		long[] dp = new long[N+1];
		ArrayDeque<Integer> deque = new ArrayDeque<>();
		long res = MIN;
		for (int i=1; i<=N; i++) {
			while (!deque.isEmpty() && i - deque.peekFirst() > D) {
				deque.pollFirst();
			}
			
			dp[i] = map[i];
			if (!deque.isEmpty()) {
				dp[i] = Math.max(dp[i], dp[deque.peekFirst()] + map[i]);
			}
			res = Math.max(dp[i], res);
			while(!deque.isEmpty() && dp[deque.peekLast()] < dp[i]) {
				deque.pollLast();
			}
			
			deque.addLast(i);
		}
		System.out.println(res);
	}
	
	
}