# [LeetCode_871_最低加油次数](https://leetcode.cn/problems/minimum-number-of-refueling-stops/)
## 解法
### 思路
动态规划：
- dp[i]：加i次油可以走的最大距离
- 状态转移方程：
  - dp[i] > stations[i][0]：dp[i + 1] = max(dp[i] + stations[i][1], dp[i + 1])
- 遍历dp数组，找到第一个大于等于target的坐标作为结果
### 代码
```java
class Solution {
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int n = stations.length;
        int[] dp = new int[n + 1];
        dp[0] = startFuel;

        for (int i = 0; i < n; i++) {
            for (int j = i; j >= 0; j--) {
                if (dp[j] >= stations[i][0]) {
                    dp[j + 1] = Math.max(dp[j + 1], dp[j] + stations[i][1]);
                }
            }
        }

        for (int i = 0; i <= n; i++) {
            if (dp[i] >= target) {
                return i;
            }
        }

        return -1;
    }
}
```
# [LeetCode_556_下一个更大元素III](https://leetcode.cn/problems/next-greater-element-iii/)
## 解法
### 思路
- 将数字整理成为一个动态列表
- 从数字的尾部开始遍历，找到递减序列的第一个元素，如果没有找到，那么这个数字无法找打更大的数
- 将找到的这个元素和从尾部开始的第一个比它小的元素互换，从而确定第一个元素（因为在这个元素之前的元素都是非递减序列里的数，所以找到的第一个数一定是比该数大的最小的元素）
- 互换以后，这个位置的元素后面的所有元素，通过双指针互换位置，从而转变为非递减序列
- 之后通过遍历序列，重新得到结果数字
### 代码
```java
class Solution {
  public int nextGreaterElement(int n) {
    List<Integer> list = new ArrayList<>();
    while (n > 0) {
      list.add(n % 10);
      n /= 10;
    }

    int index = -1;
    for (int i = 0; i < list.size() - 1; i++) {
      if (list.get(i + 1) < list.get(i)) {
        index = i + 1;
        break;
      }
    }

    if (index == -1) {
      return -1;
    }

    for (int i = 0; i < index; i++) {
      if (list.get(i) > list.get(index)) {
        swap(list, i, index);
        break;
      }
    }

    int l = 0, r = index - 1;
    while (l < r) {
      swap(list, l++, r--);
    }

    long ans = 0;
    for (int i = list.size() - 1; i >= 0; i--) {
      int num = list.get(i);
      ans = ans * 10 + num;
    }

    return ans > Integer.MAX_VALUE ? -1 : (int)ans;
  }

  private void swap(List<Integer> list, int x, int y) {
    int tmp = list.get(x);
    list.set(x, list.get(y));
    list.set(y, tmp);
  }
}
```