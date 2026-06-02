import java.util.*;
class Solution {
    public String solution(int n, int k, String[] cmd) {   
        int[] prev = new int[n];
        int[] nxt = new int[n];
        
        for (int i=0; i<n; i++) {
            prev[i] = i-1;
            nxt[i] = i+1;
        }
        nxt[n-1] = -1;
        
        boolean[] removed = new boolean[n];
        
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int curPos = k;
        
        for (int i=0; i<cmd.length; i++) {
            
            if (cmd[i].charAt(0) == 'U') {
                int cnt = Integer.parseInt(cmd[i].substring(2));
                for (int j=0; j<cnt; j++) curPos = prev[curPos];
            } else if (cmd[i].charAt(0) == 'D') {
                int cnt = Integer.parseInt(cmd[i].substring(2));
                for (int j=0; j<cnt; j++) curPos = nxt[curPos];
            } else if(cmd[i].charAt(0) == 'C') {
                removed[curPos] = true;
                stack.addLast(curPos);

                int p = prev[curPos];
                int nx = nxt[curPos];

                if (p != -1) {
                    nxt[p] = nx;
                }

                if (nx != -1) {
                    prev[nx] = p;
                }

                if (nx != -1) {
                    curPos = nx;
                } else {
                    curPos = p;
                }

            } else {
                int res = stack.pollLast();

                int p = prev[res];
                int nx = nxt[res];

                if (p != -1) {
                    nxt[p] = res;
                }

                if (nx != -1) {
                    prev[nx] = res;
                }

                removed[res] = false;
            }
        
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            if (removed[i]) sb.append('X');
            else sb.append('O');
        }
        
        return sb.toString();
    }
}