#include <iostream>

using namespace std;
int cnt[2000001];
int N;

int main() {
	ios::sync_with_stdio(false);

	cin >> N;

	
	for (int i = 0; i < N; i++) {
		int x;
		cin >> x;
		cnt[x + 1000000]++;
	}
	
	int idx = 0;
	for (int i = 0; i <= 2000000; i++) {
		if (cnt[i] == 0) continue;

		for (int j = 0; j < cnt[i]; j++) {
			cout << i - 1000000 << '\n';
		}
	}


	return 0;
}