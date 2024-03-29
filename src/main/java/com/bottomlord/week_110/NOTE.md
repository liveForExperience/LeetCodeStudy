# [LeetCode_526_优美的排列](https://leetcode-cn.com/problems/beautiful-arrangement/)
## 解法
### 思路
回溯：
- 用一个可变二维数组来存储每个坐标对应的符合优美排列的数字
  - 2层循环
  - 外层确定坐标
  - 内层确定坐标元素
  - 判断内外层的值是否能组成优美排列，如果可以就把内层值放在二维数组对应的list中
- 用一个记忆化数组来记录每次回溯时候的对应数字的选择状态
- 回溯：
  - 参数包含：坐标，记忆化数组，可变二维数组
  - 退出条件：坐标超过n
  - 回溯依赖记忆化数组中对应坐标的状态
  - 过程：循环当前坐标的所有可选元素，这些元素从二位可变数组中获取，然后根据记忆化数组判断这个元素是否已经用过，如果没有就标记并递归，返回的时候再重置该元素状态
  - 如果坐标超过n，那么也说明之前n个元素都符合了优美排列的要求，返回1，不断返回不断累加，最终返回所有的排列值
### 代码
```java
class Solution {
public int countArrangement(int n) {
        List<Integer>[] dict = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            dict[i] = new ArrayList<>();
            for (int j = 1; j <= n; j++) {
                if (i % j == 0 || j % i == 0) {
                    dict[i].add(j);
                }
            }
        }

        return backTrack(1, n, new boolean[n + 1], dict);
    }

    private int backTrack(int index, int n, boolean[] memo, List<Integer>[] dict) {
        if (index > n) {
            return 1;
        }

        List<Integer> list = dict[index];
        int count = 0;
        for (int num : list) {
            if (!memo[num]) {
                memo[num] = true;
                count += backTrack(index + 1, n, memo, dict);
                memo[num] = false;
            }
        }

        return count;
    }
}
```
# [LeetCode_1470_1_重新排列数组](https://leetcode-cn.com/problems/shuffle-the-array/)
## 解法
### 思路
- 初始化新的等长数组
- 初始化2个坐标变量，分别对应x和y元素的序列，从0开始
- 重新填充元素，填充的时候：
  - x的新坐标等于，x序列 * 2
  - y的新坐标等于，y序列 * 2 + 1
### 代码
```java
class Solution {
    public int[] shuffle(int[] nums, int n) { 
        int[] arr = new int[nums.length];
        int i1 = 0, i2 = 0;
        for (int i = 0; i < 2 * n; i++) {
            if (i < n) {
                arr[i1++ * 2] = nums[i];
            } else {
                arr[i2++ * 2 + 1] = nums[i];
            }
        }

        return arr;
    }
}
```
# [LeetCode_1474_删除链表M个节点后的N个节点](https://leetcode-cn.com/problems/delete-n-nodes-after-m-nodes-of-a-linked-list/)
## 解法
### 思路
- 遍历链表，将保留的元素放入列表
- 遍历列表重新生产链表
- 返回链表头结点
### 代码
```java
class Solution {
  public ListNode deleteNodes(ListNode head, int m, int n) {
    List<Integer> list = new ArrayList<>();
    ListNode node = head;
    int index = 0;
    while (node != null) {
      if (index < m) {
        list.add(node.val);
      }

      if (index == m + n - 1) {
        index = 0;
      } else {
        index++;
      }

      node = node.next;
    }

    ListNode fake = new ListNode(0),
            pre = fake;
    for (int num : list) {
      pre.next = new ListNode(num);
      pre = pre.next;
    }

    return fake.next;
  }
}
```
## 解法二
### 思路
- 使用index和前置指针及当前指针
- 遍历链表的过程中通过指针来操作删除或者保留的动作
- 注意index的重置
### 代码
```java
class Solution {
    public ListNode deleteNodes(ListNode head, int m, int n) {
        int index = 0;
        ListNode fake = new ListNode(0), pre = fake, node = head;
        fake.next = head;
        while (node != null) {
            if (index < m) {
                pre = node;
                node = node.next;
            } else if (index < m + n) {
                node = node.next;
                pre.next = node;
            }
            
            index++;
            
            if (index == m + n) {
                index = 0;
            }
        }
        
        return fake.next;
    }
}
```
# [LeetCode_551_学生出勤记录I](https://leetcode-cn.com/problems/student-attendance-record-i/)
## 解法
### 思路
模拟：
- 初始化ac和lc，分别对应缺勤和迟到的状态计数值
- 遍历字符串，根据不同状态累加计数值
  - 如果是A，累加ac，重置lc
  - 如果是L，累加lc
  - 如果是P，重置lc
  - 然后判断ac和lc是否有满足题目要求的情况，如果有就返回false，否则直接返回true
