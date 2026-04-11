//package BOJ.요세푸스문제2;

import java.io.*;
import java.util.*;

public class Main {
    static int[] tree;

    static void init(int start, int end, int node) {
        if (start == end) {
            tree[node] = 1;
            return;
        }

        int mid = (start + end) / 2;
        init(start, mid, node * 2);
        init(mid + 1, end, node * 2 + 1);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    static void update(int idx, int start, int end, int node) {
        if (idx < start || idx > end) return;

        if (start == end) {
            tree[node] = 0;
            return;
        }

        int mid = (start + end) / 2;
        update(idx, start, mid, node * 2);
        update(idx, mid + 1, end, node * 2 + 1);
        tree[node] = tree[node * 2] + tree[node * 2 + 1];
    }

    // 살아있는 사람 중 kth(1-based) 번째 사람 번호 찾기
    static int findKth(int kth, int start, int end, int node) {
        if (start == end) return start;

        int mid = (start + end) / 2;
        if (tree[node * 2] >= kth) {
            return findKth(kth, start, mid, node * 2);
        } else {
            return findKth(kth - tree[node * 2], mid + 1, end, node * 2 + 1);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        tree = new int[4 * N];
        init(1, N, 1);

        StringBuilder sb = new StringBuilder();
        sb.append("<");

        int idx = 0; // 현재 남은 사람들 기준 0-based 인덱스

        for (int remain = N; remain >= 1; remain--) {
            idx = (idx + K - 1) % remain;   // 이번에 제거할 순번
            int num = findKth(idx + 1, 1, N, 1); // 실제 번호 찾기

            update(num, 1, N, 1);

            sb.append(num);
            if (remain != 1) sb.append(", ");
        }

        sb.append(">");
        System.out.println(sb);
    }
}