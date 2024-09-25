package com.bottomlord.week_142;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-03-28 09:17:37
 */
public class LeetCode_635_1_设计日志存储系统 {
    class LogSystem {
        private final int year = 12 * 31 * 24 * 60 * 60,
                        month = 31 * 24 * 60 * 60,
                        day = 24 * 60 * 60,
                        hour = 60 * 60,
                        minute = 60;

        private List<long[]> list;
        private Map<String, Integer> map;
        private String[] template = new String[]{"2000", "1", "1", "0", "0", "0"};
        public LogSystem() {
            list = new ArrayList<>();
            map = new HashMap<>();
            map.put("Year", 0);
            map.put("Month", 1);
            map.put("Day", 2);
            map.put("Hour", 3);
            map.put("Minute", 4);
            map.put("Second", 5);
        }

        public void put(int id, String timestamp) {
            list.add(new long[]{parse(timestamp), (long)id});
        }

        public List<Integer> retrieve(String start, String end, String granularity) {
            long startTime = getByGranularity(start, granularity, false),
                 endTime = getByGranularity(end, granularity, true);

            List<Integer> ans = new ArrayList<>();
            for (long[] arr : list) {
                if (arr[0] >= startTime && arr[0] < endTime) {
                    ans.add((int) arr[1]);
                }
            }

            return ans;
        }

        private long getByGranularity(String time, String granularity, boolean end) {
            String[] factors = time.split(":");
            String yearStr = factors[0], monthStr = factors[1], dayStr = factors[2],
                    hourStr = factors[3], minuteStr = factors[4], secondStr = factors[5];

            int level = map.get(granularity);

            String[] origin = Arrays.copyOf(template, template.length);
            int[] cur = new int[]{Integer.parseInt(yearStr),
                    Integer.parseInt(monthStr),
                    Integer.parseInt(dayStr),
                    Integer.parseInt(hourStr),
                    Integer.parseInt(minuteStr),
                    Integer.parseInt(secondStr)};
            for (int i = 0; i < template.length; i++) {
                if (level >= i) {
                    int num = i == level && end ? cur[i] + 1 : cur[i];
                    origin[i] = "" + num;
                }
            }

            return parse(String.join(":", origin));
        }

        private long parse(String time) {
            String[] factors = time.split(":");
            return Long.parseLong(factors[0]) * year +
                    Long.parseLong(factors[1]) * month +
                    Long.parseLong(factors[2]) * day +
                    Long.parseLong(factors[3]) * hour +
                    Long.parseLong(factors[4]) * minute +
                    Long.parseLong(factors[5]);
        }
    }
}
