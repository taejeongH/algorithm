import java.util.*;
class Solution {
    final int INF = 1_000_000_000;
    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        int[] answer = {};
        List<int[]>[] g = new List[n+1]; for(int i=1; i<=n; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<paths.length; i++) {
            int s = paths[i][0];
            int e = paths[i][1];
            int c = paths[i][2];
            
            g[s].add(new int[]{e, c});
            g[e].add(new int[]{s, c});
        }
        
        boolean[] isSummit = new boolean[n+1];
        for (int i=0; i<summits.length; i++) {
            isSummit[summits[i]] = true;
        }
        
        int[] distance = new int[n+1];
        Arrays.fill(distance, INF);
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        
        for (int i=0; i<gates.length; i++) {
            pq.add(new int[] {gates[i], 0});
        }
        
        while(!pq.isEmpty()){
            int[] now = pq.poll();
            int node = now[0];
            int dis = now[1];

            if (distance[node] < dis) continue;

            for (int[] nxt : g[node]) {
                int nxtDis = Math.max(dis, nxt[1]);
                int nxtNode = nxt[0];
                if (distance[nxtNode] > nxtDis) {
                    distance[nxtNode] = nxtDis;
                    if (isSummit[nxtNode]) continue;
                    pq.add(new int[] {nxtNode, nxtDis});
                }
            }
        }
        
        int resNode = -1;
        int resDis = INF;
        
        
        for (int i=1; i<=n; i++) {
            if (!isSummit[i]) continue;
            if (resDis > distance[i]) {
                resDis = distance[i];
                resNode = i;
            } else if(resDis == distance[i] && resNode > i) {
                resNode = i;
            }
        }
        

        return new int[] {resNode, resDis};
    }
}