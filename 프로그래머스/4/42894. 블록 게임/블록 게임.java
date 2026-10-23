import java.util.*;

class Solution {
    int N;
    int[][] board;
    List<int[]>[] blocks;

    public int solution(int[][] board) {
        this.board = board;
        N = board.length;

        blocks = new List[N * N + 1];
        for (int i = 0; i < blocks.length; i++) {
            blocks[i] = new ArrayList<>();
        }

        // 블록별 좌표 수집
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (board[i][j] == 0) continue;
                blocks[board[i][j]].add(new int[]{i, j});
            }
        }

        int answer = 0;
        boolean changed = true;

        // 더 이상 제거할 수 없을 때까지 반복
        while (changed) {
            changed = false;

            for (int i = 1; i < blocks.length; i++) {
                if (blocks[i].isEmpty()) continue;
                if (!canRemove(i)) continue;

                // 블록 제거
                for (int[] pos : blocks[i]) {
                    board[pos[0]][pos[1]] = 0;
                }

                blocks[i].clear();
                answer++;
                changed = true;
            }
        }

        return answer;
    }

    boolean canRemove(int id) {
        int minY = N, maxY = -1;
        int minX = N, maxX = -1;

        // 블록을 감싸는 직사각형 범위
        for (int[] pos : blocks[id]) {
            minY = Math.min(minY, pos[0]);
            maxY = Math.max(maxY, pos[0]);
            minX = Math.min(minX, pos[1]);
            maxX = Math.max(maxX, pos[1]);
        }

        int emptyCount = 0;

        // 직사각형 내부 탐색
        for (int y = minY; y <= maxY; y++) {
            for (int x = minX; x <= maxX; x++) {

                if (board[y][x] == id) continue;

                // 다른 블록이 차지하고 있다면 제거 불가능
                if (board[y][x] != 0) return false;

                emptyCount++;

                // 검은 블록을 위에서 떨어뜨릴 수 있는지 확인
                for (int k = 0; k < y; k++) {
                    if (board[k][x] != 0) {
                        return false;
                    }
                }
            }
        }

        return emptyCount == 2;
    }
}