import java.util.*;
class Solution {
    public int[] solution(String[] operations) {
        PriorityQueue<Integer> minPq = new PriorityQueue<>();
        PriorityQueue<Integer> maxPq = new PriorityQueue<>(Collections.reverseOrder());
        int N = operations.length;
        for (int i=0; i<N; i++) {
            char order = operations[i].charAt(0);
            if (order == 'I') {
                int num = Integer.parseInt(operations[i].substring(2));
                minPq.add(num);
                maxPq.add(num);
            } else {
                if (operations[i].length() > 3) {
                    //최솟값 삭제
                    if (minPq.isEmpty()) continue;
                    int min = minPq.poll();
                    maxPq.remove(min);
                } else {
                    //최댓값 삭제
                    if(maxPq.isEmpty()) continue;
                    int max = maxPq.poll();
                    minPq.remove(max);
                }
            }
        }
        
        int[] answer = new int[2];
        if (!maxPq.isEmpty()) {
            answer[0] = maxPq.peek();
            answer[1] = minPq.peek();
        }
        return answer;
    }
}