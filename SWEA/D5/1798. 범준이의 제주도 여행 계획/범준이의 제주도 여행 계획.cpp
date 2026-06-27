#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int N, M;
int airplane;
int g[35][35];

bool isHotel[35];
bool isTour[35];
bool visited[35];

int takeTime[35];
int satisfaction[35];

vector<int> hotels;
vector<int> tours;

int bestSat;
vector<int> route;
vector<int> bestRoute;

const int MAX_TIME = 9 * 60;

int minHotelDist[35];

void updateBest(int sat) {
    if (sat > bestSat) {
        bestSat = sat;
        bestRoute = route;
    }
}

void dfs(int cur, int day, int time, int sat, int remainSat) {
    // 남은 관광지를 전부 가도 현재 best를 못 넘으면 중단
    if (sat + remainSat <= bestSat) return;

    // 1. 관광지 방문
    for (int nxt : tours) {
        if (visited[nxt]) continue;

        int nextTime = time + g[cur][nxt] + takeTime[nxt];

        if (nextTime > MAX_TIME) continue;

        // 마지막 날이면 공항까지 돌아갈 수 있어야 함
        if (day == M) {
            if (nextTime + g[nxt][airplane] > MAX_TIME) continue;
        }
        // 마지막 날이 아니면 호텔까지 갈 수 있어야 함
        else {
            if (nextTime + minHotelDist[nxt] > MAX_TIME) continue;
        }

        visited[nxt] = true;
        route.push_back(nxt);

        dfs(nxt, day, nextTime, sat + satisfaction[nxt], remainSat - satisfaction[nxt]);

        route.pop_back();
        visited[nxt] = false;
    }

    // 2. 마지막 날이면 공항으로 복귀
    if (day == M) {
        if (time + g[cur][airplane] <= MAX_TIME) {
            route.push_back(airplane); // 문제에서 공항 출력 안 하면 이 줄 제거
            updateBest(sat);
            route.pop_back();
        }
        return;
    }

    // 3. 마지막 날이 아니면 호텔로 이동 후 다음 날 시작
    for (int h : hotels) {
        if (time + g[cur][h] > MAX_TIME) continue;

        route.push_back(h);
        dfs(h, day + 1, 0, sat, remainSat);
        route.pop_back();
    }
}

int main() {
    int T;
    cin >> T;

    for (int test_case = 1; test_case <= T; test_case++) {
        cin >> N >> M;

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                cin >> g[i][j];
                g[j][i] = g[i][j];
            }
        }

        hotels.clear();
        tours.clear();
        route.clear();
        bestRoute.clear();

        bestSat = 0;

        for (int i = 0; i < N; i++) {
            isHotel[i] = false;
            isTour[i] = false;
            visited[i] = false;
            takeTime[i] = 0;
            satisfaction[i] = 0;
        }

        char place;
        int totalSat = 0;

        for (int i = 0; i < N; i++) {
            cin >> place;

            if (place == 'P') {
                cin >> takeTime[i] >> satisfaction[i];
                isTour[i] = true;
                tours.push_back(i);
                totalSat += satisfaction[i];
            }
            else if (place == 'H') {
                isHotel[i] = true;
                hotels.push_back(i);
            }
            else if (place == 'A') {
                airplane = i;
            }
        }

        // 각 지점에서 가장 가까운 호텔까지의 거리
        for (int i = 0; i < N; i++) {
            minHotelDist[i] = 1e9;
            for (int h : hotels) {
                minHotelDist[i] = min(minHotelDist[i], g[i][h]);
            }
        }

        sort(tours.begin(), tours.end(), [](int a, int b) {
            if (satisfaction[a] == satisfaction[b]) return a < b;
            return satisfaction[a] > satisfaction[b];
            });

        dfs(airplane, 1, 0, 0, totalSat);

        cout << '#' << test_case << ' ' << bestSat << ' ';

        for (int x : bestRoute) {
            cout << x + 1 << ' ';
        }

        cout << '\n';
    }

    return 0;
}