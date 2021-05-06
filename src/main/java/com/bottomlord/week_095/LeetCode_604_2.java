package com.bottomlord.week_095;

import java.util.ArrayList;
import java.util.List;

/**
 * @author ChenYue
 * @date 2021/5/6 15:46
 */
public class LeetCode_604_2 {
    class StringIterator {
        private List<Character> cs;
        private List<Integer> counts;
        private int index;
        public StringIterator(String compressedString) {
            cs = new ArrayList<>();
            counts = new ArrayList<>();
            index = 0;

            for (int i = 0; i < compressedString.length();) {
                char c = compressedString.charAt(i++);
                StringBuilder sb = new StringBuilder();
                while (i < compressedString.length()) {
                    char c1 = compressedString.charAt(i);
                    if (c1 < '0' || c1 > '9') {
                        break;
                    }
                    i++;
                    sb.append(c);
                }

                cs.add(c);
                counts.add(Integer.parseInt(sb.toString()));
            }
        }

        public char next() {
            if (index >= cs.size()) {
                return ' ';
            }
            char c = cs.get(index);
            counts.set(index, counts.get(index) - 1);
            if (counts.get(index) == 0) {
                index++;
            }

            return c;
        }

        public boolean hasNext() {
            return index < cs.size();
        }
    }
}
