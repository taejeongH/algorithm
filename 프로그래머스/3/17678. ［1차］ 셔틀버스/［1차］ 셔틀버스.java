import java.util.*;
class Solution {
    public String solution(int n, int t, int m, String[] timetable) {
        String answer = "";
        
        int start = 9 * 60;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i=0; i<timetable.length; i++) {
            int hour = (timetable[i].charAt(0)-'0')*10 + (timetable[i].charAt(1)-'0');
            int minute = (timetable[i].charAt(3)-'0')*10 + (timetable[i].charAt(4)-'0');
            
            pq.add(hour * 60 + minute);
        }
        
        int test = 0;
        for (int i=0; i<n; i++) {
            int time = start + (i*t);
            
            int cnt = 0;
            int last = 0;
            while(!pq.isEmpty() && cnt < m && pq.peek() <= time) {
                last = pq.poll();
                cnt++;
            }
            
            if (cnt < m) {
                test = time;
            } else {
                test = last-1;
            }
        }
        
        String hour = Integer.toString(test/60);
        String minute = Integer.toString(test%60);
        
        if(hour.length() == 1) {
            hour = "0" + hour;
        }
        
        if(minute.length() == 1) {
            minute = "0" + minute;
        }
        System.out.println(hour + ":" + minute);
        return hour+":"+minute;
    }
}