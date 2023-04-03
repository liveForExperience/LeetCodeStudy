# [LeetCode_1012_至少有1位重复的数字](https://leetcode.cn/problems/numbers-with-repeated-digits/)
## 解法
### 思路
- 题目要求得到至少有1位重复的数字，可以通过总数-没有重复的数字，得到这个结果
- 通过数位dp来记录当前数字中数位出现的状态
- 通过递归来搜索所有可能性，并通过dp记事本来提前减枝
- 递归的过程中要考虑2个问题
  - 当前生成的数是否大于n，这个可以通过一个标记isLimit来标识
    - 如果为true，说明当前的元素最大值只能取到n当前数位上的最大值
    - 如果为false，说明当前的元素最大可以取到9，没有限制
  - 当前数位之前是否有有数字
    - 如果为true，说明之前已经开始取数作为数位上的值了
    - 如果为false，说明之前的数位都是跳过的，那么当前的数位其实也可以跳过
### 代码
```java
class Solution {
  private String s;
  private int[][] memo;

  public int numDupDigitsAtMostN(int n) {
    this.s = Integer.toString(n);
    this.memo = new int[s.length()][1 << 10];
    for (int[] arr : memo) {
      Arrays.fill(arr, -1);
    }
    return n - count(0, 0, true, false);
  }

  private int count(int index, int mask, boolean isLimit, boolean isNum) {
    if (index == s.length()) {
      return isNum ? 1 : 0;
    }

    if (!isLimit && isNum && memo[index][mask] != -1) {
      return memo[index][mask];
    }

    int ans = 0;
    if (!isNum) {
      ans = count(index + 1, mask, false, false);
    }

    int limit = isLimit ? (s.charAt(index) - '0') : 9;
    for (int i = isNum ? 0 : 1; i <= limit; i++) {
      if ((mask >> i & 1) == 0) {
        ans += count(index + 1, mask | (1 << i), isLimit && i == limit, true);
      }
    }

    if (!isLimit && isNum) {
      memo[index][mask] = ans;
    }

    return ans;
  }
}
```
# [LeetCode_2591_将钱分给最多的儿童](https://leetcode.cn/problems/distribute-money-to-maximum-children/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int distMoney(int money, int children) {
        if (money < children) {
            return -1;
        }

        money -= children;

        int c = money / 7, leftMoney = money % 7;

        if (c == children) {
            return leftMoney == 0 ? children : Math.max(0, children - 1);
        } else if (c > children) {
            return Math.max(0, children - 1);
        } else {
            int leftChildren = Math.max(0, children - c);
            if (leftChildren == 1 && leftMoney == 3) {
                return Math.max(0, c - 1);
            }

            return c;
        }
    }
}
```
# [LeetCode_2595_奇偶位数](https://leetcode.cn/problems/number-of-even-and-odd-bits/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int[] evenOddBit(int n) {
        int[] ans = new int[2];
        for (int i = 0; i < 32; i++) {
            int index = i % 2 == 0 ? 0 : 1;
            if ((1 << i & n) != 0) {
                ans[index]++;
            }
        }
        return ans;
    }
}
```
# [LeetCode_939_最小面积矩形](https://leetcode.cn/problems/minimum-area-rectangle/)
## 解法
### 思路
回溯+记忆化+减枝
### 代码
```java
class Solution {
    private int min = Integer.MAX_VALUE;
    private Map<Integer, List<Integer>> rmap = new HashMap<>(), cmap = new HashMap<>();
    private Set<String> memo = new HashSet<>();


    public int minAreaRect(int[][] points) {
        for (int[] point : points) {
            rmap.computeIfAbsent(point[0], x -> new ArrayList<>()).add(point[1]);
            cmap.computeIfAbsent(point[1], x -> new ArrayList<>()).add(point[0]);
        }

        for (int[] point : points) {
            LinkedList<int[]> list = new LinkedList<>();
            list.add(point);
            String key = getKey(point[0], point[1]);
            memo.add(key);
            backTrack(1, point[1], false, list);
            memo.remove(key);
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }

    private void backTrack(int index, int target, boolean isR, LinkedList<int[]> list) {
        if (index == 4) {
            if (list.get(0)[0] == list.get(3)[0]) {
                min = Math.min(min, square(list));
            }
            return;
        }

        if (index == 3 && min <= square(list)) {
            return;
        }

        List<Integer> candidates = isR ? rmap.getOrDefault(target, new ArrayList<>()) : cmap.getOrDefault(target, new ArrayList<>());
        for (Integer candidate : candidates) {
            int nextr = isR ? target : candidate, nextc = isR ? candidate : target;
            String key = getKey(nextr, nextc);
            if (memo.contains(key)) {
                continue;
            }
            
            memo.add(key);
            list.addFirst(new int[]{nextr, nextc});
            backTrack(index + 1, candidate, !isR, list);
            memo.remove(key);
            list.removeFirst();
        }
    }
    
    private String getKey(int x, int y) {
        return x + "::" + y;
    }

    private int square(List<int[]> list) {
        int maxr, minr, maxc, minc;
        maxr = maxc = Integer.MIN_VALUE;
        minr = minc = Integer.MAX_VALUE;
        for (int[] arr : list) {
            maxr = Math.max(maxr, arr[0]);
            minr = Math.min(minr, arr[0]);
            maxc = Math.max(maxc, arr[1]);
            minc = Math.min(minc, arr[1]);
        }

        return (maxr - minr) * (maxc - minc);
    }
}
```
## 解法二
### 思路
哈希表
- 将行与列之间的关系存储在map中，value是一个list，key存的行，value存的列
- 遍历生成的map，根据行，获取列的列表
- 根据列的列表，选出其中的2个，组成一个唯一key，然后到另一个map中查找这个key对应的行
- 如果有，就用2组行和列，求出差值的乘积，并与暂存的最小值进行更小值的比较
- 并将当前的唯一key放入这另一个map中保存
### 代码
```java
class Solution {

    public int minAreaRect(int[][] points) {
        Map<Integer, List<Integer>> colMap = new TreeMap<>();
        for (int[] point : points) {
            colMap.computeIfAbsent(point[0], x -> new ArrayList<>()).add(point[1]);
        }

        Map<Integer, Integer> lastX = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for (Integer row : colMap.keySet()) {
            List<Integer> cols = colMap.get(row);
            Collections.sort(cols);
            for (int i = 0; i < cols.size(); i++) {
                for (int j = i + 1; j < cols.size(); j++) {
                    int c1 = cols.get(i), c2 = cols.get(j);
                    int key = c1 * 40001 + c2;
                    if (lastX.containsKey(key)) {
                        min = Math.min(min, (row - lastX.get(key)) * (c2 - c1));
                    }
                    lastX.put(key, row);
                }
            }
        }
        
        return min == Integer.MAX_VALUE ? 0 : min;
    }
}
```
# [LeetCode_1626_无矛盾的最佳球队](https://leetcode.cn/problems/best-team-with-no-conflicts/)
## 解法
### 思路
排序+动态规划
- 将2个数组内容包装在
- dp[i] = max{dp[j]} + score[i](j < i && age[i] > age[j])
- 返回dp数组中的最大值
### 代码
```java
class Solution {
    public int bestTeamScore(int[] scores, int[] ages) {
        int n = scores.length;
        int[][] peoples = new int[n][2];

        for (int i = 0; i < n; i++) {
            peoples[i][0] = ages[i];
            peoples[i][1] = scores[i];
        }

        Arrays.sort(peoples, (a, b) -> {
            if (a[1] == b[1]) {
                return a[0] - b[0];
            }

            return a[1] - b[1];
        });

        int[] dp = new int[n];
        for (int i = 0; i < peoples.length; i++) {
            dp[i] = peoples[i][1];
        }

        for (int i = 0; i < n; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (peoples[i][0] >= peoples[j][0]) {
                    dp[i] = Math.max(dp[i], dp[j] + peoples[i][1]);
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int num : dp) {
            max = Math.max(max, num);
        }

        return max;
    }
}
```
# [LeetCode_1630_等差子数组](https://leetcode.cn/problems/arithmetic-subarrays/)
## 解法
### 思路
暴力迭代
### 代码
```java
class Solution {
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        int n = l.length;
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int start = l[i], end = r[i];
            int[] sub = Arrays.copyOfRange(nums, start, end + 1);
            Arrays.sort(sub);

            int diff = sub[1] - sub[0];
            boolean flag = true;
            for (int j = 2; j <= end - start; j++) {
                if (sub[j] - sub[j - 1] != diff) {
                    flag = false;
                    break;
                }
            }

            ans.add(flag);
        }
        return ans;
    }
}
```
# [LeetCode_948_令牌放置](https://leetcode.cn/problems/bag-of-tokens/)
## 解法
### 思路
排序+双指针模拟
- 对数组排序
- 双指针相向遍历数组
- 左指针翻正面累加积分，减能量
- 右指针翻背面消耗积分，加能量
### 代码
```java
class Solution {
    public int bagOfTokensScore(int[] tokens, int power) {
        Arrays.sort(tokens);
        int ans = 0, c = 0;
        
        int l = 0, r = tokens.length - 1;
        while (l <= r) {
            int cost = tokens[l];
            if (power >= cost) {
                ans -= c;
                c = 0;
                power -= cost;
                ans++;
                l++;
                continue;
            }
            
            if (ans > 0 && ans >= c) {
                power += tokens[r--];
                c++;
                continue;
            }

            break;
        }
        
        return ans;
    }
}
```
# [LeetCode_1032_字符流](https://leetcode.cn/problems/stream-of-characters/)
## 解法
### 思路
前缀树
- 将words列表的元素按照倒序插入到字典树中
- 使用StringBuilder来累加字符流
- query的时候借助前缀树做查询，查询时从StringBuilder的尾部开始查询
### 代码
```java
class StreamChecker {
    private StringBuilder sb;
    private Trie root;
    public StreamChecker(String[] words) {
        sb = new StringBuilder();
        root = new Trie();
        for (String word : words) {
            root.insert(word);
        }
    }

    public boolean query(char letter) {
        sb.append(letter);
        return root.query(sb);
    }

    private static class Trie {
        private boolean isEnd;
        private final Trie[] children = new Trie[26];

        public void insert(String word) {
            Trie node = this;
            char[] cs = word.toCharArray();
            for (int i = cs.length - 1; i >= 0; i--) {
                char c = cs[i];
                
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new Trie();
                }

                node = node.children[c - 'a'];
            }

            node.isEnd = true;
        }

        public boolean query(StringBuilder sb) {
            int len = sb.length();
            Trie node = this;
            for (int i = len - 1; i >= 0; i--) {
                char c = sb.charAt(i);
                
                if (node.children[c - 'a'] == null) {
                    return false;
                }

                node = node.children[c - 'a'];
                if (node.isEnd) {
                    return true;
                }
            }

            return false;
        }
    }
}
```
# [LeetCode_1754_删除最短的子数组使剩余数组有序](https://leetcode.cn/problems/shortest-subarray-to-be-removed-to-make-array-sorted/)
## 解法
### 思路
双指针
- 题目要求确定1个删除子数组，所以整个数组可以有如下几种情况:
  - 整个数组是非递减序列，这时候的删除长度就是0
  - 数组分成3部分
    - 左侧非递减序列，长度可能为0
    - 中间不是非递减的序列
    - 右侧非递减序列，长度可能为0
