# [LeetCode_517_超级洗衣机](https://leetcode-cn.com/problems/super-washing-machines/)
## 解法
### 思路
- 先计算出平均值，如果不能整除，直接返回-1
- 然后计算每个洗衣机是要流出还是要流入衣服，通过遍历元素减去平均值获得这个差值
- 得到差值的元素数组后，先初始化将元素头尾的值做一个比较，判断这两个元素的最大值作为可能的做小操作数
- 然后再遍历差值元素数组，遍历这个数组的时候，计算的逻辑是：
  - 累加左边所有的差值，如果是正数，说明左边的衣服会流入到右边
  - 如果是负数，就说明右边的衣服会流入到左边
  - 然后以当前这个元素左边标准，就能得到一种操作数的可能值，这个可能值就是左边或右边的流入数，与当前差值元素中的最大值、
  - 然后将所有可能值枚举后，获得其中的最大值
### 代码
```java
class Solution {
  public int findMinMoves(int[] machines) {
    int len = machines.length, sum = Arrays.stream(machines).sum();
    if (sum % len != 0) {
      return -1;
    }

    int avg = sum / len;
    for (int i = 0; i < len; i++) {
      machines[i] = machines[i] - avg;
    }

    int ans = 0, leftSum = 0;
    for (int machine : machines) {
      leftSum += machine;
      int cur = Math.max(Math.abs(leftSum), machine);
      ans = Math.max(ans, cur);
    }

    return ans;
  }
}
```
# [LeetCode_783_二叉搜索树节点最小距离](https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/)
## 解法
### 思路
- 中序搜索获取升序序列
- 遍历升序序列找到最小差值
### 代码
```java
class Solution {
  public int minDiffInBST(TreeNode root) {
    List<Integer> list = new ArrayList<>();
    dfs(root, list);
    int min = Integer.MAX_VALUE;
    for (int i = 1; i < list.size(); i++) {
      min = Math.min(min, list.get(i) - list.get(i - 1));
    }
    return min;
  }

  private void dfs(TreeNode node, List<Integer> list) {
    if (node == null) {
      return;
    }

    dfs(node.left, list);
    list.add(node.val);
    dfs(node.right, list);
  }
}
```
## 解法
### 思路
不使用额外的空间，直接在搜索的过程中记录最小距离
### 代码
```java
class Solution {
  public int minDiffInBST(TreeNode root) {
    Result result = new Result(-1, Integer.MAX_VALUE);
    dfs(root, result);
    return result.min;
  }

  private void dfs(TreeNode node, Result result) {
    if (node == null) {
      return;
    }

    dfs(node.left, result);
    if (result.init) {
      result.init = false;
    } else {
      result.min = Math.min(result.min, node.val - result.pre);
    }
    result.pre = node.val;
    dfs(node.right, result);
  }

  static class Result {
    private boolean init;
    private int pre;
    private int min;
    public Result(int pre, int min) {
      this.pre = pre;
      this.min = min;
      this.init = true;
    }
  }
}
```
# [LeetCode_519_随机翻转矩阵](https://leetcode-cn.com/problems/random-flip-matrix/)
## 解法
### 思路
- 二维变一维
- 随机函数获取随机值，随机值范围是一维数组长度
- set记录翻转过的坐标，重复尝试
### 代码
```java
class Solution {
  private int len;
  private int row;
  private int col;
  private Set<Integer> memo;
  private Random random;

  public Solution(int n_rows, int n_cols) {
    this.len = n_cols * n_rows;
    this.row = n_rows;
    this.col = n_cols;
    this.memo = new HashSet<>();
    this.random = new Random();
  }

  public int[] flip() {
    int r = random.nextInt(len);
    while (memo.contains(r)) {
      r = random.nextInt(len);
    }

    memo.add(r);
    return new int[]{r / col, r % col};
  }

  public void reset() {
    this.memo.clear();
  }
}
```
## 解法二
### 思路
- 解法一为了避免重复会多次调用随机函数
- 为了解决这问题，就需要使求随机数的时候能够避免找到已经使用坐标
- 但又因为坐标使随机的，在使用过一个随机数后，能够使用的坐标数是线性减少的，但这个值是随机，这样就无法直接使用设置bound的方式通过random函数求到随机数，而需要中间做一次映射
- 整个过程可以理解成：
  - 初始化一个随机数和坐标的映射关系map
  - 通过可以使用的坐标个数作为随机数的边界，这个边界会在求解过程中不断-1，求得随机数r
  - 用r在map中查找对应的坐标值，获得的坐标值有2种情况：
    - r值本身，说明这个随机数代表的坐标值没有被翻转过，这是第一次翻转
    - 当前边界+1的值，也就是随机数取不到的值，这个值说明与当前随机数值相等的坐标已经被翻转过，它在上一次翻转后，被赋予了当前这个边界+1的数值
  - 将map中以当前随机值为key的映射所对应的value设置为当前的可用坐标个数，也就是下次随机的边界+1
  - 同时返回获得的坐标所对应的x和y轴的值
