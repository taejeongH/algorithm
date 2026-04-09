import java.util.*;
class Solution {
    public int solution(int[][] jobs) {
        int answer = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1[2] == o2[2]) {
                if (o1[1] == o2[1]) return o1[0] - o2[0];
                return o1[1] - o2[1];
            }
            return o1[2] - o2[2];
        });
        
        PriorityQueue<int[]> pending = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        for (int i=0; i<jobs.length; i++) {
            int start = jobs[i][0];
            int take = jobs[i][1];
            
            pending.add(new int[] {i, start, take});
        }
        
        int end = 0;
        while (!pending.isEmpty()) {
            while (!pending.isEmpty() && end >= pending.peek()[1]) {
                pq.add(pending.poll());
            }
            
            
            if (!pq.isEmpty()) {
                int[] now = pq.poll();
                int id = now[0];
                int start = now[1];
                int take = now[2];

                int newstart = Math.max(end, start);
                end = newstart + take;
                answer += end - start;
            } else {
                end++;
            }       
        }
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int id = now[0];
            int start = now[1];
            int take = now[2];

            int newstart = Math.max(end, start);
            end = newstart + take;
            answer += end - start;
        }
        
        return answer/jobs.length;
    }
}

/*
    1. 소요 시간 짧은
    2. 요청 시각 빠른
    3. 작업의 번호가 작은 것
*/