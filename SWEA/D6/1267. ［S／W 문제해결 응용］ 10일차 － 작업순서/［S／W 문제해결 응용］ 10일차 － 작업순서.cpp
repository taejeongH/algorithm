#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int T = 10;
int N, M;
vector<int> g[1001];
int indegree[1001];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> M;

        for (int i = 1; i <= N; i++) {
            indegree[i] = 0;
            g[i].clear();
        }

        int s, e;
        for (int i = 0; i < M; i++) {
            cin >> s >> e;
            g[s].push_back(e);
            indegree[e]++;
        }

        queue<int> que;

        for (int i = 1; i <= N; i++) {
            if (indegree[i]==0) {
                que.push(i);
            }
        }

        cout << "#" << test_case << ' ';
        while (!que.empty()) {
            int now = que.front();
            que.pop();

            cout << now << ' ';
            for (int nxt : g[now]) {
                if (--indegree[nxt] == 0) {
                    que.push(nxt);
                }
            }
        }

        cout << "\n";
    }

    return 0;
}