### 代码
```java
class Solution {
  private int num;
  private int row;
  private int col;
  private Map<Integer, Integer> map;
  private Random random;
  public Solution(int n_rows, int n_cols) {
    this.num = n_cols * n_rows;
    this.row = n_rows;
    this.col = n_cols;
    this.map = new HashMap<>();
    this.random = new Random();
  }

  public int[] flip() {
    int r = random.nextInt(num--);
    int index = map.getOrDefault(r, r);
    map.put(r, map.getOrDefault(num, num));
    return new int[]{index / col, index % col};
  }

  public void reset() {
    this.map.clear();
    this.num = col * row;
  }
}
```
# [LeetCode_208_实现Trie前缀树](https://leetcode-cn.com/problems/implement-trie-prefix-tree/)
## 解法
### 思路
- 定义字典树结构
  - 长度26的数组存当前字典树节点的子节点
  - 布尔值标识当前节点是否是一个单词的结尾
- 定义两个搜索函数，一个负责插入，一个负责搜索
### 代码
```java
class Trie {
  private TrieNode root;
  /** Initialize your data structure here. */
  public Trie() {
    this.root = new TrieNode();
  }

  /** Inserts a word into the trie. */
  public void insert(String word) {
    doInsert(word, 0, root);
  }

  /** Returns if the word is in the trie. */
  public boolean search(String word) {
    return doSearch(word, 0, root, true);
  }

  /** Returns if there is any word in the trie that starts with the given prefix. */
  public boolean startsWith(String prefix) {
    return doSearch(prefix, 0, root, false);
  }

  private void doInsert(String word, int index, TrieNode node) {
    if (index == word.length()) {
      return;
    }

    int ci = word.charAt(index) - 'a';
    if (node.nodes[ci] == null) {
      node.nodes[ci] = new TrieNode();
    }

    if (index == word.length() - 1) {
      node.nodes[ci].isWord = true;
    }

    doInsert(word, index + 1, node.nodes[ci]);
  }

  private boolean doSearch(String str, int index, TrieNode node, boolean needWord) {
    if (index == str.length()) {
      if (needWord) {
        return node.isWord;
      }

      return true;
    }

    TrieNode curNode = node.nodes[str.charAt(index) - 'a'];
    if (curNode == null) {
      return false;
    }

    return doSearch(str, index + 1, curNode, needWord);
  }
}

class TrieNode {
  public TrieNode[] nodes;
  public boolean isWord;

  public TrieNode() {
    this.nodes = new TrieNode[26];
    this.isWord = false;
  }
}
```
# [LeetCode_522_最长特殊序列II](https://leetcode-cn.com/problems/longest-uncommon-subsequence-ii/)
## 解法
### 思路
- 求出所有的子序列
- 将子序列存储在map中并计数
- 找到所有计数为1的字符串，返回长度最长的
### 代码
```java
class Solution {
  public int findLUSlength(String[] strs) {
    Map<String, Integer> map = new HashMap<>();
    for (String str : strs) {
      Set<String> set = new HashSet<>();
      backTrack(str, 0, new StringBuilder(), set);
      for (String result : set) {
        map.put(result, map.getOrDefault(result, 0) + 1);
      }
    }

    int ans = -1;
    for (String key : map.keySet()) {
      if (map.get(key) == 1) {
        ans = Math.max(ans, key.length());
      }
    }
    return ans;
  }

  private void backTrack(String str, int index, StringBuilder sb, Set<String> set) {
    if (index == str.length()) {
      return;
    }

    for (int i = index; i < str.length(); i++) {
      int len = sb.length();

      sb.append(str.charAt(i));
      if (!set.contains(sb.toString())) {
        set.add(sb.toString());
        backTrack(str, i + 1, sb, set);
      }

      sb.setLength(len);
    }
  }
}
```
## 解法二
### 思路

### 代码
```java

```
# [LeetCode_213_打家劫舍II](https://leetcode-cn.com/problems/house-robber-ii/)
## 解法
### 思路
动态规划：
- 因为头尾是相连的，为了方便计算，可以将数组拆成2种进行计算，然后求最大值：
  - 只包含头，不包含尾
  - 只包含尾，不包含头
