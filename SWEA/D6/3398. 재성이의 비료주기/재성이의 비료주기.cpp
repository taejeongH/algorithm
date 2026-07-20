#include <iostream>
#include <algorithm>
#include <bitset>

using namespace std;

const int MAX_N = 100'000;
const int MOD = 1000000007;

int T, N, D;
long long s, a, b;
long long tree[MAX_N + 1];

void update(int pos, int value) {
    while (pos <= N) {
        tree[pos] += value;
        pos += (pos & -pos);
    }
}

void update(int l, int r, int value) {
    update(l, value);
    update(r + 1, -value);
}

long long sum(int pos) {
    long long ret = 0;
    while (pos > 0) {
        ret += tree[pos];
        pos &= (pos - 1);
    }
    return ret;
}

long long sum(int l, int r) {
    return sum(r) - sum(l - 1);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        cin >> N >> D >> s >> a >> b;

        for (int i = 0; i <= N; i++) {
            tree[i] = 0;
        }

        long long answer = 0;
        int ptr = 1;
        int c, k;
        for (int i = 1; i <= D; i ++) {
            c = s % N + 1;
            s = (s * a + b) % MOD;
            k = s % N + 1;
            s = (s * a + b) % MOD;

            int r = ((ptr + c - 2) % N) + 1;

            if (r < ptr) {
                update(ptr, N, i);
                update(1, r, i);
            }
            else {
                update(ptr, r, i);
            }

            ptr = r % N + 1;

            int pos = (ptr + k - 2) % N + 1;

            answer += sum(pos);
        }

        printf("#%d %lld\n", tc, answer);
    }
    return 0;
}
