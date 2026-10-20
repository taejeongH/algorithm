import java.util.*;
class Solution {
    class Job {
        int id;
        int takeTime;
        int start;
        
        public Job(int id, int takeTime, int start) {
            this.id = id;
            this.takeTime = takeTime;
            this.start = start;
        }
    }
    public int solution(int[][] jobs) {
        int answer = 0;
        int N = jobs.length;
        
        PriorityQueue<Job> pq = new PriorityQueue<>((o1, o2) -> {
           if (o1.takeTime == o2.takeTime) {
               if (o1.start == o2.start) return o1.id - o2.id;
               return o1.start-o2.start;
           } 
            return o1.takeTime - o2.takeTime;
        });
        
        Job[] jobArr = new Job[N];
        for (int i=0; i<N; i++) {
            jobArr[i] = new Job(i, jobs[i][1], jobs[i][0]);
        }
        
        Arrays.sort(jobArr, (o1, o2) -> o1.start - o2.start);
        
        int curTime = 0;
        int cur = 0;
        while (cur < N) {
            while (cur < N && jobArr[cur].start <= curTime) {
                pq.add(jobArr[cur++]);
            }
            
            if (!pq.isEmpty()) {
                Job j = pq.poll();
                int endTime = j.takeTime + curTime;
                answer += endTime - j.start;
                curTime = endTime;
            } else {
                curTime = jobArr[cur].start;
            }
        }
        
        while(!pq.isEmpty()) {
            Job j = pq.poll();
            int endTime = j.takeTime + curTime;
            answer += endTime - j.start;
            curTime = endTime;
        }
        
        
        return answer/N;
    }
}