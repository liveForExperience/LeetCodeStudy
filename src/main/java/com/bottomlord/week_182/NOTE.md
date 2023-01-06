# [LeetCode_749_隔离病毒](https://leetcode.cn/problems/contain-virus/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_940_不同子序列](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1801_积压订单中的订单总数](https://leetcode.cn/problems/number-of-orders-in-the-backlog/)
## 解法
### 思路
优先级队列
- 初始化2个优先级队列
  - 存储buy类型订单
  - 存储sell类型订单
- 遍历订单列表，根据订单类型，到对应的队列中找是否能消除当前订单，如果可以就进行依次消除。
- 如果还有剩余，就放入对应的队列中
- 同样，如果队列中的订单被消耗完，那就将其从队列中去除
- 最后累加订单总数，并与10e7 + 9进行取模运算
### 代码
```java
class Solution {
    public int getNumberOfBacklogOrders(int[][] orders) {
                PriorityQueue<int[]> buyQ = new PriorityQueue<>((x, y) -> y[0] - x[0]),
                             sellQ = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));

        for (int[] order : orders) {
            int price = order[0], type = order[2];

            if (type == 0) {
                while (!sellQ.isEmpty()) {
                    int[] sell = sellQ.peek();
                    if (sell[0] > price) {
                        break;
                    }

                    int minCost = Math.min(sell[1], order[1]);
                    order[1] -= minCost;
                    sell[1] -= minCost;

                    if (sell[1] == 0) {
                        sellQ.poll();
                    }

                    if (order[1] == 0) {
                        break;
                    }
                }

                if (order[1] > 0) {
                    buyQ.offer(order);
                }
            } else {
                while (!buyQ.isEmpty()) {
                    int[] buy = buyQ.peek();
                    if (buy[0] < price) {
                        break;
                    }

                    int minCost = Math.min(buy[1], order[1]);
                    order[1] -= minCost;
                    buy[1] -= minCost;

                    if (buy[1] == 0) {
                        buyQ.poll();
                    }

                    if (order[1] == 0) {
                        break;
                    }
                }

                if (order[1] > 0) {
                    sellQ.offer(order);
                }
            }
        }

        int mod = 1000000007, ans = 0;
        while (!buyQ.isEmpty()) {
            ans += buyQ.poll()[1];
            ans %= mod;
        }
        
        while (!sellQ.isEmpty()) {
            ans += sellQ.poll()[1];
            ans %= mod;
        }
        
        return ans;
    }
}
```
# [LeetCode_2481_分割圆的最少切割次数](https://leetcode.cn/problems/minimum-cuts-to-divide-a-circle/)
## 解法
### 思路
观察发现：
- n为偶数：切割次数为n/2
- n为奇数且不为1时：切割次数为n
- n为1是特殊情况，无需切割，为0
### 代码
```java
class Solution {
  public int numberOfCuts(int n) {
    if (n == 1) {
      return 0;
    }

    return n % 2 == 0 ? n / 2 : n;
  }
}
```
# [LeetCode_1802_有界数组中指定下标处的最大值](https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/)
## 解法
### 思路
双指针
- 从index坐标开始，向左和右分别同时遍历，且依次在各个坐标对应的值上累加1
- 同时对累加的值进行存储，存储为sum，并将sum和maxSum进行比较，如果sum <= maxSum则继续循环
- 每循环一次，就对结果值ans加1
- 最后返回结果的时候，ans需要-1，因为最后一次是sum等于或者大于maxSum的时候累加的，所以需要减掉1
- 优化：当maxSum-sum的值大于n，且l和r2个指针已经移动到了0和n-1的位置，不能再移动了，那么剩下的动作其实就是每次都增加至多n个元素的同时，ans也加1，那么这个过程就可以通过(maxSum - sum) / n求出次数，从而直接得到ans要增加的个数，且此时不需要-1，那是因为此时增加的个数不像之前是超过的，这次就是正常的个数
### 代码
```java
class Solution {
    public int maxValue(int n, int index, int maxSum) {
        int ans = 1, sum = n, l = index, r = index;
        
        while (sum <= maxSum) {
            sum += r - l + 1;
            ans++;
            
            r = r == n - 1 ? n - 1 : r + 1;
            l = l == 0 ? 0 : l - 1;
            
            if (l == 0 && r == n - 1 && n < maxSum - sum) {
                return ans + (maxSum - sum) / n;
            }
        }
        
        return ans - 1;
    }
}
```
# [LeetCode_1803_统计异或值在范围内的数对有多少](https://leetcode.cn/problems/count-pairs-with-xor-in-a-range/)
## 解法
### 思路
01字典树
- 新增：将二进制从高位开始存储到字典树中，每插入一次，就在当前节点做累加
- 查询：根据目标值，和当前值，比较当前二进制位
  - 如果目标值的当前位是0，那么当前值不可能和字典树中存储的节点对应的数异或运算后，得到比目标值小的值
  - 如果目标值的当前位是1，那么字典树中与当前值的当前位值相同，那么异或后，一定能得到比目标值小的数，同时，继续搜索为1的情况，看看是否继续有可能得个数
