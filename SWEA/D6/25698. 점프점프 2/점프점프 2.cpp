#include <iostream>
#include <set>
#include <vector>
#include <cmath>
#include <numeric>

using namespace std;

int T, X, Y, S;
vector<pair<int, int>> arr;

int gcd(int a, int b) {
    int tmp, n;

    if (a < b) {
        tmp = a;
        a = b;
        b = tmp;
    }

    while (b != 0) {
        n = a % b;
        a = b;
        b = n;
    }
    return a;
}

int main() {
    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        cin >> X >> Y >> S;
        if (X == 0 && Y == 0) {
            cout << "yes\n";
            continue;
        }

        arr.clear();

        set<pair<int, int>> vectors;
        for (int a = 0; 1LL * a * a <= S; a++) {
            int remain = S - a * a;
            int b = sqrt(remain);

            if (b * b != remain) continue;

            vectors.insert({ a,  b });
            vectors.insert({ a, -b });
            vectors.insert({ -a,  b });
            vectors.insert({ -a, -b });

            vectors.insert({ b,  a });
            vectors.insert({ b, -a });
            vectors.insert({ -b,  a });
            vectors.insert({ -b, -a });
        }

        if (vectors.empty()) {
            cout << "no\n";
            continue;
        }

        arr.assign(vectors.begin(), vectors.end());
        
        long long g = 0;

        for (int i = 0; i < arr.size(); i++) {
            for (int j = i + 1; j < arr.size(); j++) {
                long long value = 1LL * arr[i].first * arr[j].second - 1LL * arr[i].second * arr[j].first;

                g = gcd(g, abs(value));
            }
        }

        bool reachable = true;
        for (int i = 0; i < arr.size(); i++) {
            int a = arr[i].first;
            int b = arr[i].second;
            long long determinant = 1LL * a * Y -  1LL * b * X;

            if (determinant % g != 0) {
                reachable = false;
                break;
            }
        }

        cout << (reachable?"yes\n":"no\n");
    }

    return 0;
}