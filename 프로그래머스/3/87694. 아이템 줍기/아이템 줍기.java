import java.util.*;

class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int answer = 0;
        int N = 0;
        int M = 0;
        for (int i=0; i<rectangle.length; i++) {
            N = Math.max(N, rectangle[i][3]);
            M = Math.max(M, rectangle[i][2]);
        }
        
        int[][] map = new int[101][101];
        for (int i=0; i<rectangle.length; i++) {
            int sx = rectangle[i][0]*2;
            int sy = rectangle[i][1]*2;
            int ex = rectangle[i][2]*2;
            int ey = rectangle[i][3]*2;
            
            for (int y = sy; y <= ey; y++) {
                if(map[y][sx]!=2) map[y][sx] = 1;
                if(map[y][ex]!=2) map[y][ex] = 1;
            }
            
            for (int x = sx; x <= ex; x++) {
                if(map[sy][x]!=2) map[sy][x] = 1;
                if(map[ey][x]!=2) map[ey][x] = 1;
            }
            
            for (int y=sy+1; y < ey; y++) {
                for (int x=sx+1; x<ex; x++) {
                    map[y][x] = 2;
                }
            }
        }
        
        //for (int i=0; i<=20; i++) System.out.println(Arrays.toString(map[i]));
        
        boolean[][] v = new boolean[101][101];
        ArrayDeque<int[]> que = new ArrayDeque<>();
        que.add(new int[] {characterY*2, characterX*2, 0});
        v[characterY*2][characterX*2] = true;
        while (!que.isEmpty()) {
            int[] now = que.poll();
            
            int y = now[0];
            int x = now[1];
            int dis = now[2];
            if (y == itemY*2 && x == itemX*2) return dis/2;
            for (int i=0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];
                
                if (ny<0 || nx<0 || ny>100 || nx>100 || v[ny][nx] || map[ny][nx]!=1) continue;
                
                v[ny][nx] =true;
                que.add(new int[] {ny, nx, dis+1});
            }
        }
        
        
        return answer;
    }
}