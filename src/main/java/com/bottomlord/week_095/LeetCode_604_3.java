package com.bottomlord.week_095;

/**
 * @author ChenYue
 * @date 2021/5/6 16:16
 */
public class LeetCode_604_3 {
    class StringIterator {
        private String str;
        private int index, num;
        Character c;
        public StringIterator(String compressedString) {
            str = compressedString;
        }

        public char next() {
           if (!hasNext()) {
               return ' ';
           }

           if (num == 0) {
               c = str.charAt(index++);
               while (index < str.length()) {
                   char numc = str.charAt(index);
                   if (numc < '0' || numc > '9') {
                       index--;
                       break;
                   }

                   num = num * 10 + (numc - '0');
                   index++;
               }
           }

           num--;
           char ans = c;
           if (num == 0) {
               c = null;
               index++;
           }

           return ans;
        }

        public boolean hasNext() {
            return index < str.length();
        }
    }
}
