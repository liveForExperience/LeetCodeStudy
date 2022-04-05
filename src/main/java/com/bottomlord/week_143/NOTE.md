# [LeetCode_307_区域和检索-数组可修改]()
## 解法
### 思路
线段树
### 代码
```java
class NumArray {
    private int n;
    private int[] tree;
    public NumArray(int[] nums) {
        this.n = nums.length;
        this.tree = new int[2 * n];
        for (int i = 0, j = n; j < 2 * n; j++, i++) {
            tree[j] = nums[i];
        }

        for (int i = n - 1; i > 0; i--) {
            tree[i] = tree[i * 2] + tree[i * 2 + 1];
        }
    }

    public void update(int index, int val) {
        int i = index + n;
        tree[i] =val;
        while (i > 0) {
            int l = i % 2 == 0 ? i : i - 1;
            int r = i % 2 == 0 ? i + 1 : i;

            i /= 2;
            tree[i] = tree[l] + tree[r];
        }
    }

    public int sumRange(int left, int right) {
        left += n;
        right += n;
        int sum = 0;
        while (left <= right) {
            if (left % 2 == 1) {
                sum += tree[left++];
            }

            if (right % 2 == 0) {
                sum += tree[right--];
            }

            left /= 2;
            right /= 2;
        }

        return sum;
    }
}
```