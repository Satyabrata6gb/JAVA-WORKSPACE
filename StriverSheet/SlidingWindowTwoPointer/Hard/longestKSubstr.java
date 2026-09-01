package StriverSheet.SlidingWindowTwoPointer.Hard;

import java.util.HashMap;
import java.util.Map;

public class longestKSubstr {

    public static void main(String[] args) {

        Solution solution = new Solution();

        String s1 = "aababbcaacc";
        int k1 = 2;

        int result1 = solution.longestKSubstr(s1, k1);

        System.out.println("Input: " + s1 + ", k = " + k1);
        System.out.println("Output: " + result1);

        System.out.println();

        String s2 = "abcddefg";
        int k2 = 3;

        int result2 = solution.longestKSubstr(s2, k2);

        System.out.println("Input: " + s2 + ", k = " + k2);
        System.out.println("Output: " + result2);
    }
}

class Solution {

    public int longestKSubstr(String s, int k) {

        int maxlength = 0;
        int l = 0;
        int r = 0;

        Map<Character, Integer> hash = new HashMap<>();

        while (r < s.length()) {

            hash.put(s.charAt(r),
                    hash.getOrDefault(s.charAt(r), 0) + 1);

            if (hash.size() > k) {

                hash.put(s.charAt(l),
                        hash.get(s.charAt(l)) - 1);

                if (hash.get(s.charAt(l)) == 0) {
                    hash.remove(s.charAt(l));
                }

                l = l + 1;

            } else {

                maxlength = Math.max(maxlength, r - l + 1);
            }

            r = r + 1;
        }

        return maxlength == 0 ? -1 : maxlength;
    }
}
