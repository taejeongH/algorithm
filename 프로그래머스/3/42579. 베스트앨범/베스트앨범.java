import java.util.*;
class Solution {
    class Music implements Comparable<Music>{
        int id;
        int amount;
        
        public Music(int id, int amount) {
            this.id = id;
            this.amount = amount;
        }
        
        public int compareTo(Music o) {
            if (o.amount == this.amount) return this.id-o.id;
            return o.amount - this.amount;
        }
    }
    public int[] solution(String[] genres, int[] plays) {
        int N = genres.length;
        
        int[] genreSum = new int[100];
        HashMap<String, Integer> map = new HashMap<>();
        PriorityQueue<Music>[] pq = new PriorityQueue[100];
        
        int genreIdx = 0;
        for (int i=0; i<N; i++) {
            String genre = genres[i];
            if (!map.containsKey(genre)) {
                genreSum[genreIdx] += plays[i];
                pq[genreIdx] = new PriorityQueue<>();
                pq[genreIdx].add(new Music(i, plays[i]));
                map.put(genre, genreIdx++);
            } else {
                int idx = map.get(genre);
                pq[idx].add(new Music(i, plays[i]));
                genreSum[idx] += plays[i];
            }
        }
        
        
        PriorityQueue<int[]> que = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
        for (int i=0; i<genreIdx; i++) {
            que.add(new int[] {i, genreSum[i]});
        }
        
        
        ArrayList<Integer> ans = new ArrayList<>();
        while(!que.isEmpty()) {
            int[] now = que.poll();
            int idx = now[0];
            
            PriorityQueue<Music> cur = pq[idx];
            int cnt = 0;
            while(!cur.isEmpty() && cnt < 2) {
                ans.add(cur.poll().id);
                cnt++;
            }
        }
        int[] answer = new int[ans.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = ans.get(i);
        }
        return answer;
    }
}