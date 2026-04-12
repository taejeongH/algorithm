import java.util.*;

class Solution {
    boolean[] used;
    String[] answer;
    List<String> path = new ArrayList<>();

    public String[] solution(String[][] tickets) {
        Arrays.sort(tickets, (a, b) -> {
            if (a[0].equals(b[0])) {
                return a[1].compareTo(b[1]);
            }
            return a[0].compareTo(b[0]);
        });

        used = new boolean[tickets.length];
        path.add("ICN");

        dfs("ICN", tickets, 0);

        return answer;
    }

    boolean dfs(String cur, String[][] tickets, int depth) {
        if (depth == tickets.length) {
            answer = path.toArray(new String[0]);
            return true;
        }

        for (int i = 0; i < tickets.length; i++) {
            if (used[i]) continue;
            if (!tickets[i][0].equals(cur)) continue;

            used[i] = true;
            path.add(tickets[i][1]);

            if (dfs(tickets[i][1], tickets, depth + 1)) {
                return true;
            }

            path.remove(path.size() - 1);
            used[i] = false;
        }

        return false;
    }
}