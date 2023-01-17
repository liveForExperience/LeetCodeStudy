# [LeetCode_940_不同子序列II](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1813_句子相似性III](https://leetcode.cn/problems/sentence-similarity-iii/)
## 解法
### 思路
双指针：
- 将两个句子根据空格切分
- 根据题目要求，使用双指针，从头尾找到相对起始位置的坐标值相等的元素
- 找到的元素的个数和，一定是2个句子中单词数最少的那个
### 代码
```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        int i = 0, j = 0;

        String[] words1 = sentence1.split(" "),
                words2 = sentence2.split(" ");

        int n1 = words1.length, n2 = words2.length;
        while (i < n1 && i < n2 && Objects.equals(words1[i], words2[i])) {
            i++;
        }

        while (j < n1 - i && j < n2 - i && Objects.equals(words1[n1 - 1 - j], words2[n2 - 1 - j])) {
            j++;
        }

        return i + j == Math.min(words1.length, words2.length);
    }
}
```
# [LeetCode_1814_统计一个数组中好对子的数目](https://leetcode.cn/problems/count-nice-pairs-in-an-array/)
## 解法
### 思路
- 题目要求：nums[i] + rev[nums[j]] == nums[j] + rev[nums[i]] 可以转化为 nums[i] - rev[nums[i]] == nums[j] - rev[nums[j]]
- 又因为0 <= i < j < nums.length，所以计算出nums[i] - rev[nums[i]]之后，查看是否在之前有获得过一样的结果，就能知道有多少对好对子了
- 而这个数据结构肯定是使用map比较合适，key是`nums[i] - rev[nums[i]]`的值，value是出现的次数
- 遍历一次数组，计算`nums[i] - rev[nums[i]]`的值，并把出现次数累加在结果中，同时记得取模就可以了
- 遍历结束，返回结果即可
### 代码
```java
class Solution {
    public int countNicePairs(int[] nums) {
        int ans = 0, mod = 1000000007;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            int rev = 0, cur = num;
            while (num > 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }

            int key = cur - rev;
            ans = (ans + map.getOrDefault(key, 0)) % mod;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }
}
```