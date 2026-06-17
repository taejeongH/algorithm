import java.util.*;

class Solution {
    public boolean solution(int n, int[][] path, int[][] order) {
        List<Integer>[] g = new List[n];
        List<Integer>[] directG = new List[n];

        for (int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            directG[i] = new ArrayList<>();
        }

        for (int i = 0; i < path.length; i++) {
            int a = path[i][0];
            int b = path[i][1];

            g[a].add(b);
            g[b].add(a);
        }

        // 1. 0번 방을 루트로 해서 무방향 트리를 방향 그래프로 바꾸기
        ArrayDeque<Integer> que = new ArrayDeque<>();
        boolean[] visited = new boolean[n];

        que.add(0);
        visited[0] = true;

        while (!que.isEmpty()) {
            int cur = que.poll();

            for (int next : g[cur]) {
                if (visited[next]) continue;

                visited[next] = true;
                directG[cur].add(next);
                que.add(next);
            }
        }

        // 2. order 조건도 방향 간선으로 추가
        for (int i = 0; i < order.length; i++) {
            int before = order[i][0];
            int after = order[i][1];

            directG[before].add(after);
        }

        // 3. 위상정렬로 사이클 체크
        int[] indegree = new int[n];

        for (int i = 0; i < n; i++) {
            for (int next : directG[i]) {
                indegree[next]++;
            }
        }

        ArrayDeque<Integer> q = new ArrayDeque<>();

        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        int count = 0;

        while (!q.isEmpty()) {
            int cur = q.poll();
            count++;

            for (int next : directG[cur]) {
                indegree[next]--;

                if (indegree[next] == 0) {
                    q.add(next);
                }
            }
        }

        return count == n;
    }
}