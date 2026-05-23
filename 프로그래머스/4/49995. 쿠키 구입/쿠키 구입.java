class Solution {
    boolean can(int s, int e) {
        int make = (sum[e]-sum[s-1])/2;
        int cur = 0;
        
        for (int i=s; i<=e; i++) {
            cur += cookie[i-1];
            if (cur == make) return true;
            if (cur > make) return false;
        }
        return false;
    }
    
    static int[] sum;
    static int[] cookie;
    public int solution(int[] cookie) {
        int answer = 0;
        
        int N = cookie.length;
        
        sum = new int[N+1];
        this.cookie = cookie;
        for (int i=1; i<=N; i++) {
            sum[i] = sum[i-1] + cookie[i-1];
        }
        
        
        for (int i=1; i<=N; i++) {
            
            for (int j=N; j>=i+1; j--) {
                int cur = sum[j] - sum[i-1];
                if (cur % 2 == 1) continue;
                
                if (can(i, j)) {
                    //System.out.println(i + " " + j);
                    answer = Math.max(answer, cur/2);
                    break;
                }
            }
            
            
        }
        return answer;
    }
}