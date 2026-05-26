import java.util.*;
class Solution {
    public int solution(int[] a) {
        int N = a.length;
        if(a.length==1) return 0;
		int[] cnt = new int[N+1];

        for (int i=0; i<N; i++) {
            cnt[a[i]]++;
        }
        
        int max = 0;
        for (int i=0; i<=N; i++) {
            if (max >= cnt[i]) continue;
            
            int curCount = 0;
            int idx = 0;
            while(idx < N-1) {
                if (a[idx]!= a[idx+1] && (a[idx] == i || a[idx+1] == i)) {
                    idx+=2;
                    curCount++;
                } else {
                    idx++;
                }
            }
            max = Math.max(curCount, max);
        }
        
        return max*2;
    }
}