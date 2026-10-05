import java.util.*;
class Solution {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    final int N = 4;
    final int INF = 1_000_000_000;
    int cardCount;
    int[][] pos;
    boolean[] v;
    int[][] board;
    public int solution(int[][] board, int r, int c) {
        this.board = board;
        cardCount = 0;
        pos = new int[7][4];
        for (int i=0; i<7; i++) Arrays.fill(pos[i], -1);
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                cardCount = Math.max(board[i][j], cardCount);
                if (board[i][j] != 0) {
                    int card = board[i][j];
                    if (pos[card][0] == -1) {
                        pos[card][0] = i;
                        pos[card][1] = j;
                    } else {
                        pos[card][2] = i;
                        pos[card][3] = j;
                    }
                }
            }
        }
        
        v = new boolean[cardCount+1];
        return dfs(r, c, 0);
    }
    
    int dfs(int y, int x, int card) {
        if (card == cardCount) return 0;
        
        int res = INF;
        for (int i=1; i<=cardCount; i++) {
            if (v[i]) continue;
            
            
            int cost1 = bfs(y, x, pos[i][0], pos[i][1]) + bfs(pos[i][0], pos[i][1], pos[i][2], pos[i][3]);
            int cost2 = bfs(y, x, pos[i][2], pos[i][3]) + bfs(pos[i][2], pos[i][3], pos[i][0], pos[i][1]);

            v[i] = true;
            int move1 = cost1 + dfs(pos[i][2], pos[i][3], card+1) + 2;
            int move2 = cost2 + dfs(pos[i][0], pos[i][1], card+1) + 2;
            
            res = Math.min(res, Math.min(move1, move2));
            v[i] = false;
        }
        return res;
    }
    
    int bfs(int sy, int sx, int ey, int ex) {
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {sy, sx, 0});
        boolean[][] visited = new boolean[N][N];
        visited[sy][sx] = true;
        while(!que.isEmpty()) {
            int[] now = que.poll();
            int y = now[0];
            int x = now[1];
            int dis = now[2];
            
            if(y == ey && x == ex) return dis;
            
            for (int i=0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
                if(visited[ny][nx]) continue;
                visited[ny][nx] = true;
                que.add(new int[] {ny, nx, dis+1});
            }
            
            for (int i=0; i<4; i++) {
                int ny = y;
                int nx = x;

                while (true) {
                    int ty = ny + dy[i];
                    int tx = nx + dx[i];

                    if (ty < 0 || ty >= N || tx < 0 || tx >= N) break;

                    ny = ty;
                    nx = tx;

                    if (board[ny][nx] != 0 && !v[board[ny][nx]]) break;
                }
                if (visited[ny][nx]) continue;
                visited[ny][nx] = true;
                que.add(new int[] {ny, nx, dis+1});
            }
        }
        return 0;
    }
    
}