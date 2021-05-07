package com.bottomlord.week_095;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/5/7 10:25
 */
public class LeetCode_751_1_IP到CIDR {
    public List<String> ipToCIDR(String ip, int n) {
        int start = toInt(ip);
        List<String> ans = new ArrayList<>();
        while (n > 0) {
            int tailingZeros = start == 0 ? 0 : Integer.numberOfTrailingZeros(start);
            int bitsInCidr = 1, mask = 0;
            while (bitsInCidr < n && mask < tailingZeros) {
                bitsInCidr <<= 1;
                mask++;
            }

            if (bitsInCidr > n) {
                bitsInCidr >>= 1;
                mask--;
            }

            ans.add(toString(start, 32 - mask));
            start += bitsInCidr;
            n -= bitsInCidr;
        }

        return ans;
    }

    private String toString(int start, int range) {
        int wordLen = 8;
        StringBuilder sb = new StringBuilder();
        for (int i = 3; i >= 0; i--) {
            sb.append((start >> (i * wordLen)) & 255);
            sb.append(".");
        }

        sb.setLength(sb.length() - 1);
        return sb.append("/").append(range).toString();
    }

    private int toInt(String str) {
        String[] words = str.split("\\.");
        int ans = 0;
        for (String word : words) {
            ans = ans * 256 + Integer.parseInt(word);
        }
        return ans;
    }
}