### 代码
```java
class Solution {
    public boolean checkRecord(String s) {
        int ac = 0, lc = 0;
        char[] cs = s.toCharArray();

        for (char c : cs) {
            if (c == 'A') {
                ac++;
                lc = 0;
            } else if (c == 'L') {
                lc++;
            } else {
                lc = 0;
            }

            if (ac >= 2 || lc >= 3) {
                return false;
            }
        }
        
        return true;
    }   
}
```
# [LeetCode_1475_商品折扣后的最终价格](https://leetcode-cn.com/problems/final-prices-with-a-special-discount-in-a-shop/)
## 解法
### 思路
- 根据题目要求，就是找到元素后面第一个比自身小的值
- 2重循环数组，外层确定要处理的元素，内层找到比自身小的后置元素
### 代码
```java
class Solution {
  public int[] finalPrices(int[] prices) {
    int n = prices.length;
    int[] ans = new int[n];

    for (int i = 0; i < n; i++) {
      int price = prices[i];
      for (int j = i + 1; j < n; j++) {
        if (prices[j] <= price) {
          price -= prices[j];
          break;
        }
      }

      ans[i] = price;
    }

    return ans;
  }
}
```
# [LeetCode_1491_去掉最低工资和最高工资后的平均工资](https://leetcode-cn.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/)
## 解法
### 思路
- 遍历数组，分别：
  - 找到最大值
  - 找到最小值
  - 累加总和
- 最后计算(总和-最大值-最小值) / (总数 - 2)
### 代码
```java
class Solution {
    public double average(int[] salary) {
        double min = Integer.MAX_VALUE, max = Integer.MIN_VALUE, sum = 0;
        for (int num : salary) {
            min = Math.min(min, num);
            max = Math.max(max, num);
            sum += num;
        }
        
        return (sum - max - min) / (salary.length - 2);
    }
}
```
# [LeetCode_1496_判断路径是否相交](https://leetcode-cn.com/problems/path-crossing/)
## 解法
### 思路
- 模拟行走路程
- 记录路过的路径
- 判断当前路径是否在记录中存在
- 注意需要将起始路径也记录进去
### 代码
```java
class Solution {
    public boolean isPathCrossing(String path) {
        int row = 0, col = 0;
        Set<String> set = new HashSet<>();
        set.add(row + ":" + col);

        char[] cs = path.toCharArray();
        for (char c : cs) {
            if (c == 'N') {
                row--;
            } else if (c == 'S') {
                row++;
            } else if (c == 'E') {
                col++;
            } else {
                col--;
            }
            
            String position = row + ":" + col;
            if (set.contains(position)) {
                return true;
            }
            
            set.add(position);
        }
        
        return false;
    }
}
```
## 解法二
### 思路
用二位数组代替set记录状态
### 代码
```java
class Solution {
    public boolean isPathCrossing(String path) {
        boolean[][] memo = new boolean[2001][2001];
        int row = 0, col = 0;
        memo[row + 1000][col + 1000] = true;

        char[] cs = path.toCharArray();
        for (char c : cs) {
            if (c == 'N') {
                row--;
            } else if (c == 'S') {
                row++;
            } else if (c == 'E') {
                col++;
            } else {
                col--;
            }

            if (memo[row + 1000][col + 1000]) {
                return true;
            }

            memo[row + 1000][col + 1000] = true;
        }
        
        return false;
    }
}
```
## 解法三
### 思路
在解法一的基础上，不使用字符串而是数字来代替，row * 10000 + col
### 代码
```java
class Solution {
    public boolean isPathCrossing(String path) {
        int row = 0, col = 0;
        Set<Integer> set = new HashSet<>();
        set.add(0);

        char[] cs = path.toCharArray();
        for (char c : cs) {
            if (c == 'N') {
                row--;
            } else if (c == 'S') {
                row++;
            } else if (c == 'E') {
                col++;
            } else {
                col--;
            }

            Integer position = row * 10000 + col;
            if (set.contains(position)) {
                return true;
            }

            set.add(position);
        }

        return false;
    }
}
```
# [LeetCode_552_学生出勤记录II](https://leetcode-cn.com/problems/student-attendance-record-ii/)
## 解法
### 思路
动态规划：
- dp[i][j][k]：三个维度对应情况下有效的个数
  - i：第i天
  - j：连续迟到天数
  - k：缺勤天数
- 状态转移方程：枚举所有情况
- base case：
  - 枚举第一天有效天数的情况
  - dp[1][0][0] = dp[1][1][0] = dp[1][0][1] = 1;
