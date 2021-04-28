# [LeetCode_1011_在D天内送达包裹的能力](https://leetcode-cn.com/problems/capacity-to-ship-packages-within-d-days/)
## 失败解法
### 原因
超时
### 思路
回溯
- 先求平均值和最大元素之间的较大值作为target
- 以这个target作为标准开始回溯，尝试所有可能
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE, sum = 0;
        for (int num : weights) {
            max = Math.max(max, num);
            sum += num;
        }
        
        int avg = sum % D == 0 ? sum / D : sum / D + 1;
        int target = Math.max(avg, max);

        boolean find = false;
        while (!find) {
            find = backTrack(weights, 0, 0, target, D);
            if (!find) {
                target++;
            }
        }

        return target;
    }

    private boolean backTrack(int[] weights, int index, int count, int target, int day) {
        if (count > day) {
            return false;
        }

        if (index == weights.length) {
            return true;
        }

        int sum = 0;
        boolean find = false;
        for (int i = index; i < weights.length; i++) {
            sum += weights[i];
            if (sum > target) {
                break;
            }
            find = backTrack(weights, i + 1, count + 1, target, day);

            if (find) {
                return true;
            }
        }

        return false;
    }
}
```
## 解法
### 思路
二分查找：
- 其实不需要依照失败解法的方式，求平均值，因为元素中的最大值一定会大于等于平均值，所以只要求得元素最大值就可以
- 这个最大值可以理解为是可能值的最小值，因为如果小于这个值就无法将所有元素都搬到船上
- 而最大值就是元素总和，可以理解成必须必须在一天之内放到船上的话，就要求得总和
### 代码
```java
class Solution {
    public int shipWithinDays(int[] weights, int D) {
        int sum = 0, max = Integer.MIN_VALUE;
        for (int weight : weights) {
            sum += weight;
            max = Math.max(max, weight);
        }
        
        int head = max, tail = sum;
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            
            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        
        return head;
    }
    
    private boolean isValid(int[] weights, int target, int day) {
        int sum = target;
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];
            
            if (sum - weight < 0) {
                sum = target;
                day--;
            } else {
                sum -= weight;
                i++;
            }
            
