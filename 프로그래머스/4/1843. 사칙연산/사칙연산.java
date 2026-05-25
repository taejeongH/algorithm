import java.util.*;
class Solution {
    static String[] arr;
    static int[][] minDp;
    static int[][] maxDp;
    void dfs(int l, int r) {
        if (l > r) return;
        if (l == r) {
            minDp[l][r] = maxDp[l][r] = Integer.parseInt(arr[l]);
            return;
        }
        if(minDp[l][r] != 100000000) return;
        
        for (int i=l; i<r; i+=2) {
            dfs(l, i);
            dfs(i+2, r);
            
            if (arr[i+1].equals("+")) {
                maxDp[l][r] = Math.max(maxDp[l][r], maxDp[l][i]+maxDp[i+2][r]);
                minDp[l][r] = Math.min(minDp[l][r], minDp[l][i]+minDp[i+2][r]);
            } else {
                maxDp[l][r] = Math.max(maxDp[l][r], maxDp[l][i]-minDp[i+2][r]);
                minDp[l][r] = Math.min(minDp[l][r], minDp[l][i]-maxDp[i+2][r]);
            }
            
        }
    }
    
    public int solution(String arr[]) {
        this.arr = arr;
        int N = arr.length;
        minDp = new int[N][N];
        maxDp = new int[N][N];
        for (int i=0; i<N; i++) {
            Arrays.fill(minDp[i], 100000000);
            Arrays.fill(maxDp[i], -1000000000);
        }
        dfs(0, N-1);
        return maxDp[0][N-1];
    }
}