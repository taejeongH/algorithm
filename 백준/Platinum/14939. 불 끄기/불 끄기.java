import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main{
    static final int SIZE = 10;
    static int[][] map;
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};
    static int answer;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        map = new int[SIZE][SIZE];
        for (int i=0; i<SIZE; i++) {
            String input = br.readLine();
            for (int j=0; j<SIZE; j++) {
                if (input.charAt(j) == 'O') {
                    map[i][j] = 1;
                }
            }
        }
        answer = Integer.MAX_VALUE;
        dfs(0, new int[SIZE]);

        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }

    static void dfs(int depth, int[] arr) {
        if (depth == SIZE) {
            int[][] tmp = new int[SIZE][SIZE];

            for (int i = 0; i < 10; i++) {
                tmp[i] = map[i].clone();
            }

            int count = 0;
            for (int i=0; i<SIZE; i++) {
                if (arr[i] == 1) {
                    count++;
                    change(tmp, 0, i);
                }
            }

            for (int i=1; i<SIZE; i++) {
                for (int j=0; j<SIZE; j++) {
                    if (tmp[i-1][j] == 1) {
                        change(tmp, i, j);
                        count++;
                    }
                }
            }
            if (isCorrect(tmp)) answer = Math.min(answer, count);
            return;
        }

        arr[depth] = 0;
        dfs(depth+1, arr);
        arr[depth] = 1;
        dfs(depth+1, arr);
    }

    static void change(int[][] arr, int y, int x) {
        arr[y][x] ^= 1;
        for (int i=0; i<4; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];
            if(ny<0 || ny>=SIZE || nx<0 || nx>=SIZE) continue;
            arr[ny][nx] ^= 1;
        }
    }

    static boolean isCorrect(int[][] arr) {
        for (int i=0; i<SIZE; i++) {
            for (int j=0; j<SIZE; j++) {
                if (arr[i][j] == 1) return false;
            }
        }
        return true;
    }

}