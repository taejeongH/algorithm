class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        
        
        int mod = n;
        for (int i=0; i<n; i++) {
            if (s / mod == 0) return new int[] {-1};
            answer[i] = s / mod;
            s -= answer[i];
            mod--;
        }
        
        return answer;
    }
}