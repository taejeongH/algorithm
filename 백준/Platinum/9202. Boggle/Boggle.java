import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main{
    static class Node {
        Node[] words;
        boolean endOfWords;

        public Node() {
            words = new Node[26];
            endOfWords = false;
        }
    }

    static class Trie {
        Node root;

        public Trie() {
            this.root = new Node();
        }

        void add(Node cur, int idx, String s) {
            int i = s.charAt(idx) - 'A';
            if (cur.words[i] == null) {
                cur.words[i] = new Node();
            }

            if (idx == s.length()-1) {
                cur.words[i].endOfWords = true;
                return;
            }

            add(cur.words[i], idx+1, s);
        }
    }

    static char[][] map;
    static boolean[][] v;
    static int[] dx = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int[] dy = {-1, 0, 1, -1, 1, -1 ,0, 1};
    static int score;
    static int count;
    static String ans;
    static Set<String> found;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int w = Integer.parseInt(br.readLine());
        Trie t = new Trie();
        for (int i=0; i<w; i++) {
            t.add(t.root, 0, br.readLine());
        }

        br.readLine();
        int b = Integer.parseInt(br.readLine());

        for(int i=0; i<b; i++) {
            map = new char[4][4];
            score = 0;
            count = 0;
            ans = "";
            found = new HashSet<>();
            for (int j=0; j<4; j++) {
                String input = br.readLine();
                for (int k=0; k<4; k++) {
                    map[j][k] = input.charAt(k);
                }
            }

            for (int j=0; j<4; j++) {
                for (int k=0; k<4; k++) {
                    int idx = map[j][k] - 'A';
                    if(t.root.words[idx] == null) continue;
                    StringBuilder words = new StringBuilder();
                    words.append(map[j][k]);
                    v = new boolean[4][4];
                    v[j][k] = true;
                    dfs(j, k, t.root.words[idx], 1, words);
                }
            }
            sb.append(score).append(" ").append(ans).append(" ").append(count).append("\n");
            if (i < b - 1) br.readLine();
        }

        System.out.println(sb);
    }

    static void dfs(int y, int x, Node cur, int depth, StringBuilder sb) {
        if (cur.endOfWords && found.add(sb.toString())) {
            count++;

            if (sb.length() <= 2) {
                score += 0;
            } else if(sb.length() <= 4) {
                score += 1;
            } else if(sb.length() <= 5) {
                score += 2;
            } else if(sb.length() <= 6) {
                score += 3;
            } else if(sb.length() <= 7) {
                score += 5;
            } else if(sb.length() <= 8) {
                score += 11;
            }

            if (ans.length() < sb.length()) {
                ans = sb.toString();
            } else if (ans.length() == sb.length()) {
                if (ans.compareTo(sb.toString()) > 0) {
                    ans = sb.toString();
                }
            }
        }

        for (int i=0; i<dx.length; i++) {
            int ny = y+dy[i];
            int nx = x+dx[i];

            if (ny < 0 || ny >= 4 || nx < 0 || nx >= 4) continue;
            if (v[ny][nx]) continue;

            int idx = map[ny][nx] - 'A';
            if (cur.words[idx] == null) continue;

            v[ny][nx] = true;
            sb.append(map[ny][nx]);

            dfs(ny, nx, cur.words[idx], depth + 1, sb);

            v[ny][nx] = false;
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}