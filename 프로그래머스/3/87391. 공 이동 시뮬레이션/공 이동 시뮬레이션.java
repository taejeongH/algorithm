import java.util.*;
class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    int[] op = {1, 0, 3, 2};
    int N, M;
    int[][] queries;
    long answer;
    public long solution(int n, int m, int x, int y, int[][] queries) {
        answer = 0;
        N = n;
        M = m;
        this.queries = queries;
        
        int sy = x;
        int ey = x;
        int sx = y;
        int ex = y;
        for (int i=queries.length-1; i>=0; i--) {
            int dir = queries[i][0];
            int opdir = op[dir];
            int amount = queries[i][1];

            if (dir == 0) {
                //좌 sx를 확인
                int nx = sx + dx[dir];
                if (nx<0) {
                    //벽에 부딪힌다면?
                    int nex = Math.min(M-1, ex + (dx[opdir] * amount));
                    ex = nex;
                } else {
                    int nsx = sx + (dx[opdir] * amount);
                    if(nsx >= M) return 0;
                    int nex = Math.min(M-1, ex + (dx[opdir] * amount));
                    sx = nsx;
                    ex = nex;
                }
            } else if (dir == 1) {
                //우 ex를 확인
                int nx = ex + dx[dir];
                if (nx>=M) {
                    //벽에 부딪힌다면?
                    int nsx = Math.max(0, sx + (dx[opdir] * amount));
                    sx = nsx;
                } else {
                    int nsx = Math.max(0, sx + (dx[opdir] * amount));
                    int nex = ex + (dx[opdir] * amount);
                    if (nex<0) return 0;
                    sx = nsx;
                    ex = nex;
                }
            } else if (dir == 2) {
                //상 sy를 확인
                int ny = sy + dy[dir];
                if (ny<0) {
                    //벽에 부딪힌다면?
                    int ney = Math.min(N-1, ey + (dy[opdir] * amount));
                    ey = ney;
                } else {
                    int nsy = Math.min(N-1, sy + (dy[opdir] * amount));
                    int ney = Math.min(N-1, ey + (dy[opdir] * amount));
                    sy = nsy;
                    ey = ney;
                }
            } else {
                int ny = ey + dy[dir];
                if (ny>=N) {
                    int nsy = Math.max(0, sy + (dy[opdir] * amount));
                    sy = nsy;
                } else {
                    int nsy = Math.max(0, sy + (dy[opdir] * amount));
                    int ney = Math.max(0, ey + (dy[opdir] * amount));
                    ey = ney;
                    sy = nsy;
                }
            }
        }
        System.out.println(sy + " " + ey + " " + sx + " " + ex);
        // return 0;
        return 1L * (ey-sy+1) * (ex-sx+1);
    }

}