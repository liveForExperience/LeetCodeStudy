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
# LeetCode_542_01矩阵
## 题目
给定一个由 0 和 1 组成的矩阵，找出每个元素到最近的 0 的距离。

两个相邻元素间的距离为 1 。

示例 1:
```
输入:

0 0 0
0 1 0
0 0 0
输出:

0 0 0
0 1 0
0 0 0
```
示例 2:
```
输入:

0 0 0
0 1 0
1 1 1
输出:

0 0 0
0 1 0
1 2 1
```
注意:
```
给定矩阵的元素个数不超过 10000。
给定矩阵中至少有一个元素是 0。
矩阵中的元素只在四个方向上相邻: 上、下、左、右。
```
## 解法
### 思路
bfs
- 遍历二维数组获取所有0的坐标
- 将所有为1的坐标改为-1，代表未搜索过
- bfs所有值为0的坐标，如果周边的坐标是-1，则其值为当前坐标的值+1
- 新生成的坐标继续放入队列中，直到队列为空为止
### 代码
```java
class Solution {
    public int[][] updateMatrix(int[][] matrix) {
        Queue<int[]> queue = new ArrayDeque<>();
        int row = matrix.length, col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new int[]{i, j});
                } else {
                    matrix[i][j] = -1;
                }
            }
        }

        int[] dx = new int[]{-1, 1, 0 ,0},
              dy = new int[]{0, 0, -1, 1};
        
        while (!queue.isEmpty()) {
            int[] arr = queue.poll();
            if (arr == null) {
                continue;
            }
            
            for (int i = 0; i < 4; i++) {
                int x = arr[0] + dx[i],
                    y = arr[1] + dy[i];
                
                if (x >= 0 && x < row && y >= 0 && y < col && matrix[x][y] == -1) {
                    matrix[x][y] = matrix[arr[0]][arr[1]] + 1;
                    queue.offer(new int[]{x, y});
                }
            }
        }
        
        return matrix;
    }
}
```
# LeetCode_56_合并区间
## 题目
给出一个区间的集合，请合并所有重叠的区间。

示例 1:
```
输入: [[1,3],[2,6],[8,10],[15,18]]
输出: [[1,6],[8,10],[15,18]]
解释: 区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
```
示例 2:
```
输入: [[1,4],[4,5]]
输出: [[1,5]]
解释: 区间 [1,4] 和 [4,5] 可被视为重叠区间。
```
## 解法
### 思路
- 二维数组排序，根据第一个元素升序，再根据第二个元素升序
- 初始化一个动态数组ans用来存放结果
- 遍历二维数组:
    - 如果ans为空，将当前数组放入ans中
    - 如果ans的最后一个数组的第二个元素小于当前数组的第一个元素，说明不能合并，将当前数组放入ans中
    - 否则，说明可以合并，依据当前数组的第二个元素和ans最后数组的第二个元素的最大值，修改ans中的最后数组的第二个元素
- 遍历结束，转换ans为二维数组并返回
### 代码
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (x1, x2) -> {
            if (x1[0] != x2[0]) {
                return x1[0] - x2[0];
            } else {
                return x1[1] - x2[1];
            }
        });

        List<int[]> ans = new ArrayList<>();
        for (int[] interval : intervals) {
            int l = interval[0], r = interval[1];
            if (ans.size() == 0 || ans.get(ans.size() - 1)[1] < l) {
                ans.add(interval);
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(r, ans.get(ans.size() - 1)[1]);
            }
        }
        
        return ans.toArray(new int[0][0]);
    }
}
```
## 解法二
### 思路
- 遍历二维数组，获取二维数组中的最大值
- 根据最大值生成一个一维数组
- 遍历二维数组，将范围内的数字标记为1
- 遍历一维数组，根据一维数组中的1的范围生成二维数组
### 代码
```java
class Solution {
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][0];
        }
        
        int max = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            max = Math.max(interval[1], max);
        }

        int[] arr = new int[max + 1];
        for (int[] interval : intervals) {
            for (int i = interval[0]; i < interval[1]; i++) {
                arr[i] = 1;
            }

            if (arr[interval[1]] != 1) {
                arr[interval[1]] = -1;
            }
        }

        List<int[]> ans = new ArrayList<>();
        int[] tmp = new int[2];
        boolean flag = false;
        for (int i = 0; i <= max; i++) {
            if ((i == 0 || !flag) && arr[i] == 1) {
                flag = true;
                tmp = new int[2];
                tmp[0] = i;
            }

            if (arr[i] == -1) {
                if (flag) {
                    tmp[1] = i;
                    ans.add(tmp);
                } else {
                    ans.add(new int[]{i, i});
                }
                flag = false;
            }
        }

        return ans.toArray(new int[0][0]);
    }
}
```