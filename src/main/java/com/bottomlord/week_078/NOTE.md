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
# [LeetCode_1712_将数组分成三个子数组的方案数](https://leetcode-cn.com/problems/ways-to-split-array-into-three-subarrays/)
## 失败解法
### 思路
暴力，嵌套循环确定2个分界点并累加
### 代码
```java
class Solution {
    public int waysToSplit(int[] nums) {
        int len = nums.length;

        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        int target = sum[len - 1] / 3, ans = 0;
        for (int i = 0; i < len && sum[i] <= target; i++) {
            for (int j = i + 1; j < len; j++) {
                if (sum[i] <= sum[j] - sum[i] && sum[j] - sum[i] <= sum[len - 1] - sum[j]) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
```
## 解法
### 思路
N平方的时间复杂度导致超时，所以在外层循环确定`left`和`mid`的分界点后，内层的分界点可以采用二分法的方式。
- 一个有效的`mid`和`right`的分界点，它有两个需要满足的条件：
    - 它确定了`mid`的值大小，所以`mid >= left`
    - 它确定了`right`的大小，所以`mid <= right`
- 如上两种情况可以分别通过求这个可能的分界点取值范围边界来获得
- 通过二分法，获得最小的分界点，满足`mid >= left`
- 通过二分法，获得最大的分界点，满足`mid <= right`
- 然后这次循环中获得2个分界点的距离就是可能数
### 代码
```java
class Solution {
    public int waysToSplit(int[] nums) {
        int len = nums.length, mod = 1000000007;
        long ans = 0;
        int[] sum = new int[len];
        sum[0] = nums[0];
        for (int i = 1; i < len; i++) {
            sum[i] = sum[i - 1] + nums[i];
        }

        for (int i = 0; i < len; i++) {
            if (sum[i] * 3 > sum[len - 1]) {
                break;
            }

            int minL = i + 1, minR = len - 2;
            while (minL <= minR) {
                int mid = minL + (minR - minL) / 2;

                if (sum[mid] - sum[i] >= sum[i]) {
                    minR = mid - 1;
                } else {
                    minL = mid + 1;
                }
            }

            int maxL = i + 1, maxR = len - 2;
            while (maxL <= maxR) {
                int mid = maxL + (maxR - maxL) / 2;

                if (sum[mid] - sum[i] <= sum[len - 1] - sum[mid]) {
                    maxL = mid + 1;
                } else {
                    maxR = mid - 1;
                }
            }

            if (maxR >= minL) {
                ans = (ans + maxR - minL + 1) % mod;    
            }
        }

        return (int)ans % mod;
    }
}
```
# [LeetCode_1710_卡车上的最大单元数](https://leetcode-cn.com/problems/maximum-units-on-a-truck/)
## 解法
### 思路
- 对boxType中每一个一维数组的第二个元素做降序排序
- 暂存`truckSize`值用来记录还剩下多少卡车空间
- 遍历排序后的boxType，通过求`truckSize`和`numberOfUnitsPerBoxi`的商，再与`numberOfBoxesi`求最小值，然后再更新`truckSize`
- 直到`truckSize == 0`为止
### 代码
```java
class Solution {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, (x, y) -> y[1] - x[1]);
        int sum = 0;
        for (int[] boxType : boxTypes) {
            if (truckSize <= 0) {
                break;
            }

            int cost = Math.min(boxType[0], truckSize);
            truckSize -= cost;
            sum += cost * boxType[1];
        }
        
        return sum;
    }
}
```
# [LeetCode_1711_大餐计数](https://leetcode-cn.com/problems/count-good-meals/)
## 解法
### 思路
- 使用hash表存储菜品美味程度和个数的映射关系
- 因为(a,b)与(b,a)是同一种情况，所以需要一边比较一边填充映射，否则回重复
- 外层遍历菜品列表，获取美味程度的值
- 内层遍历所有2的21次以内的幂的值，取21次是因为美味值最大是20次方，查询幂与当前值的差是否能在map中找到，如果有，就累加其映射的个数
- 需要注意对结果取模
### 代码
```java
class Solution {
    public int countPairs(int[] deliciousness) {
        Map<Integer, Integer> map = new HashMap<>();
        
        int mod = 1000000007, ans = 0;
        for (int num : deliciousness) {
            int power = 1;
            for (int i = 0; i <= 21; i++) {
                if (map.containsKey(power - num)) {
                    ans += map.get(power - num);
                    ans %= mod;
                }
                
                power *= 2;
            }
            
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        
        return ans;
    }
}
```
# [LeetCode_420_强密码检验器](https://leetcode-cn.com/problems/strong-password-checker/)
## 解法
### 思路
- 先解决简单的：
    - 初始化变量：
        - 3个布尔变量记录是否有满足要求的3种情况
        - int值`kind`，用来记录已经满足的情况数量
        - list集合`continuous`，用来记录超过连续重复限制的情况
    - 遍历字符串，记录满足3种要求的情况，同时记录不符合的情况
    - 遍历完成后，判断是否满足题目要求，如果满足就返回0
