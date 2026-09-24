public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int t = fr.nextInt();
        PrintWriter out = new PrintWriter(System.out);

        while(t-->0) {
            int n = fr.nextInt();
            int[] nums = new int[n];
            int[] desired = new int[n];
            for(int i=0 ; i<n ; i++) {
                nums[i] = fr.nextInt();
            }
            for(int i=0 ; i<n ;i++) {
                desired[i] = fr.nextInt();
            }

            int m = fr.nextInt();
            int[] blades = new int[m];
            for(int i=0 ; i<m ; i++) {
                blades[i] = fr.nextInt();
            }
            boolean flag = checkHaircutPossibiliy(nums, desired, blades);
            out.println(flag);
        }

        out.flush();
    }
}

class Solution {
    public boolean checkHaircutPossibiliy(int[] heights, int[] desired, int[] blades) {
        int n = heights.length;
        int m = blades.length;

    }
}

class FastReader {
    BufferedRaeder br;
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