- 最终返回：
```bash
dp[n][0][0] +
dp[n][1][0] +
dp[n][2][0] +
dp[n][0][1] +
dp[n][1][1] +
dp[n][2][1
```
- 过程：从2开始循环，进行状态转移
### 代码
```java
class Solution {
    public int checkRecord(int n) {
        int mod = 1000000007;
        long[][][] dp = new long[n + 1][3][2];
        dp[1][0][0] = dp[1][1][0] = dp[1][0][1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i][0][0] = (dp[i][0][0] + dp[i - 1][0][0]) % mod;
            dp[i][0][0] = (dp[i][0][0] + dp[i - 1][1][0]) % mod;
            dp[i][0][0] = (dp[i][0][0] + dp[i - 1][2][0]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][0][1]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][1][1]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][2][1]) % mod;

            dp[i][1][0] = (dp[i][1][0] + dp[i - 1][0][0]) % mod;
            dp[i][2][0] = (dp[i][2][0] + dp[i - 1][1][0]) % mod;
            dp[i][1][1] = (dp[i][1][1] + dp[i - 1][0][1]) % mod;
            dp[i][2][1] = (dp[i][2][1] + dp[i - 1][1][1]) % mod;

            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][0][0]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][1][0]) % mod;
            dp[i][0][1] = (dp[i][0][1] + dp[i - 1][2][0]) % mod;
        }

        return (int) ((dp[n][0][0] +
                dp[n][1][0] +
                dp[n][2][0] +
                dp[n][0][1] +
                dp[n][1][1] +
                dp[n][2][1]) % mod);
    }
}
```
# [LeetCode_345_翻转字符串中的元音字母](https://leetcode-cn.com/problems/reverse-vowels-of-a-string/)
## 解法
### 思路
- 头尾指针同时向内部移动，找到元音字母后进行换位
- 直到头尾指针相遇
### 代码
```java
class Solution {
    public String reverseVowels(String s) {
        Set<Character> set = new HashSet<>();
        set.add('a');
        set.add('A');
        set.add('e');
        set.add('E');
        set.add('i');
        set.add('I');
        set.add('o');
        set.add('O');
        set.add('u');
        set.add('U');
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (!set.contains(cs[head]) && head < tail) {
                head++;
            }

            while (!set.contains(cs[tail]) && head < tail) {
                tail--;
            }
            
            char tmp = cs[head];
            cs[head] = cs[tail];
            cs[tail] = tmp;

            head++;
            tail--;
        }
        
        return new String(cs);
    }
}
```
# [LeetCode_1502_判断能否形成等差数列](https://leetcode-cn.com/problems/can-make-arithmetic-progression-from-sequence/)
## 解法
### 思路
- 排序
- 遍历比较
### 代码
```java
class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }

        return true;
    }
}
```
# [LeetCode_541_翻转字符串II](https://leetcode-cn.com/problems/reverse-string-ii/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public String reverseStr(String s, int k) {
        char[] cs = s.toCharArray();
        int index = 0, n = s.length();
        while (index < n) {
            int head = index, tail = index + k - 1;
            
            if (tail >= n) {
                tail = n - 1;
            }

            while (head < tail) {
                char c = cs[head];
                cs[head] = cs[tail];
                cs[tail] = c;

                head++;
                tail--;
            }

            index += 2 * k;
        }

        return new String(cs);
    }
}
```
# [LeetCode_1507_转变日期格式](https://leetcode-cn.com/problems/reformat-date/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    private Map<String, String> map = new HashMap<>();
    private String[] months = new String[]{"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"};
    {
        for (int i = 0; i < months.length; i++) {
            map.put(months[i], i < 9 ? "0" + (i + 1) : "" + (i + 1));
        }
    }
    
    public String reformatDate(String date) {
        String[] factors = date.split(" ");

        String dayStr = factors[0], monthStr = factors[1], yearStr = factors[2];

        return yearStr + "-" + map.get(monthStr) + "-" + getDay(dayStr);
    }
    
    private String getDay(String dayStr) {
        return dayStr.length() == 3 ? "0" + dayStr.charAt(0) : dayStr.substring(0, 2);
    }
}
```
# [LeetCode_443_压缩字符串](https://leetcode-cn.com/problems/string-compression/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
  public int compress(char[] chars) {
    int i1 = 0, n = chars.length, count = 0;
    char c = chars[0];
    for (int i = 0; i < n; i++) {
      if (c == chars[i]) {
        count++;
      } else {
        i1++;
        c = chars[i];
        if (count != 1) {
          char[] ccs = Integer.toString(count).toCharArray();
          for (char cc : ccs) {
            chars[i1++] = cc;
          }
          count = 1;
        }
        chars[i1] = c;
      }
    }

    i1++;
    if (count == 1) {
      return i1;
    }

    char[] ccs = Integer.toString(count).toCharArray();
    for (char cc : ccs) {
      chars[i1++] = cc;
    }

    return i1;
  }
}
```
# [LeetCode_789_逃脱阻碍者](https://leetcode-cn.com/problems/escape-the-ghosts/)
## 解法
### 思路
- 如果阻碍者离终点的距离超过逃脱者，那么就是false，否则就是true
- 这里的距离就用曼哈顿距离来计算：|x1 - x2| + |y1 - y2|
### 代码
```java
class Solution {
    public boolean escapeGhosts(int[][] ghosts, int[] target) {
        int distance = getManhattanDistance(0, 0, target[0], target[1]);
        for (int[] ghost : ghosts) {
            if (getManhattanDistance(ghost[0], ghost[1], target[0], target[1]) - distance <= 0) {
                return false;
            }
        }

        return true;
    }

    private int getManhattanDistance(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }
}
```