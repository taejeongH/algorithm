import java.util.*;
class Solution {
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        List<int[]>[] g = new List[n]; for (int i=0; i<n; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<costs.length; i++) {
            int s = costs[i][0];
            int e = costs[i][1];
            int c = costs[i][2];
            
            g[s].add(new int[]{e, c});
            g[e].add(new int[]{s, c});
        }
        
        int[] prim = new int[n];
        Arrays.fill(prim, Integer.MAX_VALUE);
        boolean[] v = new boolean[n];
        
        prim[0] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        pq.add(new int[] {0, 0});

        while (!pq.isEmpty()){
            int[] now = pq.poll();
            int node = now[0];
            int cost = now[1];
            
            if (v[node]) continue;
            v[node] = true;
            answer += cost;
            
            for (int[] nxt : g[node]) {
                if (prim[nxt[0]] > nxt[1]) {
                    prim[nxt[0]] = nxt[1];
                    pq.add(new int[] {nxt[0], nxt[1]});
                }
            }
            
        }
        
        return answer;
    }
    
    
}