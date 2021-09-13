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