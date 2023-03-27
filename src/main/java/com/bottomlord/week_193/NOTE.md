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
# [LeetCode_957_N天后的牢房](https://leetcode.cn/problems/prison-cells-after-n-days/)
## 失败解法
### 原因
超时
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        while (n-- > 0) {
            int[] cur = new int[8];
            for (int i = 0; i < 8; i++) {
                if (i == 0 || i == 7) {
                    cur[i] = 0;
                    continue;
                }
                
                if (pre[i - 1] != pre[i + 1]) {
                    cur[i] = 0;
                } else {
                    cur[i] = 1;
                }
            }
            
            pre = cur;
        }
        
        return pre;
    }
}
```
## 解法
### 思路
数组长度有限，是不是可以枚举，使用hash表记录当前数组对应的下一个数组的状态
### 代码
```java

```
# [LeetCode_1638_统计只差一个字符的子串数目](https://leetcode.cn/problems/count-substrings-that-differ-by-one-character/)
## 解法
### 思路
暴力模拟
- 3层循环
  - 外2层确定s和t子串的起始坐标
  - 第3层从确定的坐标开始，向右同时移动，找到差异个数为1的最大长度，并累加所有个数为1的情况
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