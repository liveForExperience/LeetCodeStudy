# [Contest_1_按奇偶性交换后的最大数字](https://leetcode-cn.com/problems/largest-number-after-digit-swaps-by-parity/)
## 解法
### 思路
- 数字转换为字符数组
- 判断每一位上的奇偶性，统计成一个布尔数组
- 将字符数组分成奇偶两个数组，并分别排序
- 最后根据布尔数组和对应的奇偶数组坐标，依次从最大值开始放入数组中
- 将数组拼接后转换为数字返回
### 代码

```java
class Solution {
    public int largestInteger(int num) {
        String str = Integer.toString(num);
        int len = str.length();
        char[] cs = str.toCharArray();
        boolean[] bs = new boolean[len];

        int oddCount = 0, evenCount = 0;
        for (int i = 0; i < cs.length; i++) {
            int n = cs[i] - '0';

            if (n % 2 == 0) {
                evenCount++;
                bs[i] = false;
            } else {
                oddCount++;
                bs[i] = true;
            }
        }

        int[] odds = new int[oddCount], evens = new int[evenCount];
        int oi = 0, ei = 0;
        for (int i = 0; i < bs.length; i++) {
            if (bs[i]) {
                odds[oi++] = cs[i] - '0';
            } else {
                evens[ei++] = cs[i] - '0';
            }
        }

        Arrays.sort(odds);
        Arrays.sort(evens);

        oi = oddCount - 1;
        ei = evenCount - 1;
        StringBuilder sb = new StringBuilder();
        for (boolean b : bs) {
            if (b) {
                sb.append(odds[oi--]);
            } else {
                sb.append(evens[ei--]);
            }
        }

        return Integer.parseInt(sb.toString());
    }
}
```
# [Contest_2_向表达式添加括号的最小结果](https://leetcode-cn.com/problems/minimize-result-by-adding-parentheses-to-expression/)
## 解法
### 思路
- 因为括号放置在加号的两侧，而且必定需要在加号的两侧有至少一个数字，所以可以先找到加号的坐标，以及其两侧的一个数字，将这三个字符作为初始坐标
- 然后基于该初始坐标，递归的判断左右坐标左移和右移后，总的表达式组成的值是多少，并保留较小值以及对应的字符串
- 递归结束后返回保留的最小值对应的字符串即可
### 代码
```java
class Solution {
    private int min;
    private String ans;
    public String minimizeResult(String expression) {
        int plusIndex = expression.indexOf("+");
        int left = plusIndex - 1, right = plusIndex + 1;

        min = Integer.parseInt(expression.substring(0, plusIndex)) + Integer.parseInt(expression.substring(plusIndex));
        ans = "(" + expression + ")";

        dfs(left, right, expression, plusIndex);

        return ans;
    }

    private void dfs(int left, int right, String expression, int plusIndex) {
        if (left < 0 || right >= expression.length()) {
            return;
        }

        int lt = left == 0 ? 1 : Integer.parseInt(expression.substring(0, left)),
                rt = right == expression.length() - 1 ? 1 : Integer.parseInt(expression.substring(right + 1)),
                lp = Integer.parseInt(expression.substring(left, plusIndex)),
                rp = Integer.parseInt(expression.substring(plusIndex + 1, right + 1));

        int sum = (lp + rp) * lt * rt;

        if (sum < min) {
            min = sum;
            ans = expression.substring(0, left) + "(" + expression.substring(left, right + 1)
                    + ")" + expression.substring(right + 1);
        }

        dfs(left - 1, right, expression, plusIndex);
        dfs(left, right + 1, expression, plusIndex);
    }
}
```
# [Contest_3_K次增加后的最大乘积](https://leetcode-cn.com/problems/maximum-product-after-k-increments/)
## 解法
### 思路
- 根据观察发现，只要保证所有数值都保持尽量的一致，没有特别小的值，那么得到的乘积就是最大值
- 将所有数值放入到小顶堆中，一次拿出堆顶元素累加并放回队列，直到K使用完
- 然后累乘并取模即可
- 注意累成过程中可能溢出，先用long声明
### 代码
```java
class Solution {
    public int maximumProduct(int[] nums, int k) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            queue.offer(num);
        }

        while (k-- > 0 && !queue.isEmpty()) {
            int num = queue.poll();
            num++;
            queue.offer(num);
        }
        
        long ans = 1;
        while (!queue.isEmpty()) {
            ans *= queue.poll();
            ans %= 1000000007;
        }

        return (int)ans;
    }
}
```
# [Contest_4_花园的最大总美丽值](https://leetcode-cn.com/problems/maximum-total-beauty-of-the-gardens/)
## 解法
### 思路
- 最终总美丽值可以分成2部分
    - full * fullNum：大于等于target的花园数量 * full值
    - least * partial：不达到target的花园中最小值 * partial值
- 所以可以将代表花园的flowers数组分成前缀（< target）和后缀（>= target）两部分
- 计算过程：
  - 因为后缀的所有花园值预期是要达到target的，而整个过程又是不断枚举所有情况来计算，所以可以将flowers数组进行排序
  - 排序之后，数组的前半部分就是所谓的前缀，后半部分就是所谓的后缀
  - 然后计算使后缀所有都达到target，需要的flower数目
  - 初始情况可以视为将所有flowers[i]都填充为target，然后计算所需要的flowers，通常情况，这个leftFlowers是负数
  - 然后根据leftFlowers是否是非负整数，来判断情况是否有效，因为负数就说明无法全部将目前想要填充的后缀值都填充为target
  - 当leftFlowers为非负整数的时候，说明后缀ok了，然后就需要将前缀的最小值想方设法搞大
  - 因为数组已经是排序过的了，所以坐标越大，值越大。然后从头开始遍历元素，根据当前花园的个数，计算之前的花园值是否能用剩下的话填充到和当前花园值一样的数值，如果可以，那就说明当前数值就是一个可能的least，这个过程一直持续到无法再填充为止
  - 然后就根据上面的情况进行计算，用后缀 * full + least * partial来得到可能的最大值
  - 然后不断的减小后缀的大小，更新最大值
  - 遍历结束以后就返回暂存的最大值即可
### 代码
```java
class Solution {
    public long maximumBeauty(int[] flowers, long newFlowers, int target, int full, int partial) {
        Arrays.sort(flowers);
        long n = flowers.length;

        if (flowers[0] >= target) {
            return full * n;
        }

        long fullStatus = n * target;
        long curFlowers = 0;
        for (int flower : flowers) {
            curFlowers += Math.min(flower, target);
        }

        long leftFlowers = curFlowers + newFlowers - fullStatus;
        long sumFlowers = 0, max = 0;
        for (int i = 0, x = 0; i <= n; i++) {
            if (leftFlowers >= 0) {
                while (x < i && (long) x * flowers[x] - sumFlowers <= leftFlowers) {
                    sumFlowers += flowers[x++];
                }

                long beauty = (n - i) * full;
                if (x > 0) {
                    beauty += Math.min((long) (target - 1), (leftFlowers + sumFlowers) / x) * partial;
                }
                max = Math.max(max, beauty);
            }

            if (i < n) {
                leftFlowers += target - Math.min(flowers[i], target);
            }
        }

        return max;
    }
}
```