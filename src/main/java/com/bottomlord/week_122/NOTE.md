# [LeetCode_299_猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/)
## 解法
### 思路
- 第一次遍历
  - 找到两个字符串完全相等的字母，统计a的个数
  - 记录没有完全相等的字母的个数，按照字母做分类，记录在bucket数组中
- 复制一下bucket数组，记作arr
- 第二次遍历
  - 通过guess中出现的字母，到bucket数组中做累减统计，计算guess中出现的，但位置不同的字母有哪些
- 第三次遍历
  - 将arr的元素与bucket的元素做相减，计算前，如果bucket的元素小于0，记作0。
  - 将相减的值累加，就是b的数值
- 最后将a和b的值用字符串表示返回
### 代码
```java
class Solution {
    public String getHint(String secret, String guess) {
        int n = secret.length(), a = 0, b = 0;
        Set<Integer> set = new HashSet<>();
        int[] bucket = new int[10];
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                set.add(i);
                a++;
                continue;
            }

            bucket[secret.charAt(i) - '0']++;
        }

        int[] arr = Arrays.copyOf(bucket, bucket.length);
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }

            bucket[guess.charAt(i) - '0']--;
        }

        for (int i = 0; i < bucket.length; i++) {
            b += arr[i] - Math.max(bucket[i], 0);
        }

        return a + "A" + b + "B";
    }
}
```
# [LeetCode_1848_到目标元素的最小距离](https://leetcode-cn.com/problems/minimum-distance-to-the-target-element/)
## 解法
### 思路
- 从start坐标先向左再向右遍历
- 分别找到第一个与target值相等的坐标
- 算出坐标与start的距离，返回最小值
### 代码
```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int dis = Integer.MAX_VALUE;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == target) {
                dis = i - start;
                break;
            }
        }
        
        for (int i = start; i >= 0; i--) {
            if (start - i >= dis) {
                break;
            }
            
            if (nums[i] == target) {
                return start - i;
            }
        }
        
        return dis;
    }
}
```
# [LeetCode_488_祖玛游戏](https://leetcode-cn.com/problems/zuma-game/)
## 解法
### 思路
回溯：
- 首先定义一个用于清理现有字符串的函数，通过2层循环将连续3个的字符串消除，且直到不再出现连续的为止
- 主体逻辑里：
  - 首先将手上的珠子通过int数组对不同颜色珠子计数，方便在后续回溯过程中累加和恢复
  - 其次需要设定回溯中的退出条件：
    - 也就是当前字符串消除干净的时候，直接退出
    - 另外还有一种情况就是所有的放置可能都模拟过了，都没办法得到消除干净的效果，这样也就退出了
  - 设定一个减枝条件：也就是当前的步数超过了已经消除干净的最短步数，那么这个路径也就不用搜索了，这也代表了回溯过程中还需要记录当前的步数
  - 回溯主逻辑中，需要对已有珠子进行遍历，值得搜索的珠子有几种情况：
    - 珠子有1个，且手上的珠子超过2个同样颜色的，那么这种情况值得搜索
    - 珠子连续2个，且手上的珠子至少有1个同样颜色的，那么这种情况也值得搜索
    - 珠子连续2个，但是手上的珠子没有同样颜色的，那么这种情况就要寄希望于其他珠子消除以后，这个珠子会联动的消除，那么这种也值得取搜索一下，搜索的方式，就是在这组珠子后面插入所有可能的手上的珠子，然后继续搜索
### 代码
```java
class Solution {
    private int[] bucket;
    private int ans;

    public int findMinStep(String board, String hand) {
        this.ans = Integer.MAX_VALUE;
        this.bucket = new int[26];
        for (char c : hand.toCharArray()) {
            bucket[c - 'A']++;
        }
        backTrack(new StringBuilder(board), 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void backTrack(StringBuilder sb, int step) {
        if (step > ans) {
            return;
        }

        if (sb.length() == 0) {
            ans = step;
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            int j = i;
            while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                j++;
            }

            char c = sb.charAt(i);
            if (i == j && bucket[c - 'A'] >= 2) {
                StringBuilder cur = new StringBuilder(sb);
                cur.insert(i, cur.charAt(i));
                del(cur);
                bucket[c - 'A']--;
                backTrack(cur, step + 1);
                bucket[c - 'A']++;
            } else if (j - i == 1) {
                if (bucket[c - 'A'] >= 1) {
                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i, c);
                    del(cur);
                    bucket[c - 'A']--;
                    backTrack(cur, step + 1);
                    bucket[c - 'A']++;
                    continue;
                }

                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == 0 || k == c - 'A') {
                        continue;
                    }

                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i + 1, (char) (k + 'A'));
                    bucket[k]--;
                    backTrack(cur, step + 1);
                    bucket[k]++;
                }
            }
        }
    }

    private void del(StringBuilder sb) {
        boolean flag = true;
        while (flag) {
            flag = false;

            for (int i = 0; i < sb.length(); i++) {
                int j = i;
                while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                    j++;
                }

                if (j - i >= 2) {
                    flag = true;
                    sb.delete(i, j + 1);
                }
            }
        }
    }
}
```
# [LeetCode_495_提莫攻击](https://leetcode-cn.com/problems/teemo-attacking/)
## 解法
### 思路
- 使用变量end表示中毒窗口的右边界
- 遍历timeSeries，判断time是否在end的右边
  - 如果是：说明新的中毒周期与老的中毒周期没有重叠，直接累加区间即可
  - 如果不是：说明新老周期有重叠，需要剔除掉老周期与新周期重叠的部分再累加
