package com.bottomlord.week_072;

import java.util.HashMap;
import java.util.Map;

/**
 * @author ChenYue
 * @date 2020/11/27 8:23
 */
public class LeetCode_359_1_日志速率限制器 {
    class Logger {
        private Map<String, Integer> map;
        /** Initialize your data structure here. */
        public Logger() {
            this.map = new HashMap<>();
        }

        /** Returns true if the message should be printed in the given timestamp, otherwise returns false.
         If this method returns false, the message will not be printed.
         The timestamp is in seconds granularity. */
        public boolean shouldPrintMessage(int timestamp, String message) {
            boolean send = true;
            if (map.containsKey(message)) {
                Integer preTimeStamep = map.get(message);
                send = timestamp - preTimeStamep > 10;
            }

            if (send) {
                map.put(message, timestamp);
            }

            return send;
        }
    }
}
