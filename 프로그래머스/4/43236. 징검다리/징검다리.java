import java.util.*;
class Solution {
    int[] arr;
    public int solution(int distance, int[] rocks, int n) {
        int answer = 0;
        arr = new int[rocks.length+2];

        for (int i=0; i<rocks.length; i++) {
            arr[i] = rocks[i];
        }
        arr[rocks.length] = 0;
        arr[rocks.length+1] = distance;
        Arrays.sort(arr);
        //System.out.println(Arrays.toString(arr));
        
        int l = 0;
        int r = distance;
        while (l <= r) {
            int mid = (l+r)/2;
            System.out.println(mid);
            if (can(mid, n)) {
                l = mid+1;
                answer = Math.max(answer, mid);
            } else {
                r = mid-1;
            }
        }
        
        return answer;
    }
    
    boolean can(int mid, int count) {
        
        for (int i=0; i<arr.length; i++) {
            int j = i+1;
            while(j<arr.length && arr[j] - arr[i] < mid) {
                if (count-- == 0) return false;
                j++;
            }

            i = j-1;
            
        }

        return true;
    }
    
    
    
}