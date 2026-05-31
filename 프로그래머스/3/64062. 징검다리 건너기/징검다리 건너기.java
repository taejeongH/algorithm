import java.util.*;

class Solution {
    public int solution(int[] stones, int k) {
        int answer = 2_000_000_000;
        int N = stones.length;

        ArrayDeque<int[]> que = new ArrayDeque<>();

        for (int i = 0; i < N; i++) {
            while (!que.isEmpty() && que.peekLast()[1] <= stones[i]) {
                que.pollLast();
            }

            que.addLast(new int[] {i, stones[i]});

            while (!que.isEmpty() && que.peekFirst()[0] < i - k + 1) {
                que.pollFirst();
            }

            if (i >= k - 1) {
                answer = Math.min(answer, que.peekFirst()[1]);
            }
        }

        return answer;
    }
}