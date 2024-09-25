package com.bottomlord.week_143;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-04-10 09:34:55
 */
public class LeetCode_681_1_最近时刻 {
    public String nextClosestTime(String time) {
        int[] arr = new int[4];
        for (int i = 0; i < 5; i++) {
            if (i == 2) {
                continue;
            }

            int index = i < 2 ? i : i - 1;
            arr[index] = getNumFromStr(time, i);
        }

        if (arr[0] == arr[1] && arr[1] == arr[2] && arr[2] == arr[3]) {
            return time;
        }

        Map<String, Integer> map = new HashMap<>();
        TreeMap<Integer, String> treeMap = new TreeMap<>();
        backTrack(0, arr, new StringBuilder(), map, treeMap, parse(time));

        int target = map.get(time);
        return treeMap.get(treeMap.ceilingKey(target + 1));
    }

    private int getNumFromStr(String time, int index) {
        return time.charAt(index) - '0';
    }

    private List<Integer> listCandidateNum(int[] arr, int index, Integer pre) {
        List<Integer> ans = new ArrayList<>();
        for (int num : arr) {
            if (index == 0) {
                if (num <= 2) {
                    ans.add(num);
                }
            }

            if (index == 1) {
                if (pre < 2) {
                    ans.add(num);
                } else {
                    if (num <= 4) {
                        ans.add(num);
                    }
                }
            }

            if (index == 2) {
                if (num <= 5) {
                    ans.add(num);
                }
            }

            if (index == 3) {
                ans.add(num);
            }
        }

        return ans;
    }

    private int parse(String time) {
        String[] times = time.split(":");
        int hour = Integer.parseInt(times[0]), minute = Integer.parseInt(times[1]);
        return hour * 60 + minute;
    }

    private void backTrack(int index, int[] arr, StringBuilder sb, Map<String, Integer> map, TreeMap<Integer, String> treeMap, int target) {
        if (index > 3) {
            String str = sb.substring(0, 2) + ":" + sb.substring(2, 4);
            int num = parse(str);

            if (num < target) {
                num += 60 * 24;
            }

            map.put(str, num);
            treeMap.put(num, str);
            return;
        }

        List<Integer> candidates = listCandidateNum(arr, index, index != 0 ? Integer.parseInt("" + sb.charAt(index - 1)) : null);
        int len = sb.length();
        for (Integer candidate : candidates) {
            sb.append(candidate);
            backTrack(index + 1, arr, sb, map, treeMap, target);
            sb.setLength(len);
        }
    }
}
