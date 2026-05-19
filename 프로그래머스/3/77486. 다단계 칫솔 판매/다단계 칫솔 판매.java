import java.util.*;

class Solution {
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int N = enroll.length;
        int M = seller.length;
        
        int[] answer = new int[N];
        
        HashMap<String, Integer> map = new HashMap<>();
        for (int i=0; i<N; i++) {
            map.put(enroll[i], i+1);
        }
        
        int[] parent = new int[N+1];
        for (int i=1; i<=N; i++) {
            parent[i] = i;
        }
        
        for (int i=0; i<N; i++) {
            if (referral[i].equals("-")) continue;
            
            int pid = map.get(referral[i]);
            parent[i+1] = pid;
        }
        
        for (int i=0; i<M; i++) {
            int cur = map.get(seller[i]);
            int curAmount = amount[i]*100;
            
            
            while (curAmount != 0) {
                answer[cur-1] += curAmount - curAmount / 10;
                curAmount = curAmount/10;
                if (cur == parent[cur]) break;
                cur = parent[cur];
            }
            
        }
        return answer;
    }
}