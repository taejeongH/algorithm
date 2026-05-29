class Solution {

    boolean can(long num) {
        String bin = Long.toBinaryString(num);

        int len = 1;
        while (len < bin.length()) {
            len = len * 2 + 1;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len - bin.length(); i++) {
            sb.append('0');
        }
        sb.append(bin);

        return check(sb.toString(), 0, len - 1, false);
    }

    boolean check(String s, int left, int right, boolean parentIsZero) {
        if (left > right) return true;

        int mid = (left + right) / 2;
        char cur = s.charAt(mid);

        // 부모가 0인데 현재 노드가 1이면 불가능
        if (parentIsZero && cur == '1') {
            return false;
        }

        boolean nextParentIsZero = parentIsZero || cur == '0';

        return check(s, left, mid - 1, nextParentIsZero)
            && check(s, mid + 1, right, nextParentIsZero);
    }

    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];

        for (int i = 0; i < numbers.length; i++) {
            answer[i] = can(numbers[i]) ? 1 : 0;
        }

        return answer;
    }
}