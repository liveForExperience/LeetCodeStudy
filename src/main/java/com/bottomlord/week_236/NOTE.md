# [LeetCode_2744_最大字符串配对数目](https://leetcode.cn/problems/find-maximum-number-of-string-pairs)
## 解法
### 思路
- 思考过程：
  - 使用mao存储`words`字符串与坐标间的关系，因为不会重复，所以无需记录个数
  - 使用布尔数组存储成对的字符串，避免重复统计
  - 通过一次循环在`map`中查找倒序的字符串是否存在，存在就统计并记录在布尔数组中
- 算法过程：
  - 初始化`map`，存储`words`中的所有字符串及对应坐标
  - 初始化布尔数组`memo`，长度与`words`一致
  - 初始化一个变量`cnt`，用于记录出现成对的个数
  - 遍历`words`数组，通过倒序生成字符串后，到`map`查找是否存在，如果存在就记录坐标，需要个过滤掉坐标相同的情况
  - 在遍历过程中如果发现之前记录过，就跳过
  - 遍历结束后，返回`cnt`作为答案
### 代码
```java
class Solution {
    public int maximumNumberOfStringPairs(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < words.length; i++) {
            map.put(words[i], i);
        }

        boolean[] memo = new boolean[words.length];
        int cnt = 0;
        for (int i = 0; i < words.length; i++) {
            if (memo[i]) {
                continue;
            }
            
            String word = words[i];
            StringBuilder sb = new StringBuilder();
            for (int j = word.length() - 1; j >= 0; j--) {
                sb.append(word.charAt(j));
            }
            
            if (map.containsKey(sb.toString()) && map.get(sb.toString()) != i) {
                memo[i] = true;
                memo[map.get(sb.toString())] = true;
                cnt++;
            }
        }
        
        return cnt;
    }
}
```