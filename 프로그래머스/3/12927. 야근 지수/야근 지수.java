class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        
        int[] count = new int[50001];
        for (int i=0; i<works.length; i++) {
            count[works[i]]++;
        }
        
        int ptr = 50000;
        while (ptr > 0 && n > 0) {
            if (count[ptr] > n) {
                count[ptr] -= n;
                count[ptr-1] += n;
                break;
            }
            
            n -= count[ptr];
            count[ptr-1] += count[ptr];
            count[ptr] = 0;
            ptr--;
        }
        
        for (int i=1; i<=4; i++) {
            System.out.print(count[i] + " ");
        }
        System.out.println();
        
        for (int i=1; i<=50000; i++) {
            answer += (long) i * i * count[i];
        }
        
        return answer;
    }
}