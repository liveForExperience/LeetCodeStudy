package com.bottomlord.week_038;

/**
 * @author ChenYue
 * @date 2020/3/27 8:14
 */
public class Interview_0502_1_二进制数转字符串 {
    public String printBin(double num) {
        StringBuilder ans = new StringBuilder("0.");
        int time = 0;
        while (num != 0 && time <= 32) {
            num *= 2;
            if (num >= 1) {
                num--;
                ans.append("1");
            } else {
                ans.append("0");
            }
            time++;
        }

        return time > 32 ? "ERROR" : ans.toString();
    }
}
