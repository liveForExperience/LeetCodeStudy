# [LeetCode_28_实现strStr()](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路
String的indexOf函数
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }
}
```
## 解法二
### 思路
2层循环比较
### 代码
```java
class Solution {
    public int strStr(String haystack, String needle) {
        int lenH = haystack.length(), lenN = needle.length();
        if (lenN == 0) {
            return 0;
        }
        
        if (lenN > lenH) {
            return -1;
        }
        
        if (lenN == lenH) {
            return Objects.equals(haystack, needle) ? 0 : -1;
        }
        
        for (int i = 0; i < lenH - lenN + 1; i++) {
            boolean flag = true;
            for (int j = 0; j < lenN; j++) {
                if (haystack.charAt(i + j) != needle.charAt(j)) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                return i;
            }
        }
        
        return -1;
    }
}
```
## 解法三
### 思路
- 生成模式串的字符串hash值
- 遍历字符串
- 生成第一段字符串的hash值
- 然后每次移动一个字符，加上对应的字符hash值，减去最高位的字符串hash值
- 循环往复的比较
### 代码
```java
class Solution {

    private final long pow = 31;

    public int strStr(String haystack, String needle) {
        int lenH = haystack.length(), lenN = needle.length();
        if (lenN == 0) {
            return 0;
        }

        if (lenN > lenH) {
            return -1;
        }

        if (lenN == lenH) {
            return Objects.equals(haystack, needle) ? 0 : -1;
        }

        long hashN = getStrHash(needle),
             hashH = getStrHash(haystack.substring(0, lenN));

        if (hashH == hashN) {
            return 0;
        }

        long base = 1;
        for (int i = 0; i < lenN - 1; i++) {
            base *= pow;
        }

        for (int i = lenN; i < lenH; i++) {
            hashH = (hashH - ((haystack.charAt(i - lenN) - 'a' + 1) * base)) * pow + (haystack.charAt(i) - 'a' + 1);
            if (hashH == hashN) {
                return i - lenN + 1;
            }
        }

        return -1;
    }

