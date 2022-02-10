# [LeetCode_1405_最长快乐字符串](https://leetcode-cn.com/problems/longest-happy-string/)
## 解法
### 思路
贪心
- 根据字符串个数非升序排序
- 根据题目要求循环拼接字符
- 循环结束返回拼接的字符串
### 代码
```java
class Solution {
    public String longestDiverseString(int a, int b, int c) {
        int[][] pairs = new int[][]{{a, 0}, {b, 1}, {c, 2}};
        StringBuilder sb = new StringBuilder();

        while (true) {
            Arrays.sort(pairs, (x, y) -> y[0] - x[0]);
            boolean hasNext = false;
            for (int[] pair : pairs) {
                if (pair[0] <= 0) {
                    continue;
                }

                char ch = (char)(pair[1] + 'a');
                int len = sb.length();
                if (len >= 2 &&
                    sb.charAt(len - 2) == ch &&
                    sb.charAt(len - 1) == ch) {
                    continue;
                }

                hasNext = true;
                sb.append(ch);
                pair[0]--;
                break;
            }

            if (!hasNext) {
                break;
            }
        }
        
        return sb.toString();
    }
}
```
# [LeetCode_2160_拆分数位后四位数字的最小和](https://leetcode-cn.com/problems/minimum-sum-of-four-digit-number-after-splitting-digits/)
## 解法
### 思路
- 将数字拆分成4个整数
- 非降序排序4个整数
- 返回1和3以及2和4组成的两个数的和
### 代码
```java
class Solution {
    public int minimumSum(int num) {
        int[] arr = new int[4];
        int index = 0;
        while (num > 0) {
            arr[index++] = num % 10;
            num /= 10;
        }
        Arrays.sort(arr);
        return arr[0] * 10 + arr[2] + arr[1] * 10 + arr[3];
    }
}
```
# [LeetCode_1001_网格照明](https://leetcode-cn.com/problems/grid-illumination/)
## 解法
### 思路
- 使用hash表存储lamps数组中x，y所对应的坐标的横竖撇捺4条延伸线的亮灯状态
  - key对应x,y坐标对应的横竖撇捺
  - value对应出现的次数，这个次数在query的时候用来做累减
- 遍历query数组
  - 根据4个map中是否存在该坐标的对应key，来判断是否亮灯
  - 同时对该坐标的四周做灭灯处理，同时处理value，如果value为0，则从map中删除key
- 最终返回亮灯状态对应的数组
- 需要注意：
  - 在query后关闭的，必须是已经打开的开关，如果是因为开关而照亮的灯，则关闭没有作用，不能关闭，所以需要有一个set来记录开过坐标，而因为二维数组是一个n * n的二维数组，所以可以用n * x + y来唯一代表每一个坐标
  - 在query后关闭时，要对新生成的四周一圈的坐标做越界判断，否则可能会导致错误的行列value被减去而应该被减去的value却因为判重逻辑而被跳过
