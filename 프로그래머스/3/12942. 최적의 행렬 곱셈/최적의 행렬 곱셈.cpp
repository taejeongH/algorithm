#include <string>
#include <vector>
#include <iostream>
#define INF 1000000000
using namespace std;
int dp[200][200];

int dfs(int map[200][2], int l, int r) {
    if (l == r) return 0;
    if (r-l == 1) {
        return map[l][0] * map[l][1] * map[r][1];  
    }
    if (dp[l][r] != -1) return dp[l][r];
    
    int min = INF;
    for (int i=l; i<r; i++) {
        int cur = dfs(map, l, i) + dfs(map, i+1, r) + map[l][0] * map[i][1] * map[r][1];
        //cout << cur << endl;
        if (min > cur) min = cur;
    }
    
    return dp[l][r] = min;
}

int solution(vector<vector<int>> matrix_sizes) {
    int map[200][2];
    int N = matrix_sizes.size();
    for (int i=0; i<N; i++) {
        map[i][0] = matrix_sizes[i][0];
        map[i][1] = matrix_sizes[i][1];
    }
    
    for (int i=0; i<N; i++) {
        for (int j=0; j<N; j++) {
            dp[i][j] = -1;
        }
    }
    
    int answer = dfs(map, 0, N-1);
    return answer;
}

//끝점 기준 정렬 내림차순