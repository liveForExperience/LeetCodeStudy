package com.bottomlord.week_114;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author chen yue
 * @date 2021-09-13 11:51:10
 */
public class LeetCode_1656_1_设计有序流 {
    class OrderedStream {
        private String[] strs;
        private int cur;
        public OrderedStream(int n) {
            this.strs = new String[n + 1];
            this.cur = 1;
        }

        public List<String> insert(int idKey, String value) {
            if (idKey != cur) {
                strs[idKey] = value;
                return Collections.emptyList();
            } else {
                strs[idKey] = value;
                List<String> ans = new ArrayList<>();
                for (int i = cur; i < strs.length; i++, cur++) {
                    if (strs[i] != null) {
                        ans.add(strs[i]);
                    } else {
                        break;
                    }
                }
                return ans;
            }
        }
    }
}
