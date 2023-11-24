# [LeetCode_53_最大子数组和](https://leetcode.cn/problems/maximum-subarray)
## 解法
### 思路
动态规划
### 代码
```java
class Solution {
    public int maxSubArray(int[] nums) {
        int n = nums.length, ans = Integer.MIN_VALUE;
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = nums[i - 1] + Math.max(0, dp[i - 1]);
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }
}
```
# [LeetCode_1410_HTML实体解析器](https://leetcode.cn/problems/html-entity-parser)
## 解法
### 思路
- 思考过程：
  - 可以与现将`实体`与`特殊字符`的关系通过键值对存储起来
  - 有了这个映射关系，就能够通过遍历字符串来进行解析了
- 算法过程：
  - 初始化1个hash表用于存储映射关系
  - 遍历字符串，当发现当前字符为`&`时就进行匹配和替换
  - 遍历结束，返回字符串即可
### 代码
```java
class Solution {
  public String entityParser(String text) {
    Map<String, String> map = new HashMap<>(6);
    map.put("&quot;", "\"");
    map.put("&apos;", "'");
    map.put("&amp;", "&");
    map.put("&gt;", ">");
    map.put("&lt;", "<");
    map.put("&frasl;", "/");

    StringBuilder sb = new StringBuilder();
    for (int i = 0; i < text.length(); i++) {
      if (text.charAt(i) != '&') {
        sb.append(text.charAt(i));
        continue;
      }

      boolean flag = false;
      for (Map.Entry<String, String> entry : map.entrySet()) {
        int len = entry.getKey().length();
        if (i + len <= text.length() && Objects.equals(entry.getKey(), text.substring(i, i + len))) {
          sb.append(entry.getValue());
          i += len - 1;
          flag = true;
          break;
        }
      }

      if (!flag) {
        sb.append(text.charAt(i));
      }
    }

    return sb.toString();
  }
}
```
# [LeetCode_2824_统计和小于目标的下标对数目](https://leetcode.cn/problems/count-pairs-whose-sum-is-less-than-target)
## 解法
### 思路
- 思考过程：
  - 模拟即可
- 算法过程：
  - 2层循环数组，外层坐标`i`起始为0，内层坐标起始为`i + 1`
  - 内层判断内网层元素和是否小于目标`target`，如果符合就累加暂存值
  - 遍历结束返回暂存值
### 代码
```java
class Solution {
    public int countPairs(List<Integer> nums, int target) {
        int ans = 0;
        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) < target) {
                    ans++;
                }
            }
        }
        return ans;
    }
}
```