#include <iostream>
#include <algorithm>

using namespace std;

int N;
long long M;
int arr[1'000'000];
long long sum[1'000'001];

long long getWood(int h) {
    int idx = lower_bound(arr, arr + N, h) - arr;

    long long sumAbove = sum[N] - sum[idx];
    long long countAbove = N - idx;

    return sumAbove - 1LL * h * countAbove;
}

int main() {
    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> M;

        int maxVal = 0;

        for (int i = 0; i < N; i++) {
            cin >> arr[i];
            maxVal = max(maxVal, arr[i]);
        }

        sort(arr, arr + N);

        sum[0] = 0;
        for (int i = 0; i < N; i++) {
            sum[i + 1] = sum[i] + arr[i];
        }

        int l = 0;
        int r = maxVal;

        while (l < r) {
            int mid = (l + r + 1) / 2;

            long long cnt = getWood(mid);

            if (cnt >= M) {
                l = mid;
            }
            else {
                r = mid - 1;
            }
        }

        cout << '#' << test_case << ' ' << l << '\n';
    }

    return 0;
}