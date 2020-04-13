# LeetCode_355_设计推特
## 题目
设计一个简化版的推特(Twitter)，可以让用户实现发送推文，关注/取消关注其他用户，能够看见关注人（包括自己）的最近十条推文。你的设计需要支持以下的几个功能：
```
postTweet(userId, tweetId): 创建一条新的推文
getNewsFeed(userId): 检索最近的十条推文。每个推文都必须是由此用户关注的人或者是用户自己发出的。推文必须按照时间顺序由最近的开始排序。
follow(followerId, followeeId): 关注一个用户
unfollow(followerId, followeeId): 取消关注一个用户
```
示例:
```
Twitter twitter = new Twitter();

// 用户1发送了一条新推文 (用户id = 1, 推文id = 5).
twitter.postTweet(1, 5);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
twitter.getNewsFeed(1);

// 用户1关注了用户2.
twitter.follow(1, 2);

// 用户2发送了一个新推文 (推文id = 6).
twitter.postTweet(2, 6);

// 用户1的获取推文应当返回一个列表，其中包含两个推文，id分别为 -> [6, 5].
// 推文id6应当在推文id5之前，因为它是在5之后发送的.
twitter.getNewsFeed(1);

// 用户1取消关注了用户2.
twitter.unfollow(1, 2);

// 用户1的获取推文应当返回一个列表，其中包含一个id为5的推文.
// 因为用户1已经不再关注用户2.
twitter.getNewsFeed(1);
```
## 解法
### 思路
哈希表+单向链表+优先级队列：
- 哈希表+单向链表存储用户与推文的映射关系，链表头存放最新的推文
- 哈希表+set存放关注者和被关注者集合
- 使用优先级队列获取用户所有可见推文的前10位的集合
### 代码
```java
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
```
# Interview_1616_部分排序
## 题目
给定一个整数数组，编写一个函数，找出索引m和n，只要将索引区间[m,n]的元素排好序，整个数组就是有序的。注意：n-m尽量最小，也就是说，找出符合条件的最短序列。函数返回值为[m,n]，若不存在这样的m和n（例如整个数组是有序的），请返回[-1,-1]。

示例：
```
输入： [1,2,4,7,10,11,7,12,6,7,16,18,19]
输出： [3,9]
```
提示：
```
0 <= len(array) <= 1000000
```
## 解法
### 思路
遍历+双指针：
- 序列除了部分乱序，其他都是默认升序的
- 将整个序列分成3部分看待：
    - 左侧升序
    - 中间乱序
    - 右侧升序
- 两个指针分别从头和从尾开始遍历整个数组：
    - 从头的指针：
        - 记录当前遍历到的最大元素
        - 判断当前元素是否比最大元素小，因为如果是升序，那么应该每个新的元素都是最大元素，所以这个不是的元素就是需要被排序的最右边的元素
    - 从尾的指针：
        - 记录当前遍历到的最小元素
        - 判断当前元素是否比最小元素大，因为是升序序列，从尾部开始遍历，每个新的元素应该都是最小元素，所以这个不是的元素就是需要被排序的最左侧的元素
- 将记录的最左侧和最右侧的元素放入数组中返回即可
### 代码
```java
class Solution {
    public int[] subSort(int[] array) {
        int len = array.length, max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, left = -1, right = -1;
        for (int i = 0; i < len; i++) {
            if (array[i] < max) {
                right = i;
            } else {
                max = Math.max(max, array[i]);
            }
        }
        
        for (int i = len - 1; i >= 0; i--) {
            if (array[i] > min) {
                left = i; 
            } else {
                min = Math.min(min, array[i]);
            }
        }
        
        return new int[]{left, right};
    }
}
```