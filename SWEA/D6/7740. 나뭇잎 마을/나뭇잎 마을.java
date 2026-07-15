import java.io.*;
import java.util.*;


public class Solution {
	static class Ninja implements Comparable<Ninja>{
		int age;
		int power;
		int idx;
		
		public Ninja(int age, int power, int idx) {
			this.age = age;
			this.power = power;
			this.idx = idx;
		}
		
        @Override
        public int compareTo(Ninja o) {
            int result = Integer.compare(this.age, o.age);

            if (result != 0) {
                return result;
            }

            result = Integer.compare(this.power, o.power);

            if (result != 0) {
                return result;
            }

            return Integer.compare(this.idx, o.idx);
        }
	}
	
	static void update(int start, int end, int pos, int node, int value) {
		if (start > pos || pos > end) return;
		
		if (start == end) {
			tree[node] = Math.max(tree[node], value);
			return;
		}
		
		int mid = (start + end) /2;
		update(start, mid, pos, node * 2, value);
		update(mid+1, end, pos, node*2+1, value);
		tree[node] = Math.max(tree[node*2], tree[node*2+1]);
	}
	
	static int query(int start, int end, int left, int node, int value) {
		if (end < left || tree[node] < value) return -1;
		
		if (start == end) return start;
		
		int mid = (start + end) / 2;
		int result = query(start, mid, left, node*2, value);
		if (result != -1) return result;
		
		return query(mid+1, end, left, node*2+1, value);
	}
	
	
	static Ninja[] ninjas;
	static TreeSet<Ninja> set;
	static int[][] orders;
	static int[] tree;
	static HashMap<Integer, Integer> ageToIndex;
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int tc = Integer.parseInt(br.readLine());
		
		for (int t=1; t<=tc; t++) {
			sb.append("#").append(t).append(" ");
			int N = Integer.parseInt(br.readLine());
			
			set = new TreeSet<>();
			orders = new int[N][3];
			
			ArrayList<Integer> ages = new ArrayList<>();
			int M = 0;
			for (int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				
				String order = st.nextToken();
				if (order.equals("D")) {
					int power = Integer.parseInt(st.nextToken());
					int age = Integer.parseInt(st.nextToken());

					orders[i][0] = 1;
					orders[i][1] = power;
					orders[i][2] = age;
					
					ages.add(age);
					M++;
				} else {
					int num = Integer.parseInt(st.nextToken());
					
					orders[i][0] = 0;
					orders[i][1] = num;
				}
			}
			
			Collections.sort(ages);

            ArrayList<Integer> uniqueAges = new ArrayList<>();

            for (int age : ages) {
                if (uniqueAges.isEmpty() || uniqueAges.get(uniqueAges.size() - 1) != age) {
                    uniqueAges.add(age);
                }
            }

            int ageCount = uniqueAges.size();
            ageToIndex = new HashMap<>();

            for (int i = 0; i < ageCount; i++) {
                ageToIndex.put(uniqueAges.get(i), i);
            }

            ninjas = new Ninja[M + 1];
            set = new TreeSet<>();
            tree = new int[ageCount * 4];
            Arrays.fill(tree, -1);
			
			int idx = 1;
			for (int i=0; i<N; i++) {
				if (orders[i][0] == 1) {
					int power = orders[i][1];
					int age = ageToIndex.get(orders[i][2]);

					Ninja n = new Ninja(age, power, idx);
					ninjas[idx++] = n;
					set.add(n);
					update(0, ageCount-1, age, 1, power);
				} else {
					int num = orders[i][1];
					Ninja cur = ninjas[num];
					
					Ninja ans = null;
					Ninja nxt = set.higher(cur);
					if (nxt!=null && nxt.age == cur.age) {
						ans = nxt;
					} else {
						int age = query(0, ageCount-1, cur.age+1, 1, cur.power);
						
						if(age != -1) {
							Ninja key = new Ninja(age, cur.power, Integer.MIN_VALUE);
							Ninja candidate = set.ceiling(key);
							
							if(candidate != null && candidate.age==age) ans = candidate;
						}
					}
					sb.append(ans != null ? ans.idx : "NE").append(" ");
				}
			}
			sb.append("\n");
		}
		
		System.out.println(sb);
	}
}
