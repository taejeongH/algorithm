//package BOJ.배달;

import java.io.*;
import java.util.*;

public class Main{
	static int[] dx = {0, 0, -1, 1};
	static int[] dy = {-1, 1, 0, 0};
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		ArrayDeque<int[]> que = new ArrayDeque<>();
		boolean[][][][] v = new boolean[N][M][4][4];
		char[][] map = new char[N][M];
		int cnt = 0;
		for (int i=0; i<N; i++) {
			String input = br.readLine();
			for (int j=0; j<M; j++) {
				map[i][j] = input.charAt(j);
				if (map[i][j]=='S') {
					for (int k=0; k<4; k++) {
						que.add(new int[] {i, j, k, 0, 0});
					}
				} else if (map[i][j]=='C') {
					if (++cnt==1) map[i][j] = 'B';
				}
			}
		}
		
		int res = -1;
		while(!que.isEmpty()) {
			int[] now = que.poll();
			int y = now[0];
			int x = now[1];
			int dir = now[2];
			int dis = now[3];
			int key = now[4];
//			System.out.println(y+ " " + x + " " + dir + " " + dis + " " + key) ;
			
			if(key==3) {
				res= dis;
				break;
			}
			
			for (int i=0; i<4; i++) {
				if (i == dir) continue;
				int ny = y+dy[i];
				int nx = x+dx[i];
				
				if (ny<0 || ny>=N || nx<0 || nx>=M || map[ny][nx]=='#') continue;
				
				int newkey = key;
				if (map[ny][nx] == 'B') {
					newkey = newkey | (1<<1);
				}
				
				if (map[ny][nx] == 'C') {
					newkey = newkey | (1<<0);
				}
				if(v[ny][nx][i][newkey]) continue;
				v[ny][nx][i][newkey] = true;
				
				que.add(new int[] {ny, nx, i, dis+1, newkey});
			}
		}
		System.out.println(res);
	}
	
}