- 使用2个指针分别对应数组的头尾坐标
- 遍历尾指针，向数组头部遍历，如果遍历到坐标0的位置，说明整个数组是非递减序列，直接返回0
- 否则，开始嵌套遍历，外层确定头指针的起始位置，头指针需要符合2种情况中的任意一种
  - 坐标0
  - 是当前外层遍历到的，从坐标0开始的，非递减序列的最后一个坐标
- 内层向右移动尾指针，从之前确定的右侧的非递减序列的最左侧坐标开始，向右遍历，遍历的规则是，找到最小的那个大于头指针对应元素的尾指针位置，然后求头尾指针之间的区间长度，与最小值做比较，进行更新
### 代码
```java
class Solution {
  public int findLengthOfShortestSubarray(int[] arr) {
    int n = arr.length,  r = n - 1;
    while (r > 0 && arr[r] >= arr[r - 1]) {
      r--;
    }

    if (r == 0) {
      return 0;
    }

    int ans = r;
    for (int l = 0; l == 0 || arr[l - 1] <= arr[l]; l++) {
      while (r < n && arr[r] < arr[l]) {
        r++;
      }

      ans = Math.min(ans, r - l - 1);
    }

    return ans;
  }
}
```
# [LeetCode_955_删列造序II](https://leetcode.cn/problems/delete-columns-to-make-sorted-ii/)
## 解法
### 思路
哈希表+模拟：
- 问题可以这么分析，比较字典序，实际就是相邻2个字符串的相同列的比较
  - 如果当前列的字典序是升序的，那么就不用考虑之后的列了
  - 如果当前列的字典序是相同的，那么就要考虑之后一列
  - 我们可以用一个哈希集合来记录当前列，需要被判断的字符串，每一列遍历判断的时候，更新这个哈希集合，集合中就存储相邻字符串字典序相等的字符坐标，两两比较的时候，可以只记录前一个坐标
