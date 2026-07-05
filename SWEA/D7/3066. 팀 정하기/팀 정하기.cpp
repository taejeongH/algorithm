#include <iostream>

using namespace std;

int T;
int N, M, K;

bool can(int num) {
    if (N < num * 2 || (N - num * 2) + (M - num) < K) return false;

    return true;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> M >> K;

        int l = 0;
        int r = M;

        int res = 0;
        while (l <= r) {
            int mid = (l + r) / 2;

            if (can(mid)) {
                res = mid;
                l = mid + 1;
            }
            else {
                r = mid - 1;
            }
        }

        cout << '#' << test_case << ' ' << res << '\n';
    }

    return 0;
}