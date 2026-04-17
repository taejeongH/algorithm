#include <iostream>
using namespace std;

int dp[201][201];
int MOD = 1000000000;

int dfs(int sum, int count, int N) {
	if (count < 0) return 0;
	if (sum == N  && count == 0) return 1;
	if (dp[sum][count] != -1) return dp[sum][count];

	long res = 0;
	for (int i = 0; i <= N; i++) {
		if (sum + i <= N) res += dfs(sum + i, count-1, N);
		else break;
	}

	return dp[sum][count]= res % MOD;
}

int main() {
	int N, K;

	cin >> N >> K;

	for (int i = 0; i <= N; i++) {
		for (int j = 0; j <= K; j++) {
			dp[i][j] = -1;
		}
	}

	cout << dfs(0, K, N);
}