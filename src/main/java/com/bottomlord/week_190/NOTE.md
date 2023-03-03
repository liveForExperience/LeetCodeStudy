# [LeetCode_1144_递减元素使数组呈锯齿状](https://leetcode.cn/problems/decrease-elements-to-make-array-zigzag/)
## 解法
### 思路
- 遍历nums
- 根据题目要求计算奇偶坐标对应元素与相邻元素之间可能需要减去的差值
- 累加
- 遍历结束后返回奇偶坐标累加值的最小值作为结果返回
### 代码
```java
class Solution {
    public int movesToMakeZigzag(int[] nums) {
        int n = nums.length, odd = 0, even = 0;
        for (int i = 0; i < nums.length; i++) {
            int left = i == 0 ? Integer.MAX_VALUE : nums[i - 1],
                right = i == n - 1 ? Integer.MAX_VALUE : nums[i + 1],
                cur = nums[i], diff = Math.max(cur - Math.min(left, right) + 1, 0);
            if (i % 2 == 0) {
                even += diff;
            } else {
                odd += diff;
            }
        }
        
        return Math.min(even, odd);
    }
}
```
# [LeetCode_826_安排工作已达到最大收益](https://leetcode.cn/problems/most-profit-assigning-work/)
## 解法
### 思路
贪心：
- 生成坐标数组is，根据profit数组从大到小排序
- O(N^2)复杂度的2层迭代，外层遍历work数组，内层遍历is，然后比较当前difficulty元素是否小于等于work元素值，如果是就累加profit并终止当前work的计算
### 代码
```java
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        Integer[] is = new Integer[n];
        for (int i = 0; i < n; i++) {
            is[i] = i;
        }
        
        Arrays.sort(is, (x, y) -> profit[y] - profit[x]);

        int sum = 0;
        for (int w : worker) {
            for (Integer i : is) {
                if (w >= difficulty[i]) {
                    sum += profit[i];
                    break;
                }
            }
        }
        
        return sum;
    }
}
```
## 解法二
### 思路
根据解法一分析，解法一中的worker如果可以升序排列，难度也可以升序排列，那么低worker值的元素遍历过后得到的最大利润值，也一定是当前worker还没开始继续往后遍历时候的最大利润值，这也就意味着之前的遍历无需重复计算了。
### 代码
```java
class Solution {
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] arr = new int[n][2];
        for (int i = 0; i < arr.length; i++) {
            arr[i][0] = difficulty[i];
            arr[i][1] = profit[i];
        }

        Arrays.sort(arr, Comparator.comparingInt(x -> x[0]));
        Arrays.sort(worker);

        int i = 0, bestProfit = 0, ans = 0;
        for (int skill : worker) {
            while (i < n && skill >= arr[i][0]) {
                bestProfit = Math.max(bestProfit, arr[i][1]);
                i++;
            }

            ans += bestProfit;
        }

        return ans;
    }
}
```
# [LeetCode_831_隐藏个人信息](https://leetcode.cn/problems/masking-personal-information/)
## 解法
### 思路
硬核模拟
### 代码
```java
class Solution {
        public String maskPII(String s) {
        s = s.toLowerCase();
        return isMailAddress(s) ? handleMailAddress(s) : handlePhoneNumber(s);
    }

    private String handleMailAddress(String s) {
        String name = s.substring(0, s.indexOf("@"));
        return name.charAt(0) + "*****" + name.charAt(name.length() - 1) + s.substring(s.indexOf("@"));
    }

    private String handlePhoneNumber(String s) {
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isDigit(c)) {
                sb.append(c);
            }
        }

        String countryCode = getStar(sb.length() - 10), number = sb.substring(sb.length() - 4);
        return countryCode.length() == 0 ? "***-***-" + number : "+" + countryCode + "-***-***-" + number;
    }
    
    private String getStar(int num) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < num; i++) {
            sb.append("*");
        }
        return sb.toString();
    }

    private boolean isMailAddress(String s) {
        return s.contains("@");
    }
}
```
# [LeetCode_2574_左右元素和的差值](https://leetcode.cn/problems/left-and-right-sum-differences/)
## 解法
### 思路
前缀和
### 代码
```java
class Solution {
    public int[] leftRightDifference(int[] nums) {
        int n = nums.length;
        int[] sum = new int[n];
        int preSum = 0;
        for (int i = 0; i < nums.length; i++) {
            sum[i] = preSum + nums[i];
            preSum = sum[i];
        }
        
        int[] ans = new int[n];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = Math.abs(sum[i] - nums[i] - sum[n - 1] + sum[i]);
        }
        
        return ans;
    }
}
```
# [LeetCode_1326_灌溉花园的最少龙头数目](https://leetcode.cn/problems/minimum-number-of-taps-to-open-to-water-a-garden/)
## 解法
### 思路
- 初始化数组rights，用于记录区间左边界坐标对应的最大的右边界坐标值
- 从0开始遍历n+1个元素，通过range数组中对应的元素，计算出左右边界并维护出rights数组
- 从0开始循环驱动计算区间的覆盖情况
  - 退出条件时坐标idx>=n越界，或者右边界已经覆盖了所有区间
  - 初始化左右边界为0
  - 如果当前坐标小于等于上一个区间的左边界，那么就更新当前覆盖状态的最大右边界，坐标右移一位继续判断
  - 如果当前坐标大于上一个区间的左边界，说明新的区间可能与上一个区间交叉或者大于右边界
    - 如果大于右边界了，说明有空隙，返回-1
    - 如果小于等于右边界，那么就更新上一个区间的左边界为当前的右边界，然后继续做判断，更新新的区间的右边界
