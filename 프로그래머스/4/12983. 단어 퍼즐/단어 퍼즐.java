import java.util.*;
class Solution {
    String t;
    List<String>[] strings;
    final int INF = 1_000_000_000;
    int[] dp;
    public int solution(String[] strs, String t) {
        this.t = t;
        strings = new List[26]; for(int i=0; i<26; i++) strings[i] = new ArrayList<>();
        for (int i=0; i<strs.length; i++) {
            int idx = strs[i].charAt(0) - 'a';
            strings[idx].add(strs[i]);
        }
        dp = new int[t.length()];
        Arrays.fill(dp, -1);
        int answer = dfs(0);
        return answer==INF?-1:answer;
    }
    
    int dfs(int idx) {
        if (idx == t.length()) return 0;
        if (dp[idx] != -1) return dp[idx];
        
        int res = INF;
        int i = t.charAt(idx) - 'a';
        for (String s : strings[i]) {
            if (!isSame(s, idx)) continue;
            res = Math.min(dfs(idx + s.length()) + 1, res);
        }
        return dp[idx]=res;
    }
    
    boolean isSame(String s, int idx) {
        if (s.length() > t.length() - idx) return false;
        for (int i=0; i<s.length(); i++) {
            if (s.charAt(i) != t.charAt(idx + i)) return false;
        }
        return true;
    }
}