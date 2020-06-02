# LeetCode_1431_拥有最多糖果的孩子
## 题目
给你一个数组 candies 和一个整数 extraCandies ，其中 candies[i] 代表第 i 个孩子拥有的糖果数目。

对每一个孩子，检查是否存在一种方案，将额外的 extraCandies 个糖果分配给孩子们之后，此孩子有 最多 的糖果。注意，允许有多个孩子同时拥有 最多 的糖果数目。

示例 1：
```
输入：candies = [2,3,5,1,3], extraCandies = 3
输出：[true,true,true,false,true] 
解释：
孩子 1 有 2 个糖果，如果他得到所有额外的糖果（3个），那么他总共有 5 个糖果，他将成为拥有最多糖果的孩子。
孩子 2 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
孩子 3 有 5 个糖果，他已经是拥有最多糖果的孩子。
孩子 4 有 1 个糖果，即使他得到所有额外的糖果，他也只有 4 个糖果，无法成为拥有糖果最多的孩子。
孩子 5 有 3 个糖果，如果他得到至少 2 个额外糖果，那么他将成为拥有最多糖果的孩子。
```
示例 2：
```
输入：candies = [4,2,1,1,2], extraCandies = 1
输出：[true,false,false,false,false] 
解释：只有 1 个额外糖果，所以不管额外糖果给谁，只有孩子 1 可以成为拥有糖果最多的孩子。
```
示例 3：
```
输入：candies = [12,1,12], extraCandies = 10
输出：[true,false,true]
```
提示：
```
2 <= candies.length <= 100
1 <= candies[i] <= 100
1 <= extraCandies <= 50
```
## 解法
### 思路
- 遍历获取最大值
- 遍历累加判断是否大于最大值
- 将结果放入布尔数组中返回
### 代码
```java
class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = Integer.MIN_VALUE;
        List<Boolean> ans = new ArrayList<>();
        for (int candy : candies) {
            max = Math.max(candy, max);
        }

        for (int candy : candies) {
            ans.add(candy + extraCandies >= max);
        }

        return ans;
    }
}
```
# Interview_1726_稀疏相似度
## 题目
两个(具有不同单词的)文档的交集(intersection)中元素的个数除以并集(union)中元素的个数，就是这两个文档的相似度。例如，{1, 5, 3} 和 {1, 7, 2, 3} 的相似度是 0.4，其中，交集的元素有 2 个，并集的元素有 5 个。给定一系列的长篇文档，每个文档元素各不相同，并与一个 ID 相关联。它们的相似度非常“稀疏”，也就是说任选 2 个文档，相似度都很接近 0。请设计一个算法返回每对文档的 ID 及其相似度。只需输出相似度大于 0 的组合。请忽略空文档。为简单起见，可以假定每个文档由一个含有不同整数的数组表示。

输入为一个二维数组 docs，docs[i] 表示 id 为 i 的文档。返回一个数组，其中每个元素是一个字符串，代表每对相似度大于 0 的文档，其格式为 {id1},{id2}: {similarity}，其中 id1 为两个文档中较小的 id，similarity 为相似度，精确到小数点后 4 位。以任意顺序返回数组均可。

示例:
```
输入: 
[
  [14, 15, 100, 9, 3],
  [32, 1, 9, 3, 5],
  [15, 29, 2, 6, 8, 7],
  [7, 10]
]
输出:
[
  "0,1: 0.2500",
  "0,2: 0.1000",
  "2,3: 0.1429"
]
```
提示：
```
docs.length <= 500
docs[i].length <= 500
相似度大于 0 的文档对数不会超过 1000
```
## 解法
### 思路
map：
- 嵌套循环：
    - 外层遍历每一行的数组
    - 内层：
        - 将这行的每个数字作为map的key，数组的坐标集合list作为value
        - 如果当前数字第一次出现，就生成新的list，并将元素放入
        - 如果当前数字不是第一次出现，就直接将元素放入，并在matrix数组中累加计数，代表形成交集的个数
        - 遍历matrix，以最外层循环的坐标作为当前二维数组的横坐标，查找值大于1的元素，如果是，就根据最外层坐标和当前列坐标，生成对应的字符串
        - 因为当前遍历的行数一定大于map中的任意list中的坐标元素，所以生成的时候，当前行数放在id2的位置
### 代码
```java
class Solution {
    public List<String> computeSimilarities(int[][] docs) {
        int len = docs.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        int[][] matrix = new int[len][len];
        List<String> ans = new ArrayList<>();
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < docs[i].length; j++) {
                List<Integer> list = map.get(docs[i][j]);

                if (list == null) {
                    list = new ArrayList<>();
                } else {
                    for (int index : list) {
                        matrix[i][index]++;
                    }
                }

                list.add(i);
                map.put(docs[i][j], list);
            }

            for (int k = 0; k < len; k++) {
                if (matrix[i][k] > 0) {
                    ans.add(k + "," + i + ": " + String.format("%.4f", (double) matrix[i][k] / (docs[i].length + docs[k].length - matrix[i][k])));
                }
            }
        }

        return ans;
    }
}
```
# LeetCode_16_最接近的三数之和
## 题目
给定一个包括 n 个整数的数组 nums 和 一个目标值 target。找出 nums 中的三个整数，使得它们的和与 target 最接近。返回这三个数的和。假定每组输入只存在唯一答案。

例如，给定数组 nums = [-1，2，1，-4], 和 target = 1.

与 target 最接近的三个数的和为 2. (-1 + 2 + 1 = 2).
## 解法
### 思路
快速排序+双指针：
- 快速排序数组
- 定义变量ans，初始化为nums[0] + nums[1] + nums[2] - target的绝对值
- 遍历数组：
    - 定义第一个数为nums[i]
    - 定义头尾指针：
        - head = nums[i + 1]
        - tail = nums[nums.length - 1]
    - 第二第三个数对应头尾指针指向的元素
    - 计算sum，如果sum值与target的差的绝对值小于ans，那么就更新ans
    - 如果sum < target，说明总和需要增加，head右移
    - 如果sum > target，说明总和需要减小，tail左移
    - 如果sum = target，直接返回结果
### 代码
```java
class Solution {
    public int threeSumClosest(int[] nums, int target) {
        quickSort(nums);
        int ans = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length; i++) {
            int head = i + 1, tail = nums.length - 1;
            while (head < tail) {
                int sum = nums[i] + nums[head] + nums[tail];
                if (Math.abs(target - sum) < Math.abs(target - ans)) {
                    ans = sum;
                }

                if (sum < target) {
                    head++;
                } else if (sum > target) {
                    tail--;
                } else {
                    return sum;
                }
            }
        }

        return ans;
    }

    private void quickSort(int[] arr) {
        sort(0, arr.length - 1, arr);
    }

    private void sort(int head, int tail, int[] arr) {
        if (head >= tail) {
            return;
        }

        int pivot = partition(head, tail, arr);
        sort(head, pivot - 1, arr);
        sort(pivot + 1, tail, arr);
    }

    private int partition(int head, int tail, int[] arr) {
        int num = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= num) {
                tail--;
            }

            arr[head] = arr[tail];

            while (head < tail && arr[head] <= num) {
                head++;
            }

            arr[tail] = arr[head];
        }

        arr[head] = num;
        return head;
    }
}
```