import java.util.*;

class Solution {
    int[] dx = {0, 1, 0, -1};
    int[] dy = {-1, 0, 1, 0};
    int N;
    final int INF = 1_000_000_000;

    public int solution(int[][] game_board, int[][] table) {
        int answer = 0;
        N = game_board.length;

        List<int[]>[] pieces = new List[N*N];
        List<int[]>[] space = new List[N*N];

        for (int i=0; i<N*N; i++) {
            pieces[i] = new ArrayList<>();
            space[i] = new ArrayList<>();
        }

        int sIdx = bfs(table, pieces, 1);
        int pIdx = bfs(game_board, space, 0);

        normalize(space, pIdx);
        normalize(pieces, sIdx);

        boolean[] v = new boolean[pIdx];

        for (int i=0; i<sIdx; i++) {
            for (int j=0; j<pIdx; j++) {
                if (v[j] || pieces[i].size() != space[j].size()) continue;

                boolean matched = false;

                for (int k=0; k<4; k++) {
                    if (isSame(pieces[i], space[j])) {
                        matched = true;
                        break;
                    }

                    rotate(pieces[i]);
                    normalize(pieces[i]);
                }

                if (matched) {
                    v[j] = true;
                    answer += pieces[i].size();
                    break;
                }
            }
        }

        return answer;
    }

    boolean isSame(List<int[]> a, List<int[]> b) {
        if (a.size() != b.size()) return false;

        for (int i=0; i<a.size(); i++) {
            if (a.get(i)[0] != b.get(i)[0] ||
                a.get(i)[1] != b.get(i)[1]) {
                return false;
            }
        }

        return true;
    }

    void rotate(List<int[]> arr) {
        for (int[] pos : arr) {
            int y = pos[0];
            int x = pos[1];

            pos[0] = x;
            pos[1] = -y;
        }
    }

    void normalize(List<int[]>[] arr, int idx) {
        for (int i=0; i<idx; i++) {
            normalize(arr[i]);
        }
    }

    void normalize(List<int[]> arr) {
        int yMin = INF;
        int xMin = INF;

        for (int j=0; j<arr.size(); j++) {
            yMin = Math.min(yMin, arr.get(j)[0]);
            xMin = Math.min(xMin, arr.get(j)[1]);
        }

        for (int j=0; j<arr.size(); j++) {
            arr.get(j)[0] -= yMin;
            arr.get(j)[1] -= xMin;
        }

        arr.sort((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });
    }

    int bfs(int[][] map, List<int[]>[] result, int type) {
        boolean[][] v = new boolean[N][N];
        ArrayDeque<int[]> que = new ArrayDeque<>();

        int idx = 0;

        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != type || v[i][j]) continue;

                v[i][j] = true;
                que.add(new int[] {i, j});

                while (!que.isEmpty()) {
                    int[] now = que.poll();
                    int y = now[0];
                    int x = now[1];

                    result[idx].add(new int[] {y, x});

                    for (int k=0; k<4; k++) {
                        int ny = y+dy[k];
                        int nx = x+dx[k];

                        if (ny<0 || ny>=N || nx<0 || nx>=N ||
                            v[ny][nx] || map[ny][nx] != type) continue;

                        v[ny][nx] = true;
                        que.add(new int[] {ny, nx});
                    }
                }

                idx++;
            }
        }

        return idx;
    }
}