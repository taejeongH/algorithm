import java.util.*;
class Solution {
    public int solution(int N, int number) {
        
        Set<Integer>[] arr = new Set[9];
        for (int i=0; i<=8; i++) arr[i] = new HashSet<>();
        
        for (int i=1; i<=8; i++) {
            int repeated = N;
            
            for (int j=1; j<i; j++) {
                repeated *= 10;
                repeated += N;
            }
            
            arr[i].add(repeated);
            
            for (int left=1; left<i; left++) {
                int right = i - left;
                
                for (int l : arr[left]) {
                    for (int r : arr[right]) {
                        arr[i].add(l + r);
                        arr[i].add(l * r);
                        arr[i].add(l - r);
                        
                        if (r!=0) arr[i].add(l/r);
                    }
                }
            }
            
            if (arr[i].contains(number)) return i;
        }
        
        return -1;
    }
}