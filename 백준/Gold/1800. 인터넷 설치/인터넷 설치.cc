#include <iostream>
#define INF 1000000000
using namespace std;

int N, P, K;
int g[1001][1001];

bool dijkstra(int limit) {
    int dist[1001];
    bool visited[1001];

    for (int i = 1; i <= N; i++) {
        dist[i] = INF;
        visited[i] = false;
    }

    dist[1] = 0;

    for (int i = 1; i <= N; i++) {
        int cur = -1;
        int minDist = INF;

        for (int j = 1; j <= N; j++) {
            if (!visited[j] && dist[j] < minDist) {
                minDist = dist[j];
                cur = j;
            }
        }

        if (cur == -1) break;
        visited[cur] = true;

        for (int next = 1; next <= N; next++) {
            if (g[cur][next] == INF) continue;

            int cost = (g[cur][next] > limit) ? 1 : 0;

            if (dist[next] > dist[cur] + cost) {
                dist[next] = dist[cur] + cost;
            }
        }
    }

    return dist[N] <= K;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(0);

    cin >> N >> P >> K;

    for (int i = 1; i <= N; i++) {
        for (int j = 1; j <= N; j++) {
            g[i][j] = INF;
        }
    }

    int maxCost = 0;

    for (int i = 0; i < P; i++) {
        int start, end, cost;
        cin >> start >> end >> cost;

        if (cost < g[start][end]) g[start][end] = cost;
        if (cost < g[end][start]) g[end][start] = cost;

        if (cost > maxCost) maxCost = cost;
    }

    if (!dijkstra(maxCost)) {
        cout << -1;
        return 0;
    }

    int l = 0;
    int r = maxCost;
    int ans = maxCost;

    while (l <= r) {
        int mid = (l + r) / 2;

        if (dijkstra(mid)) {
            ans = mid;
            r = mid - 1;
        }
        else {
            l = mid + 1;
        }
    }

    cout << ans;
    return 0;
}