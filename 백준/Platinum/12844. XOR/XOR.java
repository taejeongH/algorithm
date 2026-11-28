import javax.swing.*;
import java.io.*;
import java.util.*;

public class Main{
    static int[] tree;
    static int[] lazy;

    static void init(int start, int end, int node, int[] arr) {
        if (start == end) {
            tree[node] = arr[start];
            return;
        }

        int mid = (start+end)/2;
        init(start, mid, node*2, arr);
        init(mid+1, end, node*2+1, arr);
        tree[node] = tree[node*2] ^ tree[node*2+1];
    }

    static void update(int left, int right, int start, int end, int node, int value) {
        propagate(start, end, node);
        if (left > end || right < start) return;

        if (left <= start && end <= right) {
            lazy[node] ^= value;
            propagate(start, end, node);
            return;
        }

        int mid = (start+end)/2;
        update(left, right, start, mid, node*2, value);
        update(left, right, mid+1, end, node*2+1, value);
        tree[node] = tree[node*2] ^ tree[node*2+1];
    }

    static int query(int left, int right, int start, int end, int node) {
        propagate(start, end, node);
        if (left > end || right < start) return 0;

        if(left <= start && end <= right) return tree[node];
        int mid = (start+end)/2;
        return query(left, right, start, mid, node*2) ^ query(left, right, mid+1, end, node*2+1);
    }

    static void propagate(int start, int end, int node) {
        if (lazy[node] == 0) return;

        if((end - start + 1) % 2 == 1) {
            tree[node] ^= lazy[node];
        }

        if (start != end) {
            lazy[node*2] ^= lazy[node];
            lazy[node*2+1] ^= lazy[node];
        }

        lazy[node] = 0;
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = null;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        tree = new int[N*4];
        lazy = new int[N*4];
        init(0, N-1, 1, arr);

        int M = Integer.parseInt(br.readLine());
        for (int q=0; q<M; q++) {
            st = new StringTokenizer(br.readLine());
            int order = Integer.parseInt(st.nextToken());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            if(order == 1) {
                int k = Integer.parseInt(st.nextToken());
                update(i, j, 0, N-1, 1, k);
            } else {
                sb.append(query(i, j, 0, N-1, 1)).append("\n");
            }
        }

        System.out.println(sb);
    }

}