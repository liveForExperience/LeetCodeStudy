# [LeetCode_1775_通过最少操作次数使数组的和相等](https://leetcode.cn/problems/equal-sum-arrays-with-minimum-number-of-operations/)
## 解法
### 思路
- 设sums1作为nums1的总和，sums2作为nums2的总和
- 然后通过比较的方式，另sums1一定比sums2大，数组也相应交换
- diff = sums1 - sums2
- 较大的数组，每一个元素都可以通过缩减数值来减小diff
- 较小的数组，每一个元素都可以通过增加数值来减小diff
- 通过一个数组arr，长度为6，存储1-5这些差值存在的个数
- 然后从大到小遍历arr，看一下是哪些是否能通过这些值将diff缩小到0，并记录操作次数，如果无法达到目的，就返回-1
### 代码
```java
class Solution {
    public int minOperations(int[] nums1, int[] nums2) {
        int sums1 = 0, sums2 = 0;
        for (int num : nums1) {
            sums1 += num;
        }

        for (int num : nums2) {
            sums2 += num;
        }

        if (sums1 == sums2) {
            return 0;
        } else if (sums1 < sums2) {
            int[] tmpArr = nums2;
            nums2 = nums1;
            nums1 = tmpArr;

            int tmp = sums1;
            sums1 = sums2;
            sums2 = tmp;
        }

        int[] arr = new int[6];
        for (int num : nums1) {
            arr[num - 1]++;
        }

        for (int num : nums2) {
            arr[6 - num]++;
        }

        int diff = sums1 - sums2, count = 0;
        for (int i = 5; i >= 0; i--) {
            while (arr[i]-- > 0) {
                count++;
                if (diff <= i) {
                    return count;
                } else {
                    diff -= i;
                }
            }
        }

        return -1;
    }
}
```
# [LeetCode_1379_找出克隆二叉树中的相同节点](https://leetcode.cn/problems/find-a-corresponding-node-of-a-binary-tree-in-a-clone-of-that-tree/)
## 解法
### 思路
dfs
### 代码
```java
class Solution {
    public final TreeNode getTargetCopy(final TreeNode original, final TreeNode cloned, final TreeNode target) {
        if (original == null || cloned == null) {
            return null;
        }
        
        if (original == target) {
            return cloned;
        }
        
        TreeNode left = getTargetCopy(original.left, cloned.left, target);
        if (left != null) {
            return left;
        }
        
        return getTargetCopy(original.right, cloned.right, target);
    }
}
```
# [LeetCode_2409_统计共同度过的日子数](https://leetcode.cn/problems/count-days-spent-together/)
## 解法
### 思路
- 用数组记录12个月天数的前缀和
- 利用前缀和数组和天数：
  - 算出2个人arrive的时间的最大值
  - 算出2个人leave的时间的最小值
- 求出他们的差值作为结果返回
### 代码
```java
class Solution {
    private final int[] arr = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    public int countDaysTogether(String arriveAlice, String leaveAlice, String arriveBob, String leaveBob) {
        int[] sums = new int[13];
        for (int i = 1; i <= arr.length; i++) {
            sums[i] = sums[i - 1] + arr[i - 1];
        }

        String rod = "-";
        String[] arriveAliceStrs = arriveAlice.split(rod), leaveAliceStrs = leaveAlice.split(rod),
                arriveBobStrs = arriveBob.split(rod), leaveBobStrs = leaveBob.split(rod);
        int aam = Integer.parseInt(arriveAliceStrs[0]), aad = Integer.parseInt(arriveAliceStrs[1]),
            lam = Integer.parseInt(leaveAliceStrs[0]), lad = Integer.parseInt(leaveAliceStrs[1]),
            abm = Integer.parseInt(arriveBobStrs[0]), abd = Integer.parseInt(arriveBobStrs[1]),
            lbm = Integer.parseInt(leaveBobStrs[0]), lbd = Integer.parseInt(leaveBobStrs[1]);

        int day = Math.min(sums[lam - 1] + lad, sums[lbm - 1] + lbd) - Math.max(sums[aam - 1] + aad, sums[abm - 1] + abd);        
        return day < 0 ? 0 : day + 1;
    }
}
```
# [LeetCode_1780_判断一个数字是否可以表示成三的幂的和](https://leetcode.cn/problems/check-if-number-is-a-sum-of-powers-of-three/)
## 解法
### 思路
- 通过进制转换来判断
  - num % 3 == 0，当前位上没有值
  - num % 3 == 1，当前位上只有1，代表通过3的整数次幂可能获得对应的值
  - 如果是其他值，则代表无法达到题目要求
### 代码
```java
class Solution {
  public boolean checkPowersOfThree(int n) {
    while (n != 0) {
      if (n % 3 == 0 || n % 3 == 1) {
        n /= 3;
      } else {
        return false;
      }
    }

    return true;
  }
}
```
# [LeetCode_2413_最小偶数倍](https://leetcode.cn/problems/smallest-even-multiple/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int smallestEvenMultiple(int n) {
        if (n <= 2) {
            return 2;
        }

        return n % 2 == 0 ? n : 2 * n;
    }
}
```
# [LeetCode_2418_按身高排序](https://leetcode.cn/problems/sort-the-people/)
## 解法
### 思路
模拟排序
### 代码
```java
class Solution {
  public String[] sortPeople(String[] names, int[] heights) {
    int n = names.length;
    Integer[] indexes = new Integer[n];
    for (int i = 0; i < n; i++) {
      indexes[i] = i;
    }

    Arrays.sort(indexes, (x, y) -> heights[y] - heights[x]);
    String[] ans = new String[n];
    for (int i = 0; i < indexes.length; i++) {
      ans[i] = names[indexes[i]];
    }
    return ans;
  }
}
```