# [LeetCode_957_N天后的牢房](https://leetcode.cn/problems/prison-cells-after-n-days/)
## 失败解法
### 原因
超时
### 思路
暴力模拟
### 代码
```java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        while (n-- > 0) {
            int[] cur = new int[8];
            for (int i = 0; i < 8; i++) {
                if (i == 0 || i == 7) {
                    cur[i] = 0;
                    continue;
                }
                
                if (pre[i - 1] != pre[i + 1]) {
                    cur[i] = 0;
                } else {
                    cur[i] = 1;
                }
            }
            
            pre = cur;
        }
        
        return pre;
    }
}
```
## 解法
### 思路
- 因为是8位的二进制数字，所以当n大于2的8次方时，一定会出现重复，然后形成一个环
- 用map存储所有二进制数字，以及对应出现的顺序
- 再用另一个map2存储顺序与二进制之间反过来的映射关系
- 然后通过数组与十进制之间的2个转换函数，将数组存储到2个map中
- 模拟数组的变化，直到出现重复的数字，然后就求出环的长度，根据长度与剩余的n值取模，得到偏移量后，到map2中用环的起始坐标+偏移量得到10进制值
- 10进制转换为二进制数组后，作为结果返回
### 代码
```java
class Solution {
    public int[] prisonAfterNDays(int[] cells, int n) {
        int[] pre = cells;
        int round = n, index = 0;
        Map<Integer, Integer> map = new HashMap<>(), map2 = new HashMap<>();
        int curMask = arr2Mask(pre);
        map.put(curMask, index);
        map2.put(index, curMask);
        index++;

        while (round-- > 0) {
            int[] cur = transfer(pre);
            int mask = arr2Mask(cur);
            if (map.containsKey(mask)) {
                int mod = round % (n - round - map.get(mask));
                return mask2Arr(map2.get(map.get(mask) + mod));
            }

            map.put(mask, index);
            map2.put(index, mask);
            index++;
            pre = cur;
        }

        return pre;
    }

    private int[] transfer(int[] pre) {
        int[] cur = new int[8];
        for (int i = 0; i < 8; i++) {
            if (i == 0 || i == 7) {
                cur[i] = 0;
                continue;
            }

            if (pre[i - 1] != pre[i + 1]) {
                cur[i] = 0;
            } else {
                cur[i] = 1;
            }
        }

        return cur;
    }

    private int arr2Mask(int[] arr) {
        int mask = 0;
        for (int i = 0; i < arr.length; i++) {
            mask |= arr[i] << (7 - i);
        }
        return mask;
    }

    private int[] mask2Arr(int n) {
        int[] arr = new int[8];
        for (int i = 0; i < 8; i++) {
            arr[i] = (n & (1 << 7 - i)) == 0 ? 0 : 1;
        }
        return arr;
    }
}
```