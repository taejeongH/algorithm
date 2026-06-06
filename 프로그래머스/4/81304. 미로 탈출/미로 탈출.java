import java.util.*;
class Solution {
    static final int INF = 1_000_000_000;
    static HashMap<Integer, Integer> map;
    public int solution(int n, int start, int end, int[][] roads, int[] traps) {
        int N = n;
        int M = traps.length;
        
        List<int[]>[] g = new List[N+1];
        List<int[]>[] reverseG = new List[N+1];
        map = new HashMap<>();
        
        for (int i=1; i<=N; i++) {
            g[i] = new ArrayList<>();
            reverseG[i] = new ArrayList<>();
        }
        
        for (int i=0; i<roads.length; i++) {
            int s = roads[i][0];
            int e = roads[i][1];
            int c = roads[i][2];
            
            g[s].add(new int[]{e, c});
            reverseG[e].add(new int[]{s, c});
        }
        
        for (int i=0; i<M; i++) {
            map.put(traps[i], i);
        }
        
        int[][] distance = new int[N+1][1<<M];
        
        for (int i=1; i<=N; i++) Arrays.fill(distance[i], INF);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        pq.add(new int[] {start, 0, 0});
        distance[start][0] = 0;
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int dis = now[1];
            int key = now[2];
            
            if (dis > distance[node][key]) continue;
            if (node == end) return dis;
            
            boolean curActive = isActive(node, key);
            
            for (int[] nxt : g[node]) {
                int nxtNode = nxt[0];
                int cost = nxt[1];
                int nxtKey = key;
                if (map.containsKey(nxtNode)) nxtKey ^= (1 << map.get(nxtNode));
                
                boolean nxtActive = isActive(nxtNode, key);
                
                if (curActive == nxtActive) {
                    if (distance[nxtNode][nxtKey] < dis + cost) continue;
                    distance[nxtNode][nxtKey] = dis+cost;
                    pq.add(new int[] {nxtNode, dis+cost, nxtKey});
                }
            }
            
            
            for (int[] nxt : reverseG[node]) {
                int nxtNode = nxt[0];
                int cost = nxt[1];
                int nxtKey = key;
                if (map.containsKey(nxtNode)) nxtKey ^= (1 << map.get(nxtNode));
                
                boolean nxtActive = isActive(nxtNode, key);
                
                if (curActive != nxtActive) {
                    if (distance[nxtNode][nxtKey] < dis + cost) continue;
                    distance[nxtNode][nxtKey] = dis+cost;
                    pq.add(new int[] {nxtNode, dis+cost, nxtKey});
                }
            }
        }
        
        return -1;
    }
    
    boolean isActive(int node, int key) {
        if (!map.containsKey(node)) return false;
        
        int bit = 1 << map.get(node);
        return (key & bit) != 0;
    }
}