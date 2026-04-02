import java.util.*;
class Solution {
    String[] user_id, banned_id;
    int answer;
    int N, M;
    boolean[] v;
    HashSet<Integer> cnt;
    public int solution(String[] user_id, String[] banned_id) {
        answer = 0;
        this.user_id = user_id;
        this.banned_id = banned_id;
        N = user_id.length;
        M = banned_id.length;
        v = new boolean[N];
        cnt = new HashSet<>();
        dfs(0, 0);
        return cnt.size();
    }
    
    void dfs(int depth, int check) {
        if (depth == M) {
            cnt.add(check);
            return;
        }
        
        for (int i=0; i<N; i++) {
            if (!v[i] && isSame(user_id[i], banned_id[depth])) {
                v[i] = true;
                int nxtCheck = check | (1<<i);
                dfs(depth+1, nxtCheck);
                v[i] = false;
            }
        }
    }
    
    boolean isSame(String a, String b) {
        if (a.length() != b.length()) return false;
        
        for (int i=0; i<a.length(); i++) {
            if (b.charAt(i) != '*' && a.charAt(i) != b.charAt(i)) return false;
        }
        return true;
        
    }
}