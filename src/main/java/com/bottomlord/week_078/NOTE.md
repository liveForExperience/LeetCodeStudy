# [LeetCode_239_滑动窗口最大值](https://leetcode-cn.com/problems/sliding-window-maximum/)
## 解法
### 思路
- 使用优先级队列来辅助存储最大值的状态
- 优先级队列中的元素需要包含两个值
    - 数组中的元素值
    - 数组中的下标
- 使用元素值是用来配合优先级队列快速获得最大值
- 使用数组中下标，是因为，获取的是滑动窗口的最大值，所以需要配合下标来淘汰掉不符合要求的最大值，也就出超出滑动窗口的最大值
- 为了能快速获得最大值，且能快速淘汰非窗口的，优先级队列的排序规则就是，先根据元素大小降序排列，然后按照下标降序排序，这样在遍历数组时候，如果遇到下标超出滑动窗口范围的，就将元素弹出队列，而第一个符合范围的就是队列中的最大值
### 代码
```java
class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums == null || nums.length == 0) {
            return new int[]{};
        }
        
        int len = nums.length;
        
        PriorityQueue<int[]> queue = new PriorityQueue<>((x, y) -> x[0] != y[0] ? y[0] - x[0] : y[1] - x[1]);
        for (int i = 0; i < k; i++) {
            queue.offer(new int[]{nums[i], i});
        }
        
        int[] ans = new int[len - k + 1];
        ans[0] = queue.peek()[0];
        
        for (int i = k; i < len; i++) {
            queue.offer(new int[]{nums[i], i});
            while (!queue.isEmpty() && queue.peek()[1] <= i - k) {
                queue.poll();
            }
            
            ans[i - k + 1] = queue.peek()[0];
        }
        
        return ans;
    }
}
```
# [LeetCode_418_屏幕可显示句子的数量](https://leetcode-cn.com/problems/sentence-screen-fitting/)
## 解法
### 思路
模拟：
- 初始化变量：
    - 获取整个句子的长度`len`，包括句末的空格
    - 当前要遍历到的单词坐标`index`
- 外层遍历rows，在这层循环中模拟填充当前行的过程
- 内层：
    - 暂存一个当前行列数的剩余值`leftCol`，用于做是否继续填充的判断
    - 开始循环，退出条件是`leftCol == 0`
    - 循环中，判断当前剩余值是否能够放置遍历到的单词
        - 可以，则`leftCol - sentence[index]`
        - 不可以，则中断退出内层循环
    - index右移，同时判断`leftCol`是否为0，如果是，代表出现单词在当前行最后一列的情况，在这种情况下不用加空格，否则就加空格，`leftCol--`
    - 判断句子中所有单词是否都被遍历过，如果是，就累加结果值`count`，同时处理特出情况：
        - 如果此时一列的长度很大，句子很短，那么此时就可以直接通过求商获得能够追加的`count`数
        - 同时因为`len`使用的是带空格的sentence长度，所以字符出现在最后一格的特殊句子不会累加到`count`里
        - 此时就重新赋值`leftCol = leftCol % len`，这样如果最后剩下不带空格句子的列数，那么就可以通过如上的过程继续循环并计算进`count`
        - 同时记得要将index复位为0，因为此时句子已经被遍历完一整遍或n遍了
- 外层循环结束后，返回`count`
### 代码
```java
class Solution {
    public int wordsTyping(String[] sentence, int rows, int cols) {
        int count = 0, len = String.join(" ", sentence).length() + 1, index = 0;
        for (int i = 0; i < rows; i++) {
            int leftCol = cols;
            while (leftCol > 0) {
                if (sentence[index].length() <= leftCol) {
                    leftCol -= sentence[index].length();
                } else {
                    break;
                }
                
                index++;
                if (leftCol != 0) {
                    leftCol--;
                }
                
                if (index == sentence.length) {
                    count += (1 + leftCol / len);
                    leftCol = leftCol % len;
                    index = 0;
                }
            }
        }
        return count;
    }
}
```