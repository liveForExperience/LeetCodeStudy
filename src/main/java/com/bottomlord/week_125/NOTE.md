# [LeetCode_786_第K个最小的素数分数](https://leetcode-cn.com/problems/k-th-smallest-prime-fraction/)
## 解法
### 思路
优先级队列
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> Double.compare(y[0] * 1.0 / y[1], x[0] * 1.0 / x[1]));
        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                queue.offer(new int[]{arr[i], arr[j]});
                if (queue.size() > k) {
                    queue.poll();
                }
            }
        }

        return queue.poll();
    }
}
```
## 解法二
### 思路
- 因为序列是严格递增的，所以必然有：
  - arr[0] / arr[1]
  - arr[0] / arr[2] < arr[1] / arr[2]
  - ......
  - arr[0] / arr[n - 1] ... < arr[n - 2] / arr[n - 1]
- 将如上每一行的第一个元素放入优先级队列中，这样就把n-1个最小的元素放入到优先级队列中
- 然后依次的取出队列顶端的元素，这个元素必定是前x个最小的元素
- 之后将这个元素所在行的后一个元素放入到队列中
- 不断循环，直到找到第k个为止
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        int len = arr.length;
        PriorityQueue<int[]> queue = new PriorityQueue<>(Comparator.comparingDouble(x -> arr[x[0]] * 1.0 / arr[x[1]]));
        for (int i = 1; i < len; i++) {
            queue.offer(new int[]{0, i});
        }

        while (k-- > 1) {
            int[] a = queue.poll();
            if (a == null) {
                continue;
            }

            if (a[0] + 1 < a[1]) {
                queue.offer(new int[]{a[0] + 1, a[1]});
            }
        }

        int[] a = queue.poll();
        return new int[]{arr[a[0]], arr[a[1]]};
    }
}
```
## 解法三
### 思路
二分法：
- 如果在0和1之间确定一个值x，然后求出分数值比x小的所有元素
- 那么如果正好有k个元素小于x值，那么这k个值里的最大值就是要求的分数组合
- 否则，比k多，说明这个x大了，往其左边找，比k少，说明这个x小了，往其右边找
- 另外，因为数组是单调递增的，所以确认过的分子，在分母增大的过程中，一定也是小于x值的，所以可以不用重复计算比较
### 代码
```java
class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
                double head = 0, tail = 1;
        while (true) {
            double mid = head + (tail - head) / 2;
            int count = 0, x = 0, y = 1, i = -1;
            for (int j = 1; j < arr.length; j++) {
                while (arr[i + 1] * 1.0 / arr[j] < mid) {
                    i++;
                    if (arr[i] * 1.0 / arr[j] > x * 1.0 / y) {
                        x = arr[i];
                        y = arr[j];
                    }
                }
                count += i + 1;
            }

            if (count == k) {
                return new int[]{x, y};
            }

            if (count > k) {
                tail = mid;
            } else {
                head = mid;
            }
        }
    }
}
```