# [LeetCode_342_4的幂](https://leetcode-cn.com/problems/power-of-four/)
## 解法
### 思路
- 非正整数，返回false
- 32位的整数，依次左移2位来判断是否与当前值相等，左移结束如果还是不相等就返回false，否则返回true
### 代码
```java
class Solution {
    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }
        
        int bit = 1, time = 0;
        while (time < 16) {
            if (bit == n) {
                return true;
            }
            
            if (bit > n) {
                return false;
            }

            bit <<= 2;
            time++;
        }

        return false;
    }
}
```
# [LeetCode_1134_阿姆斯特朗数](https://leetcode-cn.com/problems/armstrong-number/)
## 解法
### 思路
模拟计算
### 代码
```java
class Solution {
    public boolean isArmstrong(int n) {
        int bit = 0, num = n;
        List<Integer> list = new ArrayList<>();
        while (num != 0) {
            bit++;
            int a = num % 10;
            list.add(a);
            num /= 10;
        }
        
        int sum = 0;
        for (Integer c : list) {
            sum += (int) Math.pow(c, bit);
        }
        
        return sum == n;
    }
}
```
# [LeetCode_1150_检查一个数是否在数组中占绝大多数](https://leetcode-cn.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/)
## 解法
### 思路
- 遍历计算target数，且因为是递增的，可以通过`num > target`提前结束
- 最后返回count数是否大于数组长度一半
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int count = 0;
        for (int num : nums) {
            if (num == target) {
                count++;
            } else if (num > target) {
                break;
            }
        }

        return count > nums.length / 2;
    }
}
```
## 解法二
### 思路
双指针：
- 左右指针找到等于target的子数组区间的左右边界
- 通过左右指针计算区间的长度是否大于整体长度的一半
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length, head = 0, tail = len - 1;
        while (head <= tail) {
            if (nums[head] < target) {
                head++;
            } else if (nums[head] > target) {
                return false;
            }
            
            if (nums[tail] > target) {
                tail--;
            } else if (nums[tail] < target) {
                return false;
            }
            
            if (nums[head] == nums[tail] && nums[head] == target) {
                break;
            }
        }
        
        return tail - head + 1 > len / 2;
    }
}
```
## 解法三
### 思路
二分查找：
- 2次二分查找分别找到子数组的左右边界
- 根据左右边界的坐标差值确定是否符合题目的要求
- 解法是一种范式，需要记住
### 代码
```java
class Solution {
    public boolean isMajorityElement(int[] nums, int target) {
        int len = nums.length;
        int head = 0, tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (nums[mid] >= target) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        if (nums[tail] != target) {
            return false;
        }

        int r = tail;
        head = 0;
        tail = len - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2 + 1;
            if (nums[mid] <= target) {
                head = mid;
            } else {
                tail = mid - 1;
            }
        }

        return tail - r + 1 > len / 2;
    }
}
```
# [LeetCode_1744_你能在你最喜欢的那天吃到你最喜欢的糖果吗](https://leetcode-cn.com/problems/can-you-eat-your-favorite-candy-on-your-favorite-day/)
## 解法
### 思路
- 求出能够吃到目标类型糖果最早和最晚的时间，然后判断目标时间是否落在这个区间
- 初始化前缀和数组sums用于快速判断
- queries数组中每个元素数组的三个子元素分别是
    - queries[i][0]：类型t
    - queries[i][1]：天数d，此处的d要+1，因为第0天也是可以吃的，而这里的值代表的第几天，所以如果要算天数，就要在这个值的基础上+1
    - queries[i][2]：吃的上限c
- 遍历queries数组，通过糖果类型t，确定吃完t类型糖果需要的最短和最长时间
    - 最慢：每天1颗糖吃完所有t种糖果
    - 最快：每天c颗糖吃完所有t-1种糖果，在这个天数上再+1，加1是因为：
        - 如果整除c，那么就是正好吃完t-1类糖果的天数，再加1就是吃t类的天数
        - 如果不能整除，因为时向下取整，那么也就要再加1天才能吃到t类糖果
