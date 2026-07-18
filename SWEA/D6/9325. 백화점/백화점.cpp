#include <iostream>
#include <vector>

using namespace std;

const int MAX_N = 2 * 1'000'000;

int T, N, D;

long long P;
int arr[MAX_N];
long long sum[MAX_N+1];

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        cin >> N >> P >> D;

        arr[0] = 0;
        sum[0] = 0;
        for (int i = 0; i < N; i++) {
            cin >> arr[i];
            sum[i + 1] = sum[i] + arr[i];
        }

        vector<int> dq(N - D + 1);
        int head = 0;
        int tail = 0;

        int l = 0;
        int answer = D;
        for (int r = D - 1; r < N; r++) {
            int start = r - D + 1;
            long long newDiscount = sum[start + D] - sum[start];

            while (head < tail) {
                int back = dq[tail - 1];

                long long backDiscount = sum[back + D] - sum[back];
                if (backDiscount > newDiscount) break;

                tail--;
            }
            
            dq[tail++] = start;

            while (true) {
                while (head < tail && dq[head] < l) {
                    head++;
                }

                long long total = sum[r + 1] - sum[l];

                int maxStart = dq[head];
                long long maxDiscount = sum[maxStart + D] - sum[maxStart];

                long long cost = total - maxDiscount;

                if (cost <= P) break;

                l++;
            }

            answer = max(answer, r - l + 1);
        }
        


        printf("#%d %lld\n", tc, answer);
    }

    return 0;
}
