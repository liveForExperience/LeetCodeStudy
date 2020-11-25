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