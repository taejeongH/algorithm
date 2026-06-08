import java.util.*;
class Solution {
    static HashMap<Long, Long> map;
    public long[] solution(long k, long[] room_number) {
        int N = room_number.length;        
        long[] answer = new long[N];
        
        map = new HashMap<>();
        
        for (int i=0; i<N; i++) {
            answer[i] = find(room_number[i]);
        }
        return answer;
    }
    
    long find(long num) {
        if (!map.containsKey(num)) {
            map.put(num, num+1);
            return num;
        }
            
        long found = find(map.get(num));
        map.put(num, found+1);
        return found;
    }
}