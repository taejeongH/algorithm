import java.util.*;
class Solution {
    int parent[];
    
    int find (int x) {
        if (parent[x] == x) return x;
        int xRoot = find(parent[x]);
        return parent[x] = xRoot;
    }
    
    boolean union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);
        
        if(xRoot == yRoot) return false;
        parent[xRoot] = yRoot;
        return true;
    }
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        Arrays.sort(costs, (o1, o2) -> o1[2]-o2[2]);
        parent = new int[n];
        for (int i=0; i<n; i++) parent[i] = i;
        
        
        for (int i=0; i<costs.length; i++) {
            if (union(costs[i][0], costs[i][1])) answer += costs[i][2];
        }
        return answer;
    }
}