- `dp[i]`：`[0,i]`这个范围里能够抢到的最大金额
- 状态转移方程：
  - `dp[i] = max(dp[i - 2] + num[i], dp[i - 1])`
- base case：
  - `dp[0] = nums[0]`
### 代码
```java
class Solution {
  public int rob(int[] nums) {
    int len = nums.length;
    if (len == 0) {
      return 0;
    }

    if (len < 3) {
      return Arrays.stream(nums).max().getAsInt();
    }

    return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
            doRob(Arrays.copyOfRange(nums, 1, len)));
  }

  private int doRob(int[] nums) {
    int[] dp = new int[nums.length];
    dp[0] = nums[0];
    dp[1] = Math.max(nums[0], nums[1]);
    for (int i = 2; i < nums.length; i++) {
      dp[i] = Math.max(dp[i - 2] + nums[i], dp[i - 1]);
    }

    return dp[nums.length - 1];
  }
}
```
## 解法二
### 思路
通过观察解法一发现：在dp状态转移的时候，只用到了dp[i - 2]和dp[i - 1]的值，所以可以通过初始化变量来求解
### 代码
```java
class Solution {
  public int rob(int[] nums) {
    int len = nums.length;
    if (len == 0) {
      return 0;
    }

    if (len < 3) {
      return Arrays.stream(nums).max().getAsInt();
    }

    return Math.max(doRob(Arrays.copyOfRange(nums, 0, len - 1)),
            doRob(Arrays.copyOfRange(nums, 1, len)));
  }

  private int doRob(int[] nums) {
    int len = nums.length;
    int pre2 = nums[0], pre = Math.max(pre2, nums[1]), cur = pre;
    for (int i = 2; i < len; i++) {
      cur = Math.max(pre2 + nums[i], pre);
      pre2 = pre;
      pre = cur;
    }

    return cur;
  }
}
```
# [LeetCode_87_扰乱字符串](https://leetcode-cn.com/problems/scramble-string/)
## 失败解法
### 原因
超时
### 思路
回溯穷举搜索所有可能，并和另一个字符串比较，如果找到就退出
### 代码
```java
class Solution {
  public boolean isScramble(String s1, String s2) {
    if (s1.length() == 1) {
      return Objects.equals(s1, s2);
    }

    for (int i = 1; i < s1.length(); i++) {
      List<String> lefts = dfs(s1.substring(0, i)),
              rights = dfs(s1.substring(i));

      for (String left : lefts) {
        for (String right : rights) {
          if (Objects.equals(left + right, s2) ||
                  Objects.equals(right + left, s2)) {
            return true;
          }
        }
      }
    }

    return false;
  }

  private List<String> dfs(String s1) {
    if (s1.length() == 1) {
      return Collections.singletonList(s1);
    }

    List<String> ans = new ArrayList<>();
    for (int i = 1; i < s1.length(); i++) {
      List<String> lefts = dfs(s1.substring(0, i)),
              rights = dfs(s1.substring(i));

      for (String left : lefts) {
        for (String right : rights) {
          ans.add(left + right);
          ans.add(right + left);
        }
      }
    }

    return ans;
  }
}
```
## 解法
### 思路
动态规划：
- `dp[i][j][l]`：
  - 字符串s1的子字符串起始坐标i，长度为l
  - 字符串s2的子字符串起始坐标j，长度为l
  - 如上2个子字符串的是否互为混乱字符串
