//package BOJ.전구;

import java.io.*;
import java.util.*;

public class Main{
    static int[][] dp;
    static ArrayList<Integer> arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] map = new int[N+1];
        st = new StringTokenizer(br.readLine());
        for (int i=0; i<N; i++) {
            map[i] = Integer.parseInt(st.nextToken());
        }
        map[N] = 21;

        arr = new ArrayList<>();
        int cur = map[0];
        for (int i=1; i<=N; i++) {
            if (map[i]!=cur) {
                arr.add(cur);
                cur = map[i];
            }
        }
        dp = new int[arr.size()][arr.size()];
        for (int i=0; i<arr.size(); i++) Arrays.fill(dp[i], -1);

        System.out.println(find(0, arr.size()-1));
    }

    static int find(int l, int r) {
        if (l >= r) return 0;
        if (dp[l][r] != -1) return dp[l][r];

        int res = Integer.MAX_VALUE;

        for (int i = l; i < r; i++) {
            int cost = arr.get(l) == arr.get(i + 1) ? 0 : 1;

            res = Math.min(res, find(l, i) + find(i + 1, r) + cost);
        }

        return dp[l][r] = res;
    }

}