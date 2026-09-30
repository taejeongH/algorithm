import java.util.*;
class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        
        List<Integer>[] g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<edge.length; i++) {
            int s = edge[i][0];
            int e = edge[i][1];
            
            g[s].add(e);
            g[e].add(s);
        }
        
        ArrayDeque<int[]> que = new ArrayDeque<>();
        boolean[] v = new boolean[n+1];
        v[1] = true;
        que.add(new int[] {1, 0});
        
        int maxDis = 0;
        int[] distance = new int[n];
        
        while(!que.isEmpty()) {
            int[] now = que.poll();
            int node = now[0];
            int dis = now[1];
            
            distance[dis]++;
            
            maxDis = Math.max(dis, maxDis);
            
            for (int nxt : g[node]) {
                if (!v[nxt]) {
                    v[nxt] = true;
                    que.add(new int[] {nxt, dis+1});
                }
            }
        }
        return distance[maxDis];
    }
}