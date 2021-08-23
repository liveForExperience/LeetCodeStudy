# [LeetCode_1512_好数对的数目](https://leetcode-cn.com/problems/number-of-good-pairs/)
## 解法
### 思路
2层循环，外层确定i，内层确定j，判断并累加有效对
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] == nums[j]) {
                    count++;
                }
            }
        }

        return count;
    }
}
```
## 解法二
### 思路
- 初始化一个桶数组，大小为题目数组的最大值+1，这个数组用来统计遍历过程中遇到的元素对应的次数
- 初始化一个变量用来统计有效对
- 正向遍历数组，在遍历过程中，累加当前元素在桶数组中出现的次数，并在累加后对次数做+1操作
  - 累加到总数中是因为，当前元素一定比桶数组中统计的这些相同元素的坐标值更大，那么这些元素有多少个，就能和当前元素组成多少个有效对
  - 对桶数组对应数累加1，就是为下一个出现的相同元素计算做准备
- 遍历结束返回统计值
### 代码
```java
class Solution {
    public int numIdenticalPairs(int[] nums) {
        int  ans = 0;
        int[] bucket = new int[101];
        for (int num : nums) {
            ans += bucket[num]++;
        }
        return ans;
    }
}
```
# [LeetCode_1646_获取生成数组中的最大值](https://leetcode-cn.com/problems/get-maximum-in-generated-array/)
## 解法
### 思路
- 遍历数组，判断当前坐标的奇偶性
  - 偶数，当前坐标元素等于坐标除以2的元素
  - 奇数，当前坐标元素等于坐标处以2以及坐标除2+1的元素的和
- 遍历过程中，同时统计最大值
- 遍历结束后，返回最大值
### 代码
```java
class Solution {
    public int getMaximumGenerated(int n) {
        if (n <= 1) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[0] = 0;
        nums[1] = 1;
        
        int max = 1;
        for (int i = 2; i <= n; i++) {
            if (i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            
            max = Math.max(max, nums[i]);
        }
        
        return max;
    }
}
```
# [LeetCode_1518_换酒问题](https://leetcode-cn.com/problems/water-bottles/)
## 解法
### 思路
- 初始化变量：
  - drink：统计喝了多少瓶酒，初始为numBottle
  - emptyBottle：统计目前有的空瓶数量
- 设置循环
  - 退出条件，空瓶比`numExchange`的数字小，代表不能再兑换新酒了
  - 过程中：
    - drink累加当前兑换的新酒
    - emptyBottle累加兑换的新酒数，作为增加的酒瓶数，
    - 同时累减兑换时用掉的空瓶数
- 循环退出后，返回drink值
### 代码
```java
class Solution {
    public int numWaterBottles(int numBottles, int numExchange) {
        int drink = numBottles, emptyBottles = numBottles;
        
        while (emptyBottles >= numExchange) {
            int newDrink = emptyBottles / numExchange;
            drink += newDrink;
            emptyBottles = emptyBottles + newDrink - newDrink * numExchange;
        }
        
        return drink;
    }
}
```
# [LeetCode_1523_在区间范围内统计奇数数目](https://leetcode-cn.com/problems/count-odd-numbers-in-an-interval-range/)
## 失败解法
### 原因
超时
### 思路
- 遍历统计
### 代码
```java
class Solution {
  public int countOdds(int low, int high) {
    int odd = 0;
    for (int i = low; i <= high; i++) {
      odd += i % 2 == 1 ? 1 : 0;
    }
    return odd;
  }
}
```
## 解法
### 思路
- `num = high - low + 1`算出包含的元素个数
  - 如果个数是奇数：
    - low是奇数，奇数个数是`num / 2 + 1`
    - log是偶数，奇数个数是`num / 2`
  - 如果个数是偶数，奇数个数是`num / 2`
### 代码
```java
class Solution {
    public int countOdds(int low, int high) {
        int num = high - low + 1;
        return num % 2 == 0 ? num / 2 : low % 2 == 1 ? num / 2 + 1 : num / 2;
    }
}
```