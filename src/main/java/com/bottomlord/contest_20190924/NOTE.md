# 第一题
## 题目

## 解法
### 思路
遍历数组的下标比较两个数组相同下标的元素是否相同，并计数返回
### 代码
```java
class Solution {
    public int game(int[] guess, int[] answer) {
        int ans = 0;
        for (int i = 0; i < 3; i++) {
            if (guess[i] == answer[i]) {
                ans++;
            }
        }
        return ans;
    }
}
```
# 第二题
## 题目
## 解法
### 思路
- 定义两个基本变量：
    - 分子：`numerator`，初始化为1
    - 分母：`denominator`，初始化为数组最后的元素
- 从数组的最后一个元素开始向数组头部开始遍历
- 遍历过程:
    - 每一层的公式是`cont[i - 1] + 1 / cont[i] `可以计算成为`cont[i]cont[i - 1] + 1 / cont[i]`
    - 而上面推导的值又会在下一层以倒数的形式呈现，所以在当前循环就需要将上式计算为`cont[i] / cont[i]cont[i - 1] + 1`
    - 因为`denominator`初始化是等价于`cont[i]`的，`1`等价于`numerator`的初始值，所以可以推得`denominator / denominator * cont[i - 1] + numerator`
    - 根据公式，`numerator`赋值为`denominator`，`denominator` 赋值为 `denominator * cont[i - 1] + numerator`
    - 然后对`numerator`和`denominator`求最大公约数化简
- 遍历结束后因为分子分母在循环体中进行了一次颠倒给下一次处理，但最终结果不需要颠倒，所以返回时以倒数的形式返回
### 代码
```java
class Solution {
    public int[] fraction(int[] cont) {
        int len = cont.length, numerator = 1, denominator = cont[len - 1];
        for (int i = len - 2; i >= 0; i--) {
            numerator ^= denominator;
            denominator ^= numerator;
            numerator ^= denominator;

            denominator = numerator * cont[i] + denominator;
            int gcd = gcd(numerator, denominator);

            numerator /= gcd;
            denominator /= gcd;
        }
        return new int[]{denominator, numerator};
    }

    private int gcd(int x, int y) {
        return y == 0 ? x : gcd(y, x % y);
    }
}
```
# 第三题
## 题目

## 解法
### 思路
- 对对障碍物的二维数组生成一个`Map<Integer, Set<Integer>>`映射，x坐标为key，y坐标放入set中
- 从原点开始根据字符来判断是x++还是y++
- 整个过程需要不断循环字符数组来进行，所以是while(true)嵌套for循环
- 退出循环的三种条件：
    1. x或y轴超出了终点的坐标，false
    2. 遇到了障碍，false
    3. 遇到终点，true
### 代码
```java
class Solution {
    public boolean robot(String command, int[][] obstacles, int x, int y) {
        char[] commands = command.toCharArray();
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] obstacle : obstacles) {
            int ox = obstacle[0];
            int oy = obstacle[1];

            if (map.containsKey(ox)) {
                map.get(ox).add(oy);
            } else {
                Set<Integer> set = new HashSet<>();
                set.add(oy);
                map.put(ox, set);
            }
        }

        int lx = 0, ly = 0;
        while (true) {
            for (char c : commands) {
                if (c == 'U') {
                    ly++;
                } else if (c == 'R') {
                    lx++;
                }

                if (lx == x && ly == y) {
                    return true;
                }
                
                if (lx > x || ly > y) {
                    return false;
                }
                
                if (map.containsKey(lx)) {
                    if (map.get(lx).contains(ly)) {
                        return false;
                    }
                }
            }
        }
    }
}
```