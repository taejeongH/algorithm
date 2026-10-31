public class Solution {
    int[][] land;
    int N, P, Q;
    public long solution(int[][] land, int P, int Q) {
        N = land.length;
        this.land = land;
        this.P = P;
        this.Q = Q;
        
        int l = Integer.MAX_VALUE;
        int r = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                r = Math.max(land[i][j], r);
                l = Math.min(land[i][j], l);
            }
        }
        
        
        while (l < r) {
            int mid = (l+r)/2;
            
            long cur = get(mid);
            long right = get(mid+1);
            //System.out.println(mid + " " + cur + " " + right);
            if (cur > right) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        //System.out.println(l + " " + r);
        return get(r);
    }
    
    long get(int mid) {
        long sum = 0;
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (mid < land[i][j]) {
                    sum += (long) (land[i][j] - mid) * Q;
                } else {
                    sum += (long) (mid - land[i][j]) * P;
                }
            }
        }
        return sum;
    }
}