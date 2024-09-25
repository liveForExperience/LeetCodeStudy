# [LeetCode_162_寻找峰值](https://leetcode.cn/problems/find-peak-element)
## 解法
### 思路
- 思考过程：
  - 刨除特殊情况，做O(N)时间复杂度的模拟就可以
- 算法过程：
  - 如果数组长度为1，则直接返回坐标0
  - 如果数组的头尾坐标大于它们唯一相邻的元素，则返回该坐标
  - 在`[1, len - 2]`区间中迭代数据，判断是否当前元素相较于相邻元素是严格大于的，那么就返回当前坐标
### 代码
```java
class Solution {
    public int findPeakElement(int[] nums) {
        int len = nums.length;
        if (len == 1 || nums[0] > nums[1]) {
            return 0;
        }
        
        if (nums[len - 1] > nums[len - 2]) {
            return len - 1;
        }

        for (int i = 1; i < len - 1; i++) {
            if (nums[i] > nums[i - 1] && nums[i] > nums[i + 1]) {
                return i;
            }
        }
        
        return 0;
    }
}
```
## 解法二
### 思路
- 思考过程：
  - 通过观察可以发现，峰值元素，一定是比前一个元素更大的
    - 如果比前一个元素小，那么可以判断，可能的峰值一定在当前坐标的前半部分
    - 如果比前一个元素大，那么可以判断，可能的峰值一定在当前坐标的后半部分
  - 通过如上的规律，就可以通过二分查找的方法，快速缩小搜索范围，从而在解法一的基础上提速
- 算法过程：
  - 二分查找，初始化头尾坐标分别为`0`和`len - 1`
  - 二分通过递归来实现：
    - 退出条件：头尾坐标相遇
    - 递归逻辑：
      - 通过头尾坐标计算得到`mid`值
      - 比较`mid`与`mid + 1`元素（选择+1而不是思考过程中的-1，是因为-1会导致数组越界，不方便处理，而+1，在计算`mid`的时候因为int的截取小数部分特性，所以不会越界）
        - `nums[mid] > nums[mid + 1]`，搜索前半部分
        - 相反则搜索后半部分
  - 递归结束，返回递归结果即可
### 代码
```java
class Solution {
    public int findPeakElement(int[] nums) {
        return binarySearch(0, nums.length - 1, nums);
    }
    
    private int binarySearch(int head, int tail, int[] nums) {
        if (head == tail) {
            return head;
        }
        
        int mid = head + (tail - head) / 2;
        return nums[mid] > nums[mid + 1] ? binarySearch(head, mid, nums) : binarySearch(mid + 1, tail, nums);
    }
}
```
# [LeetCode_1901_寻找峰值II](https://leetcode.cn/problems/find-a-peak-element-ii/description/)
## 解法
### 思路
- 思考过程：
  - 最朴素的算法就是迭代二维数组，通过平均`O(n * m)`的时间复杂度来寻找峰值
  - 题目要求是`O(nlog(m))`或`O(mlog(n))`的时间复杂度，为了达到这个要求，就需要对2个维度中的一个进行提速，提速的方式则可以通过二分查找
  - 如果通过迭代的方式，确定当前行(或者列)的最大值，那么这个最大值在矩阵中就一定比该行的其他元素更有概率是峰值
  - 那么通过确定的当前行最大值的坐标，和上一行的元素进行比较就能够确定二分的下一步需要查找的区间了：
    - 如果大于，那么就找大于当前行的部分
    - 如果小于，那么就找小于当前行的部分
- 算法过程：
  - 开始二分查找：
    - 确定中间值`mid`，也即需要搜索最大值的行
    - 搜索`mid`坐标所在行的最大值，确定坐标`k`
    - 判断`mat[mid][k]` 与 `mat[mid + 1][k]`之间的大小关系
      - 如果小于，搜索`[mid + 1, tail]`区间
      - 如果大于，搜索`[head, mid]`区间
    - 搜索结束，也即确定了行坐标
    - 再搜索列的最大值，即可确定目标坐标，返回即可