- 过程：2层遍历
  - 外层遍历确定需要判断的字符列数
  - 内层遍历所有的字符串，通过哈希集合做过滤
- 哈希集合保存字典序相等字符串坐标，并初始化的填充所有字符串数组的坐标值
- 内层遍历的时候：
  - 如果相邻字符的字典序是降序，则累加删除数，并直接跳到下一列
  - 如果相邻字符字典序是相等的，则将当前坐标记录到哈希集合中
- 内层遍历完后，如果没有发现降序的，同时也没有记录到字典序相等的情况，那么说明当前已经处理完成了，字典序已经是非降序的了，终止循环返回记录的操作数即可
### 代码
```java
class Solution {
    public int minDeletionSize(String[] strs) {
        int n = strs[0].length(), ans = 0;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < strs.length - 1; i++) {
            set.add(i);
        }

        for (int i = 0; i < n; i++) {
            boolean flag = true;
            Set<Integer> curSet = new HashSet<>();
            for (int j = 0; j < strs.length - 1; j++) {
                if (!set.contains(j)) {
                    continue;
                }

                int diff = strs[j].charAt(i) - strs[j + 1].charAt(i);
                if (diff > 0) {
                    flag = false;
                    break;
                } else if (diff == 0) {
                    curSet.add(j);
                }
            }

            if (!flag) {
                ans++;
                continue;
            }

            set = curSet;
            if (set.isEmpty()) {
                break;
            }

        }

        return ans;
    }
}
```
# [LeetCode_1638_统计只差一个字符的子串数目](https://leetcode.cn/problems/count-substrings-that-differ-by-one-character/)
## 解法
### 思路
暴力模拟
- 3层循环
  - 外2层确定s和t子串的起始坐标
  - 第3层，从确定的坐标开始，向右同时移动，找到差异个数为1的最长子串，并累加所有个数为1的情况