            if (day <= 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
## 解法二
### 思路
- 解法一中的右边界可以再缩小为以最大值为所有元素的值，然后根据天数求出来的平均值
### 代码
```java
class Solution {
public int shipWithinDays(int[] weights, int D) {
        int max = Integer.MIN_VALUE;
        for (int weight : weights) {
            max = Math.max(max, weight);
        }
        
        int head = max, tail = max * weights.length / D;
        while (head < tail) {
            int mid = head + ((tail - head) >>> 1);
            
            if (isValid(weights, mid, D)) {
                tail = mid;
            } else {
                head = mid + 1;
            }
        }
        
        return head;
    }
    
    private boolean isValid(int[] weights, int target, int day) {
        int cur = target;
        
        for (int i = 0; i < weights.length;) {
            int weight = weights[i];
            
            if (cur - weight < 0) {
                cur = target;
                day--;
            } else {
                cur -= weight;
                i++;
            }
            
            if (day <= 0) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_544_输出比赛匹配对](https://leetcode-cn.com/problems/output-contest-matches/)
## 解法
### 思路
递归：
- 将n转换为从1开始的n个元素的字符串数组
- 递归将字符串数组的头尾元素按照题目要求做拼接
- 最终当数组长度为1，返回这一个元素
### 代码
```java
class Solution {
    public String findContestMatch(int n) {
        String[] arr = new String[n];
        for (int i = 1; i <= n; i++) {
            arr[i - 1] = "" + i;
        }

        return recuse(arr);
    }

    private String recuse(String[] arr) {
        if (arr.length == 1) {
            return arr[0];
        }

        String[] newArr = new String[arr.length / 2];
        for (int i = 0; i < arr.length / 2; i++) {
            newArr[i] = combineStr(arr[i], arr[arr.length - 1 - i]);
        }

        return recuse(newArr);
    }

    private String combineStr(String x, String y) {
        return "(" + x + "," + y + ")";
    }
}
```
# [LeetCode_545_二叉树的边界](https://leetcode-cn.com/problems/boundary-of-binary-tree/)
## 解法
### 思路
- 把过程分成3个部分：
    - 查找左边界
    - 查找叶子节点
    - 查找右边界
- 左边界和右边界的查找逻辑类似但条件相反
- 叶子节点通过dfs来查找
- 还要注意输出的顺序：
    - 左边界是先父节点再子节点
    - 右边界是先子节点再父节点
### 代码
```java
class Solution {
    public List<Integer> boundaryOfBinaryTree(TreeNode root) {
        List<Integer> ans = new ArrayList<>();

        if (root == null) {
            return ans;
        }

        ans.add(root.val);
        findLeft(root.left, ans);
        if (root.left != null || root.right != null) {
            findLeaf(root, ans);
        }
        findRight(root.right, ans);

        return ans;
    }

    private void findLeft(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        list.add(node.val);

        findLeft(node.left, list);

        if (node.left == null) {
            findLeft(node.right, list);
        }
    }

    private void findLeaf(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            list.add(node.val);
        }

        findLeaf(node.left, list);
        findLeaf(node.right, list);
    }

    private void findRight(TreeNode node, List<Integer> list) {
        if (node == null) {
            return;
        }

        if (node.left == null && node.right == null) {
            return;
        }

        if (node.right == null) {
            findRight(node.left, list);
        }

        findRight(node.right, list);

        list.add(node.val);
    }
}
```
# [LeetCode_938_二叉搜索树的范围和](https://leetcode-cn.com/problems/range-sum-of-bst/)
## 解法
### 思路
- 深度优先搜索，通过比较当前节点值和最大最小值，来判断是否要累加当前节点并返回
    - 如果当前节点是空节点，就返回0
    - 如果当前节点小于最小值，就累加其右子树的节点
    - 如果当前节点大于最大值，就累加其左子树的节点
    - 否则就累加左右子树的节点
### 代码
```java
class Solution {
    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null) {
            return 0;
        }

        int ans = 0, val = root.val;
        if (val >= low && val <= high) {
            ans += val;
        }
        
        return ans + 
               (val < low ? 0 : rangeSumBST(root.left, low, high)) + 
               (val > high ? 0 : rangeSumBST(root.right, low, high));
    }
}
```
# [LeetoCde_548_将数组分割成和相等的子数组](https://leetcode-cn.com/problems/split-array-with-equal-sum/)
## 失败解法
### 原因
超时
### 思路
- 求前缀和
- 3层循环判断
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 1; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (sums[i - 1] == sums[j - 1] - sums[i] &&
                        sums[j - 1] - sums[i] == sums[k - 1] - sums[j] &&
                        sums[k - 1] - sums[j] == sums[len - 1] - sums[k]) {
                        return true;
                    }
                }
            }
        }
        
        return false;
    }
}
```
## 失败解法
### 原因
超时
### 思路
前缀和+3重循环+提前失败
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < sums.length; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int i = 1; i < len; i++) {
            int one = sums[i - 1];
            for (int j = i + 1; j < len; j++) {
                int two = sums[j - 1] - sums[i];
                if (one != two) {
                    continue;
                }
                for (int k = j + 1; k < len; k++) {
                    int three = sums[k - 1] - sums[j],
                        four = sums[len - 1] - sums[k];
                    
                    if (two == three && three == four) {
                        return true;
                    }
                }
            }
        }

        return false;
    }
}
```
### 失败解法
### 原因
超时
### 思路
引入hashmap
- 先算出第一个和第二个分割点，然后求出能使得两部分相等的值，key为值，value为坐标的集合，存储起来
- 然后遍历所有可能相等的key，找到他们的values，以这些values为起始坐标，第3部分和第4部分相等的值，看下是否与key匹配，如果匹配就返回true
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i < len; i++) {
            int one = sums[i - 1];
            for (int j = i + 1; j < len; j++) {
                if (sums[j - 1] - sums[i] == sums[i - 1]) {
                    List<Integer> list = map.getOrDefault(one, new ArrayList<>());
                    list.add(j);
                    map.put(one, list);
                }
            }
        }
        
        for (Integer sum : map.keySet()) {
            List<Integer> list = map.get(sum);
            
            for (Integer start : list) {
                for (int i = start + 1; i < len; i++) {
                    int three = sums[i - 1] - sums[start];
                    if (three != sum) {
                        continue;
                    }
                    
                    for (int j = i + 1; j < len; j++) {
                        if (three == sums[len - 1] - sums[i]) {
                            return true;
                        }
                    }
                }
            }
        }
        
        return false;
    }
}
```
## 解法
### 思路
- 数组会被拆分成4个部分
- 如果先求出前2部分相等的情况，然后记录这些情况，同时基于第二个分界坐标，尝试右边的相等两部分，如果相等的值再之前左半部分暂存的里面能找到，就说明可以分成相等的4部分
- 那么只要外层确定把数组一分为二的那个节点坐标，然后内层分2步
    - 第1步求左边相等的情况，用set暂存起来
    - 第2步求右边相等的部分，与set中的值匹配，找到就返回true
