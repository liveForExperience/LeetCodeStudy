package com.bottomlord.week_008;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class LeetCode_535_1_TinyURL的加密与解密 {
    private Map<String, String> map = new HashMap<>();
    private int max = 'z';
    private Random random = new Random();
    public String encode(String longUrl) {
        String key = "";
        int i = 0;
        while (i++ < 5) {
            key += (char) random.nextInt(max);
        }

        while (map.containsKey(key)) {
            key += (char) random.nextInt(max);
        }

        map.put(key, longUrl);
        return "http://" + key;
    }

    public String decode(String shortUrl) {
        return map.get(shortUrl.substring(7));
    }
}