### 代码
```java
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int head = 0, tail = mat.length - 1;
        while (head < tail) {
            int mid = head + (tail - head) / 2, max = 0, k = -1;
            for (int i = 0; i < mat[mid].length; i++) {
                if (mat[mid][i] > max) {
                    max = mat[mid][i];
                    k = i;
                }
            }
            
            if (mat[mid][k] < mat[mid + 1][k]) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }
        
        int max = 0, k = -1;
        for (int i = 0; i < mat[head].length; i++) {
            if (mat[head][i] > max) {
                max = mat[head][i];
                k = i;
            }
        }
        
        return new int[]{head, k};
    }
}
```
# [LeetCode_2828_判别首字母缩略词](https://leetcode.cn/problems/check-if-a-string-is-an-acronym-of-words)
## 解法
### 思路
- 思考过程：
  - 模拟
- 算法过程：
  - 使用`StringBuilder`拼接遍历字符串数组并依次获取的字符串元素首字符
  - 遍历完成后，比较`StringBuilder`与字符串`s`是否完全一致，并返回对应的布尔结果
### 代码
```java
class Solution {
    public boolean isAcronym(List<String> words, String s) {
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            if (word == null || word.isEmpty()) {
                continue;
            }
            
            sb.append(word.charAt(0));
        }
        
        return Objects.equals(s, sb.toString());
    }
}
```
## 解法二
### 思路
不使用`StringBuilder`，直接比较
### 代码
```java
class Solution {
    public boolean isAcronym(List<String> words, String s) {
        int n = words.size();
        if (n != s.length()) {
            return false;
        }

        for (int i = 0; i < n; i++) {
            if (words.get(i).charAt(0) != s.charAt(i)) {
                return false;
            }
        }
        
        return true;
    }
}
```
# [LeetCode_1962_移除石子使总数最小](https://leetcode.cn/problems/remove-stones-to-minimize-the-total)
## 解法
### 思路
- 思考过程：
  - 使用大顶堆存储`piles`元素
  - 获取`k`次堆顶元素，并通过规则将堆顶元素减小：`num = num - floor(num / 2)`
  - `k`次处理结束后，弹出堆中所有元素累加即可
- 算法过程：
  - 初始化优先级队列`queue`，比较规则为非升序
  - 遍历`piles`数组，将数组元素依次放入`queue`中
  - 遍历`k`次，每次将`queue`的堆顶元素取出，通过计算规则处理后放回`queue`，计算逻辑：`num = num - floor(num / 2)`
  - 之后依次将`queue`中的元素弹出并累加到`sum`值中
  - 累加结束后返回`sum`即可
### 代码
```java
class Solution {
    public int minStoneSum(int[] piles, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>((x, y) -> y - x);
        for (int pile : piles) {
            queue.offer(pile);
        }

        while (k-- > 0) {
            Integer num = queue.poll();
            if (num == null) {
                break;
            }
            
            queue.offer(num - num / 2);
        }

        int sum = 0;
        while (!queue.isEmpty()) {
            sum += queue.poll();
        }

        return sum;
    }
}
```
## 解法二
### 思路
- 思考过程：
  - 通过计数桶将数组元素出现的个数直接存储在桶数组中
  - 同时获取到数组中的最大值，那么通过降序遍历最大值的方式，依次去桶中查看出现的个数，如果有个数，，那么就可以模拟题目要求的减半的计算逻辑了
- 算法过程：
  - 遍历数组获取最大值`max`和元素和`sum`
  - 初始化长度为`max + 1`的桶数组`bucket`，用于存储`piles`数组中元素出现的个数
  - 开始一个循环，遍历`k`次，每次在循环中确定当前这次要进行计算的最大值，确定的方式就是从`max`开始递减遍历，只要这个猜测的最大值在`bucket`中的元素值大于0，那么就可以将这个值的`floor(num / 2)`从`sum`中减去
  - 同时将变小后的值在`bucket`中的个数+1
  - 这样循环`k`次以后，返回`sum`值即可
### 代码
```java
class Solution {
    public int minStoneSum(int[] piles, int k) {
                int max = 0, sum = 0;
        for (int pile : piles) {
            max = Math.max(pile, max);
            sum += pile;
        }
        
        int[] bucket = new int[max + 1];
        for (int pile : piles) {
            bucket[pile]++;
        }
        
        while (k-- > 0) {
            while (max >= 0 && bucket[max] == 0) {
                max--;
            }
            
            if (max < 0) {
                break;
            }
            
            int minus = max / 2;
            sum -= minus;
            bucket[max]--;
            bucket[max - minus]++;
        }
        
        return sum;
    }
}
```