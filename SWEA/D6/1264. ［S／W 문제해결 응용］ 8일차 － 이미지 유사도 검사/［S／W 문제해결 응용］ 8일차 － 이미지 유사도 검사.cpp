#include <iostream>
#include <iomanip>

using namespace std;

int T;
int N;
char X[505];
char Y[505];
int dp[505][505];

int MAX(int x, int y) { return x > y ? x : y; }

int dfs(int x, int y) {
    if (x >= N || y >= N) return 0;
    if (dp[x][y] != -1) return dp[x][y];

    int res = MAX(dfs(x+1, y), dfs(x, y+1));

    if (X[x] == Y[y]) {
        res = MAX(res, dfs(x + 1, y + 1) + 1);
    }
    else {
        int ny = y;
        while (ny < N && X[x] != Y[ny]) ny++;
        if (ny != N) res = MAX(res, dfs(x + 1, ny + 1) + 1);

        int nx = x;
        while (nx < N && Y[y] != X[nx]) nx++;
        if (nx != N) res = MAX(res, dfs(nx + 1, y + 1) + 1);
    }

    return dp[x][y]=res;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N;

        for (int i = 0; i < N; i++)
            for (int j=0; j<N; j++)
                dp[i][j] = -1;

        cin >> X;
        cin >> Y;

        double res = (double) dfs(0, 0) / N * 100;



        cout << '#' << test_case << ' ';
        cout << fixed << setprecision(2) << res << '\n';
    }

    return 0;
}