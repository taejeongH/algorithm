import java.util.*;
class Solution {
    String t;
    int M;
    List<String>[] arr;
    final int INF = 1_000_000_000;
    int[] dp;
    public int solution(String[] strs, String t) {
        int N = strs.length;
        this.t = t;
        M = t.length();
        arr = new List[26]; for(int i=0; i<26; i++) arr[i] = new ArrayList<>();
        for (int i=0; i<N; i++) {
            arr[strs[i].charAt(0) - 'a'].add(strs[i]);
        }
        dp = new int[t.length()];
        Arrays.fill(dp, -1);
        int answer = dfs(0);
        return answer==INF?-1:answer;
    }
    
    int dfs(int idx) {
        if(idx >= M) return 0; 
        if(dp[idx] != -1) return dp[idx];
        int res = INF;
        
        for (String word : arr[t.charAt(idx)-'a']) {
            if (idx + word.length() > t.length()) continue;
            
            boolean can = true;
            for (int i=0; i<word.length(); i++) {
                if (word.charAt(i) != t.charAt(idx+i)) {
                    can = false;
                    break;
                } 
            }
            
            if(can) {
                res = Math.min(res, dfs(idx+word.length())+1);
            }
        }
        
        return dp[idx]=res;
    }
}