### 代码
```java
class Solution {
    private final int[][] dirs = new int[][]{{0, 0}, {0, -1}, {0, 1}, {-1, 0}, {-1, -1}, {-1, 1}, {1, 0}, {1, -1}, {1, 1}};

    public int[] gridIllumination(int n, int[][] lamps, int[][] queries) {
        Map<Integer, Integer> row = new HashMap<>(), col = new HashMap<>(),
                left = new HashMap<>(), right = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        for (int[] lamp : lamps) {
            int x = lamp[0], y = lamp[1],
                    l = x + y, r = x - y;

            if (set.contains(x * n + y)) {
                continue;
            }

            increase(row, x);
            increase(col, y);
            increase(left, l);
            increase(right, r);
            set.add(x * n + y);
        }

        int[] ans = new int[queries.length];
        int index = 0;
        for (int[] query : queries) {
            int x = query[0], y = query[1],
                    l = x + y, r = x - y;

            if (row.containsKey(x) || col.containsKey(y) || left.containsKey(l) || right.containsKey(r)) {
                ans[index] = 1;
            }

            for (int[] dir : dirs) {
                int nx = x + dir[0], ny = y + dir[1],
                    nl = nx + ny, nr = nx - ny;

                if (nx < 0 || nx >= n || ny < 0 || ny >= n) {
                    continue;
                }

                if (set.contains(nx * n + ny)) {
                    set.remove(nx * n + ny);
                    decrease(row, nx);
                    decrease(col, ny);
                    decrease(left, nl);
                    decrease(right, nr);
                }
            }

            index++;
        }

        return ans;
    }

    private void increase(Map<Integer, Integer> map, Integer num) {
        map.put(num, map.getOrDefault(num, 0) + 1);
    }

    private void decrease(Map<Integer, Integer> map, Integer num) {
        Integer value = map.getOrDefault(num, 0);
        if (value <= 1) {
            map.remove(num);
            return;
        }

        map.put(num, map.get(num) - 1);
    }
}
```
# [LeetCode_2164_对奇偶下标分别排序](https://leetcode-cn.com/problems/sort-even-and-odd-indices-independently/)
## 解法
### 思路
- 将数组分成奇偶2个子数组
- 将奇偶坐标对应的值放入子数组中
- 对两个子数组分别排序
- 将两个子数交叉合并为一个数组返回
- 需要注意奇偶性对应的是下标值不是实际的奇偶顺序
### 代码
```java
class Solution {
    public int[] sortEvenOdd(int[] nums) {
        int n = nums.length;
        boolean odd = n % 2 == 1;
        int evenLen = odd ? n / 2 + 1 : n / 2,
            oddLen = n / 2;

        int[] odds = new int[oddLen], evens = new int[evenLen];
        int oddIndex = 0, evenIndex = 0;
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                evens[evenIndex++] = nums[i];
            } else {
                odds[oddIndex++] = nums[i];
            }
        }

        Arrays.sort(odds);
        Arrays.sort(evens);
        
        oddIndex = oddLen - 1;
        evenIndex = 0;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                nums[i] = evens[evenIndex++];
            } else {
                nums[i] = odds[oddIndex--];
            }
        }
        
        return nums;
    }
}
```
# [LeetCode_offerII003_前n个数字二进制中1的个数](https://leetcode-cn.com/problems/w3tCBm/)
## 解法
### 思路
- 初始化数组用于储存每个数字的1的个数
- 从1开始遍历，逐个计算1的个数，并储存到数组中
- 遍历结束，返回数组
### 代码
```java
class Solution {
    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            ans[i] = count(i);
        }
        return ans;
    }

    private int count(int n) {
        int bit = 1, count = 0;
        while (bit <= n) {
            if ((bit & n) != 0) {
                count++;
            }
            
            bit <<= 1;
        }
        
        return count;
    }
}
```
## 解法二
### 思路
- 通过观察可以发现：
  - 偶数二进制1的个数和它除以2得到的二进制1的个数是一样的，因为偶数的最低位一定是0，然后除以二等于无符号右移1位，所以1的个数不会变
  - 奇数二进制1的个数和比它小的最近偶数相比，多一个1，因为奇数比偶数大1，而这个1就体现在最低位的不同
- 所以可以当做一个简单的动态规划来看待：
  - dp[i]：表示值为i的二进制中1的个数
  - 状态转移方程：
    - 当i为奇数的时候：dp[i] = dp[i - 1] + 1
    - 当i为偶数的时候：dp[i] = dp[i / 2]
  - base case：
    - dp[0] = 0
