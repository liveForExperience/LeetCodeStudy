package com.bottomlord.week_110;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chen yue
 * @date 2021-08-20 08:42:12
 */
public class LeetCode_1507_1_转变日期格式 {
    private Map<String, String> map = new HashMap<>();
    private String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    {
        for (int i = 0; i < months.length; i++) {
            map.put(months[i], i < 9 ? "0" + (i + 1) : "" + (i + 1));
        }
    }

    public String reformatDate(String date) {
        String[] factors = date.split(" ");

        String dayStr = factors[0], monthStr = factors[1], yearStr = factors[2];

        return yearStr + "-" + map.get(monthStr) + "-" + getDay(dayStr);
    }

    private String getDay(String dayStr) {
        return dayStr.length() == 3 ? "0" + dayStr.charAt(0) : dayStr.substring(0, 2);
    }
}
