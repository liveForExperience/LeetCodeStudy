# [LeetCode_475_供暖器](https://leetcode-cn.com/problems/heaters/)
## 解法
### 思路
- 先将house和heater数组进行排序
- 然后进行2层的for循环
  - 外层遍历house
  - 内层遍历heater，目的是找到第一个heater坐标比当前house坐标大的值
    - 如果没有找到，heater坐标没有移动，那说明第一个heater就比当前house更靠右，这时候，直接计算半径最大值
    - 如果没有找到，heater坐标已经越界，那说明当前房子比所有heater都靠右，那么也直接计算最大半径值
    - 如果找到了，那么就将当前找到的坐标与房子之间的半径，和前一个heater和当前房子的半径进行比较，取最小值，再和最大值作比较，更新半径值
- 遍历结束以后，返回最大值即可
### 代码
```java
class Solution {
    public int findRadius(int[] houses, int[] heaters) {
        Arrays.sort(houses);
        Arrays.sort(heaters);
        int max = 0, index = 0;
        for (int house : houses) {
            while (index < heaters.length && heaters[index] < house) {
                index++;
            }
            
            if (index == 0) {
                max = Math.max(max, heaters[index] - house);
            } else if (index == heaters.length) {
                max = Math.max(max, house - heaters[index - 1]);
            } else {
                max = Math.max(max, Math.min(heaters[index] - house, house - heaters[index - 1]));
            }
        }
        
        return max;
    }
}
```
# [LeetCode_1933_判断字符串是否可分解为值均等的子串](https://leetcode-cn.com/problems/check-if-string-is-decomposable-into-value-equal-substrings/)
## 解法
### 思路
遍历数组+模拟
### 代码
```java
class Solution {
    public boolean isDecomposable(String s) {
        boolean find2 = false;
        for (int i = 0; i < s.length(); i++) {
            int count = 1;
            while (i < s.length() - 1 && s.charAt(i) == s.charAt(i + 1)) {
                count++;
                i++;
            }

            count %= 3;

            if (count == 1) {
                return false;
            }

            if (count == 2) {
                if (find2) {
                    return false;
                }

                find2 = true;
            }
        }

        return find2;
    }
}
```