#include <iostream>
#include <vector>
#include <queue>

using namespace std;

const int INF = 1'000'000'000;

int T, N, M, X;
vector<pair<int, int>> g[1001];
vector<pair<int, int>> reverseG[1001];
int dist[1001];
int reverseDist[1001];



int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> M >> X;

        for (int i = 1; i <= N; i++) {
            g[i].clear();
            reverseG[i].clear();
            dist[i] = INF;
            reverseDist[i] = INF;
        }

        int s, e, c;
        for (int i = 0; i < M; i++) {
            cin >> s >> e >> c;
            g[s].push_back({ e, c });
            reverseG[e].push_back({ s, c });
        }

        priority_queue<
            pair<int, int>,
            vector<pair<int, int>>,
            greater<pair<int, int>>
        > pq;

        pq.push({ 0, X });
        dist[X] = 0;

        while (!pq.empty()) {
            int dis = pq.top().first;
            int node = pq.top().second;
            pq.pop();

            if (dis > dist[node]) continue;

            for (int i = 0; i < g[node].size(); i++) {
                int nn = g[node][i].first;
                int nd = dis + g[node][i].second;
                if (dist[nn] > nd) {
                    dist[nn] = nd;
                    pq.push({ nd, nn });
                }
            }
        }

        pq.push({ 0, X });
        reverseDist[X] = 0;

        while (!pq.empty()) {
            int dis = pq.top().first;
            int node = pq.top().second;
            pq.pop();

            if (dis > reverseDist[node]) continue;

            for (int i = 0; i < reverseG[node].size(); i++) {
                int nn = reverseG[node][i].first;
                int nd = dis + reverseG[node][i].second;
                if (reverseDist[nn] > nd) {
                    reverseDist[nn] = nd;
                    pq.push({ nd, nn });
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (dist[i] + reverseDist[i] > answer) answer = dist[i] + reverseDist[i];
        }

        cout << '#' << test_case << ' ' << answer << '\n';
    }

    return 0;
}