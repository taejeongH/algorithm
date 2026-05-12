#include <string>
#include <vector>

using namespace std;

static int N, t;
static int arr[20];

int dfs(int idx, int sum) {
    if (idx == N) {
        if (sum == t) return 1;
        return 0;
    }
    
    return dfs(idx+1, sum+arr[idx]) + dfs(idx+1, sum-arr[idx]);
}

int solution(vector<int> numbers, int target) {
    int answer = 0;
    N = numbers.size();
    
    for (int i=0; i<N; i++) {
        arr[i] = numbers[i];
    }
    t = target;
    return dfs(0, 0);
}

