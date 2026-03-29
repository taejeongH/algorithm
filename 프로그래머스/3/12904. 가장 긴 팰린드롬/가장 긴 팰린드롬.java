class Solution
{
    String s;
    public int solution(String s)
    {
        this.s = s;
        int answer = 1;
        int N = s.length();
        
        for(int i=0; i<N-1; i++) {
            char c = s.charAt(i);
            for (int j=i+1; j<N; j++) {
                if (c == s.charAt(j)) {
                    if (isFal(i, j)) answer = Math.max(j-i+1, answer);
                }
            }
        }

        return answer;
    }
    
    boolean isFal(int l, int r) {
        while (l<r) {
            if (s.charAt(l)!=s.charAt(r)) return false;
            l++;
            r--;
        }
        return true;
    }
}