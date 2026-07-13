#include <iostream>
#include <vector>
#include <algorithm>
#include <climits>

using namespace std;

struct Cat {
    long long x;
    long long count;
};

int max(int x, int y) { return x > y ? x : y; }
int min(int x, int y) { return x < y ? x : y; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    for (int testCase = 1; testCase <= T; testCase++) {
        int N, M;
        cin >> N >> M;

        vector<Cat> cats(N);

        for (int i = 0; i < N; i++) {
            int y;
            cin >> cats[i].x >> y >> cats[i].count;
        }

        vector<int> pos(M);

        for (int i = 0; i < M; i++) {
            cin >> pos[i];
        }

        sort(pos.begin(), pos.end());

        vector<vector<int>> order(N, vector<int>(M));

        for (int i = 0; i < N; i++) {
            int right = lower_bound(
                pos.begin(),
                pos.end(),
                cats[i].x
            ) - pos.begin();

            int left = right - 1;

            for (int k = 0; k < M; k++) {
                if (left < 0) {
                    order[i][k] = right++;
                }
                else if (right >= M) {
                    order[i][k] = left--;
                }
                else {
                    int leftDistance = cats[i].x - pos[left];
                    int rightDistance = pos[right] - cats[i].x;

                    if (leftDistance <= rightDistance) {
                        order[i][k] = left--;
                    }
                    else {
                        order[i][k] = right++;
                    }
                }
            }
        }

        vector<int> pointer(N, 0);
        vector<int> owner(N);
        vector<int> count(M, 0);
        vector<bool> removed(M, false);

        for (int i = 0; i < N; i++) {
            owner[i] = order[i][0];
            count[owner[i]] += cats[i].count;
        }

        int answer = INT_MAX;
        int activeCount = M;

        while (activeCount > 0) {
            long long currentMax = 0;

            for (int i = 0; i < M; i++) {
                if (!removed[i]) {
                    currentMax = max(currentMax, count[i]);
                }
            }

            answer = min(answer, currentMax);

            // 현재 최댓값인 배식구를 모두 제거
            vector<int> toRemove;

            for (int i = 0; i < M; i++) {
                if (!removed[i] && count[i] == currentMax) {
                    toRemove.push_back(i);
                }
            }

            // 전부 제거하면 고양이가 갈 배식구가 없어지므로 현재 상태에서 종료
            if (static_cast<int>(toRemove.size()) == activeCount) {
                break;
            }

            for (int feeder : toRemove) {
                removed[feeder] = true;
                count[feeder] = 0;
            }

            activeCount -= static_cast<int>(toRemove.size());

            // 제거된 배식구를 이용하던 고양이만 다음 배식구로 이동
            for (int i = 0; i < N; i++) {
                if (!removed[owner[i]]) {
                    continue;
                }

                while (pointer[i] < M &&
                    removed[order[i][pointer[i]]]) {
                    pointer[i]++;
                }

                owner[i] = order[i][pointer[i]];
                count[owner[i]] += cats[i].count;
            }
        }

        cout << '#' << testCase << ' ' << answer << '\n';
    }

    return 0;
}