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
            int[] orderOfSelling = sol.findOrder(a, c);
            for(int ani : orderOfSelling) {
                out.print((ani+1)+" ");
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
        List<Integer>[] adj = new ArrayList[n];
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
        // System.out.println(Arrays.toString(inDeg)+": "+nodes);
        while(!nodes.isEmpty()) {
            int node = nodes.pop();
            orderOfSelling[nodesCompleted] = node; 
            nodesCompleted++;
            
            for(int neigh : adj[node]) {
                inDeg[neigh]--;
                if(inDeg[neigh] == 0) {
                    inDeg[neigh]--;
                    nodes.add(neigh);
                }
            }
        }

        for(int i=0 ; i<n ; i++) {
            if(inDeg[i] > 0) {
                int minNode = i;
                nodes.add(minNode);
                inDeg[minNode] = 0;
                
                while(!nodes.isEmpty()) {
                    int node = nodes.pop();
                    if(cost[minNode] > cost[node]) {
                        minNode = node;
                    }

                    for(int neigh : adj[node]) {
                        if(inDeg[neigh] > 0) {
                            inDeg[neigh] = 0;
                            nodes.add(neigh);
                        }
                    }
                }

                int curr = ani[minNode] - 1;
                while(curr != minNode) {
                    orderOfSelling[nodesCompleted++] = curr;
                    curr = ani[curr] - 1;
                }
                orderOfSelling[nodesCompleted++] = minNode;
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