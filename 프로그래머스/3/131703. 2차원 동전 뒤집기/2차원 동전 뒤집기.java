class Solution {
    boolean[] vR;
    boolean[] vC;
    int N, M;
    int answer;
    int[][] beginning, target;
    public int solution(int[][] beginning, int[][] target) {
        answer = Integer.MAX_VALUE;
        N = beginning.length;
        M = beginning[0].length;
        this.beginning = beginning;
        this.target = target;
        
        vR = new boolean[N];
        vC = new boolean[M];
        dfs(0, 0, 0);
        return answer==Integer.MAX_VALUE?-1:answer;
    }
    
    void dfs(int count, int y, int x) {
        if (count > answer) return;
        if (isSame()) {
            answer = Math.min(answer, count);
            return;
        }
        
        for (int i=y; i<N; i++) {
            if (vR[i]) continue;
            vR[i] = true;
            //뒤집기 해당 행
            for (int j=0; j<M; j++) {
                beginning[i][j] = beginning[i][j]==1?0:1;
            }
            dfs(count+1, i+1, x);
            vR[i] = false;
            for (int j=0; j<M; j++) {
                beginning[i][j] = beginning[i][j]==1?0:1;
            }
        }
        
        for (int j=x; j<M; j++) {
            if(vC[j]) continue;
            vC[j] = true;
            for (int i=0; i<N; i++) {
                beginning[i][j] = beginning[i][j]==1?0:1;
            }
            dfs(count+1, y, j+1);
            vC[j] = false;
            for (int i=0; i<N; i++){
                beginning[i][j] = beginning[i][j]==1?0:1;
            }
        }
        
    }
    
    boolean isSame() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (beginning[i][j] != target[i][j]) return false;
            }
        }
        return true;
    }
}