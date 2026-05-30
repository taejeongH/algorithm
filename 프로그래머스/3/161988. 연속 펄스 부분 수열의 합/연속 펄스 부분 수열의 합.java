import java.util.*;
class Solution {
    public long solution(int[] sequence) {
        
        int N = sequence.length;

        long[] dp = new long[N];
        long[] dp2 = new long[N];
        
        dp[N-1] = sequence[N-1] * ((N-1)%2==1?1:-1);
        dp2[N-1] = sequence[N-1] * ((N-1)%2==0?1:-1);
        
        long answer = Math.max(dp[N-1], dp2[N-1]);
        for (int i=N-2; i>=0; i--) {
            int cur = sequence[i] * ((i%2)==1?1:-1);
            int cur2 = sequence[i] * ((i%2)==0?1:-1);
            
            dp[i] = Math.max(cur, cur+dp[i+1]);
            dp2[i] = Math.max(cur2, cur2+dp2[i+1]);
            
            answer = Math.max(answer, dp[i]);
            answer = Math.max(answer, dp2[i]);
        }

        return answer;
    }
    

}