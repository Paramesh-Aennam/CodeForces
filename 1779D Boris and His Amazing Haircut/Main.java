import java.util.*;
import java.io.*;

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
            Solution sol = new Solution();
            boolean flag = sol.checkHaircutPossibiliy(nums, desired, blades);
            out.println(flag ? "Yes" : "No");
        }

        out.flush();
    }
}

class Solution {
    public boolean checkHaircutPossibiliy(int[] heights, int[] desired, int[] blades) {
        int n = heights.length;
        int m = blades.length;

        Stack<Integer> stk = new Stack<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0 ; i<m ; i++) {
            map.put(blades[i], map.getOrDefault(blades[i], 0) + 1);
        }
        // System.out.println(map);
        
        for(int i=0 ; i<n ; i++) {
            if(heights[i] < desired[i]) {
                return false;
            }
            
            while(!stk.isEmpty() && desired[stk.peek()] < desired[i]) {
                int desiredHeight = desired[stk.pop()];
                if(map.getOrDefault(desiredHeight, -1) <= 0) {
                    // System.out.println(i+", "+desiredHeight+": "+map);
                    return false;
                }
                map.put(desiredHeight, map.getOrDefault(desiredHeight, 0) - 1);
            }
            if(desired[i] != heights[i] && (stk.isEmpty() || desired[stk.peek()] != desired[i])) {
                stk.push(i);
            }
            // System.out.println(stk);
        }
        
        while(!stk.isEmpty()) {
            int desiredHeight = desired[stk.pop()];
            if(map.getOrDefault(desiredHeight, -1) <= 0) {
                // System.out.println(map);
                return false;
            }
            map.put(desiredHeight, map.getOrDefault(desiredHeight, 0) + 1);
        }

        return true;
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