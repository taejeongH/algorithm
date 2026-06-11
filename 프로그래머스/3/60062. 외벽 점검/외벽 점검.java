import java.util.*;
class Solution {
    static int[] weak, dist;
    static int N, M, wallLength;
    
    static final int INF = 1_000_000_000;
    public int solution(int n, int[] weak, int[] dist) {
        int answer = 0;
        N = dist.length;
        M = weak.length;
        wallLength = n;
        
        Arrays.sort(dist);
        
        this.weak = weak;
        this.dist = dist;
        
        for (int i=0; i<N; i++) {
            List<Integer>[] group = new List[i+1];
            for (int j=0; j<i+1; j++) group[j] = new ArrayList<>();
            
            if (dfs(group, 0)) return i+1;
        }
        return -1;
    }
    
    boolean dfs(List<Integer>[] group, int idx) {
        if (idx == M) return true;
        
        for (int i=0; i<group.length; i++) {
            int d = dist[N - i - 1];

            if (canAdd(group[i], weak[idx], d)) {
                group[i].add(weak[idx]);
                if (dfs(group, idx + 1)) return true;
                group[i].remove(group[i].size() - 1);
            }
        }
        
        return false;
    }
    
    

    boolean canAdd(List<Integer> group, int point, int d) {
        List<Integer> points = new ArrayList<>(group);
        points.add(point);

        if (points.size() <= 1) return true;

        Collections.sort(points);

        int maxGap = 0;

        for (int i = 0; i < points.size() - 1; i++) {
            maxGap = Math.max(maxGap, points.get(i + 1) - points.get(i));
        }

        int last = points.get(points.size() - 1);
        int first = points.get(0);

        // 원형으로 이어지는 구간
        maxGap = Math.max(maxGap, first + wallLength - last);

        // 가장 큰 빈 구간을 제외한 나머지 길이
        int needDist = wallLength - maxGap;

        return needDist <= d;
    }
    
}