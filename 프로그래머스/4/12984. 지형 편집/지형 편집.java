class Solution {
    public long solution(int[][] land, int P, int Q) {
        int n = land.length;

        long left = 0;
        long right = 0;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                right = Math.max(right, land[i][j]);
            }
        }

        while (left < right) {
            long mid = (left + right) / 2;

            long cost1 = getCost(land, mid, P, Q);
            long cost2 = getCost(land, mid + 1, P, Q);

            if (cost1 <= cost2) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        return getCost(land, left, P, Q);
    }

    private long getCost(int[][] land, long target, int P, int Q) {
        long cost = 0;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land.length; j++) {
                long h = land[i][j];

                if (h < target) {
                    cost += (target - h) * P;
                } else {
                    cost += (h - target) * Q;
                }
            }
        }

        return cost;
    }
}