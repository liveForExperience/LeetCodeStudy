# [LeetCode_353_贪吃蛇](https://leetcode-cn.com/problems/design-snake-game/)
## 解法
### 思路
定义一个node类模拟贪吃蛇的身体单元，模拟移动的过程
### 代码
```java
class SnakeGame {
    private Node head, tail;
    private int width, height, score, index;
    private Map<String, int[]> directionMap;
    private int[][] food;
    public SnakeGame(int width, int height, int[][] food) {
        this.head = new Node(0, 0);
        this.tail = head;
        this.width = width;
        this.height = height;
        this.score = 0;
        this.directionMap = new HashMap<>();
        this.directionMap.put("U", new int[]{-1, 0});
        this.directionMap.put("D", new int[]{1, 0});
        this.directionMap.put("L", new int[]{0, -1});
        this.directionMap.put("R", new int[]{0, 1});
        this.food = food;
        this.index = 0;
    }

    /** Moves the snake.
     @param direction - 'U' = Up, 'L' = Left, 'R' = Right, 'D' = Down
     @return The game's score after the move. Return -1 if game over.
     Game over when snake crosses the screen boundary or bites its body. */
    public int move(String direction) {
        int[] foodIndex = index >= food.length ? new int[]{-1,-1} : food[index];
        int[] move = directionMap.get(direction);

        int newX = head.x + move[0], newY = head.y + move[1];
        if (newX < 0 || newX >= height || newY < 0 || newY >= width) {
            return -1;
        }

        boolean remainTail = foodIndex[0] == newX && foodIndex[1] == newY;

        Node cur = remainTail ? tail : tail.next;
        while (cur != null) {
            if (cur.x == newX && cur.y == newY) {
                return -1;
            }

            cur = cur.next;
        }

        Node newHead = new Node(head.x + move[0], head.y + move[1]);
        head.next = newHead;
        head = newHead;

        if (remainTail) {
            score++;
            index++;
        } else {
            Node tailNext = tail.next;
            tail.next = null;
            tail = tailNext;
        }

        return score;
    }

    private class Node {
        private int x;
        private int y;
        private Node next;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
```
# [LeetCode_354_俄罗斯套娃信封问题](https://leetcode-cn.com/problems/russian-doll-envelopes/)
## 解法
### 思路
- 根据宽度升序，高度降序的原则对envelopes进行排序
- 然后根据降序的高度进行动态规划，求最长升序子序列（因为宽度是升序的，所以根据高度求出的升序序列其宽度也一定是升序的，满足题目要求）
- dp过程：
    - `dp[i]`：`[0,i]`范围子序列中最长的升序序列长度
    - 状态转移方程：
        - `nums[i] > nums[j]`：`dp[i] = dp[j] + 1`
        - 遍历`[0,i]`，查看是否存在满足`nums[i] > nums[j]`的情况，然后求出在这个区间内的最大值，将这个最大值`max + 1`赋给`dp[i]`
    - base case：`dp[0] = 1`，信封个数至少为1（除非没有信封的特殊情况）
### 代码
```java
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }
        
        Arrays.sort(envelopes, (o1, o2) -> {
            if (o1[0] == o2[0]) {
                return o2[1] - o1[1];
            }
            return o1[0] - o2[0];
        });

        int[] dp = new int[envelopes.length];
        int ans = 0;
        for (int i = 0; i < envelopes.length; i++) {
            int max = 0;
            for (int j = 0; j < i; j++) {
                if (envelopes[i][1] > envelopes[j][1]) {
                    max = Math.max(max, dp[j]);
                }
            }
            dp[i] = max + 1;
            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}
```
## 解法二
### 思路
使用[lis扑克牌二分查找](https://github.com/labuladong/fucking-algorithm/blob/master/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E7%B3%BB%E5%88%97/%E5%8A%A8%E6%80%81%E8%A7%84%E5%88%92%E8%AE%BE%E8%AE%A1%EF%BC%9A%E6%9C%80%E9%95%BF%E9%80%92%E5%A2%9E%E5%AD%90%E5%BA%8F%E5%88%97.md)，替换解法一的dp查找方法：
- 初始化变量：
    - 牌堆顶元素序列：top
    - 牌堆数：piles，初始化为0
- 遍历待查数组
- 以牌堆个数作为右边界
- 以当前遍历到的元素作为标准，与，以查找左边界的方法进行二分查找
- 如果最后获得的坐标是一开始的有边界，说明没有找到元素，那么当前这个元素单独成为一个牌堆，牌堆数+1
- 所有元素遍历结束后，牌堆数相当于最大升序序列
### 代码
```java
class Solution {
    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0) {
            return 0;
        }

        Arrays.sort(envelopes, (x, y) -> {
            if (x[0] == y[0]) {
                return y[1] - x[1];
            }

            return x[0] - y[0];
        });

        int piles = 0;
        int[] top = new int[envelopes.length];
        for (int[] envelope : envelopes) {
            int num = envelope[1];
            int left = 0, right = piles;
            while (left < right) {
                int mid = left + (right - left) / 2;

                if (top[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            if (left == piles) {
                piles++;
            }

            top[left] = num;
        }

        return piles;
    }
}
```
# [LeetCode_1370_上升下降字符串](https://leetcode-cn.com/problems/increasing-decreasing-string/)
## 解法
### 思路
桶计数，升序倒叙的遍历26个字符，直到新生成的字符串和原字符串一样长
### 代码
```java
class Solution {
    public String sortString(String s) {
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        StringBuilder sb = new StringBuilder();
        while (sb.length() < s.length()) {
            for (int i = 0; i < 26; i++) {
                if (count[i] > 0) {
                    sb.append((char)(i + 'a'));
                    count[i]--;
                }
            }
            
            for (int i = 25; i >= 0; i--) {
                if (count[i] > 0) {
                    sb.append((char)(i + 'a'));
                    count[i]--;
                }
            }
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_356_直线镜像](https://leetcode-cn.com/problems/line-reflection/)
## 解法
### 思路
- 找到序列中x轴上值的最大最小值，并计算其中间值作为平行于y轴的中分线
- 遍历所有点，生成镜像点并放入map中，key为x值，value为set，存y值
- 再遍历所有点，找到map中是否存在镜像值，如果不存在就返回false
### 代码
```java
class Solution {
    public boolean isReflected(int[][] points) {
        if (points.length == 0) {
            return true;
        }

        int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
        for (int[] point : points) {
            min = Math.min(min, point[0]);
            max = Math.max(max, point[0]);
        }

        double mid = (max + min) / 2D;

        Map<Double, Set<Integer>> map = new HashMap<>();
        for (int[] point : points) {
            Set<Integer> set = map.getOrDefault((double) point[0], new HashSet<>());
            set.add(point[1]);
            map.put((double) point[0], set);
        }

        for (int[] point : points) {
            double target = mid * 2 - point[0];

            if (!map.containsKey(target)) {
                return false;
            }

            if (!map.get(target).contains(point[1])) {
                return false;
            }
        }

        return true;
    }
}
```