import java.util.*;

class Solution {
    public int solution(int[] food_times, long k) {
        int N = food_times.length;
        int[][] food = new int[N][2];

        for (int i = 0; i < N; i++) {
            food[i][0] = i;
            food[i][1] = food_times[i];
        }

        Arrays.sort(food, (a, b) -> Integer.compare(a[1], b[1]));

        int cur = 0;

        for (int i = 0; i < N; i++) {
            long sum = (long) (food[i][1] - cur) * (N - i);

            if (sum > k) {
                ArrayList<Integer> idxs = new ArrayList<>();

                for (int j = i; j < N; j++) {
                    idxs.add(food[j][0]);
                }

                Collections.sort(idxs);

                int idx = (int) (k % (N - i));
                return idxs.get(idx) + 1;
            }

            cur = food[i][1];
            k -= sum;
        }

        return -1;
    }
}