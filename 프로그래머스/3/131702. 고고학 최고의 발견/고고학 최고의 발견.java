class Solution {
    int answer, N;
    int[][] clockHands;

    public int solution(int[][] clockHands) {
        answer = Integer.MAX_VALUE;
        N = clockHands.length;
        this.clockHands = clockHands;

        dfs(0, new int[N]);

        return answer;
    }

    void dfs(int depth, int[] arr) {
        if (depth == N) {
            int[][] tmp = new int[N][N];

            for (int i = 0; i < N; i++) {
                tmp[i] = clockHands[i].clone();
            }

            int sum = 0;

            // 1. DFS로 결정한 첫 번째 행 회전
            for (int j = 0; j < N; j++) {
                rotate(tmp, 0, j, arr[j]);
                sum += arr[j];
            }

            // 2. 윗 행을 0으로 만들도록 다음 행 회전
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int count = (4 - tmp[i - 1][j]) % 4;

                    rotate(tmp, i, j, count);
                    sum += count;
                }
            }

            // 3. 모든 시계가 0인지 확인
            if (isClear(tmp)) {
                answer = Math.min(answer, sum);
            }
            return;
        }

        for (int i = 0; i < 4; i++) {
            arr[depth] = i;
            dfs(depth + 1, arr);
        }
    }

    void rotate(int[][] arr, int r, int c, int count) {
        int[] dr = {0, 0, 0, -1, 1};
        int[] dc = {0, -1, 1, 0, 0};

        for (int d = 0; d < 5; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N) continue;

            arr[nr][nc] = (arr[nr][nc] + count) % 4;
        }
    }

    boolean isClear(int[][] arr) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] != 0) return false;
            }
        }
        return true;
    }
}