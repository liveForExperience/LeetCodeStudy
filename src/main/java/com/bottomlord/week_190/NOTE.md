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