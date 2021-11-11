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
回溯：
- 首先定义一个用于清理现有字符串的函数，通过2层循环将连续3个的字符串消除，且直到不再出现连续的为止
- 主体逻辑里：
  - 首先将手上的珠子通过int数组对不同颜色珠子计数，方便在后续回溯过程中累加和恢复
  - 其次需要设定回溯中的退出条件：
    - 也就是当前字符串消除干净的时候，直接退出
    - 另外还有一种情况就是所有的放置可能都模拟过了，都没办法得到消除干净的效果，这样也就退出了
  - 设定一个减枝条件：也就是当前的步数超过了已经消除干净的最短步数，那么这个路径也就不用搜索了，这也代表了回溯过程中还需要记录当前的步数
  - 回溯主逻辑中，需要对已有珠子进行遍历，值得搜索的珠子有几种情况：
    - 珠子有1个，且手上的珠子超过2个同样颜色的，那么这种情况值得搜索
    - 珠子连续2个，且手上的珠子至少有1个同样颜色的，那么这种情况也值得搜索
    - 珠子连续2个，但是手上的珠子没有同样颜色的，那么这种情况就要寄希望于其他珠子消除以后，这个珠子会联动的消除，那么这种也值得取搜索一下，搜索的方式，就是在这组珠子后面插入所有可能的手上的珠子，然后继续搜索
### 代码
```java
class Solution {
    private int[] bucket;
    private int ans;

    public int findMinStep(String board, String hand) {
        this.ans = Integer.MAX_VALUE;
        this.bucket = new int[26];
        for (char c : hand.toCharArray()) {
            bucket[c - 'A']++;
        }
        backTrack(new StringBuilder(board), 0);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }

    private void backTrack(StringBuilder sb, int step) {
        if (step > ans) {
            return;
        }

        if (sb.length() == 0) {
            ans = step;
            return;
        }

        for (int i = 0; i < sb.length(); i++) {
            int j = i;
            while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                j++;
            }

            char c = sb.charAt(i);
            if (i == j && bucket[c - 'A'] >= 2) {
                StringBuilder cur = new StringBuilder(sb);
                cur.insert(i, cur.charAt(i));
                del(cur);
                bucket[c - 'A']--;
                backTrack(cur, step + 1);
                bucket[c - 'A']++;
            } else if (j - i == 1) {
                if (bucket[c - 'A'] >= 1) {
                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i, c);
                    del(cur);
                    bucket[c - 'A']--;
                    backTrack(cur, step + 1);
                    bucket[c - 'A']++;
                    continue;
                }

                for (int k = 0; k < bucket.length; k++) {
                    if (bucket[k] == 0 || k == c - 'A') {
                        continue;
                    }

                    StringBuilder cur = new StringBuilder(sb);
                    cur.insert(i + 1, (char) (k + 'A'));
                    bucket[k]--;
                    backTrack(cur, step + 1);
                    bucket[k]++;
                }
            }
        }
    }

    private void del(StringBuilder sb) {
        boolean flag = true;
        while (flag) {
            flag = false;

            for (int i = 0; i < sb.length(); i++) {
                int j = i;
                while (j + 1 < sb.length() && sb.charAt(j) == sb.charAt(j + 1)) {
                    j++;
                }

                if (j - i >= 2) {
                    flag = true;
                    sb.delete(i, j + 1);
                }
            }
        }
    }
}
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