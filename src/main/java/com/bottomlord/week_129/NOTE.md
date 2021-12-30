# [LeetCode_28_实现strStr()](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路
kmp：

### 代码
```java

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