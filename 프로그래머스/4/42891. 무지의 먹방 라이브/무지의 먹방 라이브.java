import java.util.*;
class Solution {
    public int solution(int[] food_times, long k) {
        int answer = 0;
        int N = food_times.length;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);
        
        for (int i=0; i<N; i++){
            pq.add(new int[] {i, food_times[i]});
        }
        
        long time = 0;
        long rotCount = 0;
        while (!pq.isEmpty()) {
            int minValue = pq.peek()[1];
            long cnt = minValue-rotCount;
            
            if (time + ((long) pq.size() * cnt) > k) {
                break;
            }
            time += pq.size() * cnt;
            rotCount += cnt;
            pq.poll();
        }
        
        if (pq.isEmpty()) return -1;

        int idx = (int) ((k - time) % pq.size());

        ArrayList<int[]> arr = new ArrayList<>();
        while(!pq.isEmpty()) {
            arr.add(pq.poll());
        }

        Collections.sort(arr, (o1, o2) -> o1[0] - o2[0]);

        return arr.get(idx)[0] + 1;
    }
}