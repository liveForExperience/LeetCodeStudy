package com.bottomlord.week_041;

import java.util.*;

/**
 * @author ChenYue
 * @date 2020/4/13 8:02
 */
public class LeetCode_355_1_设计推特 {
    class Twitter {
        private Map<Integer, Tweet> map;
        private Map<Integer, Set<Integer>> followers;
        private PriorityQueue<Tweet> maxHeap;
        private int timestamp = 0;

        public Twitter() {
            this.map = new HashMap<>();
            this.followers = new HashMap<>();
            this.maxHeap = new PriorityQueue<>((x1, x2) -> x2.timestamp - x1.timestamp);
        }

        public void postTweet(int userId, int tweetId) {
            Tweet tweet = new Tweet(tweetId, timestamp++);
            if (map.containsKey(userId)) {
                tweet.next = map.get(userId);
            }
            map.put(userId, tweet);
        }

        public List<Integer> getNewsFeed(int userId) {
            maxHeap.clear();
            if (map.containsKey(userId)) {
                maxHeap.offer(map.get(userId));
            }

            if (followers.containsKey(userId)) {
                for (Integer id : followers.get(userId)) {
                    if (map.containsKey(id)) {
                        maxHeap.offer(map.get(id));
                    }
                }
            }

            int count = 0;
            List<Integer> ans = new ArrayList<>();
            while (!maxHeap.isEmpty() && count < 10) {
                Tweet tweet = maxHeap.poll();
                ans.add(tweet.id);

                if (tweet.next != null) {
                    maxHeap.offer(tweet.next);
                }

                count++;
            }

            return ans;
        }

        public void follow(int followerId, int followeeId) {
            if (followeeId == followerId) {
                return;
            }

            Set<Integer> set = followers.getOrDefault(followerId, new HashSet<>());
            set.add(followeeId);
            followers.put(followerId, set);
        }

        public void unfollow(int followerId, int followeeId) {
            if (followers.containsKey(followerId)) {
                followers.get(followerId).remove(followeeId);
            }
        }
    }

    private class Tweet {
        private int id;
        private int timestamp;
        private Tweet next;

        public Tweet(int id, int timestamp) {
            this.id = id;
            this.timestamp = timestamp;
        }
    }
}
