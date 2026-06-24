#include <iostream>
#include <vector>

using namespace std;

int color[100001];
vector<int> graph[100001];
long long dp[100001][1 << 6];

int N, M, K;

long long dfs(int cur, int mask) {
    long long& ret = dp[cur][mask];
    if (ret != -1) return ret;

    ret = 0;

    for (int nxt : graph[cur]) {
        int bit = 1 << color[nxt];

        if (mask & bit) continue;

        int nxtMask = mask | bit;

        ret += 1 + dfs(nxt, nxtMask);
    }

    return ret;
}

int main() {
    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> M >> K;

        for (int i = 1; i <= N; i++) {
            graph[i].clear();
        }

        for (int i = 1; i <= N; i++) {
            cin >> color[i];
        }

        for (int i = 0; i < M; i++) {
            int s, e;
            cin >> s >> e;

            graph[s].push_back(e);
            graph[e].push_back(s);
        }

        int maxMask = 1 << K + 1;

        for (int i = 1; i <= N; i++) {
            for (int mask = 0; mask < maxMask; mask++) {
                dp[i][mask] = -1;
            }
        }

        long long answer = 0;

        for (int i = 1; i <= N; i++) {
            int mask = 1 << color[i];
            answer += dfs(i, mask);
        }

        cout << answer << '\n';
    }

    return 0;
}