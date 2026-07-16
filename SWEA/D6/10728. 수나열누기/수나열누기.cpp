#include <iostream>
#include <stack>

using namespace std;

const int MAX_N = 300'000;

int T, N;
int arr[MAX_N+1];
int maxTree[MAX_N * 4];
int nextSmaller[MAX_N];

void update(int start, int end, int left, int node, int idx) {
    if (left < start || end < left) return;
    if (start == end) {
        maxTree[node] = idx;
        return;
    }

    int mid = (start + end) / 2;
    update(start, mid, left, node * 2, idx);
    update(mid + 1, end, left, node * 2 + 1, idx);
    
    if (arr[maxTree[node*2]] < arr[maxTree[node*2+1]]) maxTree[node] = maxTree[node * 2 + 1];
    else if (arr[maxTree[node * 2]] > arr[maxTree[node * 2 + 1]]) maxTree[node] = maxTree[node * 2];
    else {
        maxTree[node] = maxTree[node * 2];
        if (maxTree[node * 2] < maxTree[node * 2 + 1]) maxTree[node] = maxTree[node * 2 + 1];
    }
}

int findMax(int left, int right, int start, int end, int node) {
    if (right < start || left > end) return N;

    if (left <= start && end <= right) return maxTree[node];

    int mid = (start + end) / 2;
    int leftIdx = findMax(left, right, start, mid, node * 2);
    int rightIdx = findMax(left, right, mid + 1, end, node * 2 + 1);

    if (arr[leftIdx] > arr[rightIdx]) return leftIdx;
    else if (arr[leftIdx] < arr[rightIdx]) return rightIdx;
    else return leftIdx < rightIdx ? rightIdx : leftIdx;
}

int main() {
    cin >> T;

    for (int tc = 1; tc <= T; tc++) {
        cin >> N;

        for (int i = 0; i < N * 4; i++) {
            maxTree[i] = 0;
        }

        arr[N] = 0;
        for (int i = 0; i < N; i++) {
            cin >> arr[i];
            update(0, N-1, i, 1, i);
        }
        
        int answer = 0;
        stack<int> st;

        for (int i = N - 1; i >= 0; i--) {
            while (!st.empty() && arr[st.top()] >= arr[i]) {
                st.pop();
            }

            if (st.empty()) {
                nextSmaller[i] = N;
            }
            else {
                nextSmaller[i] = st.top();
            }

            st.push(i);
        }

        for (int i = 0; i < N; i++) {
            int r = nextSmaller[i];
            int max = findMax(i, r, 0, N - 1, 1);
            i = max;
            answer++;
        }

        printf("#%d %d\n", tc, answer);
    }

    return 0;
}