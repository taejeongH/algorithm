import java.util.*;
class Solution {
    public int solution(int n, int[][] results) {
        int answer = 0;
        
        List<Integer>[] g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        List<Integer>[] reverseG = new List[n+1]; for(int i=1; i<=n; i++) reverseG[i] = new ArrayList<>();
        
        for (int i=0; i<results.length; i++) {
            int s = results[i][0];
            int e = results[i][1];
            g[s].add(e);
        }
        int[] in = new int[n+1];
        int[] out = new int[n+1];
        ArrayDeque<Integer> que = new ArrayDeque<>();
        boolean[] v;
        for (int i=1; i<=n; i++) {
            v = new boolean[ n+1];
            que.add(i);
            v[i] = true;
            while(!que.isEmpty()) {
                int now = que.poll();
                
                for (int nxt : g[now]) {
                    if(v[nxt]) continue;
                    v[nxt] = true;
                    que.add(nxt);
                    in[nxt]++;
                    out[i]++;
                }
            }
        }
        
        for (int i=1; i<=n; i++) {
            if (in[i] + out[i] == n-1) answer++;
        }
        
        System.out.println(Arrays.toString(in));
        System.out.println(Arrays.toString(out));
        return answer;
    }
}