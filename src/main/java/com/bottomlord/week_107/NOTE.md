# [LeetCode_1713_得到子序列的最少操作次数](https://leetcode-cn.com/problems/minimum-operations-to-make-a-subsequence/)
## 解法
### 思路
- 如果target数组的长度n，arr数组的长度是m，那么m减去他们的最长公共子序列，就是最少的操作次数
- 然后将arr中的元素对应到target数组的坐标上，并将不在target数组中的坐标去除
- 同时将target数组也转换成坐标数组，同时会发现新生成的数组是单调递增的，此时就可以最长递增子序列长度
- 在确定最长递增子序列的时候，参考[题解](https://leetcode-cn.com/problems/longest-increasing-subsequence/solution/yi-ti-shuang-jie-tu-jie-yuan-li-ji-chu-d-ptpz/)
    - 在确定序列的时候，如果当前元素比候选序列中的元素都大，那么就直接增加候选序列长度
    - 如果比序列最大值小，就找到序列中比当前值大的最小值，做替换，以确保后续元素在判断时保留更大的增长可能性
### 代码
```java
class Solution {
public int minOperations(int[] target, int[] arr) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < target.length; i++) {
            map.put(target[i], i);
        }

        List<Integer> ans = new ArrayList<>();
        for (int num : arr) {
            if (map.containsKey(num)) {
                int index = map.get(num);
                int i = binarySearch(ans, index);
                if (i == ans.size()) {
                    ans.add(index);
                } else {
                    ans.set(i, index);
                }
            }
        }

        return target.length - ans.size();
    }

    private int binarySearch(List<Integer> ans, int num) {
        int size = ans.size();
        if (size == 0 || num > ans.get(size - 1)) {
            return size;
        }

        int head = 0, tail = size - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (ans.get(mid) < num) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
```