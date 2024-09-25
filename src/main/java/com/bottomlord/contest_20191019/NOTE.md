# Contest_1_等差数列中缺失的数字
## 题目
有一个数组，其中的值符合等差数列的数值规律，也就是说：
```
在 0 <= i < arr.length - 1 的前提下，arr[i+1] - arr[i] 的值都相等。
我们会从该数组中删除一个 既不是第一个 也 不是最后一个的值，得到一个新的数组  arr。
```
给你这个缺值的数组 arr，请你帮忙找出被删除的那个数。

示例 1：
```
输入：arr = [5,7,11,13]
输出：9
解释：原来的数组是 [5,7,9,11,13]。
```
示例 2：
```
输入：arr = [15,13,12]
输出：14
解释：原来的数组是 [15,14,13,12]。
```
提示：
```
3 <= arr.length <= 1000
0 <= arr[i] <= 10^5
```
## 解法
### 思路
- 通过等差数列求和获得应有的总和：`(a[0] + a[n - 1]) * (n) / 2`
- 遍历数组求和`count`
- 总和减去`count`就获得结果
### 代码
```java
class Solution {
    public int missingNumber(int[] arr) {
        int sum = (arr[0] + arr[arr.length - 1]) * (arr.length + 1) / 2;
        int count = 0;
        for (int num : arr) {
            count += num;
        }
        return sum - count;
    }
}
```
# Contest_1_安排会议日程
## 题目
你是一名行政助理，手里有两位客户的空闲时间表：slots1 和 slots2，以及会议的预计持续时间 duration，请你为他们安排合适的会议时间。

「会议时间」是两位客户都有空参加，并且持续时间能够满足预计时间 duration 的 最早的时间间隔。

如果没有满足要求的会议时间，就请返回一个 空数组。

「空闲时间」的格式是 [start, end]，由开始时间 start 和结束时间 end 组成，表示从 start 开始，到 end 结束。 

题目保证数据有效：同一个人的空闲时间不会出现交叠的情况，也就是说，对于同一个人的两个空闲时间 [start1, end1] 和 [start2, end2]，要么 start1 > end2，要么 start2 > end1。

示例 1：
```
输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 8
输出：[60,68]
```
示例 2：
```
输入：slots1 = [[10,50],[60,120],[140,210]], slots2 = [[0,15],[60,70]], duration = 12
输出：[]
```
提示：
```
1 <= slots1.length, slots2.length <= 10^4
slots1[i].length, slots2[i].length == 2
slots1[i][0] < slots1[i][1]
slots2[i][0] < slots2[i][1]
0 <= slots1[i][j], slots2[i][j] <= 10^9
1 <= duration <= 10^6 
```
## 解法
### 思路
- 使用两个指针分别指向两个时间区间数组的元素
- 通过比较两个数组的起始和结束元素来判断：
    - 如果某一个元素的起始时间大于另一个的结束位置，则另一个的下标前进
    - 如果不需要移动，就判断最大的起始值和最小的结束值的差是否大于等于区间值
        - 如果不是：移动结束值小的下标
        - 如果是：返回起始值+区间值的结果
- 因为两个数组的起始值不是升序排列，需要先进行一次排序，可以使用TreeMap
### 代码
```java
class Solution {
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        int s1 = 0, s2 = 0;
        List<Integer> list = new ArrayList<>();
        Map<Integer, int[]> map1 = new TreeMap<>();
        for (int[] arr : slots1) {
            map1.put(arr[0], arr);
        }
        int index = 0;
        for (int[] arr : map1.values()) {
            slots1[index++] = arr;
        }

        Map<Integer, int[]> map2 = new TreeMap<>();
        for (int[] arr : slots2) {
            map2.put(arr[0], arr);
        }
        index = 0;
        for (int[] arr : map2.values()) {
            slots2[index++] = arr;
        }

        while (s1 < slots1.length && s2 < slots2.length) {
            if (slots1[s1][0] > slots2[s2][1]) {
                s2++;
                continue;
            }

            if (slots2[s2][0] > slots1[s1][1]) {
                s1++;
                continue;
            }

            int start = Math.max(slots1[s1][0], slots2[s2][0]);
            int end = Math.min(slots1[s1][1], slots2[s2][1]);

            if (end - start >= duration) {
                list.add(start);
                list.add(start + duration);
                return list;
            }

            if (slots1[s1][1] > slots2[s2][1]) {
                s2++;
            } else {
                s1++;
            }
        }

        return list;
    }
}
```
# Contest_3_抛掷硬币
## 题目
有一些不规则的硬币。在这些硬币中，prob[i] 表示第 i 枚硬币正面朝上的概率。

