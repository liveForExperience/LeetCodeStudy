package com.bottomlord.week_075;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2020/12/17 8:28
 */
public class LeetCode_388_1_文件的最长绝对路径 {
    private int max;
    public int lengthLongestPath(String input) {
        String[] eles = input.split("\n");
        List<Dir> dirs = new ArrayList<>();
        for (String ele : eles) {
            int level = 0;
            StringBuilder sb = new StringBuilder();
            for (char c : ele.toCharArray()) {
                if (c == '\t') {
                    level++;
                } else {
                    sb.append(c);
                }
            }

            dirs.add(new Dir(level, sb.toString()));
        }

        for (int i = 0; i < dirs.size(); i++) {
            if (dirs.get(i).level == 0) {
                backTrack(dirs, i, 0, new StringBuilder());
            }
        }
        return max;
    }

    private void backTrack(List<Dir> dirs, int index, int level, StringBuilder sb) {
        if (index >= dirs.size()) {
            return;
        }

        Dir dir = dirs.get(index++);
        sb.append("/").append(dir.name);
        if (isFile(dir.name)) {
            max = Math.max(max, sb.length());
            return;
        }

        for (int i = index + 1; i < dirs.size(); i++) {
            if (dirs.get(i).level == level) {
                break;
            }

            if (dirs.get(i).level == level + 1) {
                int len = sb.length();
                backTrack(dirs, i, level + 1, sb);
                sb.delete(len, sb.length());
            }
        }
    }

    private boolean isFile(String name) {
        for (char c : name.toCharArray()) {
            if (c == '.') {
                return true;
            }
        }
        return false;
    }

    class Dir {
        private int level;
        private String name;

        public Dir(int level, String name) {
            this.level = level;
            this.name = name;
        }
    }
}
