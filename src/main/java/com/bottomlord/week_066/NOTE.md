# LeetCode_296_最佳的碰头地点
## 题目
有一队人（两人或以上）想要在一个地方碰面，他们希望能够最小化他们的总行走距离。

给你一个 2D 网格，其中各个格子内的值要么是 0，要么是 1。

1 表示某个人的家所处的位置。这里，我们将使用 曼哈顿距离 来计算，其中 distance(p1, p2) = |p2.x - p1.x| + |p2.y - p1.y|。

示例：
```
输入: 

1 - 0 - 0 - 0 - 1
|   |   |   |   |
0 - 0 - 0 - 0 - 0
|   |   |   |   |
0 - 0 - 1 - 0 - 0

输出: 6 

解析: 给定的三个人分别住在(0,0)，(0,4) 和 (2,2):
     (0,2) 是一个最佳的碰面点，其总行走距离为 2 + 2 + 2 = 6，最小，因此返回 6。
```
## 解法
### 思路
- 遍历二维数组，找到为1的坐标
- 遍历二维数组，计算所有坐标与所有为1坐标的曼哈顿距离之和
- 求所有可能坐标中最小的总和距离
### 代码
```java
class Solution {
    public int minTotalDistance(int[][] grid) {
        int r = grid.length;
        if (r == 0) {
            return 0;
        }
        int c = grid[0].length;
        if (c == 0) {
            return 0;
        }

        List<int[]> points = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    points.add(new int[]{i, j});
                }
            }
        }

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int sum = 0;
                for (int[] point : points) {
                    sum += distance(point[0], point[1], i, j);
                }

                ans = Math.min(ans, sum);
            }
        }

        return ans;
    }

    private int distance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```
# LeetCode_298_二叉树最长连续序列
## 题目
给你一棵指定的二叉树，请你计算它最长连续序列路径的长度。

该路径，可以是从某个初始结点到树中任意结点，通过「父 - 子」关系连接而产生的任意路径。

这个最长连续的路径，必须从父结点到子结点，反过来是不可以的。

示例 1：
```
输入:

   1
    \
     3
    / \
   2   4
        \
         5

输出: 3

解析: 当中，最长连续序列是 3-4-5，所以返回结果为 3
```
示例 2：
```
输入:

   2
    \
     3
    / 
   2    
  / 
 1

输出: 2 

解析: 当中，最长连续序列是 2-3。注意，不是 3-2-1，所以返回 2。
```
## 解法
### 思路
dfs：
- 自顶向下
- 使用3个变量进行递归：
    - `len`记录当前的长度
    - `pre`记录父节点引用
    - `node`记录当前节点引用
- 退出条件：当前节点为null，返回len值
- 过程：判断pre是否为null且pre的值是否与当前节点形成升序，如果是就在len基础上+1，否则就初始化为1
- 返回：当前len与左右子树递归后获得的结果的最大值，再作比较，取最大值返回
### 代码
```java
class Solution {
    public int longestConsecutive(TreeNode root) {
        return dfs(null, root, 0);
    }
    
    private int dfs(TreeNode pre, TreeNode node, int len) {
        if (node == null) {
            return len;
        }
        
        len = (pre != null && pre.val == node.val - 1) ? len + 1 : 1;
        return Math.max(len, Math.max(dfs(node, node.left, len), dfs(node, node.right, len)));
    }
}
```
## 解法二
### 思路
dfs：
- 自底向上
- 使用1个变量：`node`代表当前层节点的指针
- 退出条件：`node`为空时，返回0，说明当前层没有节点，距离就是0
- 过程：
    - 递归左右子树
    - 返回左右子树的距离值，返回的值并不考虑当前层是否与上一层的节点形成升序
    - 在当前层处理时，就需要考虑是否与下一层的节点是否能形成升序，如果能，就累加在这个距离中
    - 所以返回的左右子树的距离，需要分别判断是否与下一层能够相连，如果可以，就在其距离上+1，否则说明当前层和之前层断开，只记录当前层这1个节点的距离值1
    - 之后，将当前层计算完成后，与全局变量ans做比较，更新最大距离
