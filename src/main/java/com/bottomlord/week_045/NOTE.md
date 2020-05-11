# Interview_1714_最小K个数
## 题目
设计一个算法，找出数组中最小的k个数。以任意顺序返回这k个数均可。

示例：
```
输入： arr = [1,3,5,7,2,4,6,8], k = 4
输出： [1,2,3,4]
```
提示：
```
0 <= len(arr) <= 100000
0 <= k <= min(100000, len(arr))
```
## 解法
### 思路
快排
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }
        
        Arrays.sort(arr);
        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }
}
```
## 解法二
### 思路
小顶堆
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        if (k == 0) {
            return new int[0];
        }

        PriorityQueue<Integer> queue = new PriorityQueue<>(k);
        for (int num : arr) {
            queue.offer(num);
        }

        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            if (queue.isEmpty()) {
                return ans;
            }
            
            ans[i] = queue.poll();
        }
        
        return ans;
    }
}
```
## 解法三
### 思路
自己实现快排
- 题目要求返回的数组元素不需要排序，所以只要partition的坐标位置与k-1相等就可以
- 因为确定partition的过程就是将小于当前坐标应有元素的值全部放置到了该坐标左侧
### 代码
```java
class Solution {
    public int[] smallestK(int[] arr, int k) {
        int head = 0, tail = arr.length - 1;
        while (head < tail) {
            int pivot = partition(arr, head, tail);
            if (pivot == k - 1) {
                break;
            } else if (pivot < k - 1) {
                head = pivot + 1;
            } else {
                tail = pivot - 1;
            }
        }

        int[] ans = new int[k];
        System.arraycopy(arr, 0, ans, 0, k);
        return ans;
    }

    private int partition(int[] arr, int head, int tail) {
        int low = arr[head];
        while (head < tail) {
            while (head < tail && arr[tail] >= low) {
                tail--;
            }
            arr[head] = arr[tail];
            
            while (head < tail && arr[head] <= low) {
                head++;
            }
            arr[tail] = arr[head];
        }
        
        arr[head] = low;
        return head;
    }
}
```