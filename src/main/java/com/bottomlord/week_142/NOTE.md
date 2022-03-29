# [LeetCode_635_设计日志存储系统](https://leetcode-cn.com/problems/design-log-storage-system/)
## 解法
### 思路
- 使用时间戳对字符串进行等价转义
- 默认使用31天作为一个月的天数
- 然后保存时间戳和id对应的数组放到list里
- put的时候就是转换，然后放入list
- 获取的时候，就是转换，然后遍历list，取出符合的，因为是范围查询，所以可以采用前闭后开的方式，因为end时间如果是2000年，那么2000年的数据都应该包含，所以end的数值要+1，然后采用前闭后开
### 代码
```java
class LogSystem {
    private final int year = 12 * 31 * 24 * 60 * 60,
                    month = 31 * 24 * 60 * 60,
                    day = 24 * 60 * 60,
                    hour = 60 * 60,
                    minute = 60;

    private List<long[]> list;
    private Map<String, Integer> map;
    private String[] template = new String[]{"2000", "1", "1", "0", "0", "0"};
    public LogSystem() {
        list = new ArrayList<>();
        map = new HashMap<>();
        map.put("Year", 0);
        map.put("Month", 1);
        map.put("Day", 2);
        map.put("Hour", 3);
        map.put("Minute", 4);
        map.put("Second", 5);
    }

    public void put(int id, String timestamp) {
        list.add(new long[]{parse(timestamp), (long)id});
    }

    public List<Integer> retrieve(String start, String end, String granularity) {
        long startTime = getByGranularity(start, granularity, false),
                endTime = getByGranularity(end, granularity, true);

        List<Integer> ans = new ArrayList<>();
        for (long[] arr : list) {
            if (arr[0] >= startTime && arr[0] < endTime) {
                ans.add((int) arr[1]);
            }
        }
        
        return ans;
    }

    private long getByGranularity(String time, String granularity, boolean end) {
        String[] factors = time.split(":");
        String yearStr = factors[0], monthStr = factors[1], dayStr = factors[2],
                hourStr = factors[3], minuteStr = factors[4], secondStr = factors[5];

        int level = map.get(granularity);

        String[] origin = Arrays.copyOf(template, template.length);
        int[] cur = new int[]{Integer.parseInt(yearStr), 
                Integer.parseInt(monthStr),
                Integer.parseInt(dayStr),
                Integer.parseInt(hourStr),
                Integer.parseInt(minuteStr),
                Integer.parseInt(secondStr)};
        for (int i = 0; i < template.length; i++) {
            if (level >= i) {
                int num = i == level && end ? cur[i] + 1 : cur[i];
                origin[i] = "" + num;
            }
        }

        return parse(String.join(":", origin));
    }

    private long parse(String time) {
        String[] factors = time.split(":");
        return Long.parseLong(factors[0]) * year +
                Long.parseLong(factors[1]) * month +
                Long.parseLong(factors[2]) * day +
                Long.parseLong(factors[3]) * hour +
                Long.parseLong(factors[4]) * minute +
                Long.parseLong(factors[5]);
    }
}
```
# [LeetCode_2024_考试的最大困扰度](https://leetcode-cn.com/problems/maximize-the-confusion-of-an-exam/)
## 解法
### 思路
滑动窗口
- 通过滑动窗口来确定最大长度，这个方法需要对`T`和`F`的情况做分别的计算
- 滑动窗口要确定窗口的left和right指针
- 初始left和right都是0，然后right递增
- 在遍历的过程中，统计有效长度和不相等字符的个数
- 如果出现不相等字符数大于要求的k，那么就内层循环移动left，直到统计的不相等个数不大于k为止
- 每次遍历过程中都用统计到的长度和暂存的最大值作比较，保留最大值
- 遍历结束后返回暂存的最大值
### 代码
```java
class Solution {
    public int maxConsecutiveAnswers(String answerKey, int k) {
        return Math.max(count(answerKey, 'T', k), count(answerKey, 'F', k));
    }

    private int count(String key, char target, int k) {
        int ans = 0, left = 0, n = key.length(), cur = 0;
        for (int right = 0; right < n; right++) {
            cur += key.charAt(right) != target ? 1 : 0;

            while (cur > k) {
                cur -= key.charAt(left++) != target ? 1 : 0;
            }

            ans = Math.max(right - left + 1, ans);
        }

        return ans;
    }
}
```
# [LeetCode_651_4键键盘](https://leetcode-cn.com/problems/4-keys-keyboard/)
## 解法
### 思路
动态规划：
- dp[i]：第i步能够生成A的最大个数
- 状态转移方程：dp[i] = max(dp[i - 1] + 1, dp[j] * (i - j - 1))
- base case：dp[i] = i
- 过程：
  - 外层遍历初始化base case
  - 内层遍历`j`，然后比较`base case`和`dp[j] * (i - j - 1)`的最大值
  - 遍历结束后返回`dp[n]`
### 代码
```java
class Solution {
    public int maxA(int n) {
        int[] dp = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            dp[i] = i;
            
            for (int j = 0; j < i - 1; j++) {
                dp[i] = Math.max(dp[j] * (i - j - 1), dp[i]);
            }
        }
        
        return dp[n];
    }
}
```