class Solution {
    int N;
    int[] nums;
    boolean[] prime;
    public int solution(int[] nums) {
        this.N = nums.length;
        this.nums = nums;
        
        prime = new boolean[3001];
        for (int i=1; i<=3000; i++) {
            prime[i] = isPrime(i);
        }
        int ans=0;
        for (int i=0; i<N; i++) {
            for (int j=i+1; j<N; j++) {
                for (int k=j+1; k<N; k++) {
                    if (prime[nums[i]+nums[j]+nums[k]]) ans++;
                }
            }
        }
        return ans;
    }
    
    boolean isPrime(int num) {
        for (int i=2; i<num; i++) {
            if (num%i == 0) return false;
        }
        return true;
    }
}