- 循环结束返回累加值
### 代码
```java
class Solution {
    public int countSubstrings(String s, String t) {
        int sn = s.length(), tn = t.length(), ans = 0;
        for (int i = 0; i < sn; i++) {
            for (int j = 0; j < tn; j++) {
                int cnt = 0;
                int offset = 0;
                while (cnt < 2 && i + offset < sn && j + offset < tn) {
                    char sc = s.charAt(i + offset), tc = t.charAt(j + offset);
                    cnt += sc == tc ? 0 : 1;
                    if (cnt == 1) {
                        ans++;
                    }
                    offset++;
                }
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1092_最短公共超序列](https://leetcode.cn/problems/shortest-common-supersequence/)
## 失败解法
### 原因
内存超限，记忆化搜索时候存储的字符串过大
### 思路
记忆化搜索
- 整个搜索过程可以分成3种路径
  - 如果2个字符串的最后一个字符相等，那么就相当于求两个字符串剔除最后一个字符后的子字符串的超序列，成为了一个新的子问题
  - 如果2个字符串的最后一个字符不相等
    - 那么势必需要在2个字符串的最后一个字符中选择一个作为结果中当前坐标的最后一个字符
    - 然后再求选中的那个字符串剔除掉最后一个字符后，和没有选中的那个字符串之间的的超序列，在2种情况下找到最短的那个作为结果
- 求解过程中，根据字符串最后一个字符的坐标来进行递归，退出条件就是坐标越界
- 然后可以通过记事本来记录字符串，从而做到减枝
### 代码
```java
class Solution {
    public String shortestCommonSupersequence(String str1, String str2) {
        return dfs(str1, str2, new String[str1.length()][str2.length()]);
    }

    private String dfs(String str1, String str2, String[][] memo) {
        if (str1.isEmpty()) {
            return str2;
        }

        if (str2.isEmpty()) {
            return str1;
        }

        int n1 = str1.length(), n2 = str2.length();
        if (memo[n1 - 1][n2 - 1] != null) {
            return memo[n1 - 1][n2 - 1];
        }

        char c1 = str1.charAt(n1 - 1), c2 = str2.charAt(n2 - 1);
        String ns1 = str1.substring(0, n1 - 1), ns2 = str2.substring(0, n2 - 1);

        if (c1 == c2) {
            return memo[n1 - 1][n2 - 1] = dfs(ns1, ns2, memo) + c1;
        } else {
            String x = dfs(ns1, str2, memo),
                    y = dfs(str1, ns2, memo);

            return memo[n1 - 1][n2 - 1] = (x.length() < y.length() ? x + c1 : y + c2);
        }
    }
}
```
## 解法
### 思路
- 解法一失败的原因是记录了太多没有必要存在的字符串作为记事本中的内容
- 如果先通过递归找到长度短的那个选择路径，然后再进行搜索，生成字符串，从而就能够减少对不必要的字符串的生成操作
### 代码
```java
class Solution {
    private String s1, s2;
    private int[][] memo;