- 在利用这两个字典树函数的时候，因为累加的时候，需要排除当前值，所以可以循环nums数组，先查询当前值，再插入当前值
- 遍历过程中累加查询出来的结果，作为结果返回
### 代码
```java
class Solution {
    private Tire tire;

    public int countPairs(int[] nums, int low, int high) {
        this.tire = new Tire();
        int ans = 0;
        for (int num : nums) {
            ans += tire.get(num, high + 1) - tire.get(num, low);
            tire.insert(num);
        }
        return ans;
    }

    class Tire {

        private TireNode root;

        public Tire() {
            this.root = new TireNode();
        }

        public void insert(int num) {
            TireNode node = this.root;

            for (int i = 15; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.children[bit] == null) {
                    node.children[bit] = new TireNode();
                }
                node = node.children[bit];
                node.sum++;
            }
        }

        public int get(int num, int limit) {
            int sum = 0;
            TireNode node = this.root;
            for (int i = 15; i >= 0 && node != null; i--) {
                int v = (num >> i) & 1;
                if ((limit >> i & 1) != 0) {
                    if (node.children[v] != null) {
                        sum += node.children[v].sum;
                    }
                    node = node.children[v ^ 1];
                } else {
                    node = node.children[v];
                }
            }
            
            return sum;
        }
    }

    class TireNode {
        private TireNode[] children;
        private int sum;

        public TireNode() {
            this.children = new TireNode[2];
            this.sum = 0;
        }
    }
}
```
# [LeetCode_1658_将x减到0的最小操作数](https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/)
## 失败解法
### 原因
超时
### 思路
回溯
### 代码
```java
class Solution {

  private int ans = Integer.MAX_VALUE;

  public int minOperations(int[] nums, int x) {
    int ans = dfs(x, 0, nums.length - 1, 0, nums);
    return ans == Integer.MAX_VALUE ? -1 : ans;
  }

  private int dfs(int x, int l, int r, int count, int[] nums) {
    if (count > ans) {
      return Integer.MAX_VALUE;
    }

    if (l > r) {
      return Integer.MAX_VALUE;
    }

    if (x < nums[l] && x < nums[r]) {
      return Integer.MAX_VALUE;
    }

    if (x == nums[l] || x == nums[r]) {
      return count + 1;
    }

    int ans = -1;
    if (x < nums[l]) {
      return dfs(x - nums[r], l, r - 1, count + 1, nums);
    }

    if (x < nums[r]) {
      return dfs(x - nums[l], l + 1, r, count + 1, nums);
    }

    return Math.min(
            dfs(x - nums[r], l, r - 1, count + 1, nums),
            dfs(x - nums[l], l + 1, r, count + 1, nums)
    );
  }
}
```
## 解法
### 思路
双指针
- 初始化双指针l和r，l=-1，r=0
  - 代表前缀是空数组，后缀是满数组
