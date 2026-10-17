import java.util.*;
class Solution {
    class Node {
        Node[] words;
        int[] count;
        boolean endsOfWord;
        
        public Node() {
            words = new Node[26];
            count = new int[26];
            endsOfWord = false;
        }
    }
    
    void add(Node cur, int idx, String word) {
        int i = word.charAt(idx) - 'a';
        
        if (cur.words[i] == null) {
            cur.words[i] = new Node();
        }
        
        cur.count[i]++;
        
        if(idx == word.length()-1) {
            cur.endsOfWord = true;
            return;
        }
        
        add(cur.words[i], idx+1, word);
    }
    
    int find(Node cur, int idx, String word) {
        if (idx == word.length()-1 || cur.count[word.charAt(idx) - 'a'] == 1) return 0;
        
        return find(cur.words[word.charAt(idx) - 'a'], idx+1, word) + 1;
    }
    
    Node root;
    public int solution(String[] words) {
        int answer = 0;
        int N = words.length;
        
        root = new Node();
        for (int i=0; i<N; i++) {
            add(root, 0, words[i]);
        }
        
        for (int i=0; i<N; i++) {
            answer += find(root, 0, words[i]) + 1;
        }
        
        return answer;
    }
}