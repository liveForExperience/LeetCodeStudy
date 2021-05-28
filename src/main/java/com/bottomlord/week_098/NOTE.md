# [LeetCode_664_奇怪的打印机](https://leetcode-cn.com/problems/strange-printer/)
## 解法
### 思路
动态规划：
- 观察到的特征：
    - 如果只有1种单词，例“a”，那就只需要1次打印
    - 如果有2种单词，例“ab”，那就需要2次打印
    - 如果字符子串2头的字符相同，例“aba”，那也需要2次打印
    - 如果字符子串2头的字符不相同，例“abab”，那就需要3次打印，也就是在情况3的基础上选择一个子串+1
- `dp[i][j]`：i和j区间内需要打印的最少次数
- base case：单个字符的打印次数为1
- 状态转移方程：
    - 如果i和j对应的字符相同：`dp[i][j] = dp[i][j - 1]`
    - 如果i和j对应的字符不相同：`就找这个区间中两两组合的最优解`
- 返回结果：`dp[0][n - 1]`
### 代码
```java
class Solution {
    public int strangePrinter(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];
        
        for (int i = len - 1; i >= 0; i--) {
            dp[i][i] = 1;
            for (int j = i + 1; j < len; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = dp[i][j - 1];
                } else {
                    dp[i][j] = j - i + 1;
                    for (int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                    }
                }
            }
        }
        
        return dp[0][len - 1];
    }
}
```
# [LeetCode_1064_不动点](https://leetcode-cn.com/problems/fixed-point/)
## 解法
### 思路
遍历一次搞定
### 代码
```java
class Solution {
    public int fixedPoint(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (i == arr[i]) {
                return i;
            }
        }
        return -1;
    }
}
```
# [LeetCode_1065_字符串的索引对](https://leetcode-cn.com/problems/index-pairs-of-a-string/)
## 解法
### 思路
使用String的Api-indexof
### 代码
```java
class Solution {
    public int[][] indexPairs(String text, String[] words) {
        List<int[]> list = new ArrayList<>();
        for (String word : words) {
            int index = -1;
            do {
                index = text.indexOf(word, index);
                if (index != -1) {
                    list.add(new int[]{index, index + word.length() - 1});
                    index++;
                }
            } while (index != -1);
        }
        
        int[][] ans = new int[list.size()][2];
        for (int i = 0; i < list.size(); i++) {
            ans[i][0] = list.get(i)[0];
            ans[i][1] = list.get(i)[1];
        }

        Arrays.sort(ans, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }
            return x[0] - y[0];
        });
        return ans;
    }
}
```
# [LeetCode_1787_使所有区间的异或结果为零](https://leetcode-cn.com/problems/make-the-xor-of-all-segments-equal-to-zero/)
## 解法
### 思路
- 通过观察可以发现题目的要求如下：
  - `a[i] ^ a[i + 1] ^ a[i + 2] ^ a[i + 3] ... a[i + k - 1] == 0`
  - `a[i + 1] ^ a[i + 2] ^ a[i + 3] ... a[i + k - 1] ^ a[i + k] == 0`
  - 如上两个公式左右两边同时异或，就可以得到：`a[i] ^ a[i + k] = 0`
  - 所以，要获得题目要求的结果，就是要获得一个在修改后，以k为周期的周期性数组，周期长度为k，且周期内元素的异或为0
- 将数组分成k个组，每个组的元素应该是相等的，且k各组的元素值的异或和应该为0
- 动态规划：
  - `dp[i][j]`：表示处理了k中的i各组后，得到的异或值为j的情况下，最小的修改次数
  - 状态转移方程：
    - 假设需要将当前组的元素设置成x，那么这个x就可以通过题目设置的范围(1024)来进行枚举，而相对的前i-1个组处理完后得到的异或值就应该是`j ^ x`
    - 那么求的状态转移方程就是，遍历0到1024，求最小的`dp[i][j]`：`dp[i][j] = dp[i - 1][j ^ x] + size(i) - count(i, x)`
    - `size(i)`：代表第i组的元素个数
    - `count(i, x)：代表第i组中x出现的个数
    - 整个状态转移方程可以理解为：当修改完第i组后，得到的异或和为j的情况下，当前第i组的所有元素若是被修改成x，那么前i-1的所有组修改完后应该得到的是j ^ x这个数，然后得到这个状态的最小值，也就是`dp[i - 1][j ^ x]`与当前需要修改的个数的和
  - 状态压缩，因为每次状态转移只依赖前一次的状态值，所以可以将二维转为一维
  - 返回结果：`dp[0]`
### 代码
```java
class Solution {
    private final int max = 1 << 10;
    private final int infinity = Integer.MAX_VALUE / 2;

