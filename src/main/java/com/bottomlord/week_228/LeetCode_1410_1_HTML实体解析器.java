package com.bottomlord.week_228;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * @author chen yue
 * @date 2023-11-23 18:39:25
 */
public class LeetCode_1410_1_HTML实体解析器 {
    public String entityParser(String text) {
        Map<String, String> map = new HashMap<>(6);
        map.put("&quot;", "\"");
        map.put("&apos;", "'");
        map.put("&amp;", "&");
        map.put("&gt;", ">");
        map.put("&lt;", "<");
        map.put("&frasl;", "/");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != '&') {
                sb.append(text.charAt(i));
                continue;
            }

            boolean flag = false;
            for (Map.Entry<String, String> entry : map.entrySet()) {
                int len = entry.getKey().length();
                if (i + len <= text.length() && Objects.equals(entry.getKey(), text.substring(i, i + len))) {
                    sb.append(entry.getValue());
                    i += len - 1;
                    flag = true;
                    break;
                }
            }

            if (!flag) {
                sb.append(text.charAt(i));
            }
        }

        return sb.toString();
    }
}
