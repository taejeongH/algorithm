import java.util.*;
class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        int N = A.length;
        
        PriorityQueue<Integer> Apq = new PriorityQueue<>();
        PriorityQueue<Integer> Bpq = new PriorityQueue<>();
        
        for (int i=0; i<N; i++) {
            Apq.add(A[i]);
            Bpq.add(B[i]);
        }
        
        while (!Apq.isEmpty()) {
            int cur = Apq.poll();
            while(!Bpq.isEmpty() && cur >= Bpq.peek()) {
                Bpq.poll();
            }
            
            if (Bpq.isEmpty()) break;
            
            Bpq.poll();
            answer++;
        }
        return answer;
    }
}