- 然后移动left指针，直到left指针遍历完整个数组为止
- 循环内部
  - 通过leftSum + rightSum来确定当前的总和值，如果大于sum，那么将right指针向右移动
  - 如果leftSum + rightSum == sum，那么就与暂存的操作数比较最小值
  - 操作数的计算公式：`left + 1 + (n - right)`
### 代码
```java
class Solution {
    
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int sum = Arrays.stream(nums).sum();
        if (x > sum) {
            return -1;
        }

        if (x == sum) {
            return n;
        }
        
        int l = -1, r = 0, lSum = 0, rSum = sum, ans = Integer.MAX_VALUE;
        for (; l < n; l++) {
            if (l != -1) {
                lSum += nums[l];
            }
            
            while (r < n && lSum + rSum > x) {
                rSum -= nums[r++];
            }
            
            if (lSum + rSum == x) {
                ans = Math.min(ans, l + 1 + (n - r));
            }
        }
        
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
```
# [LeetCode_2490_回环句](https://leetcode.cn/problems/circular-sentence/)
## 解法
### 思路
- 切分字符串
- 遍历判断
### 代码
```java
class Solution {
    public boolean isCircularSentence(String sentence) {
        String[] words = sentence.split(" ");
        int n = words.length;
        String firstWord = words[0], lastWord = words[n - 1];
        if (!Objects.equals(lastWord.charAt(lastWord.length() - 1), firstWord.charAt(0))) {
            return false;
        }
        
        for (int i = 0; i < n - 1; i++) {
            String first = words[i], second = words[i + 1];
            if (!Objects.equals(first.charAt(first.length() - 1), second.charAt(0))) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_2496_数组中字符串的最大值](https://leetcode.cn/problems/maximum-value-of-a-string-in-an-array/)
## 解法
### 思路
- 遍历字符串数组
- 将元素根据题目要求转换成数字
- 与暂存的最大值比较获取最大值
### 代码
```java
class Solution {
    public int maximumValue(String[] strs) {
        int ans = Integer.MIN_VALUE;
        for (String str : strs) {
            ans = Math.max(ans, getNum(str));
        }
        return ans;
    }
    
    private int getNum(String str) {
        int num = 0;
        char[] cs = str.toCharArray();
        for (char c : cs) {
            if (!Character.isDigit(c)) {
                return str.length();
            }
            
            num = num * 10 + (c - '0');
        }
        
        return num;
    }
}
```
# [LeetCode_2500_删除每行中的最大值](https://leetcode.cn/problems/delete-greatest-value-in-each-row/)
## 解法
### 思路
记忆化+模拟
### 代码
```java
class Solution {
    public int deleteGreatestValue(int[][] grid) {
        int r = grid.length, c = grid[0].length, count = 0, ans = 0;
        boolean[][] memo = new boolean[r][c];
        
        while (count < c) {
            int max = Integer.MIN_VALUE;
            for (int i = 0; i < r; i++) {
                int rowMax = Integer.MIN_VALUE;
                int maxC = -1;
                for (int j = 0; j < c; j++) {
                    if (memo[i][j]) {
                        continue;
                    }

                    if (grid[i][j] > rowMax) {
                        maxC = j;
                        rowMax = grid[i][j];
                    }
                }
                
                memo[i][maxC] = true;
                max = Math.max(max, rowMax);
            }

            count++;
            ans += max;
        }
        return ans;
    }
}
```
## 解法二
### 思路
排序+模拟
### 代码
```java
class Solution {
    public int deleteGreatestValue(int[][] grid) {
        for (int[] arr : grid) {
            Arrays.sort(arr);
        }
        
        int ans = 0;
        for (int i = 0; i < grid[0].length; i++) {
            int max = Integer.MIN_VALUE;

            for (int[] arr : grid) {
                max = Math.max(max, arr[i]);
            }
            
            ans += max;
        }
        
        return ans;
    }
}
```