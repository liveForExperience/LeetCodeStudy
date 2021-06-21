# [LeetCode_401_二进制手表](https://leetcode-cn.com/problems/binary-watch/submissions/)
## 解法
### 思路
- 枚举所有0到59的10进制对应二进制数中为1的位的个数，做关联映射
- 2层循环，外层循环12个小时，内层循环60分钟，判断哪些时间的个数总和是目标值，如果是就转译为字符串
### 代码
```java
class Solution {
    public List<String> readBinaryWatch(int turnedOn) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 0);
        for (int i = 1; i < 60; i++) {
            int count = 1, j = i;
            while ((j &= (j - 1)) != 0) {
                count++;
            }
            
            map.put(i, count);
        }
        
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 60; j++) {
                if (map.get(i) + map.get(j) == turnedOn) {
                    String min = j < 10 ? "0" + j : "" + j;
                    ans.add(i + ":" + min);
                }
            }
        }
        
        return ans;
    }
}
```