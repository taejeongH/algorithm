class Solution {
    public int solution(int[] a) {
        
        int N = a.length;
        int[] leftMin = new int[N];
        int[] rightMin = new int[N];
        
        leftMin[0] = a[0];
        for (int i=1; i<N; i++) {
            leftMin[i] = Math.min(leftMin[i-1], a[i]);
        }
        
        rightMin[N-1] = a[N-1];
        for (int i=N-2; i>=0; i--) {
            rightMin[i] = Math.min(rightMin[i+1], a[i]);
        }
        
        int answer = 0;
        for (int i=0; i<N; i++) {
            int count = 0;
            if (i != 0 && leftMin[i-1] < a[i]) count++;
            if (i != N-1 && rightMin[i+1] < a[i]) count++;
            
            if(count < 2) answer++;
        }
        return answer;
    }
}

// 왼쪽 오른쪽 중 나보다 작은게 1개 이하 있을 때