### 代码
```java
class Solution {
    public boolean[] canEat(int[] candiesCount, int[][] queries) {
        int len = queries.length, type = candiesCount.length;
        boolean[] ans = new boolean[len];
        long[] sums = new long[type + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + candiesCount[i - 1];
        }

        for (int i = 0; i < queries.length; i++) {
            int t = queries[i][0], d = queries[i][1] + 1, c = queries[i][2];
            long l = sums[t] / c + 1, r = sums[t + 1];
            ans[i] = d >= l && d <= r;
        }

        return ans;
    }
}
```
# [LeetCode_523_连续的子数组和](https://leetcode-cn.com/problems/continuous-subarray-sum/)
## 错误解法
### 原因
超时
### 思路
前缀和+2层循环迭代
### 代码
```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int len = nums.length;
        int[] sums = new int[len + 1];
        for (int i = 1; i < len + 1; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        for (int i = 0; i < len + 1; i++) {
            for (int j = i + 2; j < len + 1; j++) {
                if ((sums[j] - sums[i]) % k == 0) {
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
- 在失败解法基础上，通过map来降低时间复杂度
- 失败解法中通过嵌套循环来计算前缀和数组中是否有2个元素的差是k的整数倍
- 如果用map，将之前所有的前缀和与k取模后得到的余数以及该坐标的值记录下来，那么在遍历获取到一个新的前缀和时，就可以直接通过取余k，得到的余数到map中去找是否有重复的
- 如果有重复的，那么这两个前缀和相减，就一定是能被k整除的，然后再看坐标之间的距离是否大于2：
    - 如果是，就是对的 
    - 如果不是，则因为当前遍历到的坐标比map中以存在的相同余数对应的坐标要大，而这种情况还不符合题目距离为2的要求，那么这个坐标就不用储存了，因为存下来覆盖了之前更小的坐标，会使得之后相同余数的坐标与当前坐标的差值变得更小
- 如果没有重复，那就把余数存储下来
- 务必还要将sum值为0的情况记录下来，这个代表从头开始累加的前缀和能够被整除的情况，它的坐标应该为-1，这样第二个元素坐标1的时候，就能够获取大于1的距离
### 代码
```java
class Solution {
    public boolean checkSubarraySum(int[] nums, int k) {
        int sum = 0;
        Map<Integer, Integer> map = new HashMap<>();
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
# [LeetCode_1165_单行键盘](https://leetcode-cn.com/problems/single-row-keyboard/)
## 解法
### 思路
- map存储字符与坐标的映射关系
- 遍历要打印的字符串，通过map计算耗时并累加
### 代码
```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        Map<Character, Integer > map = new HashMap<>();
        for (int i = 0; i < keyboard.length(); i++) {
            map.put(keyboard.charAt(i), i);
        }

        int ans = map.get(word.charAt(0));
        for (int i = 0; i < word.length() - 1; i++) {
            ans += Math.abs(map.get(word.charAt(i)) - map.get(word.charAt(i + 1)));
        }

        return ans;
    }
}
```
## 解法二
### 思路
使用数组代替解法1的map
### 代码
```java
class Solution {
    public int calculateTime(String keyboard, String word) {
        int[] bucket = new int[26];
        for (int i = 0; i < keyboard.length(); i++) {
            bucket[keyboard.charAt(i) - 'a'] = i;
        }
        
        int start = 0, ans = 0;
        for (int i = 0; i < word.length(); i++) {
            int index = bucket[word.charAt(i) - 'a'];
            ans += Math.abs(index - start);
            start = index;
        }
        
        return ans;
    }
}
```
# [LeetCode_1175_质数排列](https://leetcode-cn.com/problems/prime-arrangements/)
## 解法
### 思路
- 求质数的个数
    - `厄拉多塞筛法`求个数
- 通过排列公式求得可能个数,假如n以内有m个素数，(n - m)个其他数，总共的排列组合总数目为 m! * (n-m)!种
### 代码
```java
class Solution {
    public int numPrimeArrangements(int n) {
        boolean[] arr = new boolean[n + 1];
        int count = 0;
        for (int i = 2; i < arr.length; i++) {
            if (!arr[i]) {
                count++;
                for (int j = 2; i * j <= n; j++) {
                    arr[i * j] = true;
                }
            }
        }

        return (int)(calculate(count) * calculate(n - count) % 1000000007);
    }

    private long calculate(int count) {
        long ans = 1;
        for (int i = count; i >= 1; i--) {
            ans *= i;
            ans %= 1000000007;
        }
        return ans;
    }
}
```
# [LeetCode_525_连续数组](https://leetcode-cn.com/problems/contiguous-array/)
## 失败解法
### 原因
超时
### 思路
前缀和+2层循环
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        int[] sums = new int[nums.length + 1];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }

        int ans = 0;
        for (int i = 0; i < sums.length; i++) {
            for (int j = i + 2; j < sums.length; j += 2) {
                if ((j - i) / 2 == sums[j] - sums[i]) {
                    ans = Math.max(ans, j - i);
                }
            }
        }
        
        return ans;
    }
}
```
## 解法
### 思路
- 初始化一个hash表
    - key：当前前缀和数组中0和1的个数差值
    - value：当前前缀和数组的长度
- 初始化一个int变量sum用来存储前缀和
- 遍历nums数组
    - 更新sum变量
    - 计算当前前缀和数组的0和1的个数差值，也就是当前前缀和数组长度与2倍的sum值的差，因为数组只包含0和1
    - 然后查看map中是否有存在相同个数差值的key
        - 如果有，求当前长度与记录长度的差值，更新最大长度
        - 如果没有，记录当前差值和长度到map中
- 遍历结束，返回结果，没找到就是0
### 代码
```java
class Solution {
    public int findMaxLength(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        int sum = 0, ans = 0;
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i];
            int len = i + 1, diff = len - 2 * sum;
            if (map.containsKey(diff)) {
                ans = Math.max(ans, len - map.get(diff));
            } else {
                map.put(diff, i + 1);
            }
        }

        return ans;
    }
}
```