    public int minChanges(int[] nums, int k) {
        int len = nums.length;
        int[] dp = new int[max];
        Arrays.fill(dp, infinity);
        dp[0] = 0;

        for (int i = 0; i < k; i++) {
            Map<Integer, Integer> countMapping = new HashMap<>();
            int size = 0;
            for (int j = i; j < len; j += k) {
                countMapping.put(nums[j], countMapping.getOrDefault(nums[j], 0) + 1);
                size++;
            }

            int lastGroupMin = Arrays.stream(dp).min().getAsInt();
            int[] tmpDp = new int[max];
            Arrays.fill(tmpDp, lastGroupMin);

            for (int xor = 0; xor < max; xor++) {
                for (Integer x : countMapping.keySet()) {
                    tmpDp[xor] = Math.min(tmpDp[xor], dp[xor ^ x] - countMapping.get(x));
                }
            }

            for (int index = 0; index < tmpDp.length; index++) {
                tmpDp[index] += size;
            }

            dp = tmpDp;
        }

        return dp[0];
    }
}
```
<<<<<<< HEAD
# [LeetCode_LCP28_采购方案](https://leetcode-cn.com/problems/4xy4Wx/)
## 失败解法
### 原因
超时
### 思路
2层循环累加，同时取模
### 代码
```java
class Solution {
    public int purchasePlans(int[] nums, int target) {
        int len = nums.length, count = 0;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                if (nums[i] + nums[j] <= target) {
                    count = (count + 1) % 1000000007;
                }
            }
        }
        return count;            
    }
}
```
## 解法
### 思路
排序+双指针
### 代码
```java
class Solution {
  public int purchasePlans(int[] nums, int target) {
    int len = nums.length, head = 0, tail = len - 1, count = 0;
    Arrays.sort(nums);
    while (head < tail) {
      if (nums[head] + nums[tail] > target) {
        tail--;
      } else {
        count += tail - head;
        head++;
      }

      count %= 1000000007;
    }

    return count;
  }
}
```
# [LeetCode_1190_反转每对括号间的子串](https://leetcode-cn.com/problems/reverse-substrings-between-each-pair-of-parentheses/)
## 解法
### 思路
栈：
- 遍历字符串，将字符依次入栈，直到遇到第一个右括号
- 依次弹栈，直到遇到第一个左括号
- 反转弹出的字符串
- 再依次从头压入栈中
- 继续如上流程操作，直到字符串遍历结束
### 代码
```java
class Solution {
    public String reverseParentheses(String s) {
        char[] cs = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (c != ')') {
                stack.push(c);
            } else {
                StringBuilder sb = new StringBuilder();
                while (!stack.isEmpty()) {
                    char pushedC = stack.pop();
                    if (pushedC != '(') {
                        sb.append(pushedC);
                    } else {
                        break;
                    }
                }
                
                for (int j = 0; j < sb.length(); j++) {
                    stack.push(sb.charAt(j)); 
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character c : stack) {
            sb.append(c);
        }
        return sb.toString();
    }
}
```
# [LeetCode_461_汉明距离](https://leetcode-cn.com/problems/hamming-distance/)
## 解法
### 思路
两数异或后求二进制位是1的个数
### 代码
```java
class Solution {
    public int hammingDistance(int x, int y) {
        int xor = x ^ y;
        int count = 0;
        while (xor != 0) {
            count++;
            xor = xor & (xor - 1);
        }
        
        return count;
    }
}
```
# [LeetCode1084_最小元素各数位之和](https://leetcode-cn.com/problems/sum-of-digits-in-the-minimum-number/)
## 解法
### 思路
- 遍历求最小值
- 求各数位之和
- 奇数返回1，偶数返回0
### 代码
```java
class Solution {
    public int sumOfDigits(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int min = Arrays.stream(nums).min().getAsInt(), ans = 0;
        while (min != 0) {
            int num = min % 10;
            min /= 10;
            ans += num;
        }
        
        return (ans & 1) == 0 ? 1 : 0;
    }
}
```
# [LeetCode_1086_前五科的均分](https://leetcode-cn.com/problems/high-five/)
## 解法
### 思路
- 初始化hash表，key为id，value为大顶堆，用于存放相同id学生的分数
- 遍历二维数组，填充hash表
- 遍历hash表，求每个学生最高5项的最高分和的平均分
### 代码
```java
class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, PriorityQueue<Integer>> map = new HashMap<>();
        for (int[] item : items) {
            int id = item[0], score = item[1];
            PriorityQueue<Integer> queue = map.getOrDefault(id, new PriorityQueue<>(Comparator.reverseOrder()));
            queue.offer(score);
            map.put(id, queue);
        }
        
