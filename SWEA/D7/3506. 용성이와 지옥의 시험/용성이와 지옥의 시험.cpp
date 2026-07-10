#include <iostream>
#include <algorithm>

using namespace std;

const int MAX_N = 1'000'000;

int T, N;
long long P;

long long prefix[MAX_N + 1];
long long sortedPrefix[MAX_N + 1];
int tree[MAX_N + 2];

void update(int idx, int size) {
    while (idx <= size) {
        tree[idx]++;
        idx += idx & -idx;
    }
}

int query(int idx) {
    int result = 0;

    while (idx > 0) {
        result += tree[idx];
        idx -= idx & -idx;
    }

    return result;
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> P;

        prefix[0] = 0;
        sortedPrefix[0] = 0;

        for (int i = 1; i <= N; i++) {
            long long input;
            cin >> input;

            prefix[i] = prefix[i - 1] + input - P;
            sortedPrefix[i] = prefix[i];
        }

        sort(sortedPrefix, sortedPrefix + N + 1);

        int size =
            unique(sortedPrefix, sortedPrefix + N + 1)
            - sortedPrefix;

        fill(tree, tree + size + 1, 0);

        long long answer = 0;

        for (int i = 0; i <= N; i++) {
            int idx =
                lower_bound(
                    sortedPrefix,
                    sortedPrefix + size,
                    prefix[i]
                ) - sortedPrefix + 1;

            answer += query(idx);
            update(idx, size);
        }

        cout << '#' << test_case << ' ' << answer << '\n';
    }

    return 0;
}