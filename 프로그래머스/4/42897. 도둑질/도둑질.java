import java.util.*;

class Solution {
    public int solution(int[] money) {
        int n = money.length;

        if (n == 1) return money[0];
        if (n == 2) return Math.max(money[0], money[1]);

        return Math.max(
            rob(money, 0, n - 2), // 첫 집 포함 가능, 마지막 집 제외
            rob(money, 1, n - 1)  // 첫 집 제외, 마지막 집 포함 가능
        );
    }

    int rob(int[] money, int start, int end) {
        int prev2 = 0;
        int prev1 = 0;

        for (int i = start; i <= end; i++) {
            int cur = Math.max(prev1, prev2 + money[i]);
            prev2 = prev1;
            prev1 = cur;
        }

        return prev1;
    }
}