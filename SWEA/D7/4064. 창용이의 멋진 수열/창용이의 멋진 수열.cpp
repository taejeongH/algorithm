#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N;
long long S;

long long arr[100000];
int cnt[100000];
int tree[100000 * 4];

void update(int node, int start, int end, int idx, int value) {
    if (idx < start || end < idx) return;

    if (start == end) {
        tree[node] = value;
        return;
    }

    int mid = (start + end) / 2;

    update(node * 2, start, mid, idx, value);
    update(node * 2 + 1, mid + 1, end, idx, value);

    tree[node] = max(tree[node * 2], tree[node * 2 + 1]);
}

int query(int node, int start, int end, int left, int right) {
    if (right < start || end < left) return -1;

    if (left <= start && end <= right) {
        return tree[node];
    }

    int mid = (start + end) / 2;

    int lRes = query(node * 2, start, mid, left, right);
    int rRes = query(node * 2 + 1, mid + 1, end, left, right);

    return max(lRes, rRes);
}

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> S;

        for (int i = 0; i < N; i++) {
            cin >> arr[i];
            cnt[i] = 0;
        }

        for (int i = 0; i < N * 4; i++) {
            tree[i] = -1;
        }

        // cnt[i] = i에서 시작해서 합이 S 이하인 최대 길이
        int r = 0;
        long long sum = 0;

        for (int l = 0; l < N; l++) {
            while (r < N && sum + arr[r] <= S) {
                sum += arr[r];
                r++;
            }

            cnt[l] = r - l;

            if (r == l) {
                r++;
            } else {
                sum -= arr[l];
            }
        }

        vector<pair<int, int>> events;
        events.reserve(N);

        for (int j = 0; j < N; j++) {
            int need = j - cnt[j];
            events.push_back({need, j});
        }

        sort(events.begin(), events.end());

        cout << '#' << test_case << ' ';

        int ptr = 0;

        for (int i = 0; i < N; i++) {
            while (ptr < N && events[ptr].first <= i) {
                int j = events[ptr].second;
                update(1, 0, N - 1, j, j);
                ptr++;
            }

            int left = i + 1;
            int right = min(N - 1, i + cnt[i]);

            int j = -1;
            if (left <= right) {
                j = query(1, 0, N - 1, left, right);
            }

            int res = 0;

            if (j != -1) {
                res = (j - i) * 2;
            }

            cout << res << ' ';
        }

        cout << '\n';
    }

    return 0;
}