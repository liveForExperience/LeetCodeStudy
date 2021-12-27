# [LeetCode_28_实现strStr()](https://leetcode-cn.com/problems/implement-strstr/)
## 解法
### 思路
kmp：

### 代码
```java

```
# [LeetCode_825_适龄的朋友](https://leetcode-cn.com/problems/friends-of-appropriate-ages/)
## 失败解法
### 原因
超时
### 思路
嵌套循环
### 代码
```java
class Solution {
    public int numFriendRequests(int[] ages) {
        int n = ages.length, count = 0;
        for (int i = 0; i < n; i++) {
            int ageA = ages[i];
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    continue;
                }
                
                if (ages[j] <= ageA * 0.5 + 7 ||
                ages[j] > ages[i] ||
                ages[j] > 100 && ages[i] < 100) {
                    continue;
                }

                count++;
            }
        }
        
        return count;
    }
}
```
## 解法二
### 思路
排序+双指针
- 先对数组进行排序
- 初始化三个指针
  - i对应有效对象值的下界
  - j对应有效对象值的上界
  - k对应发送者
- 遍历k，直到k数组越界
- 内层分别确定i和j，确定的方式就使用题目要求的条件
- 确定好上下界后，求差值就是当前k可以发送的人数，但因为这个范围中包含k自身，所以还需要减去1
- 因为数组是有序的，所以每次遍历k都无需重置i和j，从上一个k确定后的位置继续判断即可
- 注意：下界i一定不能超过坐标k
### 代码
```java
class Solution {
public int numFriendRequests(int[] ages) {
        Arrays.sort(ages);
        int ans = 0, n = ages.length;
        for (int i = 0, j = 0, k = 0; k < n; k++) {
            while (i < k && !check(ages[k], ages[i])) {
                i++;
            }
            if (j < k) {
                j = k;
            }

            while (j < n && check(ages[k], ages[j])) {
                j++;
            }

            if (j > i) {
                ans += j - i - 1;
            }
        }

        return ans;
    }

    private boolean check(int x, int y) {
        return (!(y <= 0.5 * x + 7)) && (y <= x);
    }
}
```