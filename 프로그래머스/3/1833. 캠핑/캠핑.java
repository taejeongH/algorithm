import java.util.*;
class Solution {
    public int solution(int n, int[][] data) {
        int answer = 0;
        
        Arrays.sort(data,(o1,o2)->{
            if(o1[0]==o2[0]) return o1[1]-o2[1];
            return o1[0]-o2[0];
        });
        
        for (int i=0; i<n; i++) {
            //System.out.println(Arrays.toString(data[i]));
            
            for (int j=i+1; j<n; j++) {
                if (data[i][0] == data[j][0] || data[i][1] == data[j][1]) continue;
                int minX = Math.min(data[i][1], data[j][1]);
                int maxX = Math.max(data[i][1], data[j][1]);
                
                boolean can = true;
                for (int k=i+1; k<j; k++) {
                    if (data[k][0] == data[i][0] || 
                        data[k][0] == data[j][0]) continue;

                    if (minX < data[k][1] && data[k][1] < maxX) {
                        can = false;
                        break;
                    }
                }
                if (can) {
                    //System.out.println(i + " " + j);
                    answer++;
                }
            }
        }
        
        return answer;
    }
}