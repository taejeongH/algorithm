class Solution {
    public int[] solution(int[] sequence, int k) {
        int N = sequence.length;
        
        int start = 0;
        int end = N;
        
        int i=0;
        int j=0;
        
        int sum = sequence[0];
        
        while (i < N && j < N) {
            while (j+1 < N && sum < k) {
               sum += sequence[++j]; 
            }
            
            
            if (sum == k && end - start > j - i) {
                start = i;
                end = j;
            }
            
            sum -= sequence[i++];
        }
        
        return new int[] {start, end};
    }
}