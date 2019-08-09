package com.bottomlord.week_5;

public class LeetCode_67_1_二进制的求和 {
    public String addBinary(String a, String b) {
        StringBuilder sb = new StringBuilder();

        int iA = a.length() - 1;
        int iB = b.length() - 1;

        int carry = 0;

        while (iA > -1 || iB > -1) {
            int aBit = iA > -1 ? a.charAt(iA) - '0' : 0;
            int bBit = iB > -1 ? b.charAt(iB) - '0' : 0;

            int sum = aBit + bBit + carry;

            sb.insert(0, sum & 1);

            carry = (sum >>> 1) & 1;

            iA--;
            iB--;
        }

        if (carry == 1) {
            sb.insert(0, carry);
        }

        return sb.toString();
    }
}
