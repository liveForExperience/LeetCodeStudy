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
# [LeetCode_955_删列造序II](https://leetcode.cn/problems/delete-columns-to-make-sorted-ii/)
## 解法
### 思路

### 代码
```java

```