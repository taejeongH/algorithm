class Solution {
    int N;
    int[] sum;
    public int solution(int[] cookie) {
        int answer = 0;
        N = cookie.length;
        sum = new int[N+1];
        
        for (int i=1; i<=N; i++) {
            sum[i] = sum[i-1] + cookie[i-1];
        }
        
        
        for (int l=1; l<N; l++) {
            for (int r=l+1; r<=N; r++) {
                if (binarySearch(l, r)) {
                    answer = Math.max(answer, (sum[r] - sum[l-1])/2);
                }
            }
        }
        
        return answer;
    }
    
    boolean binarySearch(int l, int r) {
        if ((sum[r] - sum[l-1]) % 2 != 0) return false; 
        
        int key = (sum[r] - sum[l-1])/2;
        int lorg = l;
        while (l <= r) {
            int mid = (l+r)/2;
            
            int cur = sum[mid] - sum[lorg-1];
            if (cur == key) return true;
            else if (cur < key) l = mid+1;
            else r = mid-1;
        }
        
        return false;
    }
}