# [LeetCode_2269_找到一个数字的K美丽值](https://leetcode.cn/problems/find-the-k-beauty-of-a-number/)
## 解法
### 思路
- 遍历字符串
- 截取k长度的连续子字符串，转换成int值后，用num进行整除判断，如果可以整除就计数
- 需要注意，转换成的int值如果是0，要特殊判断，否则会有异常抛出
- 遍历结束返回count值
### 代码
```java
class Solution {
    public int divisorSubstrings(int num, int k) {
        String str = String.valueOf(num);
        int n = str.length(), count = 0;
        for (int i = 0; i + k <= n; i++) {
            int cur = Integer.parseInt(str.substring(i, i + k));
            if (cur != 0 && num % cur == 0) {
                count++;
            }
        }

        return count;
    }
}
```