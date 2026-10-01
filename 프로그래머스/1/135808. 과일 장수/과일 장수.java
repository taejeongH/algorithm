import java.util.*;
class Solution {
    public int solution(int k, int m, int[] score) {
        int answer = 0;
        
        int[] count = new int[k+1];
        
        for (int i=0; i<score.length; i++) {
            count[score[i]]++;
        }

        int ptr = k;
        //System.out.println(Arrays.toString(count));
        while (ptr > 0) {
            int sum = 0;
            while (ptr>0 && sum < m) {
                if (sum + count[ptr] >= m) {
                    count[ptr] -= m - sum;
                    sum = m;
                    break;
                }
                
                sum += count[ptr];
                count[ptr] = 0;
                ptr--;
            }
            if (sum==m) answer += ptr * m;
            //System.out.println(Arrays.toString(count));
            //System.out.println(answer);
        }
        
        return answer;
    }
}