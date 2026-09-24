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
            boolean flag = checkHaircutPossibiliy(nums, desired, blades);
            out.println(flag ? "Yes" : "No");
        }

        out.flush();
    }
}

class Solution {
    public boolean checkHaircutPossibiliy(int[] heights, int[] desired, int[] blades) {
        int n = heights.length;
        int m = blades.length;

        Stack<Integer> stk = new LinkedList<>();
        Map<Integer, Integer> map = new HashMap<>();

        for(int i=0 ; i<m ; i++) {
            map.put(blades[i], map.getOrDefault(blades[i], 0) + 1);
        }

        for(int i=0 ; i<n ; i++) {
            if(heights[i] < desired[i]) {
                return false;
            }

            while(desired[stk.peek()] < desired[i]) {
                int desiredHeight = stk.pop();
                if(map.getOrDefault(desiredHeight, -1) <= 0) {
                    return false;
                }
                map.put(desired[desiredHeight], map.getOrDefault(stk.pop(), 0) - 1);
            }
            if(desired[i] != heights[i]) {
                stk.push(desired[i]);
            }
        }

        while(!stk.isEmpty()) {
            int desiredHeight = stk.pop();
            if(map.getOrDefault(desiredHeight, -1) <= 0) {
                return false;
            }
            map.put(desired[desiredHeight], map.getOrDefault(stk.pop(), 0) + 1);
        }

        return true;
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