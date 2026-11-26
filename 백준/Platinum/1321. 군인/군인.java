import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main{
    static int[] tree;

    static void update(int pos, int start, int end, int node, int value) {
        if(pos < start || pos > end) return;
        if (start == pos && pos == end) {
            tree[node] += value;
            return;
        }
        int mid = (start + end) / 2;
        update(pos, start, mid, node*2, value);
        update(pos, mid+1, end, node*2+1, value);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    static int find(int pos, int start, int end, int node, int value) {
        if (start == end) return start;

        int mid = (start + end) / 2;
        int left = tree[node*2];

        if (pos <= left+value) {
            return find(pos, start, mid, node*2, value);
        }

        return find(pos, mid+1, end, node*2+1, value+left);
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        tree = new int[N*4];

        st = new StringTokenizer(br.readLine());
        for (int i=1; i<=N; i++) {
            update(i, 1, N, 1, Integer.parseInt(st.nextToken()));
        }

        int M = Integer.parseInt(br.readLine());
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());

            int order = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            if (order == 1) {
                int amount = Integer.parseInt(st.nextToken());
                update(num, 1, N, 1, amount);
            } else {
                sb.append(find(num, 1, N, 1, 0)).append("\n");
            }
        }
        System.out.println(sb);
    }

}