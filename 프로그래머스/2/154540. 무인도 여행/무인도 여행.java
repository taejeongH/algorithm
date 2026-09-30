import java.util.*;
class Solution {
    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};
    
    public int[] solution(String[] maps) {
        int N = maps.length;
        int M = maps[0].length();
        
        ArrayList<Integer> ans = new ArrayList<>();
        ArrayDeque<int[]> que = new ArrayDeque<>();
        boolean[][] v = new boolean[N][M];
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (v[i][j] || maps[i].charAt(j) == 'X') continue;
                
                que.add(new int[]{i, j});
                v[i][j] = true;
                int sum = maps[i].charAt(j) - '0';
                while(!que.isEmpty()) {
                    int[] now = que.poll();
                    int y = now[0];
                    int x = now[1];
                    
                    for (int k=0; k<4; k++) {
                        int ny = y+dy[k];
                        int nx = x+dx[k];
                        
                        if(ny < 0 || ny >= N || nx<0 || nx>=M) continue;
                        if(v[ny][nx] || maps[ny].charAt(nx) == 'X') continue;
                        v[ny][nx] = true;
                        sum += maps[ny].charAt(nx) - '0';
                        que.add(new int[] {ny, nx});
                    }
                }                
                ans.add(sum);
            }
        }
        if(ans.isEmpty()) return new int[] {-1};
        
        Collections.sort(ans);
        int[] answer = new int[ans.size()];
        for (int i=0; i<ans.size(); i++) {
            answer[i] = ans.get(i);
        } 
        return answer;
    }
}