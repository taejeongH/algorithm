#include <iostream>
#include <vector>
#include <queue>

using namespace std;

int T, N;
int arr[100][100];
int matrix[100+1];
bool v[100][100];
bool check[101];
int map[100][2];
int dp[5000][5000];
const long long INF = 1'000'000'000'000'000;

int dx[] = { 0, 0, -1, 1 };
int dy[] = { -1, 1, 0, 0 };

long long MIN(long long a, long long b) { return a > b ? b : a; }

int dfs(int l, int r) {
    if (l == r) return 0;
    if (r - l == 1) return map[l][0] * map[l][1] * map[r][1];
    if (dp[l][r] != -1) return dp[l][r];

    long long res = INF;
    for (int mid = l; mid < r; mid++) {
        res = MIN(res, dfs(l, mid) + dfs(mid+1, r) + map[l][0] * map[mid][1] * map[r][1]);
    }
    return dp[l][r] = res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cin >> arr[i][j];
                v[i][j] = false;
            }
        }
        
        for (int i = 1; i <= N; i++) {
            matrix[i] = 0;
            check[i] = false;
        }

        queue<vector<int>> que;
        
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (arr[i][j] == 0 || v[i][j]) continue;
                v[i][j] = true;
                que.push({ i, j, 1 });

                int row = 1;
                int col = 1;
                while (!que.empty()) {
                    vector<int> now = que.front();
                    que.pop();

                    int y = now[0];
                    int x = now[1];
                    int dis = now[2];

                    if (y == i) row = dis;
                    if (x == j) col = dis;

                    for (int k = 0; k < 4; k++) {
                        int ny = y + dy[k];
                        int nx = x + dx[k];

                        if (ny < 0 || ny >= N || nx < 0 || nx >= N || v[ny][nx] || arr[ny][nx] == 0) continue;
                        v[ny][nx] = true;
                        que.push({ ny, nx, dis + 1 });
                    }
                }

                matrix[col] = row;
                check[row] = true;
            }
        }
        int start = 0;
        for (int i = 1; i <= N; i++) {
            if (!check[i] && matrix[i] != 0) {
                start = i;
                break;
            }
        }
        int idx = 0;
        while (matrix[start] != 0) {
            map[idx][0] = start;
            map[idx++][1] = matrix[start];
            start = matrix[start];
        }

        for (int i = 0; i < idx; i++)
            for (int j = 0; j < idx; j++)
                dp[i][j] = -1;

        cout << "#" << test_case << " "  << dfs(0, idx - 1) << "\n";
    } 

    return 0;
}