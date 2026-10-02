import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        
        Arrays.sort(routes, (o1, o2) -> o1[1] - o2[1]);
        
        for (int i=0; i<routes.length; i++) {
            int cur = routes[i][1];
            int nxt = i+1;
            while (nxt < routes.length && routes[nxt][0] <= cur) {
                nxt++;
            }
            answer++;
            i = nxt-1;
        }
        
        
        return answer;
    }
}