    public String shortestCommonSupersequence(String str1, String str2) {
        s1 = str1;
        s2 = str2;
        memo = new int[s1.length()][s2.length()];
        return dfs2(s1.length() - 1, s2.length() - 1);
    }

    private int dfs(int i, int j) {
        if (i < 0) {
            return j + 1;
        }

        if (j < 0) {
            return i + 1;
        }

        if (memo[i][j] > 0) {
            return memo[i][j];
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return memo[i][j] = dfs(i - 1, j - 1) + 1;
        } else {
            int len1 = dfs(i - 1, j), len2 = dfs(i, j - 1);
            return memo[i][j] = len1 <= len2 ? len1 + 1 : len2 + 1;
        }
    }

    private String dfs2(int i, int j) {
        if (i < 0) {
            return s2.substring(0, j + 1);
        }

        if (j < 0) {
            return s1.substring(0, i + 1);
        }

        if (s1.charAt(i) == s2.charAt(j)) {
            return dfs2(i - 1, j - 1) + s1.charAt(i);
        }

        if (dfs(i - 1, j) <= dfs(i, j - 1)) {
            return dfs2(i - 1, j) + s1.charAt(i);
        } else {
            return dfs2(i, j - 1) + s2.charAt(j);
        }
    }
}
```
# [LeetCode_1641_统计字典序元音字符串的数目](https://leetcode.cn/problems/count-sorted-vowel-strings/)
## 解法
### 思路
递归搜索
### 代码
```java
class Solution {
  private int cnt;

  public int countVowelStrings(int n) {
    dfs(0, 0, n);
    return cnt;
  }

  private void dfs(int index, int len, int n) {
    if (len == n) {
      cnt++;
      return;
    }

    for (int i = index; i < 5; i++) {
      dfs(i, len + 1, n);
    }
  }
}
```
## 解法二
### 思路
动态规划
- dp[i][j]：
  - 长度为i+1的字符串
  - 数组[a,e,i,o,u]的坐标
- 状态转移方程
  - dp[i][j] = sum(dp[i - 1][k]), k <= j
### 代码
```java
class Solution {
  public int countVowelStrings(int n) {
    int[][] dp = new int[n][5];
    Arrays.fill(dp[0], 1);

    for (int i = 1; i < n; i++) {
      for (int j = 0; j < 5; j++) {
        for (int k = 0; k <= j; k++) {
          dp[i][j] += dp[i - 1][k];
        }
      }
    }

    int sum = 0;
    for (int i = 0; i < 5; i++) {
      sum += dp[n - 1][i];
    }

    return sum;
  }
}
```
## 解法三
### 思路
在解法二的基础上做状态压缩
### 代码
```java
class Solution {
    public int countVowelStrings(int n) {
        int a = 1, e = 1, i = 1, o = 1, u = 1;
        for (int j = 1; j < n; j++) {
            a = a + e + i + o + u;
            e = e + i + o + u;
            i = i + o + u;
            o = o + u;
            u = u;
        }

        return a + e + i + o + u;
    }
}
```
# [LeetCode_1637_两点之间不包含任何点的最宽垂直区域](https://leetcode.cn/problems/widest-vertical-area-between-two-points-containing-no-points/)
## 解法
### 思路
- 根据横坐标排序
- 遍历数组，比较相邻两个横坐标的差值，暂存最大值
- 遍历结束，返回结果即可
### 代码
```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        Arrays.sort(points, Comparator.comparingInt(x -> x[0]));
        int ans = 0;
        for (int i = 0; i < points.length - 1; i++) {
            ans = Math.max(points[i + 1][0] - points[i][0], ans);
        }
        return ans;
    }
}
```
## 解法二
### 思路
- 假设数组从小到大排序，那么排序数组中相邻元素的差值可以表示为nums[i] - nums[i - 1]
- 假设最大的相邻间距是maxGap
- 又因为数组的最大间距是nums[n - 1] - nums[0]
- 那么就可以得到这样一个公式nums[n - 1] - nums[0] = sum(nums[i] - nums[i - 1]) <= maxGap * (n - 1)，i ∈ [1, n - 1]
- 转换一下就可以得到：maxGap >= nums[n - 1] - nums[0] / (n - 1)，这表示maxGap一定不小于数组最大间距除以间距个数的值
- 而数组最大间距maxN除以间距个数cnt的值x，就相当于得到了cnt个长度为x的桶，在桶中的所有坐标点，互相之间的间距一定小于maxGap，所以maxGap不会出现在桶里的这些坐标对里，一定出现在相邻的桶之间
- 那么就可以通过将坐标放入桶中，然后维护桶的最大和最小横坐标点，来求maxGap，从而使时间复杂度从O(nlogn)降低到O(n)
### 代码
```java
class Solution {
    public int maxWidthOfVerticalArea(int[][] points) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE, n = points.length;
        for (int[] point : points) {
            max = Math.max(max, point[0]);
            min = Math.min(min, point[0]);
        }

