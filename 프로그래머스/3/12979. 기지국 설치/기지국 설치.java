class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;

        int N = stations.length;
        
        int s = 1;
        for (int i=0; i<N; i++) {         
            int e = stations[i] - w - 1;
            
            if (e >= s) {
                answer += (e-s+1) / (w*2+1);
                if ((e-s+1) % (w*2+1) != 0) answer++;
            }
            s = stations[i] + w + 1;
        }
        
        int e = n;
        if (e >= s) {
            answer += (e-s+1) / (w*2+1);
            if ((e-s+1) % (w*2+1) != 0) answer++;
        }

        return answer;
    }
}