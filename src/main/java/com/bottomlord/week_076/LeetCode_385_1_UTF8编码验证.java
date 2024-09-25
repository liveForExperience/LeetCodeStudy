package com.bottomlord.week_076;

/**
 * @author ChenYue
 * @date 2020/12/21 8:25
 */
public class LeetCode_385_1_UTF8编码验证 {
    public boolean validUtf8(int[] data) {
        int count = 0;
        for (int num : data) {
            String numBinaryStr = Integer.toBinaryString(num);
            if (numBinaryStr.length() >= 8) {
                numBinaryStr = numBinaryStr.substring(numBinaryStr.length() - 8);
            } else {
                numBinaryStr = "00000000".substring(numBinaryStr.length() % 8) + numBinaryStr;
            }

            if (count == 0) {
                for (char c : numBinaryStr.toCharArray()) {
                    if (c == '0') {
                        break;
                    }

                    count++;
                }

                if (count == 0) {
                    continue;
                }

                if (count > 4 || count == 1) {
                    return false;
                }
            } else {
                if (numBinaryStr.charAt(0) != '1' || numBinaryStr.charAt(1) != '0') {
                    return false;
                }
            }

            count--;
        }

        return true;
    }
}