- 同时遍历过程中还要不断更新右边界，已备下一个循环进行判断
### 代码
```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int end = -1, ans = 0;
        for (int time : timeSeries) {
            if (time > end) {
                ans += duration;
            } else {
                ans += time + duration - 1 - end;
            }

            end = time + duration - 1;
        }

        return ans;
    }
}
```
# [LeetCode_375_猜数字大小II](https://leetcode-cn.com/problems/guess-number-higher-or-lower-ii/)
## 解法
### 思路
动态规划：
- 思考过程：
  1. 首先在[1,n]的范围中，要求出最小的花费，这个题目的结构就是在范围中求最值的架构，适合使用dp
  2. 在确定dp后，开始找状态转移方程：
    - 在猜数字的过程中，如果随机选择一个值x，那么基于这个值就会出现3种情况，猜中和选中的值比x大或者小，那么随之而来的问题就变成了，求出[1,x - 1]和[x + 1, n]这2个范围中的较大花费
    - 那么从上面推演的过程，可以得到这个方程：dp[1][n] = x + max(dp[1][x-1], dp[x+1][n])
      - 这里x就是选择x的花费
      - max的原因是，考虑的是猜中至少需要的金钱数，也就说在选择x后在最坏的情况下需要花费的金额
    - 那么进一步的，状态转移方程也就出现了：
      - dp[i][j]（i和j表示范围的左右边界）
      - dp[i][j] = x + max(dp[i][x-1], dp[x+1][j])
  3. 这个得到的状态转移方程，对应的是选择x时猜中一定需要的花费，那么为了得到题目的答案，就需要将所有的可能枚举一次，求出所有可能的值中最小的那个，从而契合题目要求的最小花费的要求
  4. dp的base case可以这么理解，如果范围是1个数字，那么这个花费就是0，因为直接就可以猜中，而如果左边界大于右边界，这种情况不会出现，所以花费也是0，所以i>=j的情况下，花费就是0
### 代码
```java
class Solution {
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int i = n - 1; i >= 1; i--) {
            for (int j = i + 1; j <= n; j++) {
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    int cost = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                    min = Math.min(min, cost);
                }
                dp[i][j] = min;
            }
        }
        return dp[1][n];
    }
}
```
# [LeetCode_677_键值映射](https://leetcode-cn.com/problems/map-sum-pairs/)
## 解法
### 思路
map+字典树
- map存储数值，字典树存储字符串前缀
- insert的时候，分别在map和字典树中存储对应的信息
- sum的时候，通过字典树进行搜索，然后通过map找到对应的数值，并进行累加，之后返回
### 代码
```java
class MapSum {
    private DictTree tree;
    public MapSum() {
        this.tree = new DictTree();
    }

    public void insert(String key, int val) {
        this.tree.insert(key, val);
    }

    public int sum(String prefix) {
        return tree.search(prefix);
    }

    private  class DictTree {
        private Map<String, Integer> mapping;
        private DictNode root;

        public DictTree() {
            this.mapping = new HashMap<>();
            this.root = new DictNode(' ');
        }

        public void insert(String str, Integer val) {
            this.mapping.put(str, val);
            char[] cs = str.toCharArray();
            DictNode node = root;
            for (char c : cs) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new DictNode(c);
                }

                node = node.children[c - 'a'];
            }
        }

        public Integer search(String str) {
            char[] cs = str.toCharArray();
            DictNode node = root;
            for (char c : cs) {
                node = node.children[c - 'a'];
                if (node == null) {
                    return 0;
                }
            }
            return dfs(node, new StringBuilder(str));
        }

        private int dfs(DictNode node, StringBuilder sb) {
            if (node == null) {
                return 0;
            }

            if (node.isEmpty()) {
                return mapping.getOrDefault(sb.toString(), 0);
            }

            int sum = mapping.getOrDefault(sb.toString(), 0);
            DictNode[] children = node.children;
            for (int i = 0; i < 26; i++) {
                DictNode child = children[i];
                int len = sb.length();
                sum += dfs(child, sb.append((char)('a' + i)));
                sb.setLength(len);
            }

            return sum;
        }
    }

    private  class DictNode {
        private char c;
        private DictNode[] children;

        public DictNode(char c) {
            this.c = c;
            this.children = new DictNode[26];
        }

        public boolean isEmpty() {
            for (DictNode child : children) {
                if (child != null) {
                    return false;
                }
            }
            return true;
        }
    }
}
```
# [LeetCode_520_检测大写字母](https://leetcode-cn.com/problems/detect-capital/)
## 解法
### 思路
按情况模拟判断
### 代码
```java
class Solution {
    public boolean detectCapitalUse(String word) {
        if (word.length() == 1) {
            return true;
        }
        
        char first = word.charAt(0);
        boolean firstBig = Character.isUpperCase(first);
        if (firstBig) {
            boolean secondBig = Character.isUpperCase(word.charAt(1));
            if (secondBig) {
                for (int i = 2; i < word.toCharArray().length; i++) {
                    if (Character.isLowerCase(word.charAt(i))) {
                        return false;
                    }
                }
            } else {
                for (int i = 2; i < word.toCharArray().length; i++) {
                    if (Character.isUpperCase(word.charAt(i))) {
                        return false;
                    }
                }
            }
        } else {
            for (int i = 1; i < word.toCharArray().length; i++) {
                if (Character.isUpperCase(word.charAt(i))) {
                    return false;
                }
            }

        }
        return true;
    }
}
```