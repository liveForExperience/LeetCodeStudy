# [LeetCode_575_分糖果](https://leetcode-cn.com/problems/distribute-candies/)
## 解法
### 思路
- set统计糖果种类
- 计算每个人能够分到的糖果数量，也就是总糖果数/2
- 获取两个数之间的最小值返回
### 代码
```java
class Solution {
    public int distributeCandies(int[] candyType) {
        Set<Integer> set = Arrays.stream(candyType).boxed().collect(Collectors.toSet());
        return Math.min(set.size(), candyType.length / 2);
    }
}
```
## 解法二
### 思路
使用数组替代解法一的set来统计candy的种类
### 代码
```java
class Solution {
    public int distributeCandies(int[] candyType) {
        boolean[] bucket = new boolean[200001];
        int count = 0;
        for (int num : candyType) {
            if (!bucket[num + 100000]) {
                count++;
                bucket[num + 100000] = true;
            }
        }
        return Math.min(count, candyType.length / 2);
    }
}
```
# [LeetCode_1822](https://leetcode-cn.com/problems/sign-of-the-product-of-an-array/)
## 解法
### 思路
- 遍历数组
- 找到0就返回0
- 统计数组中负号的个数，如果是奇数就返回-1，否则返回1
### 代码
```java
class Solution {
    public int arraySign(int[] nums) {
        boolean flag = true;
        for (int num : nums) {
            if (num == 0) {
                return 0;
            }
            
            if (num < 0) {
                flag = !flag;
            }
        }
        
        return flag ? 1 : -1;
    }
}
```