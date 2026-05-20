import java.util.*;


class Solution {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        int N = land.length;
        
        boolean[][] v = new boolean[N][N];
        int[][] map = new int[N][N];
        int idx = 1;
        
        ArrayDeque<int[]> que = new ArrayDeque<>();
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (map[i][j] != 0) continue;
                
                map[i][j] = idx;
                que.add(new int[] {i, j});
                while(!que.isEmpty()) {
                    int[] now = que.poll();
                    int y = now[0];
                    int x = now[1];
                    
                    
                    for (int k=0; k<4; k++) {
                        int ny = y+dy[k];
                        int nx = x+dx[k];
                        
                        if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx] != 0) continue;
                        if (Math.abs(land[ny][nx] - land[y][x]) > height) continue;
                        
                        map[ny][nx] = idx;
                        que.add(new int[] {ny, nx});
                    }
                }
                idx++;
            }
        }
        
        List<int[]>[] g = new List[idx]; for (int i=1; i<idx; i++) g[i] = new ArrayList<>();
        
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                int curId = map[i][j];
                for (int k=0; k<4; k++){
                    int ny = i+dy[k];
                    int nx = j+dx[k];
                    
                    if(ny<0 || ny>=N || nx<0 || nx>=N || map[ny][nx] == curId) continue;
                    g[curId].add(new int[]{map[ny][nx], Math.abs(land[ny][nx] - land[i][j])}); 
                }
            }
        }
        
        // for (int i=1; i<idx; i++) {
        //     for (int j=0; j<g[i].size(); j++) {
        //         System.out.print(Arrays.toString(g[i].get(j)));    
        //     }
        //     System.out.println();
        // }
        int[] distance = new int[idx];
        Arrays.fill(distance, 10000000);
        
        boolean[] visited = new boolean[idx];
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1]-o2[1]);
        pq.add(new int[] {1, 0});
        distance[1] = 0;
        
        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            int node = now[0];
            int dis = now[1];
            if(visited[node]) continue;
            visited[node] = true;
            answer += dis;
            
            for (int[] nxt : g[node]) {
                if (!visited[nxt[0]] && distance[nxt[0]] > nxt[1]) {
                    distance[nxt[0]] = nxt[1];
                    pq.add(new int[] {nxt[0], nxt[1]});
                }
            }
            
        }
        return answer;
    }
}