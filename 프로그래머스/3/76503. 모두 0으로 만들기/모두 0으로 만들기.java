import java.util.*;
class Solution {
    static boolean[] v;
    static int[] a;
    static List<Integer>[] g;
    static long answer;
    
    public long solution(int[] a, int[][] edges) {
        int N = a.length;
        g = new List[N];
        this.a = a;
        for (int i=0; i<N; i++) g[i] = new ArrayList<>();
        
        for (int i=0; i<edges.length; i++) {
            g[edges[i][0]].add(edges[i][1]);
            g[edges[i][1]].add(edges[i][0]);
        }
        
        v = new boolean[N];
        answer = 0;
        v[0] = true;
        dfs(0, 0);
        
        return answer;
    }
    
    
    long dfs(int idx, int start) {
        
        
        long sum = 0;
        for (int i=0; i<g[idx].size(); i++) {
            if(v[g[idx].get(i)]) continue;
            v[g[idx].get(i)] = true;
            sum += dfs(g[idx].get(i), start);
        }
        
        
        answer += Math.abs(sum + a[idx]);
        if (start == idx && a[idx] + sum != 0) answer = -1;
        return sum + a[idx];
    }
}