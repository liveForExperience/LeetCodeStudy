# [LeetCode_306_累加数](https://leetcode-cn.com/problems/additive-number/)
## 解法
### 思路
回溯+减枝+高精度运算
### 代码
```java
class Solution {
    private String num;
    private int n;
    private final List<List<Integer>> list = new ArrayList<>();
    public boolean isAdditiveNumber(String num) {
        this.num = num;
        this.n = num.length();
        return backTrack(0);
    }

    private boolean backTrack(int index) {
        int size = list.size();
        if (index == n) {
            return list.size() >= 3;
        }

        LinkedList<Integer> cur = new LinkedList<>();
        int max = num.charAt(index) == '0' ? index + 1 : n;

        for (int i = index; i < max; i++) {
            cur.addFirst(num.charAt(i) - '0');
            if (size < 2 || check(list.get(size - 1), list.get(size - 2), cur)) {
                list.add(cur);
                if (backTrack(i + 1)) {
                    return true;
                }
                list.remove(list.size() - 1);
            }
        }

        return false;
    }

    private boolean check(List<Integer> x, List<Integer> y, List<Integer> cur) {
        int num = 0;
        List<Integer> sum = new ArrayList<>();
        for (int i = 0; i < x.size() || i < y.size(); i++) {
            if (i < x.size()) {
                num += x.get(i);
            }

            if (i < y.size()) {
                num += y.get(i);
            }

            sum.add(num % 10);
            num /= 10;
        }
        
        if (num > 0) {
            sum.add(num);
        }

        boolean result = sum.size() == cur.size();

        for (int i = 0; i < sum.size() && result; i++) {
            if (!Objects.equals(sum.get(i), cur.get(i))) {
                result = false;
                break;
            }
        }

        return result;
    }
}
```