import java.util.*;
class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        

        
        Arrays.sort(routes, (o1, o2) -> {
            if (o1[0] == o2[0]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        int r = routes[0][1];
        answer++;
        
        for (int i=1; i<routes.length; i++) {
            if (r >= routes[i][0]) {
                r = Math.min(r, routes[i][1]);
            } else {
                answer++;
                r = routes[i][1];
            }
        }
        return answer;
    }
}