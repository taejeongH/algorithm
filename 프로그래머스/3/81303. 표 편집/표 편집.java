import java.util.*;
class Solution {
    class Node {
        Node down;
        Node up;
        int num;
        
        public Node(int num) {
            this.num = num;
        }
        
        void setDown(Node down) {
            this.down = down;
        }
        
        void setUp(Node up) {
            this.up = up;
        }
    }
    public String solution(int n, int k, String[] cmd) {
        int N = cmd.length;
        Node[] nodes = new Node[n];
        for (int i=0; i<n; i++) {
            nodes[i] = new Node(i);
            
            if(i != 0) {
                nodes[i].setUp(nodes[i-1]);
                nodes[i-1].setDown(nodes[i]);
            }
        }
        
        Stack<Node> removed = new Stack<>();
        Node cur = nodes[k];
        for (int i=0; i<N; i++) {
            char order = cmd[i].charAt(0);
            
            if(order == 'U') {
                int x = Integer.parseInt(cmd[i].substring(2));
                while (x > 0 && cur.up != null) {
                    cur = cur.up;
                    x--;
                }
            } else if (order == 'D') {
                int x = Integer.parseInt(cmd[i].substring(2));
                
                while (x > 0 && cur.down != null) {
                    cur = cur.down;
                    x--;
                }
            } else if (order == 'C') {
                Node up = cur.up;
                Node down = cur.down;
                removed.add(cur);
                if (up == null) {
                    down.up = null;
                    cur = down;
                } else if (down == null) {
                    up.down = null;
                    cur = up;
                } else {
                    up.down = down;
                    down.up = up;
                    cur = down;
                }
            } else {
                Node node = removed.pop();
                
                Node up = node.up;
                Node down = node.down;
                
                if (up == null) {
                    down.up = node;
                } else if (down == null) {
                    up.down = node;
                } else {
                    down.up = node;
                    up.down = node;
                }
            }
            //System.out.println(cur.num);
        }
        
        boolean[] v = new boolean[n];
        while (!removed.isEmpty()) {
            v[removed.pop().num] = true;
        }
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            sb.append(v[i]?"X":"O");
        }

        return sb.toString();
    }
}