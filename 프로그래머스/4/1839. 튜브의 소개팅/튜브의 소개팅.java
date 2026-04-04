import java.util.*;

class Solution {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    final long INF = Long.MAX_VALUE / 4;

    public int[] solution(int m, int n, int s, int[][] time_map) {
        if (time_map[0][0] == -1 || time_map[m - 1][n - 1] == -1) {
            return new int[]{-1, -1};
        }

        long[][] curr = new long[m][n];
        for (int i = 0; i < m; i++) {
            Arrays.fill(curr[i], INF);
        }

        // 시작 칸의 대화시간을 포함하지 않는다고 가정
        curr[0][0] = 0;

        if (m == 1 && n == 1) {
            return new int[]{0, 0};
        }

        int maxDist = m * n - 1;

        for (int dist = 0; dist < maxDist; dist++) {
            long[][] next = new long[m][n];
            for (int i = 0; i < m; i++) {
                Arrays.fill(next[i], INF);
            }

            boolean updated = false;

            for (int y = 0; y < m; y++) {
                for (int x = 0; x < n; x++) {
                    if (curr[y][x] == INF) continue;

                    for (int dir = 0; dir < 4; dir++) {
                        int ny = y + dy[dir];
                        int nx = x + dx[dir];

                        if (ny < 0 || ny >= m || nx < 0 || nx >= n) continue;
                        if (time_map[ny][nx] == -1) continue;

                        long nxtTalk = curr[y][x] + time_map[ny][nx];
                        if (nxtTalk > s) continue;

                        if (next[ny][nx] > nxtTalk) {
                            next[ny][nx] = nxtTalk;
                            updated = true;
                        }
                    }
                }
            }

            if (next[m - 1][n - 1] != INF) {
                return new int[]{dist + 1, (int) next[m - 1][n - 1]};
            }

            if (!updated) break;
            curr = next;
        }

        return new int[]{-1, -1};
    }
}