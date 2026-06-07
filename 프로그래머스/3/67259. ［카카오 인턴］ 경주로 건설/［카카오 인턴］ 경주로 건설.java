import java.util.*;
class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static final int INF = 1_000_000_000;
    public int solution(int[][] board) {
        int N = board.length;
        int[][][] distance = new int[N][N][4];
        for (int i=0; i<N; i++)
            for (int j=0; j<N; j++)
                Arrays.fill(distance[i][j], INF);
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[3] - o2[3]);
        pq.add(new int[] {0, 0, 1, 0});
        pq.add(new int[] {0, 0, 3, 0});
        distance[0][0][1] = 0;
        distance[0][0][3] = 0;
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int y = now[0];
            int x = now[1];
            int dir = now[2];
            int dis = now[3];
            
            if (dis > distance[y][x][dir]) continue;
            if (y == N-1 && x == N-1) return dis;
            
            for (int i=0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                int cost = 100;
                if (dir != i) cost = 600;

                if (ny < 0 || ny >= N || nx < 0 || nx >= N || board[ny][nx] == 1) continue;
                
                if (distance[ny][nx][i] < dis+cost) continue;
                distance[ny][nx][i] = dis+cost;
                pq.add(new int[] {ny, nx, i, dis+cost});
            }
        }
        
        return -1;
    }
}