### 代码
```java
class Solution {
    public int[] countBits(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                dp[i] = dp[i / 2];
            } else {
                dp[i] = dp[i - 1] + 1;
            }
        }
        
        return dp;
    }
}
```
# [LeetCode_offerII6_排序数组中两个数字之和](https://leetcode-cn.com/problems/kLl5u1/)
## 解法
### 思路
使用map
### 代码
```java
class Solution {
  public int[] twoSum(int[] numbers, int target) {
    Map<Integer, Integer> map = new HashMap<>();
    for (int i = 0; i < numbers.length; i++) {
      int num = numbers[i];
      if (map.containsKey(target - num)) {
        return new int[]{Math.min(i, map.get(target - num)), Math.max(i, map.get(target - num))};
      }
      map.put(num, i);
    }
    return null;
  }
}
```
## 解法二
### 思路
- 因为是升序的数组，所以一定没有重复元素
- 又有序就可以使用双指针
### 代码
```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        int head = 0, tail = numbers.length - 1;
        while (head < tail) {
            int sum = numbers[head] + numbers[tail];
            if (sum == target) {
                return new int[]{head, tail};
            } else if (sum > target) {
                tail--;
            } else {
                head++;
            }
        }
        
        return null;
    }
}
```
# [LeetCode_1447_最简分数](https://leetcode-cn.com/problems/simplified-fractions/)
## 解法
### 思路
- 定义最大公约数方程
- 2层嵌套遍历，外层确定分母，内层确定分子
- 判断分母和分子的最大公约数是否是1，如果是就是最简分数，存储到list中
- 循环结束，返回list
### 代码
```java
class Solution {
  public List<String> simplifiedFractions(int n) {
    List<String> ans = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      for (int j = i + 1; j <= n; j++) {
        if (gcd(i, j) == 1) {
          ans.add(i + "/" + j);
        }
      }
    }
    return ans;
  }

  private int gcd(int x, int y) {
    return y == 0 ? x : gcd(y, x % y);
  }
}
```
# [LeetCode_offerII12_左右两边子数组的和相等](https://leetcode-cn.com/problems/tvdfij/)
## 解法
### 思路
- 求数组总和
- 判断特殊情况：如果总和减去第一个元素值，等于0，那就返回第一个坐标，意思是说这个数组的剩余元素相加等于0，以第一个元素为中间坐标，两侧相等
- 循环遍历数组，退出条件是遍历到倒数第二个元素。
- 循环过程中累加数组值，然后判断是否当前累加值和后一个元素之后的所有元素之和相等，这个等式依赖总和来表示，如果判断成立，就直接返回当前坐标的后一个坐标
- 循环结束，如果没有找到坐标，那么就和循环开始前一样，判断一下数组是否以最后一个元素为中心坐标，如果是就返回最后一个元素
- 如果都没有找到，就返回-1
### 代码
```java
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = Arrays.stream(nums).sum();
        
        if (sum - nums[0] == 0) {
            return 0;
        }
        
        int curSum = 0;
        for (int i = 0; i < nums.length - 1; i++) {
            curSum += nums[i];
            if (sum - nums[i + 1] == curSum * 2) {
                return i + 1;
            }
        }
        
        if (sum - nums[nums.length - 1] == 0) {
            return nums.length - 1;
        }

        return -1;
    }
}
```
## 解法二
### 思路
- 因为有第一个和倒数第一个元素这样的特殊情况，所以可以换一种思路
- 使用两个变量，一个是数组总和sum，一个是遍历的累加总和cur
- 然后sum在循环过程中不断累减，cur不断累加，这两个值是否相等就可以匹配题目的中心坐标两侧相等的要求，只不过需要在累减后但没有累加前的这个时间点进行比较，如果相等就返回当前坐标
- 也就相当于解法一，遍历的i代表的是中心坐标左侧的最后一个元素，而当前解法将i对应的元素当做是那个中心坐标
### 代码
```java
class Solution {
    public int pivotIndex(int[] nums) {
        int sum = 0, cur = 0;
        for (int num : nums) {
            sum += num;
        }

        for (int i = 0; i < nums.length; i++) {
            sum -= nums[i];
            if (sum == cur) {
                return i;
            }
            cur += nums[i];
        }
        
        return -1;
    }
}
```
# [LeetCode_offer18_有效的回文](https://leetcode-cn.com/problems/XltzEq/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
public boolean isPalindrome(String s) {
        char[] cs = s.toCharArray();
        int head = 0, tail = s.length() - 1;
        while (head < tail) {
            while (head < tail && !valid(cs[head])) {
                head++;
            }

            while (head < tail && !valid(cs[tail])) {
                tail--;
            }

            if (head >= tail) {
                return true;
            }

            if (toLower(cs[head]) != toLower(cs[tail])) {
                return false;
            }

            head++;
            tail--;
        }

        return true;
    }

    private boolean valid(char c) {
        return isNum(c) || isAlpha(c);
    }

    private boolean isNum(char c) {
        return c <= '9' && c >= '0';
    }

    private boolean isAlpha(char c) {
        return isLower(c) || isUpper(c);
    }

    private boolean isUpper(char c) {
        return c <= 'Z' && c >= 'A';
    }

    private boolean isLower(char c) {
        return c <= 'z' && c >= 'a';
    }

    private char toLower(char c) {
        if (isUpper(c)) {
            return (char)(c + 32);
        }
        
        return c;
    }
}
```