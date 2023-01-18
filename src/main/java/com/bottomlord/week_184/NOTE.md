# [LeetCode_940_不同子序列II](https://leetcode.cn/problems/distinct-subsequences-ii/)
## 解法
### 思路

### 代码
```java

```
# [LeetCode_1813_句子相似性III](https://leetcode.cn/problems/sentence-similarity-iii/)
## 解法
### 思路
双指针：
- 将两个句子根据空格切分
- 根据题目要求，使用双指针，从头尾找到相对起始位置的坐标值相等的元素
- 找到的元素的个数和，一定是2个句子中单词数最少的那个
### 代码
```java
class Solution {
    public boolean areSentencesSimilar(String sentence1, String sentence2) {
        int i = 0, j = 0;

        String[] words1 = sentence1.split(" "),
                words2 = sentence2.split(" ");

        int n1 = words1.length, n2 = words2.length;
        while (i < n1 && i < n2 && Objects.equals(words1[i], words2[i])) {
            i++;
        }

        while (j < n1 - i && j < n2 - i && Objects.equals(words1[n1 - 1 - j], words2[n2 - 1 - j])) {
            j++;
        }

        return i + j == Math.min(words1.length, words2.length);
    }
}
```
# [LeetCode_1814_统计一个数组中好对子的数目](https://leetcode.cn/problems/count-nice-pairs-in-an-array/)
## 解法
### 思路
- 题目要求：nums[i] + rev[nums[j]] == nums[j] + rev[nums[i]] 可以转化为 nums[i] - rev[nums[i]] == nums[j] - rev[nums[j]]
- 又因为0 <= i < j < nums.length，所以计算出nums[i] - rev[nums[i]]之后，查看是否在之前有获得过一样的结果，就能知道有多少对好对子了
- 而这个数据结构肯定是使用map比较合适，key是`nums[i] - rev[nums[i]]`的值，value是出现的次数
- 遍历一次数组，计算`nums[i] - rev[nums[i]]`的值，并把出现次数累加在结果中，同时记得取模就可以了
- 遍历结束，返回结果即可
### 代码
```java
class Solution {
    public int countNicePairs(int[] nums) {
        int ans = 0, mod = 1000000007;
        Map<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {

            int rev = 0, cur = num;
            while (num > 0) {
                rev = rev * 10 + num % 10;
                num /= 10;
            }

            int key = cur - rev;
            ans = (ans + map.getOrDefault(key, 0)) % mod;
            map.put(key, map.getOrDefault(key, 0) + 1);
        }

        return ans;
    }
}
```
# [LeetCode_1825_求出MK平均值](https://leetcode.cn/problems/finding-mk-average/)
## 解法
### 思路
- 初始化3个有序的Map数据结构，用于存储如下3部分数据
  - 序列最小的m个数：s1
  - 序列最大的m个数：s2
  - 序列中 m - 2k 个数：s3
- 初始化3个变量，用于存储s1、s2、s3的元素个数，因为map不能O(1)复杂度的查询总数，需要用变量来维护
- 初始化1个变量sum，用于保存s2的元素总和
- 初始化1个队列，用于模拟数据流
- 添加方法：
  - 往队列里添加元素num
  - 如果队列长度<m
    - 将元素放入s2中
    - 处理s1的元素，如果s1的元素个数小于k，将s2里最小的元素依次加入到s1中，直到s1的元素个数不再小于m
    - 处理s3的元素，如果s3的元素个数小于k，将s2里最大的元素依次加入到s3中，找到s3的元素个数不再小于m
  - 如果队列长度>=m
    - 如果num小于s1的最大值，那么插入s1，同时将s1的最大值加入到s2
    - 如果num大于s3的最小值，那么插入s3，同时将s3的最小值加入到s2
    - 将num加入到s2
    - 如果队列长度是m就直接返回
    - 将队尾元素从队列中推出
      - 如果在s2中存在，直接将s2的对应元素-1
      - 如果在s1中存在，将对应元素-1后，将s2的最小值放入s1
      - 如果在s3中存在，将对应元素-1后，将s2的最大值放入s3
