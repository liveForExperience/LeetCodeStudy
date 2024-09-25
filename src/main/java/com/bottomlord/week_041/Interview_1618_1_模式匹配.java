package com.bottomlord.week_041;

import java.util.Objects;

/**
 * @author ChenYue
 * @date 2020/4/16 13:37
 */
public class Interview_1618_1_模式匹配 {
    public boolean patternMatching(String pattern, String value) {
        if ("".equals(pattern) && "".equals(value)) {
            return true;
        }

        if ("".equals(pattern)) {
            return false;
        }

        if ("".equals(value)) {
            return pattern.length() == 1;
        }

        int aNum = 0, bNum = 0, len = value.length();
        for (char c : pattern.toCharArray()) {
            if (c == 'a') {
                aNum++;
            } else {
                bNum++;
            }
        }

        if (aNum == 0 || bNum == 0) {
            int num = aNum != 0 ? aNum : bNum, numLen = len / num, start = 0;
            String str = null;

            for (int i = 0; i < pattern.length(); i++) {
                if (str == null) {
                    str = value.substring(start, start + numLen);
                } else {
                    if (!Objects.equals(str, value.substring(start, start + numLen))) {
                        return false;
                    }
                }
                start += numLen;
            }

            return true;
        }

        boolean flag = false;
        for (int i = 1; i <= len - bNum; i++) {
            if (len - aNum * i < 0 || (len - aNum * i) % bNum != 0) {
                continue;
            }

            int aLen = i, bLen = (len - aNum * i) / bNum, start = 0;
            String a = null, b = null;
            boolean innerFlag = true;
            for (int j = 0; j < pattern.length(); j++) {
                if (pattern.charAt(j) == 'a') {
                    if (a == null) {
                        a = value.substring(start, start + aLen);
                    } else {
                        if (!Objects.equals(a, value.substring(start, start + aLen))) {
                            innerFlag = false;
                            break;
                        }
                    }
                    start += aLen;
                } else {
                    if (b == null) {
                        b = value.substring(start, start + bLen);
                    } else {
                        if (!Objects.equals(b, value.substring(start, start + bLen))) {
                            innerFlag = false;
                            break;
                        }
                    }
                    start += bLen;
                }
            }

            if (innerFlag && !Objects.equals(a, b)) {
                flag = true;
                break;
            }
        }

        return flag;
    }
}
