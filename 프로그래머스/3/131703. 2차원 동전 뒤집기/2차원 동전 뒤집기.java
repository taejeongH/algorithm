class Solution {
    
    boolean isCorrect() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<M; j++) {
                if (map[i][j] != target[i][j]) return false;
            }
        }
        return true;
    }
    
    static int N;
    static int M;
    static int[][] map;
    static int[][] target;
    static final int INF = 1_000_000_000;
    
    public int solution(int[][] beginning, int[][] target) {
        
        this.N = beginning.length;
        this.M = beginning[0].length;
        this.map = beginning;
        this.target = target;
        int answer = dfs(0, 0);
        return answer==INF?-1:answer;
    }
    
    int dfs(int y, int x) {
        if (isCorrect()) {
            return 0;
        }
        
        if (y>=N && x >= M) return INF;
        
        int res = INF;
        if (y+1 <= N) {
            res = Math.min(res, dfs(y+1, x));
            
            for (int i=0; i<M; i++) {
                map[y][i] = (map[y][i]==0?1:0);
            }
            res = Math.min(res, dfs(y+1, x) + 1);
            
            for (int i=0; i<M; i++) {
                map[y][i] = (map[y][i]==0?1:0);
            }
        }
    
        else {
            res = Math.min(dfs(y, x+1), res);
            
            for (int i=0; i<N; i++) {
                map[i][x] = (map[i][x]==0?1:0);
            }
            res = Math.min(res, dfs(y, x+1) + 1);
            for (int i=0; i<N; i++) {
                map[i][x] = (map[i][x]==0?1:0);
            }
        }
        return res;
    }
}