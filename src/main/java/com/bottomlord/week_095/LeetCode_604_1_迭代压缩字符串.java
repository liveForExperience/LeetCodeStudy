package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/6 15:13
 */
public class LeetCode_604_1_迭代压缩字符串 {
    class StringIterator {
        private char[] cs;
        private int index;
        public StringIterator(String compressedString) {
            char[] ccs = compressedString.toCharArray();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < ccs.length;) {
                char c = ccs[i++];
                StringBuilder numSb = new StringBuilder();
                for (; i < ccs.length; i++) {
                    if (ccs[i] > '9' || ccs[i] < '0') {
                        break;
                    }

                    numSb.append(ccs[i]);
                }

                for (int j = 0; j < Integer.parseInt(numSb.toString()); j++) {
                    sb.append(c);
                }
            }

            cs = sb.toString().toCharArray();

            index = 0;
        }

        public char next() {
            return index >= cs.length ? ' ' : cs[index++];
        }

        public boolean hasNext() {
            return index < cs.length;
        }
    }
}
