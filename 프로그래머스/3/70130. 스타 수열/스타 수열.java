import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        int N = a.length;

        int[] count = new int[N];
        for (int x : a) count[x]++;

        for (int i = 0; i < N; i++) {
            if (count[i] * 2 <= answer) continue;

            int cur = 0;

            for (int j = 0; j < N - 1; j++) {
                if (a[j] == a[j + 1]) continue;
                if (a[j] != i && a[j + 1] != i) continue;

                cur += 2;
                j++;
            }

            answer = Math.max(answer, cur);
        }

        return answer;
    }
}