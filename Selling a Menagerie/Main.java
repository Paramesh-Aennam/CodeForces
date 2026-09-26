import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int t = fr.nextInt();
        PrintWriter out = new PrintWriter(System.out);

        while(t-->0) {
            int n = fr.nextInt();
            int[] a = new int[n];
            int[] c = new int[n];
            for(int i=0 ; i<n ; i++) {
                a[i] = fr.nextInt();
            }
            for(int i=0 ; i<n ;i++) {
                c[i] = fr.nextInt();
            }

            Solution sol = new Solution();
            int[] orderOfSelling = sol.checkHaircutPossibiliy(a, c);
            for(int ani : orderOfSelling) {
                out.print(ani+" ");
            }
            out.println();
        }

        out.flush();
    }
}

class Solution {
    public int[] findOrder(int[] ani, int[] cost) {
        int n = ani.length;
        int[] inDeg = new int[n];
        int[] outDeg = new int[n];
        List<Integer>[] adj = new ArrayList[];
        for(int i=0 ; i<n ; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int u=0 ; u<n ; u++) {
            int v = ani[u] - 1;
            adj[u].add(v);
            inDeg[v]++;
            outDeg[u]++;
        }

        Stack<Integer> nodes = new Stack<>();
        for(int i=0 ; i<n ; i++) {
            if(inDeg[i] == 0) {
                nodes.add(i);
            }
        }
        int nodesCompleted = 0;
        int[] orderOfSelling = new int[n];
        int x = 0;
        while(nodesCompleted < n) {
            int maxNode = -1;
            for(int i=0 ; i<n ; i++) {
                if(nodes.isEmpty()) {
                    if(inDeg[i] != -1) {
                        if(maxNode == -1 || cost[maxNode] < cost[i]) {
                            maxNode = i;
                        }
                    }
                }
            }
            nodes.add(maxNode);
            while(!nodes.isEmpty()) {
                int node = nodes.pop();
                orderOfSelling[x++] = node; 
                nodesCompleted++;
                
                for(int neigh : adj[node]) {
                    inDeg[neigh]--;
                    if(inDeg[neigh] == 0) {
                        inDeg[neigh]--;
                        nodes.add(neigh);
                    }
                }
            }
        }

        return orderOfSelling;
    }
}

class FastReader {
    BufferedReader br;
    StringTokenizer st;

    FastReader() {
        br = new BufferedReader(new InputStreamReader(System.in));
    }

    String next() {
        while(st == null || !st.hasMoreElements()) {
            try {
                st = new StringTokenizer(br.readLine()); 
            } catch (Exception e) {}
        }
        return st.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }
}