# [LeetCode_447_回旋镖的数量](https://leetcode-cn.com/problems/number-of-boomerangs/)
## 解法
### 思路
- 2层循环
  - 外层确定作为回旋镖交叉点的点
  - 内层遍历所有其他的点
- 内层开始循环前，初始化一个map，key为两点间距离，value为该距离对应的组成的对数
- 内层循环的时候，判断当前计算出的2点距离，在map中是否存在
  - 存在就取出该值，并在该值基础上乘以2，累加到结果变量上，因为该值代表除了当前对，已经存在的其他距离为当前值的对，且因为顺序变更是不同的回旋镖，所以要乘以2
  - 在取出的该值基础上，加1，代表当前对也统计到之后可能的计算中
### 代码
```java
class Solution {
    public int numberOfBoomerangs(int[][] points) {
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Double, Integer> map = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }

                double distance = Math.pow(points[i][0] - points[j][0], 2) + Math.pow(points[i][1] - points[j][1], 2);
                int count = map.getOrDefault(distance, 0);
                ans += count * 2;
                map.put(distance, count + 1);
            }
        }
        
        return ans;
    }
}
```
# [LeetCode_1656_设计有序流](https://leetcode-cn.com/problems/design-an-ordered-stream/)
## 解法
### 思路
- 初始化一个变量用于记录坐标，一个数组用于存储插入的元素
- 根据insert的idKey来判断是直接插入，还是返回连续的字符串列表
### 代码
```java
class OrderedStream {
    private String[] strs;
    private int cur;
    public OrderedStream(int n) {
        this.strs = new String[n + 1];
        this.cur = 1;
    }

    public List<String> insert(int idKey, String value) {
        if (idKey != cur) {
            strs[idKey] = value;
            return Collections.emptyList();
        } else {
            strs[idKey] = value;
            List<String> ans = new ArrayList<>();
            for (int i = cur; i < strs.length; i++, cur++) {
                if (strs[i] != null) {
                    ans.add(strs[i]);
                } else {
                    break;
                }
            }
            return ans;
        }
    }
}
```
# [LeetCode_1662_检查两个字符串数组是否相等](https://leetcode-cn.com/problems/check-if-two-string-arrays-are-equivalent/)
## 解法
### 思路
2个字符串数组依次拼接并最终比较
### 代码
```java
class Solution {
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb1 = new StringBuilder(), sb2 = new StringBuilder();
        for (String s : word1) {
            sb1.append(s);
        }

        for (String s : word2) {
            sb2.append(s);
        }
        
        return sb1.toString().equals(sb2.toString());
    }
}
```
# [LeetCode_1668_最大重复子字符串](https://leetcode-cn.com/problems/maximum-repeating-substring/)
## 解法
### 思路
2层循环：
- 外层确定起始坐标
- 内层确定从该位置开始能够找到的最大的连续字符串个数
- 更新最大值
- 遍历结束后返回
### 代码
```java
class Solution {
    public int maxRepeating(String sequence, String word) {
int wordLen = word.length(), max = 0;
        for (int i = 0; i < sequence.length(); i++) {
            int count = 0;
            for (int j = i; j < sequence.length() && j + wordLen <= sequence.length();) {
                String sub = sequence.substring(j, j + wordLen);
                if (Objects.equals(sub, word)) {
                    count++;
                    j += wordLen;
                } else {
                    max = Math.max(max, count);
                    count = 0;
                    j++;
                }
            }

            max = Math.max(max, count);
        }
        return max;
    }
}
```
## 解法二
### 思路
使用字符数组遍历计数来代替String API
### 代码
```java
class Solution {
    public int maxRepeating(String sequence, String word) {
        int max = 0, len = sequence.length(), wlen = word.length();
        char[] wcs = word.toCharArray(), scs = sequence.toCharArray();
        for (int i = 0; i < len; i++) {
            if (wcs[0] == scs[i]) {
                int w = 0, k = i;
                while (k < len && wcs[w % wlen] == scs[k]) {
                    k++;
                    w++;
                }

                max = Math.max(max, w / wlen);
            }
        }
        
        return max;
    }
}
```