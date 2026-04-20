#include <iostream>
#include <cstring>
using namespace std;

int N;
int arr[51];
bool primeNum[2001];

int leftPart[51], rightPart[51];
int leftCnt, rightCnt;

int adj[51][51];
int adjCnt[51];

int matchR[51];
bool visited[51];

int answer[51];
int answerCnt;

void makePrime() {
    for (int i = 0; i <= 2000; i++) primeNum[i] = true;
    primeNum[0] = primeNum[1] = false;

    for (int i = 2; i * i <= 2000; i++) {
        if (!primeNum[i]) continue;
        for (int j = i * i; j <= 2000; j += i) {
            primeNum[j] = false;
        }
    }
}

bool dfs(int cur) {
    for (int i = 0; i < adjCnt[cur]; i++) {
        int nxt = adj[cur][i];
        if (visited[nxt]) continue;
        visited[nxt] = true;

        if (matchR[nxt] == -1 || dfs(matchR[nxt])) {
            matchR[nxt] = cur;
            return true;
        }
    }
    return false;
}

void sortAnswer() {
    for (int i = 0; i < answerCnt - 1; i++) {
        for (int j = i + 1; j < answerCnt; j++) {
            if (answer[i] > answer[j]) {
                int tmp = answer[i];
                answer[i] = answer[j];
                answer[j] = tmp;
            }
        }
    }
}

int main() {
    cin >> N;
    for (int i = 1; i <= N; i++) cin >> arr[i];

    makePrime();

    leftCnt = rightCnt = 0;

    // arr[1]과 같은 parity는 left, 다른 parity는 right
    for (int i = 2; i <= N; i++) {
        if ((arr[i] % 2) == (arr[1] % 2)) leftPart[leftCnt++] = i;
        else rightPart[rightCnt++] = i;
    }

    if (rightCnt != leftCnt + 1) {
        cout << -1;
        return 0;
    }

    answerCnt = 0;

    for (int r = 0; r < rightCnt; r++) {
        int firstPair = rightPart[r];

        if (!primeNum[arr[1] + arr[firstPair]]) continue;

        for (int i = 0; i < 51; i++) adjCnt[i] = 0;

        // leftPart -> rightPart 간 간선 구성
        // 단, firstPair는 이미 1과 매칭했다고 가정하므로 제외
        for (int i = 0; i < leftCnt; i++) {
            int lNode = leftPart[i];
            for (int j = 0; j < rightCnt; j++) {
                int rNode = rightPart[j];
                if (rNode == firstPair) continue;

                if (primeNum[arr[lNode] + arr[rNode]]) {
                    adj[lNode][adjCnt[lNode]++] = rNode;
                }
            }
        }

        for (int i = 0; i < 51; i++) matchR[i] = -1;
        matchR[firstPair] = 1; // 1과 firstPair를 고정 매칭

        int cnt = 0;
        for (int i = 0; i < leftCnt; i++) {
            memset(visited, false, sizeof(visited));
            if (dfs(leftPart[i])) cnt++;
        }

        if (cnt == leftCnt) {
            answer[answerCnt++] = arr[firstPair];
        }
    }

    if (answerCnt == 0) {
        cout << -1;
    }
    else {
        sortAnswer();
        for (int i = 0; i < answerCnt; i++) {
            cout << answer[i] << ' ';
        }
    }

    return 0;
}