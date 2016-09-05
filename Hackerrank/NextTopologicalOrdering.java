public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();

        ArrayList<Integer> g[] = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++)
            g[i] = new ArrayList<Integer>();

        int IN[] = new int[n + 1];
        for (int i = 0; i < m; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            g[u].add(v);
            IN[v]++;
        }

        int p[] = new int[n];
        for(int i = 0; i < n; i++) p[i] = in.nextInt();


        int l = -1;
        int c[] = new int[n + 1];

        TreeSet<Integer> set = new TreeSet<Integer>();
        for (int i = 1; i <= n; i++) {
            if (c[i] == IN[i]) {
                set.add(i);
            }
        }

        int pos = 0;

        while(!set.isEmpty()) {
            if (set.last() != p[pos]) {
                l = pos;
            }
            set.remove(p[pos]);
            for (int next : g[p[pos]]) {
                c[next]++;
                if (c[next] == IN[next])
                    set.add(next);
            }
            pos++;
        }


        if (l == -1)
            System.out.println(-1);
        else {
            pos = 0;
            Arrays.fill(c, 0);
            for (int i = 1; i <= n; i++) {
                if (c[i] == IN[i]) {
                    set.add(i);
                }
            }
            while(!set.isEmpty()) {
                int now = -1;
                if (pos == l) now = set.higher(p[pos]);
                else if (pos < l) now = p[pos];
                else now = set.first();

                System.out.print(now + " ");
                set.remove(now);
                for (int next : g[now]) {
                    c[next]++;
                    if (c[next] == IN[next])
                        set.add(next);
                }
                pos++;
            }
            System.out.println();
        }

    }
    public static int[] nextTopologicalOrdering(int n, int[][] edge, int[] order){
      
    }
}
