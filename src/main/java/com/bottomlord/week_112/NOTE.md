# [LeetCode_528_权重随机选择](https://leetcode-cn.com/problems/random-pick-with-weight/)
## 失败解法
### 原因
超出内存限制
### 思路
- 初始化一个list，w数组中的坐标对应什么值，就讲坐标放入list中多少个
- pick的时候就生成list长度的随机数，然后根据随机值到list中找到对应的坐标
### 代码
```java
class Solution {
    private List<Integer> list;
    public Solution(int[] w) {
        list = new ArrayList<>();
        for (int i = 0; i < w.length; i++) {
            int count = w[i];
            while (count-- > 0) {
                list.add(i);
            }
        }
    }

    public int pickIndex() {
        return list.get((int) (Math.random() * list.size()) + 1);
    }
}
```
## 解法
### 思路
- 根据w求前缀和
- 根据前缀和最后一个元素的值(也就是失败解法中list的长度)，算出一个随机值
- 根据随机值到前缀和数组中找到第一个大于等于该值的坐标，返回该坐标
### 代码
```java
class Solution {
    private int[] sum;
    private int total;
    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }
        
        total = sum[sum.length - 1];
    }

    public int pickIndex() {
        int num = (int) (Math.random() * total) + 1;
        for (int i = 0; i < sum.length; i++) {
            if (sum[i] >= num) {
                return i;
            }
        }
        return 0;
    }
}
```
## 解法二
### 思路
在解法一的基础上，将一次遍历搜索改为二分查找，降低时间复杂度
### 代码
```java
class Solution {
    private int[] sum;
    private int total;
    public Solution(int[] w) {
        sum = new int[w.length];
        sum[0] = w[0];
        for (int i = 1; i < w.length; i++) {
            sum[i] = sum[i - 1] + w[i];
        }

        total = sum[sum.length - 1];
    }

    public int pickIndex() {
        int num = (int) (Math.random() * total) + 1;
        return binarySearch(num);
    }
    
    private int binarySearch(int num) {
        int head = 0, tail = sum.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            if (sum[mid] >= num) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }

        return head;
    }
}
```