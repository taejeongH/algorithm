import java.util.*;

class Solution {
    List<int[]>[] g;
    int k;

    public int solution(int n, int infection, int[][] edges, int k) {
        this.k = k;

        g = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            g[i] = new ArrayList<>();
        }

        for (int i = 0; i < n - 1; i++) {
            int s = edges[i][0];
            int e = edges[i][1];
            int type = edges[i][2];

            g[s].add(new int[]{e, type});
            g[e].add(new int[]{s, type});
        }

        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(infection);

        boolean[] v = new boolean[n + 1];
        v[infection] = true;

        return find(0, arr, v);
    }

    public int find(int depth, ArrayList<Integer> arr, boolean[] v) {
        if (depth == k) {
            return arr.size();
        }

        int ans = 0;

        for (int type = 1; type <= 3; type++) {
            ArrayList<Integer> nextArr = new ArrayList<>(arr);
            boolean[] nextV = v.clone();

            for (int idx = 0; idx < nextArr.size(); idx++) {
                int node = nextArr.get(idx);

                for (int[] nxt : g[node]) {
                    int nextNode = nxt[0];
                    int edgeType = nxt[1];

                    if (!nextV[nextNode] && edgeType == type) {
                        nextV[nextNode] = true;
                        nextArr.add(nextNode);
                    }
                }
            }

            ans = Math.max(
                ans,
                find(depth + 1, nextArr, nextV)
            );
        }

        return ans;
    }
}