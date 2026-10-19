import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        List<Integer>[] g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<results.length; i++) {
            g[results[i][0]].add(results[i][1]);
        }
        
        ArrayDeque<Integer> que = new ArrayDeque<>();
        int[] cnt = new int[n+1];
        for (int i=1; i<=n; i++) {
            que.add(i);
            boolean[] v = new boolean[n+1];
            while (!que.isEmpty()) {
                int now = que.poll();
                
                for (int nxt : g[now]) {
                    if (v[nxt]) continue;
                    v[nxt] = true;
                    que.add(nxt);
                    cnt[nxt]++;
                    cnt[i]++;
                }
            }
        }
        System.out.println(Arrays.toString(cnt));
        for (int i=1; i<=n; i++) {
            if (cnt[i] == n-1) answer++;
        }
        return answer;
    }
}