import java.util.*;
class Solution {
    public int solution(String begin, String target, String[] words) {
        int N = words.length+1;
        List<Integer>[] g = new List[N]; for (int i=0; i<N; i++) g[i] = new ArrayList<>();
        
        
        int targetNode = -1;
        for (int i=0; i<words.length; i++) {
            String cur = words[i];
            for (int j=i+1; j<words.length; j++) {
                String nxt = words[j];
                if (isSame(cur, nxt)) {
                    g[i+1].add(j+1);
                    g[j+1].add(i+1);
                }
            }
            
            if (isSame(cur, begin)) {
                g[i+1].add(0);
                g[0].add(i+1);
            }
            if(cur.equals(target)) targetNode = i+1;
        }
        if(targetNode == -1) return 0;
        boolean[] v=  new boolean[N];
        ArrayDeque<int[]> que = new ArrayDeque<>();
        
        que.add(new int[] {0, 0});
        v[0] = true;
        while(!que.isEmpty()) {
            int[] now = que.poll();
            int node = now[0];
            int dis = now[1];
            
            if (node == targetNode) {
                return dis;
            }
            
            for (int nxt : g[node]) {
                if (!v[nxt]) {
                    v[nxt] = true;
                    que.add(new int[] {nxt, dis+1});
                }
            }
        }
        
        
        return 0;
    }
    
    boolean isSame(String a, String b) {
        int diff = 0;
        for (int i=0; i<a.length(); i++) {
            if(a.charAt(i) != b.charAt(i)) {
                if (++diff == 2) return false;
            }
        }
        return true;
    }
}