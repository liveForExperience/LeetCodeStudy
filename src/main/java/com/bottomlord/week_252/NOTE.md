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
# [LeetCode_2105_给植物浇水II](https://leetcode.cn/problems/watering-plants-ii)
## 解法
### 思路
模拟过程
- 初始化变量：
  - `leftA`：Alice水壶中还剩余的水量
  - `leftB`：Bob水壶中还剩余的水量
  - `back`：回去补水的次数（也即题目结果）
  - `ia`：Alice在数组中遍历的坐标
  - `ib`：Bob在数组中遍历的坐标
- 遍历数组模拟浇水过程：
  - 如果`ia == ib`，说明Alice和Bob碰头，会浇同一株植物的水，此时按照题目的规则，需要判断3种情况
    - `leftA > needA`：此时Alice浇水，无需返回
    - `leftB > needB`：此时Bob浇水，无需返回
    - 其他情况：说明Alice和Bob都不能完成浇水的工作，此时就由Alice返回补水并浇水，此时`back++`来记录
    - 处理完成后，break循环即可
  - 其他情况：
    - 基于Alice和Bob水壶中的水量和他们对应的植物要求的水量的关系，来判断是否需要返回，如果需要，则`back++`
    - 处理完成后，`ia++`，`ib--`，两者相向而行
- 遍历结束后，返回`back`作为结果即可
### 代码
```java
class Solution {
  public int minimumRefill(int[] plants, int capacityA, int capacityB) {
    int leftA = capacityA, leftB = capacityB, ia = 0, ib = plants.length - 1, back = 0;
    while (ia <= ib) {
      int needA = plants[ia], needB = plants[ib];

      if (ia == ib) {
        if (needA > leftA && needB > leftB) {
          back++;
        }

        break;
      }

      if (needA > leftA) {
        back++;
        leftA = capacityA;
      }

      if (needB > leftB) {
        back++;
        leftB = capacityB;
      }

      leftA -= needA;
      leftB -= needB;
      ia++;
      ib--;
    }

    return back;
  }
}
```
# [LeetCode_2960_统计已测试设备](https://leetcode.cn/problems/count-tested-devices-after-test-operations)
## 解法
### 思路
模拟
- 根据题意，声明一个int变量`cnt`，存储已测试的电池个数
- 遍历数组，用当前电池电量减去当前电池个数，即可得到题目所描述的每次测试完成，剩余电池就减1%的状况，并得到当时的电池数
  - 如果大于0，则`cnt++`
  - 否则直接跳过
- 遍历结束后，返回`cnt`即可
### 代码
```java
class Solution {
    public int countTestedDevices(int[] batteryPercentages) {
        int cnt = 0;
        for (int p : batteryPercentages) {
            if (p - cnt > 0) {
                cnt++;
            }
        }
        
        return cnt;
    }
}
```