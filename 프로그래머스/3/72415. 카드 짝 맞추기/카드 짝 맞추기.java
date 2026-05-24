import java.util.*;

class Solution {
    static final int N = 4;
    static final int INF = 1_000_000_000;

    static int[][] board;
    static int correct;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    static class Node {
        int y, x, key, cost;

        Node(int y, int x, int key, int cost) {
            this.y = y;
            this.x = x;
            this.key = key;
            this.cost = cost;
        }
    }

    public int solution(int[][] board, int r, int c) {
        Solution.board = board;
        correct = 0;

        int maxCard = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                maxCard = Math.max(maxCard, board[i][j]);
            }
        }

        for (int i = 1; i <= maxCard; i++) {
            correct |= (1 << i);
        }

        return dijkstra(r, c);
    }

    int dijkstra(int sy, int sx) {
        int[][][] dist = new int[N][N][correct + 1];

        for (int y = 0; y < N; y++) {
            for (int x = 0; x < N; x++) {
                Arrays.fill(dist[y][x], INF);
            }
        }

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.cost - b.cost);

        dist[sy][sx][0] = 0;
        pq.add(new Node(sy, sx, 0, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > dist[now.y][now.x][now.key]) continue;
            if (now.key == correct) return now.cost;

            // 현재 위치에 제거되지 않은 카드가 있으면, 해당 짝 제거
            int card = board[now.y][now.x];
            if (card != 0 && (now.key & (1 << card)) == 0) {
                int[] pair = getPair(now.y, now.x, now.key);
                int ny = pair[0];
                int nx = pair[1];
                int moveCost = pair[2];

                int nextKey = now.key | (1 << card);
                int nextCost = now.cost + moveCost;

                if (dist[ny][nx][nextKey] > nextCost) {
                    dist[ny][nx][nextKey] = nextCost;
                    pq.add(new Node(ny, nx, nextKey, nextCost));
                }
            }

            // 한 칸 이동
            for (int i = 0; i < 4; i++) {
                int ny = now.y + dy[i];
                int nx = now.x + dx[i];

                if (!inRange(ny, nx)) continue;

                int nextCost = now.cost + 1;

                if (dist[ny][nx][now.key] > nextCost) {
                    dist[ny][nx][now.key] = nextCost;
                    pq.add(new Node(ny, nx, now.key, nextCost));
                }
            }

            // Ctrl 이동
            for (int i = 0; i < 4; i++) {
                int[] next = ctrlMove(now.y, now.x, i, now.key);
                int ny = next[0];
                int nx = next[1];

                int nextCost = now.cost + 1;

                if (dist[ny][nx][now.key] > nextCost) {
                    dist[ny][nx][now.key] = nextCost;
                    pq.add(new Node(ny, nx, now.key, nextCost));
                }
            }
        }

        return INF;
    }

    int[] getPair(int y, int x, int key) {
        int target = board[y][x];

        ArrayDeque<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[N][N];

        q.add(new int[]{y, x, 0});
        visited[y][x] = true;

        while (!q.isEmpty()) {
            int[] now = q.poll();
            int cy = now[0];
            int cx = now[1];
            int dist = now[2];

            if (!(cy == y && cx == x)
                    && board[cy][cx] == target
                    && (key & (1 << board[cy][cx])) == 0) {
                // 현재 카드 Enter + 이동 거리 + 짝 카드 Enter
                return new int[]{cy, cx, dist + 2};
            }

            // 한 칸 이동
            for (int i = 0; i < 4; i++) {
                int ny = cy + dy[i];
                int nx = cx + dx[i];

                if (!inRange(ny, nx) || visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new int[]{ny, nx, dist + 1});
            }

            // Ctrl 이동
            for (int i = 0; i < 4; i++) {
                int[] next = ctrlMove(cy, cx, i, key);
                int ny = next[0];
                int nx = next[1];

                if (visited[ny][nx]) continue;

                visited[ny][nx] = true;
                q.add(new int[]{ny, nx, dist + 1});
            }
        }

        return new int[]{-1, -1, INF};
    }

    int[] ctrlMove(int y, int x, int dir, int key) {
        int ny = y;
        int nx = x;

        while (true) {
            int ty = ny + dy[dir];
            int tx = nx + dx[dir];

            if (!inRange(ty, tx)) break;

            ny = ty;
            nx = tx;

            if (board[ny][nx] != 0 && (key & (1 << board[ny][nx])) == 0) {
                break;
            }
        }

        return new int[]{ny, nx};
    }

    boolean inRange(int y, int x) {
        return y >= 0 && y < N && x >= 0 && x < N;
    }
}