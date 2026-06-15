import java.util.*;
class Solution {
    public int[] solution(int e, int[] starts) {
        int N = starts.length;
        
        int[] count = new int[e+1];
        int[] max = new int[e+1];
        
        for (int i=1; i<=e; i++) {
            for (int j=i; j<=e; j+=i) {
                count[j]++;
            }
        }
        //System.out.println(Arrays.toString(count));
        max[e] = e;
        for (int i=e-1; i>=1; i--) {
            if (count[max[i+1]] <= count[i]) {
                max[i] = i;
            } else {
                max[i] = max[i+1];
            }
        }
        
        int[] answer = new int[N];
        for (int i=0; i<N; i++) {
            answer[i] = max[starts[i]];
        }
        
        return answer;
    }
    
}