        int maxN = max - min, bucketLen = Math.max(1, maxN / (n - 1)), bucketCount = maxN / bucketLen + 1;
        int[][] gaps = new int[bucketCount + 1][2];
        for (int[] gap : gaps) {
            gap[0] = Integer.MAX_VALUE;
            gap[1] = Integer.MIN_VALUE;
        }

        for (int[] point : points) {
            int index = (point[0] - min) / bucketLen;
            gaps[index][0] = Math.min(point[0], gaps[index][0]);
            gaps[index][1] = Math.max(point[0], gaps[index][1]);
        }

        int ans = 0, pre = Integer.MAX_VALUE;
        for (int[] gap : gaps) {
            if (gap[0] > gap[1]) {
                continue;
            }

            ans = Math.max(ans, gap[0] - pre);
            pre = gap[1];
        }

        return ans;
    }
}
```
# [LeetCode_1039_多边形三角剖分的最低得分](https://leetcode.cn/problems/minimum-score-triangulation-of-polygon/)
## 解法
### 思路
记忆化搜索：
- 确定2个顶点i,j，然后遍历搜索第3个顶点k
- 这样就将多边形拆分成了3部分：
  - ijk组成的三角形
  - 以i，k为范围的凸多边形
  - 以k，j位范围的凸多边形
- 通过上面3部分中的后2部分，可以明显的看出，后两部分是2个当前问题的子问题
- 这个明显的地递归过程，其退出条件就是凸多边形无法构成，也就是i与k或k与j相邻，意味着只有2个顶点，而当一个部分无法形成的时候，也就说明其实多边形被切分成了2部分，一个三角形ijk，和另一个多边形
- 整个过程也很简单，就是遍历所有的k，通过`dfs(i,k)+dfs(k,j)+values[i] * values[j] * values[k]`中的最小值作为结果
- 为了提速，就通过memo来进行减枝
### 代码
```java
class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        return dfs(0, n - 1, values, new Integer[n][n]);
    }
    
    private int dfs(int i, int j, int[] values, Integer[][] memo) {
        if (i + 1 == j) {
            return 0;
        }
        
        if (memo[i][j] != null) {
            return memo[i][j];
        }
        
        int ans = Integer.MAX_VALUE;
        for (int k = i + 1; k < j; k++) {
            ans = Math.min(dfs(i, k, values, memo) + dfs(k, j, values, memo) + values[i] * values[j] * values[k], ans);
        }
        
        return memo[i][j] = ans;
    }
}
```
## 解法二
### 思路
动态规划：
- 通过解法一可以发现，解法中发现了子问题，使用了记事本，那么其实这个问题就可以通过动态规划来解决
- dp[i][j]：代表区间为i和j的凸多边形的最小值
- 状态转移方程：dp[i][j] = min(dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]), i < k < j
- 最后结果返回dp[0][n - 1]
### 代码
```java
class Solution {
    public int minScoreTriangulation(int[] values) {
        int n = values.length;
        int[][] dp = new int[n][n];
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n; j++) {
                int ans = Integer.MAX_VALUE;
                for (int k = i + 1; k < j; k++) {
                    ans = Math.min(dp[i][k] + dp[k][j] + values[i] * values[k] * values[j], ans);
                }
                dp[i][j] = ans;
            }
        }
        
        return dp[0][n - 1];
    }

}
```