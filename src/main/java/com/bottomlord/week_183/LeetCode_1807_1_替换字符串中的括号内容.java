package com.bottomlord.week_183;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author chen yue
 * @date 2023-01-12 08:53:52
 */
public class LeetCode_1807_1_替换字符串中的括号内容 {
    private final Pattern pattern = Pattern.compile("(\\(a-z\\))");
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> map = new HashMap<>();
        for (List<String> str : knowledge) {
            map.put("(" + str.get(0) + ")", str.get(1));
        }

        String regex = "\\([a-z]+\\)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(s);
        StringBuffer stringBuffer = new StringBuffer();
        while (matcher.find()) {
            String substring = s.substring(matcher.start(), matcher.end());
            matcher.appendReplacement(stringBuffer, matcher.group().replace(substring, map.getOrDefault(substring, "?")));
        }
        matcher.appendTail(stringBuffer);
        return stringBuffer.toString();
    }
}
