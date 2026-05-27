import java.util.*;

class Solution {
    
    public int[] solution(String[] gems) {
        int N = gems.length;
        
        HashMap<String, Integer> map = new HashMap<>();
        int[] arr = new int[N];
        
        int idx = 0;
        for (int i=0; i<N; i++) {
            if (!map.containsKey(gems[i])) map.put(gems[i], idx++);
            
            arr[i] = map.get(gems[i]);
        }
        
        int l = 0;
        int r = 0;
        
        int resl = 0;
        int resr = 0;
        int minSize = 1_000_000_000;
        
        int[] v = new int[idx];
        
        while (r < N) {
            if (v[arr[r]] == 0) idx--;
            v[arr[r]]++;
            r++;
            
            while (v[arr[l]] > 1) {
                v[arr[l]]--;
                l++;
            }
            
            

            
            
            if (idx == 0) {
                if (minSize > r-l) {
                    minSize = r-l;
                    resl = l;
                    resr = r;
                }
            }
        }
        
//         while (v[arr[l]] > 1) {
//             l++;
//             v[arr[l]]--;
//         }
        
//         if (minSize > r-l) {
//             minSize = r-l;
//             resl = l;
//             resr = r;
//         }
        
        
        return new int[] {resl+1, resr};
    }
}