#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int MAX_N = 100'000;
const int LOG = 18;

int T;
int N;
vector<int> g[MAX_N+1];
int parent[MAX_N + 1][LOG];
int depth[MAX_N + 1];

int lca(int a, int b) {
    if (depth[a] < depth[b]) swap(a, b);

    int diff = depth[a] - depth[b];

    for (int i = 0; i < LOG; i++) {
        if (diff & (1 << i)) a = parent[a][i];
    }

    if (a == b) return a;

    for (int i = LOG - 1; i >= 0; i--) {
        if (parent[a][i] != parent[b][i]) {
            a = parent[a][i];
            b = parent[b][i];
        }
    }

    return parent[a][0];
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N;

        for (int i = 1; i <= N; i++) {
            g[i].clear();
            depth[i] = 0;

            for (int j = 0; j < LOG; j++) {
                parent[i][j] = 0;
            }
        }
        
        int p;
        for (int i = 2; i <= N; i++) {
            cin >> p;

            g[p].push_back(i);
        }

        int path[100001];
        int idx = 0;
        parent[1][0] = 1;

        queue<int> que;
        que.push(1);
        while (!que.empty()) {
            int now = que.front();
            que.pop();

            path[idx++] = now;
            for (int nxt : g[now]) {
                depth[nxt] = depth[now] + 1;
                parent[nxt][0] = now;
                que.push(nxt);
            }
        }

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= N; i++) {
                parent[i][j] = parent[parent[i][j - 1]][j - 1];
            }
        }

        long long answer = 0;
        for (int i = 0; i < idx-1; i++) {
            int a = path[i];
            int b = path[i + 1];
            int p = lca(a, b);
            answer += depth[a] + depth[b] - 2 * depth[p];
        }

        cout << "#" << test_case << " " << answer << "\n";
    }

    return 0;
}