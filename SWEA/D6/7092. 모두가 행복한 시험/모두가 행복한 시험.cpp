#include <iostream>

using namespace std;

int T, N;
char arr[30000][3];
int cnt[5];

int MIN(int x, int y) { return x < y ? x : y; }

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N;

        for (int j = 0; j < 3; j++) {
            for (int i = 0; i < N; i++) {
                cin >> arr[i][j];
            }
        }

        for (int i = 0; i < 5; i++) cnt[i] = 0;

        for (int i = 0; i < N; i++) {
            if (arr[i][0] == arr[i][1] && arr[i][1] == arr[i][2]) cnt[0]++;
            else if (arr[i][0] == arr[i][1]) cnt[1]++;
            else if (arr[i][0] == arr[i][2]) cnt[2]++;
            else if (arr[i][1] == arr[i][2]) cnt[3]++;
            else cnt[4]++;
        }


        
        int answer = cnt[0] ;

        if (cnt[1] != 0 && cnt[2] != 0 && cnt[3] != 0) {
            int min = MIN(MIN(cnt[1], cnt[2]), cnt[3]);
            answer += min * 2;

            for (int i = 1; i <= 3; i++) {
                cnt[i] -= min;
            }
        }
        
        int sum = cnt[1] + cnt[2] + cnt[3];
        if (sum < cnt[4]) {
            answer += sum;
            answer += (cnt[4] - sum) / 3;
        }
        else if (sum > cnt[4]) {
            answer += cnt[4];

            while (cnt[4] != 0) {
                int max = 1;
                for (int i = 2; i <= 3; i++) {
                    if (cnt[max] < cnt[i]) {
                        max = i;
                    }
                }
                cnt[max]--;
                cnt[4]--;
            }

            int rem = cnt[1] + cnt[2] + cnt[3];
            int plus = rem;
            plus = MIN(plus, 2 * rem / 3);
            plus = MIN(plus, (2 * rem - (cnt[2] + cnt[3])) / 2);
            plus = MIN(plus, (2 * rem - (cnt[1] + cnt[3])) / 2);
            plus = MIN(plus, (2 * rem - (cnt[1] + cnt[2])) / 2);

            answer += plus;
        }
        else {
            answer += sum;
        }

        cout << "#" << test_case << " "  << answer << "\n";
    } 

    return 0;
}