#include <iostream>


#define MIN(x,y) (x) < (y) ? (x) : (y)
using namespace std;

int N;
int g[1000][1000];
int MAX = 1'000'000'000;

int main() {
    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N;

        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                g[i][j] = MAX;

        int num;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                cin >> num;
                if (num == 1) g[i][j] = 1;
            }
        }


        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (i == j) continue;

                    g[i][j] = MIN(g[i][j], g[i][k] + g[k][j]);
                }
            }
        }

        int res = MAX;
        for (int i = 0; i < N; i++) {
            
            int sum = 0;
            for (int j = 0; j < N; j++) {
                if (i == j) continue;
                sum += g[i][j];
            }

            res = MIN(res, sum);
        }

        cout << '#' << test_case << ' ' << res << '\n';
    }

    return 0;
}