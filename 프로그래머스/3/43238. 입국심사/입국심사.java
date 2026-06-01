class Solution {
    static int[] arr;
    static int N, M;
    public long solution(int n, int[] times) {
        arr = times;
        N = arr.length;
        M = n;
        int min = Integer.MAX_VALUE;
        for (int i=0; i<N; i++) {
            min = Math.min(arr[i], min);
        }
        
        long left = 0;
        long right = (long) min * n;
        
        long answer = right;
        while (left <= right) {
            long mid = (left+right)/2;
            
            if (can(mid)) {
                right = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                left = mid + 1;
            }
        }
        return answer;
    }
    
    boolean can(long time) {
        
        long sum = 0;
        for (int i=0; i<N; i++) {
            if (time < arr[i]) continue;
            sum += time / arr[i];
            if (sum >= M) return true;
        }
        
        return false;
    }
}