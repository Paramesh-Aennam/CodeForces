import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) {
        FastReader fr = new FastReader();
        int t = fr.nextInt();
        PrintWriter out = new PrintWriter(System.out);

        while(t-->0) {
            int l = fr.nextInt();
            int r = fr.nextInt();

            Solution sol = new Solution();
            int flag = sol.findCountOfBeautifulNums(l, r);
            out.println(flag);
        }

        out.flush();
    }
}

class Solution {
    int MOD = 2520;
    public int findCountOfBeautifulNums(int l, int r) {
        String low = String.valueOf(l);
        String high = String.valueOf(r);

        return memo(high, 0, true, 0, 0) - memo(low, 0, true, 0, 0);
    }

    private int memo(String num, int i, boolean tight, int rem, int lcm) {
        int n = num.length();
        // base
        if(i == n) {
            if((rem%lcm)%2 == 0) {
                return 1;
            }
            return 0;
        }

        int limit = tight ? num.charAt(i)-'0' : 9;
        int noofNums = 0;
        for(int k=0 ; k<=limit ; k++) {
            int newLcm = lcm/(gcd(lcm, k));
            noofNums += (memo(num, i+1, tight && (k==limit), (rem*10 + k)%MOD), newLcm);
        }

        return noofNums;
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);

        while(b != 0) {
            int temp = b;
            b = a%b;
            a = temp;
        }
        return a;
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