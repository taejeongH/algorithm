class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int l = 1;
        for (int i=0; i<stations.length; i++) {
            int r = stations[i]-w-1;
            if (l <= r) {
                answer += (r - l + 1) / (w * 2 + 1);
                if ((r-l+1) % (w*2+1) != 0) answer++;
            }
            
            l = stations[i]+w+1;
        }
        
        int r = n;
        if (l <= r) {
            answer += (r - l + 1) / (w * 2 + 1);
            if ((r-l+1) % (w*2+1) != 0) answer++;
        }

        return answer;
    }
}