#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

int T, K;
string input;

int main() {
    ios::sync_with_stdio(false);
    cin.tie(nullptr);

    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> K >> input;

        vector<string> v;
        int N = input.length();

        for (int i = 0; i < N; i++) {
            string s = "";
            for (int j = i; j < N; j++) {
                s += input[j];
                v.push_back(s);
            }
        }

        sort(v.begin(), v.end());
        v.erase(unique(v.begin(), v.end()), v.end());

        string answer;
        if ((int)v.size() < K) {
            answer = "none";
        }
        else {
            answer = v[K - 1];
        }

        cout << "#" << test_case << " " << answer << '\n';
    }

    return 0;
}