请对每一枚硬币抛掷 一次，然后返回正面朝上的硬币数等于 target 的概率。

示例 1：
```
输入：prob = [0.4], target = 1
输出：0.40000
```
示例 2：
```
输入：prob = [0.5,0.5,0.5,0.5,0.5], target = 0
输出：0.03125
```
提示：
```
1 <= prob.length <= 1000
0 <= prob[i] <= 1
0 <= target <= prob.length
如果答案与标准答案的误差在 10^-5 内，则被视为正确答案。
```
## 解法
### 思路
使用动态规划：
- dp[i]：代表抛掷i个正面朝上的硬币的概率
- base case：`dp[0] = 1`
- 状态转移方程：`dp[i] = dp[i] * (1 - prop[i]) + dp[i - 1] * prop[i]`
    - 前一部分代表在抛掷这个硬币之前，出现i个正面朝上的硬币的概率 + 当前这个硬币不能朝上的概率
    - 后一部分代表在炮制这个硬币之前，出现i - 1 个正面朝上的硬币概率，因为是i - 1，所以当前的硬币需要正面朝上，所以是`prop[i]`
- 过程：
    - 因为prop中的所有硬币的概率之间是没有关联关系的，所以需要依次的将这些概率累乘起来才能算出所有硬币都翻一次后的概率
    - 所以需要循环遍历所有的prop里的硬币的概率，然后使用状态转移方程来更新这个dp数组，重新计算所有次数的可能性
- 结果：返回dp[target]
###  代码
```java
class Solution {
    public double probabilityOfHeads(double[] prob, int target) {
        double[] dp = new double[target + 1];
        dp[0] = 1;
        
        for (int i = 0; i < prob.length; i++) {
            double[] ndp = new double[target + 1];
            ndp[0] = dp[0] * (1 - prob[i]);
            for (int j = 1; j <= target; j++) {
                ndp[j] = dp[j] * (1 - prob[i]) + (dp[j - 1] * prob[i]);
            }
            dp = ndp;
        }
        
        return dp[target];
    }
}
```
# Contest_4_分享巧克力
## 题目
你有一大块巧克力，它由一些甜度不完全相同的小块组成。我们用数组 sweetness 来表示每一小块的甜度。

你打算和 K 名朋友一起分享这块巧克力，所以你需要将切割 K 次才能得到 K+1 块，每一块都由一些 连续 的小块组成。

为了表现出你的慷慨，你将会吃掉 总甜度最小 的一块，并将其余几块分给你的朋友们。

请找出一个最佳的切割策略，使得你所分得的巧克力 总甜度最大，并返回这个 最大总甜度。

示例 1：
```
输入：sweetness = [1,2,3,4,5,6,7,8,9], K = 5
输出：6
解释：你可以把巧克力分成 [1,2,3], [4,5], [6], [7], [8], [9]。
```
示例 2：
```
输入：sweetness = [5,6,7,8,9,1,2,3,4], K = 8
输出：1
解释：只有一种办法可以把巧克力分成 9 块。
```
示例 3：
```
输入：sweetness = [1,2,2,1,2,2,1,2,2], K = 2
输出：5
解释：你可以把巧克力分成 [1,2,2], [1,2,2], [1,2,2]。
```
提示：
```
0 <= K < sweetness.length <= 10^4
1 <= sweetness[i] <= 10^5
```
## 解法
### 思路
贪心算法：
- 假设最小甜度是m，遍历数组，当大于等于m的时候就将进行切的动作，在遍历完以后计算切下来的份数是否符合`k + 1`块的要求，如果符合，就增大m再尝试，直到m不能再增大为止。
- 使用二分法来缩短确定m的过程：
    - 头指针为0，尾指针为1e9，代表m的取数范围
    - 通过二分来更改头尾指针，直到头>=尾为止
    - 循环体内的逻辑就是判断mid值是否能够将数组切分成`k + 1`份
    - 最终返回head指针值
### 代码
```java
class Solution {
    public int maximizeSweetness(int[] sweetness, int K) {
        int head = 0, tail = (int)1e9 + 1;

        while (head + 1 < tail) {
            int mid = head + (tail - head) / 2;

            int count = 0, sum = 0;
            for (int i = 0; i < sweetness.length; i++) {
                sum += sweetness[i];

                if (sum >= mid) {
                    count++;
                    sum = 0;
                }
            }

            if (count >= K + 1) {
                head = mid;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
```