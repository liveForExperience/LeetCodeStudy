package com.bottomlord.week_7;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

public class LeetCode_125_1_验证回文串 {
    public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = cs.length - 1;
        while (head < tail) {
            if (!Character.isLetter(cs[head]) && !Character.isDigit(cs[head])) {
                head++;
                continue;
            }

            if (!Character.isLetter(cs[tail]) && !Character.isDigit(cs[tail])) {
                tail--;
                continue;
            }

            if (cs[head] == cs[tail]) {
                head++;
                tail--;
                continue;
            }

            if (cs[head] >= 97) {
                cs[head] -= 32;
            }

            if (cs[tail] >= 97) {
                cs[tail] -= 32;
            }

            if (cs[head] != cs[tail]) {
                return false;
            }

            head++;
            tail--;
        }
        return true;
    }

    public static void main(String[] args) {
        LeetCode_125_1_验证回文串 test = new LeetCode_125_1_验证回文串();
        test.isPalindrome("A man, a plan, a canal: Panama");
    }
}
