import java.util.*;
class Solution {
    public int solution(String[] lines) {
        int answer = 0;
        int N = lines.length;
        
        int[][] map = new int[N][2];
        for (int i=0; i<N; i++) {
            double hour = Double.parseDouble(lines[i].substring(11, 13)) ;
            double minute = Double.parseDouble(lines[i].substring(14, 16));
            double second = Double.parseDouble(lines[i].substring(17, 23));
            int seconds = (int) ((hour*60*60 + minute*60 + second) * 1000);

            Double dur = Double.parseDouble(lines[i].substring(24, lines[i].length()-1));
            
            int end = seconds;
            int start = seconds - ((int) (dur * 1000))+1;
            map[i][0] = start;
            map[i][1] = end;
            System.out.println(start + " " + end);
        }
        
        Arrays.sort(map, (o1, o2) -> {
           if(o1[0] == o2[1]) return o1[1] - o2[1];
            return o1[0] - o2[0];
        });
        
        for (int i=0; i<N; i++) {
            int s = map[i][1];
            int e = map[i][1] + 999;
            int cnt = 0;
            for (int j=0; j<N; j++) {
                if (map[j][0] > e || map[j][1] < s) continue;
                cnt++;
            }
            System.out.println(cnt);
            answer = Math.max(cnt, answer);
        }

        return answer;
    }
}