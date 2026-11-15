import java.util.*;

class Solution {
    int[] answer;
    int[] parent;
    Map<String, Integer> idxs;

    public int[] solution(String[] enroll, String[] referral,
                          String[] seller, int[] amount) {
        int N = enroll.length;

        idxs = new HashMap<>();
        parent = new int[N + 1];
        answer = new int[N + 1];

        for (int i = 0; i < N; i++) {
            idxs.put(enroll[i], i + 1);
        }

        for (int i = 0; i < N; i++) {
            parent[i + 1] = referral[i].equals("-") ? 0 : idxs.get(referral[i]);
        }

        for (int i = 0; i < seller.length; i++) {
            int cur = idxs.get(seller[i]);
            int money = amount[i] * 100;

            while (cur != 0 && money > 0) {
                int fee = money / 10;

                answer[cur] += money - fee;

                money = fee;
                cur = parent[cur];
            }
        }

        return Arrays.copyOfRange(answer, 1, N + 1);
    }
}