- 所有情况遍历完如果还没找到，就返回false
### 代码
```java
class Solution {
    public boolean splitArray(int[] nums) {
        int len = nums.length;
        int[] sums = new int[len];
        sums[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sums[i] = sums[i - 1] + nums[i];
        }

        for (int j = 3; j < len - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for (int i = 1; i + 1 < j; i++) {
                if (sums[i - 1] == sums[j - 1] - sums[i]) {
                    set.add(sums[i - 1]);
                }
            }

            for (int k = j + 2; k < len - 1; k++) {
                int tempSum = sums[k - 1] - sums[j];
                if (tempSum == sums[len - 1] - sums[k] && set.contains(tempSum)) {
                    return true;
                }
            }
        }

        return false;
    }
}
```
# [LeetCode_633_平方数之和](https://leetcode-cn.com/problems/sum-of-square-numbers/)
## 解法
### 思路
使用JDK内建函数sqrt
- 遍历c的平方根范围内的所有整数
- 依次计算判断
- 使用long防止溢出
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - (a * a));
            if (b == (long) b) {
                return true;
            }
        }
        return false;
    }
}
```
## 解法二
### 思路
双指针
- 头指针代表a，初始化为0
- 尾指针代表b，初始化为c的平方根
- 如果相等就返回true
- 如果小于c说明a小了，加1
- 如果大于c说明b大了，减1
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        int a = 0, b = (int) Math.sqrt(c);
        while (a <= b) {
            int x = (int)Math.pow(a, 2), y = (int)Math.pow(b, 2);
            if (x + y == c) {
                return true;
            } else if (x + y < c) {
                a++;
            } else {
                b--;
            }
        }
        return false;
    }
}
```
## 解法三
### 思路
费马平方和定理
> 一个非负整数 `c` 如果能够表示为两个整数的平方和，当且仅当 `c` 的所有形如 `4k + 3` 的质因子的幂均为偶数。
- 遍历c所有可能的质因子
- 先求出当前质因子的幂，求的过程中c也不断调整，调整方式是按照质数整除缩小，因为是质数，所以不影响求其他质因数
- 然后判断当前质因子是否是形为4k+3，且幂不是偶数的，如果是就说明不符合，返回false
- 如果可能的质因子都符合定理，再判断c如果是质数，其是否形如4k+3，如果是，那么他的幂就不是偶数，就返回false，反之为true
### 代码
```java
class Solution {
    public boolean judgeSquareSum(int c) {
        for (int base = 2; base * base <= c; base++) {
            if (c % base != 0) {
                continue;
            }

            int exp = 0, cur = c;
            while (cur % base == 0) {
                cur /= base;
                exp++;
            }

            if (c % 4 == 3 && exp % 2 != 0) {
                return false;
            }
        }

        return c % 4 != 3;
    }
}
```