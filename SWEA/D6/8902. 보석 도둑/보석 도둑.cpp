#include <iostream>
#include <algorithm>
#include <bitset>

using namespace std;

const int MAX_N = 5000;

int T, N, K;
int arr[MAX_N];


bool isUseful(int num) {
    bitset<MAX_N+1> dp;
    dp[0] = true;

    for (int i = 0; i < N; i++) {
        if (num == i || arr[i] >= K) continue;
        dp |= dp << arr[i];
    }

    int l = max(0, K - arr[num]);

    for (int sum = l; sum < K; sum++) {
        if (dp[sum]) return true;
    }

    return false;
}


int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        cin >> N >> K;

        for (int i = 0; i < N; i++) cin >> arr[i];
        
        sort(arr, arr + N);

        int l = 0;
        int r = N;

        while (l < r) {
            int mid = (l + r) / 2;

            if (isUseful(mid)) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }

        printf("#%d %d\n", tc, l);
    }

    return 0;
}
