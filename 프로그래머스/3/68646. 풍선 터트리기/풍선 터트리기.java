class Solution {
    
    void init(int[]map, int start, int end, int node) {
        if (start > end) return;
        if (start==end) {
            tree[node] = map[start];
            return;
        }
        int mid = (start+end)/2;
        init(map, start, mid, node*2);
        init(map, mid+1, end, node*2+1);
        tree[node] = Math.min(tree[node*2], tree[node*2+1]);
    }
    
    int query(int left, int right, int start, int end, int node) {
        if (end < left || start > right) return INF;
        if (left <= start && end <= right) return tree[node];
        
        int mid = (start+end)/2;
        return Math.min(query(left, right, start, mid, node*2), query(left, right, mid+1, end, node*2+1));
    }
    
    int[] tree;
    final int INF = 1_000_000_000;
    
    public int solution(int[] a) {
        int N = a.length;
        int answer = N;
        tree = new int[N*4];
        
        
        init(a, 0, N-1, 1);
        for (int i=1; i<=N; i++) {
            if (query(1, i, 1, N, 1)<a[i-1] 
                && query(i+1, N, 1, N, 1)<a[i-1]) {
                answer--;
            }
        }
        return answer;
    }
}