        int[][] ans = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, PriorityQueue<Integer>> entry : map.entrySet()) {
            int id = entry.getKey(), count = 5, sum = 0;
            PriorityQueue<Integer> queue = entry.getValue();
            while (count > 0 && !queue.isEmpty()) {
                sum += queue.poll();
                count--;
            }
            
            ans[index][0] = id;
            ans[index][1] = sum / 5;
            index++;
        }
        
        return ans;
    }
}
```
## 解法
### 思路
使用桶替换大顶堆
### 代码
```java
class Solution {
    public int[][] highFive(int[][] items) {
        Map<Integer, int[]> map = new HashMap<>();
        for (int[] item : items) {
            int id = item[0], score = item[1];
            int[] scores = map.getOrDefault(id, new int[101]);
            scores[score]++;
            map.put(id, scores);
        }

        int[][] ans = new int[map.size()][2];
        int index = 0;
        for (Map.Entry<Integer, int[]> entry : map.entrySet()) {
            int id = entry.getKey(), sum = 0;
            int[] scores = entry.getValue();
            for (int i = scores.length - 1, count = 5; i > 0 && count > 0; i--) {
                if (scores[i] > 0) {
                    while (scores[i] > 0 && count > 0) {
                        scores[i]--;
                        count--;
                        sum += i;
                    }
                }
            }

            ans[index][0] = id;
            ans[index][1] = sum / 5;
            index++;
        }

        return ans;
    }
}
```
# [LeetCode_1099_小于K的两数之和](https://leetcode-cn.com/problems/two-sum-less-than-k/)
## 解法
### 思路
排序+双指针
### 代码
```java
class Solution {
  public int twoSumLessThanK(int[] nums, int k) {
    Arrays.sort(nums);
    int sum = -1, head = 0, tail = nums.length - 1;
    while (head < tail) {
      if (nums[head] + nums[tail] >= k) {
        tail--;
      } else {
        sum = Math.max(nums[head++] + nums[tail], sum);
      }
    }
    return sum;
  }
}
```
# [LeetCode_477_汉明距离总和](https://leetcode-cn.com/problems/total-hamming-distance/)
## 解法
### 思路
- 所有数字之间的汉明距离总和可以理解为，每一位上的1和0出现的个数的乘积的总和
- 求所有数字32位上的1和0的个数，乘积后求和
### 代码
```java
class Solution {
    public int totalHammingDistance(int[] nums) {
        int sum = 0;
        for (int i = 0; i < 32; i++) {
            int count = 0;
            for (int num : nums) {
                count += (num >> i) & 1;
            }
            
            sum += count * (nums.length - count);
        }
        return sum;
    }
}
```
# [LeetCode_1114_按序打印](https://leetcode-cn.com/problems/print-in-order/)
## 解法
### 思路
2个线程间共享变量+自旋等待
### 代码
```java
class Foo {
    private volatile boolean twoCanRun;
    private volatile boolean threeCanRun;
    public Foo() {

    }

    public void first(Runnable printFirst) throws InterruptedException {
        printFirst.run();
        twoCanRun.set(true);
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (!twoCanRun.get()) {}
        printSecond.run();
        threeCanRun.set(true);
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (!threeCanRun.get()) {}
        printThird.run();
    }
}
```
## 解法二
### 思路
信号量
### 代码
```java
class Foo {
  private Semaphore s1 = new Semaphore(0), s2 = new Semaphore(0);
  public Foo() {

  }

  public void first(Runnable printFirst) throws InterruptedException {
    printFirst.run();
    s1.release();
  }

  public void second(Runnable printSecond) throws InterruptedException {
    s1.acquire();
    printSecond.run();
    s2.release();
  }

  public void third(Runnable printThird) throws InterruptedException {
    s2.acquire();
    printThird.run();
  }
}
```
## 解法三
### 思路
volatile + lock
### 代码
```java
class Foo {
    private volatile int n = 0;
    private final Object lock = new Object();
    public Foo() {}

    public void first(Runnable printFirst) throws InterruptedException {
        synchronized (lock) {
            printFirst.run();
            n++;
            lock.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock) {
            while (n != 1) {
                lock.wait();
            }
            printSecond.run();
            n++;
            lock.notifyAll();
        }
    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock) {
            while (n != 2) {
                lock.wait();
            }
        }
        printThird.run();
    }
}
```