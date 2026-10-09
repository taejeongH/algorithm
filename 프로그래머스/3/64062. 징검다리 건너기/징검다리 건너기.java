import java.util.*;
class Solution {
    public int solution(int[] stones, int k) {
        int answer = 200_000_000;
        ArrayDeque<Integer> dq = new ArrayDeque<>();

        for (int i = 0; i < stones.length; i++) {
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            while (!dq.isEmpty() && stones[dq.peekLast()] <= stones[i]) {
                dq.pollLast();
            }

            dq.offerLast(i);

            if (i >= k - 1) {
                answer = Math.min(answer, stones[dq.peekFirst()]);
            }
        }
        return answer;
    }
}