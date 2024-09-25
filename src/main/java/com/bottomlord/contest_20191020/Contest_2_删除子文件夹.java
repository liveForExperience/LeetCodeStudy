package com.bottomlord.contest_20191020;

import java.util.*;

public class Contest_2_删除子文件夹 {
    public List<String> removeSubfolders(String[] folder) {
        List<String> ans = new ArrayList<>();
        Set<String> set = new HashSet<>();
        Arrays.sort(folder);

        for (String dir : folder) {
            String[] strs = dir.split("/");
            StringBuilder sb = new StringBuilder();
            sb.append(strs[0]);
            boolean flag = true;

            for (int i = 1; i < strs.length; i++) {
                sb.append("/").append(strs[i]);

                if (set.contains(sb.toString())) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                ans.add(sb.toString());
                set.add(sb.toString());
            }
        }

        return ans;
    }
}