- 查询方法：
  - 如果长度<m，返回-1
  - 如果长度不小于m，返回 sum / (m - 2k)
### 代码
```java
class MKAverage {

    private TreeMap<Integer, Integer> s1 = new TreeMap<>(),
    s2 = new TreeMap<>(), s3 = new TreeMap<>();
    private int sum = 0, n1 = 0, n3 = 0, m, k;
    private Queue<Integer> queue = new ArrayDeque<>();

    public MKAverage(int m, int k) {
        this.m = m;
        this.k = k;
    }

    public void addElement(int num) {
        queue.offer(num);

        if (queue.size() < m) {
            s2.put(num, s2.getOrDefault(num, 0) + 1);
            sum += num;
            return;
        }

        while (n1 < k) {
            int firstNum = s2.firstKey();
            s2.put(firstNum, s2.get(firstNum) - 1);
            if (s2.get(firstNum) == 0) {
                s2.remove(firstNum);
            }
            sum -= firstNum;
            s1.put(firstNum, s1.getOrDefault(firstNum, 0) + 1);
            n1++;
        }

        while (n3 < k) {
            int lastNum = s2.lastKey();
            s2.put(lastNum, s2.get(lastNum) - 1);
            if (s2.get(lastNum) == 0) {
                s2.remove(lastNum);
            }
            sum -= lastNum;
            s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
            n3++;
        }

        if (num < s1.lastKey()) {
            s1.put(num, s1.getOrDefault(num, 0) + 1);
            int lastNum = s1.lastKey();
            s1.put(lastNum, s1.get(lastNum) - 1);
            if (s1.get(lastNum) == 0) {
                s1.remove(lastNum);
            }

            s2.put(lastNum, s2.getOrDefault(lastNum, 0) + 1);
            sum += lastNum;
        } else if (num > s3.firstKey()) {
            s3.put(num, s3.getOrDefault(num, 0) + 1);
            int firstNum = s3.firstKey();
            s3.put(firstNum, s3.get(firstNum) - 1);
            if (s3.get(firstNum) == 0) {
                s3.remove(firstNum);
            }

            s2.put(firstNum, s2.getOrDefault(firstNum, 0) + 1);
            sum += firstNum;
        } else {
            s2.put(num, s2.getOrDefault(num, 0) + 1);
            sum += num;
        }

        if (queue.isEmpty() || queue.size() == m) {
            return;
        }

        int pollNum = queue.poll();
        if (s1.containsKey(pollNum)) {
            s1.put(pollNum, s1.get(pollNum) - 1);
            if (s1.get(pollNum) == 0) {
                s1.remove(pollNum);
            }

            int firstNum = s2.firstKey();
            s1.put(firstNum, s1.getOrDefault(firstNum, 0) + 1);
            s2.put(firstNum, s2.get(firstNum) - 1);
            if (s2.get(firstNum) == 0) {
                s2.remove(firstNum);
            }
            sum -= firstNum;
        } else if (s3.containsKey(pollNum)) {
            s3.put(pollNum, s3.get(pollNum) - 1);
            if (s3.get(pollNum) == 0) {
                s3.remove(pollNum);
            }

            int lastNum = s2.lastKey();
            s3.put(lastNum, s3.getOrDefault(lastNum, 0) + 1);
            s2.put(lastNum, s2.get(lastNum) - 1);
            if (s2.get(lastNum) == 0) {
                s2.remove(lastNum);
            }
            sum -= lastNum;
        } else {
            sum -= pollNum;
            s2.put(pollNum, s2.get(pollNum) - 1);
            if (s2.get(pollNum) == 0) {
                s2.remove(pollNum);
            }
        }
    }

    public int calculateMKAverage() {
        return queue.size() < m ? -1 : sum / (m - 2 * k);
    }
}
```