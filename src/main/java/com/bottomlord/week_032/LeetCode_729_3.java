package com.bottomlord.week_032;

/**
 * @author ThinkPad
 * @date 2020/2/12 8:22
 */
public class LeetCode_729_3 {
    class MyCalendar {
        private CalendarNode root;
        public MyCalendar() {}

        public boolean book(int start, int end) {
            if (root == null) {
                root = new CalendarNode(start, end);
                return true;
            }

            return dfs(root, start, end);
        }

        private boolean dfs(CalendarNode node, int start, int end) {
            if (end <= node.start) {
                if (node.left == null) {
                    node.left = new CalendarNode(start, end);
                    return true;
                } else {
                    return dfs(node.left, start, end);
                }
            }

            if (start >= node.end) {
                if (node.right == null) {
                    node.right = new CalendarNode(start, end);
                    return true;
                } else {
                    return dfs(node.right, start, end);
                }
            }

            return false;
        }
    }
}
