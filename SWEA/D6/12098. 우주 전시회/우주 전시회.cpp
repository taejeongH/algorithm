#include <iostream>
#include <queue>

using namespace std;

const int MAX_N = 500'000;
const int LOG = 19;

int N, K;
int nxt[MAX_N+1];
int up[MAX_N+1][LOG];
long long sumUp[MAX_N + 1][LOG];
int indegree[MAX_N + 1];

int dist[MAX_N + 1];
int cycleId[MAX_N + 1];
int cycleLen[MAX_N + 1];

void findCircle() {
    vector<int> removedOrder;
    queue<int> que;

    for (int i = 1; i <= N; i++) {
        if (indegree[i] == 0) que.push(i);
    }

    while (!que.empty()) {
        int now = que.front();
        que.pop();

        removedOrder.push_back(now);

        if (--indegree[nxt[now]] == 0) {
            que.push(nxt[now]);
        }
    }

    //cycle 길이 계산
    int idx = 0;
    for (int i = 1; i <= N; i++) {
        if (indegree[i] <= 0) continue;
        dist[i] = 0;
        if (cycleId[i] != -1) continue;

        int pos = 0;
        int cur = i;
        while (cycleId[cur] == -1) {
            cycleId[cur] = idx;
            cur = nxt[cur];
            pos++;
        }
        cycleLen[idx++] = pos;
    }

    //cycle 까지 거리 계산
    for (int i = removedOrder.size() - 1; i >= 0; i--) {
        int cur = removedOrder[i];
        dist[cur] = dist[nxt[cur]] + 1;
        cycleId[cur] = cycleId[nxt[cur]];
    }
}

long long getSum(int start, int count) {
    long long result = 0;
    int cur = start;

    for (int j = 0; j < LOG; j++) {
        if (count & (1 << j)) {
            result += sumUp[cur][j];
            cur = up[cur][j];
        }
    }

    return result;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    for (int testCase = 1; testCase <= T; testCase++) {
        cin >> N >> K;

        for (int i = 1; i <= N; i++) {
            indegree[i] = 0;
            cycleId[i] = -1;
            sumUp[i][0] = i;
        }

        for (int i = 1; i <= N; i++) {
            cin >> nxt[i];
            up[i][0] = nxt[i];
            indegree[nxt[i]]++;
        }

        findCircle();

        for (int j = 1; j < LOG; j++) {
            for (int i = 1; i <= N; i++) {
                int mid = up[i][j - 1];

                up[i][j] = up[mid][j - 1];
                sumUp[i][j] = sumUp[i][j - 1] + sumUp[mid][j - 1];
            }
        }

        long long answer = 0;
        for (int i = 1; i <= N; i++) {
            int count = min(K + 1, dist[i] + cycleLen[cycleId[i]]);
            answer += getSum(i, count);
        }

        cout << '#' << testCase << ' ' << answer << '\n';
    }

    return 0;
}