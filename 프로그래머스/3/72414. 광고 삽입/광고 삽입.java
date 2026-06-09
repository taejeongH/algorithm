import java.util.*;
class Solution {
    public String solution(String play_time, String adv_time, String[] logs) {
        int N = logs.length;
        
        long[] arr = new long[360_000];
        for (int i=0; i<N; i++) {
            int shour = Integer.parseInt(logs[i].substring(0, 2));
            int sminute = Integer.parseInt(logs[i].substring(3, 5));
            int ssecond = Integer.parseInt(logs[i].substring(6, 8));
            
            int ehour = Integer.parseInt(logs[i].substring(9, 11));
            int eminute = Integer.parseInt(logs[i].substring(12, 14));
            int esecond = Integer.parseInt(logs[i].substring(15, 17));
            
            int ss = shour * 60 * 60 + sminute * 60 + ssecond;
            int es = ehour * 60 * 60 + eminute * 60 + esecond;
            for (int j=ss; j<es; j++) {
                arr[j]++;
            }
        }
        int advSecond = Integer.parseInt(adv_time.substring(0, 2)) * 60 * 60 +
            Integer.parseInt(adv_time.substring(3, 5)) * 60 +
            Integer.parseInt(adv_time.substring(6, 8));
        
        int playSecond = Integer.parseInt(play_time.substring(0, 2)) * 60 * 60 +
            Integer.parseInt(play_time.substring(3, 5)) * 60 +
            Integer.parseInt(play_time.substring(6, 8));
        
        for (int i=1; i<arr.length; i++) {
            arr[i] = arr[i-1] + arr[i];
        }
        
        long maxSum = arr[advSecond-1];
        int answer = 0;
        for (int i=advSecond; i<playSecond; i++){
            long sum = arr[i] - arr[i-advSecond];
            
            if (maxSum < sum) {
                maxSum = sum;
                answer = i-advSecond+1;
            }
        }
        
        String hour = "" + answer / 60 / 60;
        String minute = "" + (answer / 60) % 60;
        String second = "" + answer % 60;
        
        if(hour.length() == 1) {
            hour = "0" + hour;
        }
        
        if(minute.length() == 1){
            minute = "0" + minute;
        }
        
        if(second.length() == 1) {
            second = "0" + second;
        }
        
        return hour + ":" + minute + ":" + second;
    }
}

/*
    초단위로 변경 -> 정렬
    
    

*/