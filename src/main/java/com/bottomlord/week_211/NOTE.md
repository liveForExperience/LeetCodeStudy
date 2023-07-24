# [LeetCode_771_宝石与石头](https://leetcode.cn/problems/jewels-and-stones/)
## 解法
### 思路
- 使用布尔数组作为宝石字符的判断记录
- 遍历石头数组，与布尔数组的坐标值做判断，如果为宝石就累加结果值
- 遍历结束返回结果值
### 代码
```java
class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        boolean[] bucket = new boolean['z' - 'A' + 1];
        char[] jcs = jewels.toCharArray(), cs = stones.toCharArray();
        for (char jc : jcs) {
            bucket[jc - 'A'] = true;
        }

        int count = 0;
        for (char c : cs) {
            if (bucket[c - 'A']) {
                count++;
            }
        }
        
        return count;
    }
}
```