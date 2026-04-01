import java.util.*;
class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        
        boolean[] v = new boolean[n];
        
        ArrayDeque<Integer> que = new ArrayDeque<>();
        for (int i=0; i<n; i++) {
            if (v[i]) continue;
            v[i] = true;
            que.add(i);
            answer++;
            while(!que.isEmpty()) {
                int now = que.poll();
                
                for (int j=0; j<n; j++) {
                    if (computers[now][j] == 0 || v[j]) continue;
                    v[j] = true;
                    que.add(j);
                }
                
            }
            
        }
        return answer;
    }
}