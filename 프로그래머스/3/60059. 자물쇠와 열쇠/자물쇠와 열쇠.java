class Solution {
    static int N, M;
    public boolean solution(int[][] key, int[][] lock) {        
        N = lock.length;
        M = key.length;
        
        int startY = N-1;
        int startX = N-1;
        
        int endY = 0;
        int endX = 0;
        
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                if (lock[i][j] == 0) {
                    startY = Math.min(i, startY);
                    endY = Math.max(i, endY);
                    
                    startX = Math.min(j, startX);
                    endX = Math.max(j, endX);
                }
            }
        }
        
        int ySize = endY - startY + 1;
        int xSize = endX - startX + 1;
        
        int[][] turn90 = new int[M][M];
        int[][] turn180 = new int[M][M];
        int[][] turn270 = new int[M][M];
        
        for (int i=0; i<M; i++) {
            for (int j=0; j<M; j++) {
                turn90[i][j] = key[M-j-1][i];
                turn180[i][j] = key[M-i-1][M-j-1];
                turn270[i][j] = key[j][M-i-1];
            }
        }
        
        if(ans(key, lock, startY, startX, ySize, xSize)) return true;
        if(ans(turn90, lock, startY, startX, ySize, xSize)) return true;
        if(ans(turn180, lock, startY, startX, ySize, xSize)) return true;
        if(ans(turn270, lock, startY, startX, ySize, xSize)) return true;
        
        return false;
    }
    
    public boolean ans(int[][] key, int[][] lock, int startY, int startX, int ySize, int xSize) {
        for (int i=0; i<=M-ySize; i++) {
            for (int j=0; j<=M-xSize; j++) {
                
                boolean can = true;
                nxt: for (int k=0; k<ySize; k++) {
                    for (int n=0; n<xSize; n++) {
                        if ((lock[startY+k][startX+n] ^ key[i+k][j+n]) == 0) {
                            can = false;
                            break nxt;
                        } 
                    }
                }
                if (can) return true;
            }
        }
        return false;
    }
}