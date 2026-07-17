#include <iostream>
#include <algorithm>

using namespace std;

const int MAX_N = 100'000;

int T, N;
int arr[MAX_N];
int sorted[MAX_N];
int sum[MAX_N];

int main() {
    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        cin >> N;

        for (int i = 0; i < N; i++) {
            cin >> arr[i];
            sorted[i] = arr[i];
        }

        sort(sorted, sorted + N);

        int answer = 0;
        if (N % 2 == 0) {
            int lower = sorted[N / 2 - 1];
            int upper = sorted[N / 2];

            sum[0] = arr[0] < upper ? -1 : 1;
            int cnt = 0;
            for (int i = 1; i < N; i ++) {
                if (arr[i] < upper) arr[i] = -1;
                else arr[i] = +1;

                sum[i] = sum[i - 1] + arr[i];
                if (sum[i] == 0) cnt++;
            }
            answer = (cnt % 2 == 0) ? upper : lower;
        }
        else {
            answer = sorted[N / 2];
        }

        printf("#%d %d\n", tc, answer);
    }

    return 0;
}
