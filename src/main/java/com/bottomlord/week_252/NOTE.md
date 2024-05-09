# [LeetCode_2079_给植物浇水](https://leetcode.cn/problems/watering-plants)
## 解法
### 思路
模拟过程
- 初始化变量：
  - `left`：初始值为`capacity`，用于代表当前水壶中可用的水量
  - `step`：代表当前已用的步数
- 遍历数组来模拟浇水的过程
  - 判断当前植物需要的数量`need`与`left`之间的关系
    - `need <= left`：说明水壶水量足够浇水，则`step`累加1
    - `need > left`：说明水壶水量不足，则需要回头去补充水，`step += i * 2 + 1`（回头`i`步，返回`i + 1`步），代表来回的步数
- 遍历结束后返回`step`作为结果
### 代码
```java
class Solution {
    public int wateringPlants(int[] plants, int capacity) {
        int left = capacity, step = 0;
        for (int i = 0; i < plants.length; i++) {
            int plant = plants[i];
            if (plant > left) {
                step += i * 2 + 1;
                left = capacity;
            } else {
                step++;
            }
            
            left -= plant;
        }
        
        return step;
    }
}
```