import java.util.*;
class Solution {
    List<Integer>[] g;
    boolean[][] v;
    int[] info;
    int answer;
    public int solution(int[] info, int[][] edges) {
        answer = 0;
        g = new List[info.length]; for(int i=0; i<info.length; i++) g[i] = new ArrayList<>();
        v = new boolean[info.length][1 << info.length];
        this.info = info;
        
        for (int i=0; i<info.length-1; i++) {
            int s = edges[i][0];
            int e = edges[i][1];
            g[s].add(e);
            g[e].add(s);
        }
        
        v[0][1] = true;
        dfs(1, 1, 0);
        return answer;
    }
    
    void dfs(int key, int sheep, int wolf) {
        answer = Math.max(answer, sheep);

        for (int node = 0; node < info.length; node++) {

            // 아직 방문하지 않은 노드는 출발점으로 사용할 수 없음
            if ((key & (1 << node)) == 0) continue;

            for (int nxt : g[node]) {

                // 이미 먹은 동물
                if ((key & (1 << nxt)) != 0) continue;

                int ns = sheep + (info[nxt] == 0 ? 1 : 0);
                int nw = wolf + (info[nxt] == 1 ? 1 : 0);

                if (ns <= nw) continue;

                dfs(key | (1 << nxt), ns, nw);
            }
        }
    }
}