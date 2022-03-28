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