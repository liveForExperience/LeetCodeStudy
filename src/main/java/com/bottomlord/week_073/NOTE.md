# [LeetCode_493_翻转对](https://leetcode-cn.com/problems/reverse-pairs/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_767_重构字符串](https://leetcode-cn.com/problems/reorganize-string/)
## 解法
### 思路
- 计算字符串个数，生成长度为26的计数桶`bucket`
- 初始化一个存储元素为int数组的大顶堆`queue`
- 遍历`bucket`，生成长度为2，0坐标为字母index，1坐标为字母个数的int数组，压入`queue`
- 初始化一个StringBuilder，用来暂存结果
- 遍历`queue`，终止条件为`queue`空
- 获取大顶堆堆顶的2个元素，如果堆顶只有一个元素，且堆顶元素的字母个数大于1，则返回空字符串，说明无法满足题目要求，否则将最后的字母追加到sb中，并返回
- 如果有2个元素：
    - 比对两个元素的字母个数，如果不一样，就用大的减去小的，重新生成int数组，放入大顶堆
    - 然后循环少字母的出现个数次，以先多字母后少字母的顺序将字母追加到sb中
- 遍历大顶堆结束，返回暂存的sb
### 代码
```java
class Solution {
    public String reorganizeString(String S) {
        if (S.length() <= 1) {
            return S;
        }

        int[] bucket = new int[26];
        for (char c : S.toCharArray()) {
            bucket[c - 'a']++;
        }

        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> y[1] - x[1]);
        for (int i = 0; i < bucket.length; i++) {
            if (bucket[i] > 0) {
                queue.offer(new int[]{i, bucket[i]});
            }
        }

        StringBuilder sb = new StringBuilder();
        
        while (!queue.isEmpty()) {
            int[] first = queue.poll();
            
            if (queue.isEmpty()) {
                if (first[1] != 1) {
                    return "";
                } else {
                    sb.append((char)(first[0] + 'a'));
                    return sb.toString();
                }
            }
            
            int[] second = queue.poll();
            if (first[1] != second[1]) {
                first[1] -= second[1];
                queue.offer(first);
            }
            
            for (int i = 0; i < second[1]; i++) {
                sb.append((char)(first[0] + 'a'));
                sb.append((char)(second[0] + 'a'));
            }
        }

        return sb.toString();
    }
}
```