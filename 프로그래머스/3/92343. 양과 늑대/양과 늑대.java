import java.util.*;
class Solution {
    List<Integer>[] g;
    int[] info;
    int N;
    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        this.info = info;
        g = new List[info.length]; for (int i=0; i<info.length; i++) g[i] = new ArrayList<>();
        for (int i=0; i<info.length-1; i++) {
            int s = edges[i][0];
            int e = edges[i][1];
            g[s].add(e);
            g[e].add(s);
        }
        this.N = info.length;
        answer = dfs(1, 0, 1);
        return answer+1;
    }
    
    int dfs(int nodes, int wolf, int sheep) {
        int res = 0;
        
        for (int i=0; i<N; i++) {
            if ((nodes & (1<<i)) != 0) {
                for (int nxt : g[i]) {
                    
                    if ((nodes & (1<<nxt)) != 0) continue;
                    // System.out.println(nxt);
                    int nxtwolf = wolf + (info[nxt]==0?0:1);
                    int nxtsheep = sheep + (info[nxt]==0?1:0);
                    
                    if (nxtwolf < nxtsheep) {
                        res = Math.max(res, dfs(nodes | (1<<nxt), nxtwolf, nxtsheep) + (info[nxt]==0?1:0));
                    }
                   
                }
            }
        }

        return res;
    }
}