package com.bottomlord.week_134;

/**
 * @author chen yue
 * @date 2022-02-06 15:41:57
 */
public class LeetCode_offerII02_1_二进制加法 {
    public String addBinary(String a, String b) {
        int carry = 0;
        char[] csa = a.toCharArray(), csb = b.toCharArray();
        int ai = csa.length - 1, bi = csb.length - 1;

        StringBuilder sb = new StringBuilder();

        while (ai >= 0 || bi >= 0) {
            if (bi < 0) {
                char ca = csa[ai--];
                int curA = ca - '0';
                if (carry != 0) {
                    curA += carry;
                    carry = curA / 2;
                    curA = curA % 2;
                }
                sb.insert(0, curA);
                continue;
            }

            if (ai < 0) {
                char cb = csb[bi--];
                int curB = cb - '0';
                if (carry != 0) {
                    curB += carry;
                    carry = curB / 2;
                    curB %= 2;
                }
                sb.insert(0, curB);
                continue;
            }

            char ca = csa[ai--], cb = csb[bi--];
            int curA = ca - '0', curB = cb - '0', cur = curA + curB + carry;

            carry = cur / 2;
            cur %= 2;

            sb.insert(0, cur);
        }

        if (carry != 0) {
            sb.insert(0, 1);
        }

        return sb.toString();
    }
}
