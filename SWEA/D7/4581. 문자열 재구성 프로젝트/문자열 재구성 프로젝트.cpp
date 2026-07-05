#include <iostream>
#include <string>

using namespace std;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        string s;
        cin >> s;

        int n = s.length();

        string answer;
        answer.reserve(n);

        int l = 0;
        int r = n - 1;

        for (int i = 0; i < n; i++) {
            if (s[l] < s[r]) {
                answer.push_back(s[l++]);
            }
            else if (s[l] > s[r]) {
                answer.push_back(s[r--]);
            }
            else {
                int left = l;
                int right = r;

                while (left < right && s[left] == s[right]) {
                    left++;
                    right--;
                }

                if (left < right && s[left] < s[right]) {
                    answer.push_back(s[l++]);
                }
                else {
                    answer.push_back(s[r--]);
                }
            }
        }

        cout << '#' << test_case << ' ' << answer << '\n';
    }

    return 0;
}