import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int[] parent, cnt;

    static int union(int x, int y) {
        int xRoot = find(x);
        int yRoot = find(y);

        if(xRoot == yRoot) return cnt[xRoot];

        parent[xRoot] = yRoot;
        cnt[yRoot] += cnt[xRoot];
        return cnt[yRoot];
    }

    static int find(int x) {
        if (parent[x] == x) return x;

        int xRoot = find(parent[x]);
        return parent[x] = xRoot;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][N];
        int idx = 1;
        ArrayDeque<int[]> que = new ArrayDeque<>();
        for (int i=0; i<K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken())-1;
            int y = Integer.parseInt(st.nextToken())-1;
            map[y][x] = idx++;
            que.add(new int[] {y, x, 0});
        }

        parent = new int[idx];
        cnt = new int[idx];
        for (int i=1; i<idx; i++) {
            parent[i] = i;
            cnt[i] = 1;
        }

        int[][] time = new int[N][N];
        int answer = 0;
        nxt: while(!que.isEmpty()) {
            int[] now = que.poll();
            int y = now[0];
            int x = now[1];
            int dis = now[2];

            for (int i=0; i<4; i++) {
                int ny = y+dy[i];
                int nx = x+dx[i];

                if(ny<0 || ny>=N || nx<0 || nx>=N) continue;
                if (map[ny][nx] != 0 && map[ny][nx] != map[y][x]) {
                    if (time[ny][nx] > dis) continue;

                    if (union(map[ny][nx], map[y][x]) == K) {
                        answer = dis;
                        break nxt;
                    }
                }

                if (map[ny][nx] == 0) {
                    map[ny][nx] = map[y][x];
                    time[ny][nx] = dis + 1;
                    que.add(new int[] {ny, nx, dis + 1});
                }
            }
        }
        System.out.println(answer);
    }
}