    private long getStrHash(String str) {
        long num = 0;
        char[] cs = str.toCharArray();
        for (char c : cs) {
            num = num * pow + (c - 'a' + 1);
        }
        return num;
    }
}
```
# [LeetCode_825_适龄的朋友](https://leetcode-cn.com/problems/friends-of-appropriate-ages/)
## 失败解法
### 原因
超时
### 思路
嵌套循环
### 代码
```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int n = ages.length, count = 0;
        for (int i = 0; i < n; i++) {
            int ageA = ages[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                
                if (ages[j] <= ageA * 0.5 + 7 ||
                ages[j] > ages[i] ||
                ages[j] > 100 && ages[i] < 100) {
                    continue;
                }

                count++;
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
排序+双指针
- 先对数组进行排序
- 初始化三个指针
  - i对应有效对象值的下界
  - j对应有效对象值的上界
  - k对应发送者
- 遍历k，直到k数组越界
- 内层分别确定i和j，确定的方式就使用题目要求的条件
- 确定好上下界后，求差值就是当前k可以发送的人数，但因为这个范围中包含k自身，所以还需要减去1
- 因为数组是有序的，所以每次遍历k都无需重置i和j，从上一个k确定后的位置继续判断即可
- 注意：下界i一定不能超过坐标k
### 代码
```java
class Solution {
public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0, n = ages.length;
        for (int i = 0, j = 0, k = 0; k < n; k++) {
            while (i < k && !check(ages[k], ages[i])) {
                i++;
            }
            if (j < k) {
                j = k;
            }

            while (j < n && check(ages[k], ages[j])) {
                j++;
            }

            if (j > i) {
                ans += j - i - 1;
            }
        }

        return ans;
    }

    private boolean check(int x, int y) {
        return (!(y <= 0.5 * x + 7)) && (y <= x);
    }
}
```
## 解法三
### 思路

### 代码
```java

```
# [LeetCode_472_连接词](https://leetcode-cn.com/problems/concatenated-words/)
## 解法
### 思路
字典树+dfs
- 对字符串数组排序，短字符串优先
- 遍历字符串
- 先dfs搜索字典树，如果当前字符串依次都能在字典树中找到，那么就放入结果中
- 此处不需要将放入结果中的字符串放到字典树中，因为结果中的字符串是由多个已有的字符串组成的，所以由它组成的其他字符串也可以由组成它的字符串组成
- 如果dfs搜索结果判断这个字符串不能由线程的字典树字符串组成，就把它放到字典树中
- 遍历结束以后，返回结果字符串数组
### 代码
```java
class Solution {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Tire tire = new Tire();
        Arrays.sort(words, Comparator.comparingInt(String::length));
        List<String> ans = new ArrayList<>();
        for (String word : words) {
            if (Objects.equals("", word)) {
                continue;
            }

            if (dfs(tire.root, 0, word)) {
                ans.add(word);
            } else {
                tire.insert(word);
            }
        }
        return ans;
    }

    private boolean dfs(TireNode root, int index, String word) {
        if (index == word.length()) {
            return true;
        }

        TireNode node = root;
        while (index < word.length()) {
            node = node.children[word.charAt(index) - 'a'];
            if (node == null) {
                return false;
            }

            if (node.isWord && dfs(root, index + 1, word)) {
                return true;
            }

            index++;
        }

        return false;
    }

    private class Tire {
        private TireNode root;

        public Tire() {
            this.root = new TireNode();
        }

        public void insert(String word) {
            TireNode node = root;
            char[] cs = word.toCharArray();
            for (char c : cs) {
                if (node.children[c - 'a'] == null) {
                    node.children[c - 'a'] = new TireNode();
                }
                
                node = node.children[c - 'a'];
            }

            node.isWord = true;
        }
    }

    private class TireNode {
        private char c;
        private boolean isWord;
        private TireNode[] children;

        public TireNode() {
            this.children = new TireNode[26];
        }
    }
}
```
# [LeetCode_1995_统计特殊四元组](https://leetcode-cn.com/problems/count-special-quadruplets/)
## 解法
### 思路
- 排序
- 暴力穷举
### 代码
```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    for (int l = k + 1; l < nums.length; l++) {
                        int sum = nums[i] + nums[j] + nums[k];
                        if (sum == nums[l]) {
                            count++;
                        }
                    }
                }
            }
        }
        return count;
    }
}
```
## 解法二
### 思路
- 四个数字之间的关系可以整理成`nums[a] + nums[b] = nums[d] - nums[c]`
- 通过一次二层嵌套的for循环生成`nums[a] + nums[b]`，对值进行计数
- 再通过一次二层嵌套for循环，匹配是否存在对应的值，累加计数值
- 但如果按照如上的方式处理，无法保证b和c之间的前后顺序关系，从而使得结果变多
- 所以需要将2个for循环再合并在一起
  - 外层确定b的位置
  - 内层第一个循环，确定a并记录count值
  - 内层第二个循环，确定c和d，c为b+1，遍历允许范围内的d，然后在统计数组中找count值
### 代码
```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int[] arr = new int[10000];
        int n = nums.length, count = 0;
        for (int b = 1; b < n - 2; b++) {
            for (int a = 0; a < b; a++) {
                arr[nums[a] + nums[b] + 200]++;
            }

            for (int d = b + 2; d < n; d++) {
                count += arr[nums[d] - nums[b + 1] + 200];
            }
        }
        return count;
    }
}
```
# [LeetCode_1995_3](https://leetcode-cn.com/problems/count-special-quadruplets/)
## 解法
### 思路
动态规划：
- `dp[i][j][k]`：前i个数字中，总和为j，参与个数为k的情况下的可能数
- base case：dp[0][0][0] = 1，代表没有元素参与，总数为0，参与个数为0的情况下，可能数为1
- 状态转移方程：
  - `dp[i][j][k] = dp[i - 1][j][k] + dp[i - 1][j - 1][k - 1]`
  - 代表的含义：
    - 当`nums[i-1]`不参与到可能性判断中时候，可能数就是`dp[i - 1][j][k]`，
    - 当`nums[i-1]`参与到可能性判断中时候，可能数就是`dp[i - 1][j - 1][k - 1]`
    - 它们两者的和就是总可能数
- 这里j的总和就是400，k就是3，而i的范围就是从4到n
### 代码
```java
class Solution {
    public int countQuadruplets(int[] nums) {
        int n = nums.length;
        int[][][] dp = new int[n][401][4];
        dp[0][0][0] = 1;
        for (int i = 1; i < n; i++) {
            int num = nums[i - 1];
            for (int j = 0; j < 401; j++) {
                for (int k = 0; k < 4; k++) {
                    dp[i][j][k] += dp[i - 1][j][k];
                    if (j - num >= 0 && k - 1 >= 0) {
                        dp[i][j][k] += dp[i - 1][j - num][k - 1];
                    }
                }
            }
        }

        int count = 0;
        for (int i = 3; i < n; i++) {
            count += dp[i][nums[i]][3];
        }
        return count;
    }
}
```
# [LeetCode_846_一手顺子](https://leetcode-cn.com/problems/hand-of-straights/)
## 解法
### 思路
- 首先数组长度需要被`groupSize`整除
- 因为需要所有组都是顺子，所以可以对数组进行桶排序
- 然后遍历桶，将桶中元素依次放入优先级队列中
- 每次从队列中找出`groupSize`个元素
- 判断这些元素是否是有顺序的，如果没有就返回false，如果有，就对元素对应的个数依次减一，并剔除掉个数为0的
- 数组桶排序会超出内存限制，换成map统计
### 代码
```java
class Solution {
    public boolean isNStraightHand(int[] hand, int groupSize) {
        int n = hand.length;
        if (n == 0) {
            return true;
        }

        if (n % groupSize != 0) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();
        for (int num : hand) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        

        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        for (Integer num : map.keySet()) {
            queue.offer(new int[]{num, map.get(num)});
        }

        while (!queue.isEmpty()) {
            int count = groupSize;
            Integer pre = null;
            List<int[]> toAdd = new ArrayList<>();
            while (count-- > 0) {
                if (queue.isEmpty()) {
                    return false;
                }

                int[] arr = queue.poll();
                if (pre != null && arr[0] - 1 != pre) {
                    return false;
                }

                pre = arr[0];
                arr[1]--;

                if (arr[1] != 0) {
                    toAdd.add(arr);
                }
            }

            for (int[] arr : toAdd) {
                queue.offer(arr);
            }
        }

        return true;
    }
}
```
# [LeetCode_1961_检查字符串是否为数组前缀](https://leetcode-cn.com/problems/check-if-string-is-a-prefix-of-array/)
## 解法
### 思路
- 初始化StringBuilder对象，用于存放临时字符串
- 遍历字符串数组words
- 循环逻辑中，将当前遍历到的字符串追加到sb中，并与s进行一次比较，如果相等就直接返回true
- 遍历结束如果还没有返回，则代表不是数组前缀，返回false
### 代码
```java
class Solution {
    public boolean isPrefixString(String s, String[] words) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word);
            if (Objects.equals(sb.toString(), s)) {
                return true;
            }
        }
        
        return false;
    }
}
```
# [LeetCode_507_完美数](https://leetcode-cn.com/problems/perfect-number/)
## 解法
### 思路
- 遍历1到n的平方根，找出正因数
- 累加正因数和能被整除的另一个正因数
- 遍历结束后减去自身，自身是num和1相除得到的
- 判断是否是完美数并返回
### 代码
```java
class Solution {
    public boolean checkPerfectNumber(int num) {
        int n = (int) Math.sqrt(num) + 1;
        int sum = 0;
        for (int i = 1; i < n; i++) {
            if (num % i == 0) {
                sum += i;
                sum += num / i == i ? 0 : num / i;
            }
        }
 
        return sum - num == num;
    }
}
```
# [LeetCode_1967_作为子字符串出现在单词中的字符串数目](https://leetcode-cn.com/problems/number-of-strings-that-appear-as-substrings-in-word/)
## 解法
### 思路
使用String的contains方法判断是否存在，然后循环判断和累加，循环结束后返回计数值
### 代码
```java
class Solution {
  public int numOfStrings(String[] patterns, String word) {
    int count = 0;
    for (String pattern : patterns) {
      count += word.contains(pattern) ? 1 : 0;
    }
    return count;
  }
}
```
## 解法二
### 思路
暴力2层循环
### 代码
```java
class Solution {
  public int numOfStrings(String[] patterns, String word) {
    int count = 0;
    for (String pattern : patterns) {
      if (pattern.length() > word.length()) {
        continue;
      }

      boolean flag = false;
      for (int i = 0; i < word.length() - pattern.length() + 1; i++) {
        if (word.charAt(i) != pattern.charAt(0)) {
          continue;
        }

        if (word.startsWith(pattern, i)) {
          flag = true;
          break;
        }
      }

      if (flag) {
        count++;
      }
    }

    return count;
  }
}
```
# [LeetCode_2022_将一维数组转变成二维数组](https://leetcode-cn.com/problems/convert-1d-array-into-2d-array/)
## 解法
### 思路
- 根据m和n初始化二维数组
- 遍历m和n，m作为外层循环次数，n作为内层循环次数
- 遍历结束后返回结果
- 需要先确定m*n与数组长度是否相等，如果不相等就返回空数组
### 代码
```java
class Solution {
    public int[][] construct2DArray(int[] original, int m, int n) {
        if (original.length != m * n) {
            return new int[0][0];
        }
        
        int[][] arr = new int[m][n];
        int index = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = original[index++];
            }
        }
        
        return arr;
    }
}
```
# [LeetCode_390_消除游戏](https://leetcode-cn.com/problems/elimination-game/)
## 解法
### 思路
- 题目的要求是求最后剩下的数，当最后只剩下1个数的时候，这个数也是那次处理时候的第一个数，所以其实就是在确定每次处理的第一个数的同时，确定是否只剩下一个数
- 只剩下一个数比较好求，每次跳一个删除，会导致要么删掉一半（长度是偶数的时候），要么删掉一半多1个（奇数的时候），所以每次n/2就可以了
- 起始数字head，要确定，就要看每次删除数字的时候，会不会把原来的数字删掉，如果删掉，新的head和原来的数差多少
  - 会不会把原来的数字删掉，起始要看2种情况
    - 如果是向右删除，一定会删掉原来的起始
    - 如果是向左边删除，那么剩下的数字是偶数个，不会删掉，奇数个会删掉
  - 差多少数字，通过观察可以发现，每次删掉以后，2个数之间的差值会以2的幂变化，1，2，4，8这样
    - 所以这个差距也可以在每次处理的时候，通过*2来维护
- 最后就是找到剩下值为1的情况时，head是多少找到即可，也就是不断地循环，n /= 2，当n == 1的时候返回head
### 代码
```java
class Solution {
    public int lastRemaining(int n) {
        boolean left = true;
        int head = 1, step = 1;
        while (n > 1) {
            if (left || n % 2 == 1) {
                head += step;
            }
            step <<= 1;
            n >>= 1;
            left = !left;
        }
        
        return head;
    }
}
```
# [LeetCode_1971_寻找图中是否存在路径](https://leetcode-cn.com/problems/find-if-path-exists-in-graph/)
## 失败解法
### 原因
超时
### 思路
dfs + memo
### 代码
```java
class Solution {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        Map<Integer, List<Integer>> mapping = new HashMap<>();
        for (int[] edge : edges) {
            mapping.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(edge[1]);
            mapping.computeIfAbsent(edge[1], x -> new ArrayList<>()).add(edge[0]);
        }

        return dfs(start, end, mapping, new boolean[n]);
    }

    private boolean dfs(int start, int end, Map<Integer, List<Integer>> mapping, boolean[] memo) {
        if (memo[start]) {
            return false;
        }

        memo[start] = true;

        if (start == end) {
            return true;
        }

        if (!mapping.containsKey(start)) {
            return false;
        }

        for (Integer next : mapping.get(start)) {
            boolean result = dfs(next, end, mapping, memo);
            if (result) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法二
### 思路
并查集
### 代码
```java
class Solution {
    public boolean validPath(int n, int[][] edges, int start, int end) {
        Dsu dsu = new Dsu(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }
        
        return dsu.find(start) == dsu.find(end);
    }

    private class Dsu {
        private int[] parent;

        public Dsu(int n) {
            this.parent = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }

            return parent[x];
        }

        public void union(int x, int y) {
            parent[find(x)] = find(y);
        }
    }
}
```