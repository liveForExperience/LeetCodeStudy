package com.bottomlord.week_187;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * @author chen yue
 * @date 2023-02-09 08:39:49
 */
public class LeetCode_1797_1_设计一个验证系统 {
    class AuthenticationManager {
        private Map<String, Unit> map;
        private PriorityQueue<Unit> queue;
        private int timeToLive;

        public AuthenticationManager(int timeToLive) {
            this.map = new HashMap<>();
            this.queue = new PriorityQueue<>();
            this.timeToLive = timeToLive;
        }

        public void generate(String tokenId, int currentTime) {
            Unit unit = new Unit(tokenId, currentTime);
            map.put(tokenId, unit);
            queue.offer(unit);
        }

        public void renew(String tokenId, int currentTime) {
            if (!map.containsKey(tokenId)) {
                return;
            }

            Unit unit = map.get(tokenId);
            if (isExpired(unit, currentTime)) {
                map.remove(tokenId);
                queue.remove(unit);
                return;
            }

            queue.remove(unit);
            Unit newUnit = new Unit(tokenId, currentTime);
            queue.offer(newUnit);
            map.put(tokenId, newUnit);
        }

        public int countUnexpiredTokens(int currentTime) {
            while (!queue.isEmpty()) {
                Unit unit = queue.peek();
                if (isExpired(unit, currentTime)) {
                    queue.poll();
                    map.remove(unit.tokenId);
                } else {
                    break;
                }
            }

            return map.size();
        }

        private boolean isExpired(Unit unit, int currentTime) {
            return unit.currentTime + timeToLive <= currentTime;
        }

        private class Unit {
            private String tokenId;
            private Integer currentTime;

            public Unit(String tokenId, Integer currentTime) {
                this.tokenId = tokenId;
                this.currentTime = currentTime;
            }

            public String toString() {
                return tokenId + ":" + currentTime;
            }
        }
    }
}
