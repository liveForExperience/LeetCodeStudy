package com.bottomlord.contest_20220605;

/**
 * @author chen yue
 * @date 2022-06-05 10:28:32
 */
public class Contest_4_1_设计一个文本编辑器 {
    class TextEditor {
        private StringBuilder post = new StringBuilder(), pre = new StringBuilder();
        int ci = 0;
        public TextEditor() {

        }

        public void addText(String text) {
            pre.append(text);
            ci += text.length();
        }

        public int deleteText(int k) {
            int preLen = pre.length();
            if (k >= pre.length()) {
                pre = new StringBuilder();
                ci -= preLen;
                return preLen;
            }

            pre.delete(preLen - k, preLen);
            ci -= k;
            return k;
        }

        public String cursorLeft(int k) {
            int preLen = pre.length(), move = Math.min(preLen, k);
            String moveStr = pre.substring(preLen - move, preLen);
            pre.delete(preLen - move, preLen);
            post.insert(0, moveStr);
            ci -= move;
            preLen = pre.length();
            int l = Math.min(preLen, 10);

            return pre.substring(preLen - l, preLen);
        }

        public String cursorRight(int k) {
            int postLen = post.length(), move = Math.min(postLen, k);
            String moveStr = post.substring(0, move);
            post.delete(0, move);
            pre.append(moveStr);
            ci += move;
            int preLen = pre.length();
            int l = Math.min(preLen, 10);
            return pre.substring(preLen - l, preLen);
        }
    }
}
