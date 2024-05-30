# [LeetCode_2951_找出峰值](https://leetcode.cn/problems/find-the-peaks)
## 解法
### 思路
- 从数组坐标为1的元素开始遍历，终止坐标为`n - 1`（`n`为数组长度）
- 将遍历到的元素与前后元素进行比较，如果严格大于前后元素，则将该元素对应的坐标在答案列表`ans`中
- 遍历结束后，返回`ans`即可
### 代码
```java
class Solution {
    public List<Integer> findPeaks(int[] mountain) {
        int n = mountain.length;
        List<Integer> ans = new ArrayList<>();
        for (int i = 1; i < n - 1; i++) {
            if (mountain[i] > mountain[i - 1] && mountain[i] > mountain[i + 1]) {
                ans.add(i);
            }
        }
        return ans;
    }
}
```
# [LeetCode_2981_找出出现至少三次的最长特殊子字符串I](https://leetcode.cn/problems/find-longest-special-substring-that-occurs-thrice-i)
## 解法
### 思路
- 初始化一个二维矩阵
  - 行为26，代表题目范围的26个小写字母
  - 列为51，代表子字符串的最大可能长度的个数，题目限制字符串最长为50
- 遍历字符串`s`，从每一个字符开始模拟并记录所有的子字符串，同时记录在二维数组中
- 遍历结束后，再次遍历二维数组，依次遍历每个字母可能，因为第二维的数组，坐标代表子字符串长度，而题目又要求必须有至少3个这样长度的相同字母子字符串，所以，从数组尾部开始遍历，跳过所有计数值小于3的，记录第一个碰到的符合个数规则的坐标值作为暂存的待比较结果
- 遍历结束后，返回暂存结果即可
### 代码
```java
class Solution {
    public int maximumLength(String s) {
        int[][] matrix = new int[26][51];
        char[] cs = s.toCharArray();
        int n = cs.length;
        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            int len = 0;
            while (i + len < n && cs[i + len] == c) {
                len++;
                matrix[c - 'a'][len]++;
            }
        }

        int max = -1;
        for (int[] arr : matrix) {
            for (int len = 0; len < arr.length; len++) {
                if (arr[len] < 3) {
                    continue;
                }
                
                max = Math.max(max, len);
            }
        }

        return max;
    }
}
```