- 再解决不符合时候，复杂的情况：
    - 如果数量满足要求：这些字符串只需要做替换动作就能符合要求，那么就只要求2个值
        - 还差多少值到达3种类型的要求
        - 根据统计出的continuous，计算出需要替换的个数，其实也就是`continuous / 3`的商
        - 求得上述2个值后，计算它们的最大值作为这种情况下要修改的值
    - 如果数量不足，那代表需要做添加操作：
        - 因为种类是3种，数量是[1,5]，所以如果需要插入的是1个，那在这种情况下，最坏的情况就是5个元素都一样，那这样的，就需要将5个连续的元素中的1个做更新，同时再加上1个，这样就能满足
        - 其他情况，首先种类最多需要2种，所以在insert的时候满足种类要求即可
        - 于是，计算出要插入的个数，只有在需要插入1个且连续序列长度是5的时候，需要返回2，其他都只要返回要插入的个数就可以了
    - 如果数量大于20，相对上面两种更加复杂：
        - 如果在删除最少字符的情况下，也就是保留20个字符，能够处理掉所有的连续字符，那么就可以直接通过删除掉所有多余20字符，并替换需要的类型数字来满足要求
        - 如果在删除最少字符的情况下不能处理掉所有的连续序列，就需要考虑通过减少处理剩下的update内容来处理
            - 通过观察发现，如果连续序列是3的整数倍，那么也就需要update整数倍次，但是，如果在这些数字上-1，那么就可以减少一次update，且删除也只需耗费1次
            - 相应的，如果是3的倍数+1，同样消耗2次操作，也能实现减少update的效果
            - 按照如上两步操作后，剩下的连续序列就都是差3的整数倍为1的长度了，如果还剩下需要删除的字符数，就继续循环清除3个字符长度，这样等同于减少一次update操作，这步操作是循环的，处理到可以删除的字符数不足3个为止
            - 在如上情况都处理完之后，就只需要计算下`过长的长度+max(需要更新的个数，需要补齐的种类数)`
### 代码
```java
class Solution {
    public int strongPasswordChecker(String password) {
        int len = password.length(), kind = 0;
        boolean num, lower, upper;
        num = lower = upper = false;
        char[] cs = password.toCharArray();
        List<Integer> continuesList = new ArrayList<>();

        for (int i = 0; i < cs.length; i++) {
            char c = cs[i];
            if (!num && Character.isDigit(c)) {
                num = true;
                kind++;
            } else if (!lower && Character.isLowerCase(c)) {
                lower = true;
                kind++;
            } else if (!upper && Character.isUpperCase(c)) {
                upper = true;
                kind++;
            }

            int continues = 1;
            while (i < len - 1 && cs[i + 1] == c) {
                i++;
                continues++;
            }

            if (continues >= 3) {
                continuesList.add(continues);
            }
        }

        if (len >= 6 && len <= 20 && kind == 3 && continuesList.isEmpty()) {
            return 0;
        }

        int needChangeKind = 3 - kind;
        if (len >= 6 && len <= 20) {
            int needChangeContinusCount = 0;
            for (int count : continuesList) {
                needChangeContinusCount += count / 3;
            }
            return Math.max(needChangeContinusCount, needChangeKind);
        }

        if (len < 6) {
            int needInsert = 6 - len;
            return needInsert == 1 && continuesList.size() == 1 && continuesList.get(0) == 5 ? 2 : needInsert;
        }

        int needDelete = len - 20, continusDeleteCount = 0;
        for (int count : continuesList) {
            continusDeleteCount += (count - 2);
        }
        if (needDelete - continusDeleteCount >= 0) {
            return needDelete + needChangeKind;
        }

        int remain = needDelete;
        for (int i = 0; i < continuesList.size(); i++) {
            int count = continuesList.get(i);
            if (count % 3 == 0) {
                remain -= 1;
                continuesList.set(i, count - 1);
                if (remain == 0) {
                    break;
                }
            }
        }

        if (remain >= 2) {
            for (int i = 0; i < continuesList.size(); i++) {
                int count = continuesList.get(i);
                if ((count - 1) % 3 == 0) {
                    continuesList.set(i, count - 2);
                    if ((remain -= 2) < 2) {
                        break;
                    }
                }
            }
        }

        while (remain >= 3) {
            for (int i = 0; i < continuesList.size(); i++) {
                int count = continuesList.get(i);
                if (count > 2) {
                    continuesList.set(i, count - 3);
                    if ((remain -= 3) < 3) {
                        break;
                    }
                }
            }
        }
        
        int needChangeContinusCount = 0;
        for (int count : continuesList) {
            needChangeContinusCount += count / 3;
        }
        
        return needDelete + Math.max(needChangeContinusCount, needChangeKind);
    }
}
```