class Solution {
    int n;
    public int solution(int n) {
        this.n = n;
        return dfs(0, 0);
    }
    
    public int dfs(int l, int r) {
        if (r > l) return 0;
        if (l == n && r == n) return 1;
        if (l > n || r > n) return 0;
        
        return dfs(l+1, r) + dfs(l, r+1);
    }
}