package com.bottomlord.contest_20220403;

import java.util.*;

/**
 * @author chen yue
 * @date 2022-04-03 11:14:44
 */
public class Contest_4_1_加密解密字符串 {
    class Encrypter {
        private String[] values;
        private int[] cindex = new int[26];
        private Map<String, Integer> map;

        public Encrypter(char[] keys, String[] values, String[] dictionary) {
            this.values = values;
            Arrays.fill(cindex, -1);
            for (int i = 0; i < keys.length; i++) {
                char c = keys[i];
                cindex[c - 'a'] = i;
            }

            map = new HashMap<>();
            for (String word : dictionary) {
                String encryptWord = doEncrypt(word);
                map.put(encryptWord, map.getOrDefault(encryptWord, 0) + 1);
            }
        }

        public String encrypt(String word1) {
            return doEncrypt(word1);
        }

        public int decrypt(String word2) {
            return map.getOrDefault(word2, 0);
        }

        private String doEncrypt(String word) {
            StringBuilder sb = new StringBuilder();
            char[] cs = word.toCharArray();
            for (char c : cs) {
                sb.append(values[cindex[c - 'a']]);
            }
            return sb.toString();
        }
    }
}