- 返回：当前层，基于左右子树返回的距离，重新计算与当前层节点关系后，生成的新值，并取两者之间的最大值返回
### 代码
```java
class Solution {
    private int ans;
    public int longestConsecutive(TreeNode root) {
        dfs(root);
        return ans;
    }

    private int dfs(TreeNode node) {
        if (node == null) {
            return 0;
        }
        
        int left = dfs(node.left),
            right = dfs(node.right);
        
        left = node.left != null && node.left.val == node.val + 1 ? left + 1 : 1;
        right = node.right != null && node.right.val == node.val + 1 ? right + 1 : 1;
        
        ans = Math.max(ans, Math.max(left, right));
        
        return Math.max(left, right);
    }
}
```
# LeetCode_299_猜数字游戏
## 题目
你在和朋友一起玩 猜数字（Bulls and Cows）游戏，该游戏规则如下：
```
你写出一个秘密数字，并请朋友猜这个数字是多少。
朋友每猜测一次，你就会给他一个提示，告诉他的猜测数字中有多少位属于数字和确切位置都猜对了（称为“Bulls”, 公牛），有多少位属于数字猜对了但是位置不对（称为“Cows”, 奶牛）。
朋友根据提示继续猜，直到猜出秘密数字。
请写出一个根据秘密数字和朋友的猜测数返回提示的函数，返回字符串的格式为 xAyB ，x 和 y 都是数字，A 表示公牛，用 B 表示奶牛。

xA 表示有 x 位数字出现在秘密数字中，且位置都与秘密数字一致。
yB 表示有 y 位数字出现在秘密数字中，但位置与秘密数字不一致。
请注意秘密数字和朋友的猜测数都可能含有重复数字，每位数字只能统计一次。
```
示例 1:
```
输入: secret = "1807", guess = "7810"
输出: "1A3B"
解释: 1 公牛和 3 奶牛。公牛是 8，奶牛是 0, 1 和 7。
```
示例 2:
```
输入: secret = "1123", guess = "0111"
输出: "1A1B"
解释: 朋友猜测数中的第一个 1 是公牛，第二个或第三个 1 可被视为奶牛。
```
```
说明: 你可以假设秘密数字和朋友的猜测数都只包含数字，并且它们的长度永远相等。
```
## 解法
### 思路
哈希表
### 代码
```java
class Solution {
    public String getHint(String secret, String guess) {
        char[] scs = secret.toCharArray(),
               gcs = guess.toCharArray();

        Map<Character, List<Integer>> sMap = new HashMap<>();
        for (int i = 0; i < scs.length; i++) {
            List<Integer> list = sMap.getOrDefault(scs[i], new ArrayList<>());
            list.add(i);
            sMap.put(scs[i], list);
        }

        int bulls = 0, cows = 0;
        for (int i = 0; i < gcs.length; i++) {
            char c = gcs[i];
            if (sMap.containsKey(c) && sMap.get(c).contains(i)) {
                bulls++;
                sMap.get(c).remove(new Integer(i));

                if (sMap.get(c).size() == 0) {
                    sMap.remove(c);
                }
                gcs[i] = '-';
            }
        }

        for (int i = gcs.length - 1; i >= 0; i--) {
            char c = gcs[i];
            if (c == '-') {
                continue;
            }

            if (sMap.containsKey(c)) {
                cows++;
                sMap.get(c).remove(0);

                if (sMap.get(c).size() == 0) {
                    sMap.remove(c);
                }
            }
        }

        return bulls + "A" + cows + "B";
    }
}
```
## 解法二
### 思路
- 题目限制了2个字符串的长度永远相等
- bull的个数就是同位且同值的个数
- cow的个数：
    - 两个字符串在某一个数字上出现次数的最小值，也就是这个数字在两个字符串中同时出现的次数，将这些数字的次数累加
    - 将累加值减去求出bull的个数，也就是总的同时出现的个数 - 同位同值的个数，就得到了同值不同位的个数
### 代码
```java
class Solution {
    public String getHint(String secret, String guess) {
        int[] s = new int[10],
              g = new int[10];
        
        int bull = 0, cow = 0;
        for (int i = 0; i < secret.length(); i++) {
            s[secret.charAt(i) - '0']++;
            g[guess.charAt(i) - '0']++;
            
            bull += secret.charAt(i) == guess.charAt(i) ? 1 : 0;
        }
        
        for (int i = 0; i < s.length; i++) {
            cow += Math.min(s[i], g[i]);
        }
        
        cow -= bull;
        
        return bull + "A" + cow + "B";
    }
}
```