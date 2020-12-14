# [LeetCode_379_电话目录管理系统](https://leetcode-cn.com/problems/design-phone-directory/)
## 解法
### 思路
- 优先级队列：保存get时候返回的号码，因为返回时需要有顺序，所以使用优先级队列
- 普通队列：保存release后的号码，在优先级队列为空后，将队列元素放入优先级队列中，在传递的时候，因为release是无序的，所以会使用优先级队列
- 布尔数组：保存号码状态
### 代码
```java
class PhoneDirectory {
    private PriorityQueue<Integer> pQueue;
    private Queue<Integer> queue;
    private boolean[] bucket;
    public PhoneDirectory(int maxNumbers) {
        this.pQueue = new PriorityQueue<>();
        this.queue = new LinkedList<>();
        for (int i = 0; i < maxNumbers; i++) {
            pQueue.offer(i);
        }
        this.bucket = new boolean[maxNumbers];
        Arrays.fill(bucket, true);
    }

    public int get() {
        if (pQueue.isEmpty()) {
            if (queue.isEmpty()) {
                return -1;
            }

            while (!queue.isEmpty()) {
                pQueue.offer(queue.poll());
            }
        }

        if (pQueue.isEmpty()) {
            return -1;
        }

        Integer num = pQueue.poll();
        bucket[num] = false;
        return num;
    }

    public boolean check(int number) {
        if (number > bucket.length) {
            return false;
        }

        return bucket[number];
    }

    public void release(int number) {
        if (check(number)) {
            return;
        }

        queue.offer(number);
        bucket[number] = true;
    }
}
```