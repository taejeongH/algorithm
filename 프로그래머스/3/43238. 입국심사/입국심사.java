class Solution {
    int[] times;
    int n;
    public long solution(int n, int[] times) {
        long answer = 0;
        this.times = times;
        this.n = n;
        
        long l = 1L;
        long r = Long.MAX_VALUE;
        for (int i=0; i<times.length; i++) {
            r = Math.min((long) times[i] * n, r);
        }
        
        while (l <= r) {
            long mid = (l+r)/2;
            
            if (can(mid)) {
                answer = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return answer;
    }
    
    boolean can(long mid) {
        long sum = 0;
        for (int i=0; i<times.length; i++) {
            sum += mid / times[i];
        }
        
        if (sum >= n) return true;
        return false;
    }
}