package com.bottomlord.week_057;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/8/7 19:55
 */
public class LeetCode_170_1_两数之和III数据结构设计 {
    class TwoSum {
        private List<Integer> list;
        private boolean ordered;
        /** Initialize your data structure here. */
        public TwoSum() {
            this.list  = new ArrayList<>();
        }

        /** Add the number to an internal data structure.. */
        public void add(int number) {
            this.list.add(number);
            this.ordered = false;
        }

        /** Find if there exists any pair of numbers which sum is equal to the value. */
        public boolean find(int value) {
            if (!ordered) {
                Collections.sort(this.list);
                ordered = true;
            }
            int head = 0, tail = this.list.size() - 1;
            while (head < tail) {
                int sum = list.get(head) + list.get(tail);

                if (sum == value) {
                    return true;
                } else if (sum < value) {
                    head++;
                } else {
                    tail--;
                }
            }

            return false;
        }
    }
}
