import java.util.*;

class Solution {
    public String solution(int n, int t, int m, String[] timetable) {

        int[] crew = new int[timetable.length];

        for (int i = 0; i < timetable.length; i++) {
            String[] time = timetable[i].split(":");
            int hour = Integer.parseInt(time[0]);
            int minute = Integer.parseInt(time[1]);

            crew[i] = hour * 60 + minute;
        }

        Arrays.sort(crew);

        int start = 9 * 60;
        int ptr = 0;
        int answer = 0;

        for (int i = 0; i < n; i++) {
            int available = m;
            int last = -1;

            while (available > 0 && ptr < crew.length && crew[ptr] <= start) {
                last = crew[ptr];
                available--;
                ptr++;
            }

            if (i == n - 1) {
                if (available > 0) {
                    answer = start;
                } else {
                    answer = last - 1;
                }
            }

            start += t;
        }

        return String.format("%02d:%02d", answer / 60, answer % 60);
    }
}