- 状态转移方程：
  - 引入一个坐标k用来确定扰乱时候的翻转坐标点
  - `dp[i][j][l] = dp[i][j][k] && dp[i + k][j + k][l - k]`：这段代表当前子字符串无需翻转直接由2个以k为分界线的子子字符串拼接而成
  - ·dp[i][j][l] = dp[i][j + l - k][k] && dp[i + l - k][j][l - k]`：这段代表翻转后的2段子子字符串是互为扰乱的
- base case：将l=1的情况填充好
- 最终返回`dp[0][0][len]`
### 代码
```java
class Solution {
  public boolean isScramble(String s1, String s2) {
    int len1 = s1.length(), len2 = s2.length();
    if (len1 != len2) {
      return false;
    }

    if (len1 == 1) {
      return Objects.equals(s1, s2);
    }

    boolean[][][] dp = new boolean[len1][len1][len1 + 1];
    for (int i = 0; i < len1; i++) {
      for (int j = 0; j < len1; j++) {
        dp[i][j][1] = s1.charAt(i) == s2.charAt(j);
      }
    }

    for (int l = 2; l <= len1; l++) {
      for (int i = 0; i <= len1 - l; i++) {
        for (int j = 0; j <= len2 - l; j++) {
          for (int k = 1; k < l; k++) {
            if ((dp[i][j][k] && dp[i + k][j + k][l - k]) ||
                    (dp[i][j + l - k][k] && dp[i + k][j][l - k])) {
              dp[i][j][l] = true;
              break;
            }
          }
        }
      }
    }

    return dp[0][0][len1];
  }
}
```
# [LeetCode_523_连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)
## 失败解法
### 原因
超时
### 思路
- 求前缀和
- 嵌套2层循环判断总和是否为k的n倍
### 代码
```java
class Solution {
  public boolean checkSubarraySum(int[] nums, int k) {
    int[] sums = new int[nums.length + 1];
    for (int i = 0; i < nums.length; i++) {
      sums[i + 1] = sums[i] + nums[i];
    }

    for (int i = 0; i < nums.length; i++) {
      for (int j = i + 1; j < nums.length; j++) {
        if ((sums[j + 1] - sums[i]) % k == 0) {
          return true;
        }
      }
    }

    return false;
  }
}
```
## 解法
### 思路
- 失败解法的时间复杂度是O(N2)，还是过高了
- 尝试使用hash表将复杂度降低到O(N)
- 题目要求连续子序列的和是k的整数倍
- 当我求出前缀和之后，每个前缀和就代表了一个连续子序列的和，这个和与k进行取余就可以求出，当前和比k的倍数要大多少
- 将这个余数存储下来，这样下次有一个前缀和如果也余相同的值，就代表这两个数一减，就能获得k的整数倍，那么根据前缀和的特性，也就是这两个数之间的数与结尾的数组成的子序列的和是符合要求的
- 基于如上，那么除了要存下余数外，还要记录坐标值，用来计算距离是否满足题目要求的2，同时，当出现有重复的余数，但是距离不满足的时候，不要去覆盖这个坐标
### 代码
```java
class Solution {
  public boolean checkSubarraySum(int[] nums, int k) {
    Map<Integer, Integer> map = new HashMap<>();

    int sum = 0;
    map.put(0, -1);
    for (int i = 0; i < nums.length; i++) {
      sum = (sum + nums[i]) % k;

      if (map.containsKey(sum)) {
        if (i - map.get(sum) > 1) {
          return true;
        }
      } else {
        map.put(sum, i);
      }
    }

    return false;
  }
}
```
# [LeetCode_220_存在重复元素III](https://leetcode-cn.com/problems/contains-duplicate-iii/)
## 解法
### 思路
滑动窗口+有序集合
- 确定滑动窗口后，要确定窗口的范围，我在遍历整个数组的时候，除了考虑当前元素之前的元素，是否还要考虑之后的元素。
  - 因为求的是2个数之间的关系，那么其实当前元素就不用考虑之后的元素，因为当遍历到之后的元素的时候，自然会将当前元素当作那个时候的之前的元素来进行考虑了
  - 所以在处理滑动窗口的时候就只需要考虑当前元素及之前k个元素的范围
- 关于有序集合，题目要求的是2个数的差值的绝对值是否小于等于某个数，那么这个有序数组就需要帮助我快速的找到边界值，此时就可以使用树结构的元素，而因为已经限定的取值的范围，且只需要知道是否有符合的值，所以重复的值是没有意义的，所以最终确定用TreeSet来记录，然后通过ceiling这个API来快速判断边界值
- 整个算法的过程就是：
  - 初始化TreeSet
  - 从0开始遍历数组
  - 判断当前TreeSet中是否存在大于等于nums[i] - t的元素（因为数组元素和t都是大于等于0的，所以最小值一定就是nums[i] - t）
  - 如果有这个值，再判断是否小于等于`nums[i] + t`，如果是就直接返回true
  - 如果没有，则将当前元素加入到set中
  - 然后再判断是否需要将不是当前窗口的元素去除掉，判断的依据就是当前坐标是否大于等于k，因为如果大于了k，那么每次增加一个元素就要去除一个元素，代表窗口的移动
  - 一直遍历整个数组，直到遍历结束，还没有退出函数，就说明结果是false
  - 因为t可能很大，为了避免溢出，用long来存储
### 代码
```java
class Solution {
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Long> set = new TreeSet<>();
        for (int i = 0; i < nums.length; i++) {
            Long ceilNum = set.ceiling((long) nums[i] - t);
            if (ceilNum != null && ceilNum <= (long) nums[i] + t) {
                return true;
            }

            set.add((long) nums[i]);
            if (i >= k) {
                set.remove((long) nums[i - k]);
            }
        }
        return false;
    }
}
```