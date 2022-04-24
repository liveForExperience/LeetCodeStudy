# [LeetCode_2239_找到最接近0的数字](https://leetcode-cn.com/problems/find-closest-number-to-zero/)
## 解法
### 思路
- 遍历数组
- 计算和0的距离，找 到距离最小值
- 记录这个最小值用于后续比较，同时记录产生距离的元素值，用于判断当距离相等时，是否需要替换为较大的那个值作为答案
- 遍历结束后，返回暂存的元素
### 代码
```java
class Solution {
    public int findClosestNumber(int[] nums) {
        int min = Integer.MAX_VALUE, ans = Integer.MIN_VALUE;
        for (int num : nums) {
            int distance = Math.abs(num);
            if (distance < min) {
                min = distance;
                ans = num;
            } else if (distance == min) {
                ans = Math.max(ans, num);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_LCP50_宝石补给](https://leetcode-cn.com/problems/WHnhjV/)
## 解法
### 思路
模拟
### 代码
```java
class Solution {
    public int giveGem(int[] gem, int[][] operations) {
        int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
        for (int[] operation : operations) {
            int x = operation[0], y = operation[1];
            int count = gem[x] / 2;
            gem[x] -= count;
            gem[y] += count;
        }

        for (int num : gem) {
            min = Math.min(min, num);
            max = Math.max(max, num);
        }

        return max - min;
    }
}
```
# [LeetCode_LCP51_烹饪料理](https://leetcode-cn.com/problems/UEcfPD/)
## 解法
### 思路
回溯
### 代码
```java
class Solution {
    private int x = -1;
    private int limit;
    private int[][] cookbooks;
    private int[][] attribute;
    public int perfectMenu(int[] materials, int[][] cookbooks, int[][] attribute, int limit) {
        this.cookbooks = cookbooks;
        this.attribute = attribute;
        this.limit = limit;
        backTrack(materials, null, 0, 0, 0);
        return this.x;
    }

    private void backTrack(int[] materials, int[] cost, int index, int x, int y) {
        if (y >= limit) {
            this.x = Math.max(this.x, x);
        }

        int n = cookbooks.length;
        for (int i = index; i < n; i++) {
            int[] cookbook = cookbooks[i];
            if(remove(materials, cookbook)) {
                backTrack(materials, cookbook, i + 1, x + attribute[i][0], y + attribute[i][1]);
            }

            add(materials, cookbook);
        }
    }

    private boolean remove(int[] materials, int[] cost) {
        int n = materials.length;
        boolean flag = true;
        for (int i = 0; i < n; i++) {
            if (materials[i] < cost[i]) {
                flag = false;
            }

            materials[i] -= cost[i];
        }

        return flag;
    }

    private void add(int[] materials, int[] cost) {
        int n = materials.length;
        for (int i = 0; i < n; i++) {
            materials[i] += cost[i];
        }
    }
}
```
# [LeetCode_587_安装栅栏](https://leetcode-cn.com/problems/erect-the-fence/)
## 解法
### 思路

### 代码
```java

```