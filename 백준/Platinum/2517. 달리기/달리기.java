//package BOJ.전구;

import java.io.*;
import java.util.*;

public class Main{
    static int N;
    static int[] tree;

    static int query(int left, int right, int start, int end, int node) {
        if (end < left || start > right) return 0;

        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return query(left, right, start, mid, node*2) + query(left, right, mid+1, end, node*2+1);
    }

    static void update(int pos, int start, int end, int node) {
        if (end < pos || start > pos) return;

        if (start == pos && pos == end) {
            tree[node] = 1;
            return;
        }

        int mid = (start + end) / 2;
        update(pos, start, mid, node*2);
        update(pos, mid+1, end, node*2+1);
        tree[node] = tree[node*2] + tree[node*2+1];
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[][] arr = new int[N][2];
        for (int i=0; i<N; i++) {
            arr[i][0] = Integer.parseInt(br.readLine());
            arr[i][1] = i;
        }

        Arrays.sort(arr, (o1, o2) -> o2[0] - o1[0]);

        int[] answer = new int[N];
        tree = new int[N*4];
        for (int i=0; i<N; i++) {
            answer[arr[i][1]] = query(0, arr[i][1], 0, N-1, 1);
            update(arr[i][1], 0, N-1, 1);
        }

        StringBuilder sb = new StringBuilder();
        for (int i=0; i<N; i++) {
            sb.append(answer[i]+1).append("\n");
        }

        System.out.println(sb);
    }

}