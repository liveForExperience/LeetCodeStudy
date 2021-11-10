# [LeetCode_299_猜数字游戏](https://leetcode-cn.com/problems/bulls-and-cows/)
## 解法
### 思路
- 第一次遍历
  - 找到两个字符串完全相等的字母，统计a的个数
  - 记录没有完全相等的字母的个数，按照字母做分类，记录在bucket数组中
- 复制一下bucket数组，记作arr
- 第二次遍历
  - 通过guess中出现的字母，到bucket数组中做累减统计，计算guess中出现的，但位置不同的字母有哪些
- 第三次遍历
  - 将arr的元素与bucket的元素做相减，计算前，如果bucket的元素小于0，记作0。
  - 将相减的值累加，就是b的数值
- 最后将a和b的值用字符串表示返回
### 代码
```java
class Solution {
    public String getHint(String secret, String guess) {
        int n = secret.length(), a = 0, b = 0;
        Set<Integer> set = new HashSet<>();
        int[] bucket = new int[10];
        for (int i = 0; i < n; i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                set.add(i);
                a++;
                continue;
            }

            bucket[secret.charAt(i) - '0']++;
        }

        int[] arr = Arrays.copyOf(bucket, bucket.length);
        for (int i = 0; i < n; i++) {
            if (set.contains(i)) {
                continue;
            }

            bucket[guess.charAt(i) - '0']--;
        }

        for (int i = 0; i < bucket.length; i++) {
            b += arr[i] - Math.max(bucket[i], 0);
        }

        return a + "A" + b + "B";
    }
}
```
# [LeetCode_1848_到目标元素的最小距离](https://leetcode-cn.com/problems/minimum-distance-to-the-target-element/)
## 解法
### 思路
- 从start坐标先向左再向右遍历
- 分别找到第一个与target值相等的坐标
- 算出坐标与start的距离，返回最小值
### 代码
```java
class Solution {
    public int getMinDistance(int[] nums, int target, int start) {
        int dis = Integer.MAX_VALUE;
        for (int i = start; i < nums.length; i++) {
            if (nums[i] == target) {
                dis = i - start;
                break;
            }
        }
        
        for (int i = start; i >= 0; i--) {
            if (start - i >= dis) {
                break;
            }
            
            if (nums[i] == target) {
                return start - i;
            }
        }
        
        return dis;
    }
}
```
# [LeetCode_488_祖玛游戏](https://leetcode-cn.com/problems/zuma-game/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_495_提莫攻击](https://leetcode-cn.com/problems/teemo-attacking/)
## 解法
### 思路
- 使用变量end表示中毒窗口的右边界
- 遍历timeSeries，判断time是否在end的右边
  - 如果是：说明新的中毒周期与老的中毒周期没有重叠，直接累加区间即可
  - 如果不是：说明新老周期有重叠，需要剔除掉老周期与新周期重叠的部分再累加
- 同时遍历过程中还要不断更新右边界，已备下一个循环进行判断
### 代码
```java
class Solution {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        int end = -1, ans = 0;
        for (int time : timeSeries) {
            if (time > end) {
                ans += duration;
            } else {
                ans += time + duration - 1 - end;
            }

            end = time + duration - 1;
        }

        return ans;
    }
}
```