### 代码
```java
class Solution {
    public int minTaps(int n, int[] ranges) {
        int[] rights = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            int l = Math.max(i - ranges[i], 0);
            rights[l] = Math.max(rights[l], i + ranges[i]);
        }

        int l = 0, r = 0, idx = 0, ans = 0;
        while (idx <= n && r < n) {
            if (idx <= l) {
                r = Math.max(r, rights[idx]);
                idx++;
            } else {
                if (idx > r) {
                    return -1;
                } else {
                    l = r;
                    ans++;
                }
            }
        }

        return r >= n ? ans + 1 : -1;
    }
}
```
# [LeetCode_833_字符串中的查找与替换](https://leetcode.cn/problems/find-and-replace-in-string/)
## 解法
### 思路
模拟
- 用数组arr作为桶，记录indices中的坐标值，arr表示s坐标位置需要做处理，处理的内容根据arr元素值到3个数组中查找
- arr的其他元素都记录为-1，用于表示这个坐标的字符不需要处理
### 代码
```java
class Solution {
    public String findReplaceString(String s, int[] indices, String[] sources, String[] targets) {
                int n = s.length();
        int[] arr = new int[n];
        Arrays.fill(arr, -1);
        for (int i = 0; i < indices.length; i++) {
            arr[indices[i]] = i;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            int index = arr[i];
            if (index == -1) {
                sb.append(s.charAt(i));
            } else {
                if (s.startsWith(sources[index], indices[index])) {
                    sb.append(targets[index]);
                    i += sources[index].length() - 1;
                } else {
                    sb.append(s.charAt(i));
                }
            }
        }

        return sb.toString();
    }
}
```
# [LeetCode_848_字母移位]()
## 解法
### 思路
后缀和
### 代码
```java
class Solution {
    public String shiftingLetters(String s, int[] shifts) {
        int n = shifts.length;
        int[] sum = new int[n];
        int pre = 0;
        for (int i = n - 1; i >= 0; i--) {
            sum[i] = (shifts[i] + pre) % 26;
            pre = sum[i];
        }

        char[] cs = s.toCharArray();
        for (int i = 0; i < cs.length; i++) {
            cs[i] = (char)((((s.charAt(i) - 'a') + sum[i]) % 26) + 'a');
        }
        
        return String.valueOf(cs);
    }
}
```
# [LeetCode_853_车队](https://leetcode.cn/problems/car-fleet/)
## 解法
### 思路
单调栈
- 如果计算出从出发点到target的耗时
  - 那么距离长耗时短的能追上距离短耗时长的
  - 但是距离更短的，如果耗时短，那么肯定不能相遇
- 通过二维数组对position和time进行合并
- 然后对position进行非降序排序，是的距离长的在前面
- 初始化一个stack用来存储从距离长的开始的所有车
- 循环的时候从栈顶获取元素，与当前元素的耗时进行比较
  - 如果当前元素耗时比栈顶的大或者等于，说明距离更短的耗时却比栈顶的长，那么栈顶的车一定能追上当前车，所以会合并，也就从栈里弹出
  - 如果当前元素耗时比栈顶的小，那么说明当前车到target的时候，栈顶的车还没有到，而那些比栈顶元素快，耗时少的车会优先和栈顶元素合并，而那些更慢的车又不能和当前车合并，所以就无需再处理了。
- 遍历结束后，返回栈内的元素个数即可
### 代码
```java
class Solution {
  public int carFleet(int target, int[] position, int[] speed) {
    int n = position.length;
    double[][] arr = new double[n][2];
    for (int i = 0; i < n; i++) {
      arr[i][0] = position[i];
      arr[i][1] = (target - position[i]) / (double)speed[i];
    }

    Arrays.sort(arr, (x, y) -> (int) (x[0] - y[0]));

    Stack<Double> stack = new Stack<>();
    for (int i = 0; i < n; i++) {
      double time = arr[i][1];

      while (!stack.isEmpty() && time >= stack.peek()) {
        stack.pop();
      }

      stack.push(time);
    }

    return stack.size();
  }
}
```
# [LeetCode_866_回文素数](https://leetcode.cn/problems/prime-palindrome/)
## 解法
### 思路
暴力
- 注意因为8位数没有素数，所以需要跳过
### 代码
```java
class Solution {
    public int primePalindrome(int n) {
        while (true) {
            if (reverse(n) == n && isPrime(n)) {
                return n;
            }
            n++;

            if (n >= 10_000_000 && n <= 100_000_000) {
                n = 100_000_000;
            }
        }
    }
    
    private boolean isPrime(int num) {
        if (num == 1) {
            return false;
        }

        int target = (int)Math.sqrt(num);
        for (int i = 2; i <= target; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    private int reverse(int num) {
        int reverse = 0;
        while (num > 0) {
            reverse = reverse * 10 + num % 10;
            num /= 10;
        }
        return reverse;
    }
}
```
# [LeetCode_1487_保证文件名唯一](https://leetcode.cn/problems/making-file-names-unique/)
## 解法
### 思路
hash表+模拟
### 代码
```java
class Solution {
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        int n = names.length;
        String[] ans = new String[n];
        for (int i = 0; i < names.length; i++) {
            String name = names[i];
            Integer val = map.get(name);

            if (val == null) {
                map.put(name, 0);
                ans[i] = name;
            } else {
                val++;
                String newName = name + "(" + val + ")";
                while (map.containsKey(newName)) {
                    newName = name + "(" + (++val) + ")";
                }
                
                map.put(name, val);
                map.put(newName, 0);
                ans[i] = newName;
            }
        }

        return ans;
    }
}
```