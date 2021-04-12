# [LeetCode_517_超级洗衣机](https://leetcode-cn.com/problems/super-washing-machines/)
## 解法
### 思路
- 先计算出平均值，如果不能整除，直接返回-1
- 然后计算每个洗衣机是要流出还是要流入衣服，通过遍历元素减去平均值获得这个差值
- 得到差值的元素数组后，先初始化将元素头尾的值做一个比较，判断这两个元素的最大值作为可能的做小操作数
- 然后再遍历差值元素数组，遍历这个数组的时候，计算的逻辑是：
    - 累加左边所有的差值，如果是正数，说明左边的衣服会流入到右边
    - 如果是负数，就说明右边的衣服会流入到左边
    - 然后以当前这个元素左边标准，就能得到一种操作数的可能值，这个可能值就是左边或右边的流入数，与当前差值元素中的最大值、
    - 然后将所有可能值枚举后，获得其中的最大值
### 代码
```java
class Solution {
    public int findMinMoves(int[] machines) {
        int len = machines.length, sum = Arrays.stream(machines).sum();
        if (sum % len != 0) {
            return -1;
        }

        int avg = sum / len;
        for (int i = 0; i < len; i++) {
            machines[i] = machines[i] - avg;
        }

        int ans = 0, leftSum = 0;
        for (int machine : machines) {
            leftSum += machine;
            int cur = Math.max(Math.abs(leftSum), machine);
            ans = Math.max(ans, cur);
        }

        return ans;
    }
}
```