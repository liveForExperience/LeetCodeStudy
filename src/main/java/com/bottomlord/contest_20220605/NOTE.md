# [Contest_1_极大极小游戏](https://leetcode.cn/problems/min-max-game/)

## 解法

### 思路

递归模拟

### 代码

```java
class Solution {
    public int minMaxGame(int[] nums) {
        int n = nums.length;

        if (n == 1) {
            return nums[0];
        }

        int[] newNums = new int[n / 2];
        boolean flag = true;
        for (int i = 0; i < n; i += 2) {
            if (flag) {
                newNums[i / 2] = Math.min(nums[i], nums[i + 1]);
            } else {
                newNums[i / 2] = Math.max(nums[i], nums[i + 1]);
            }
            flag = !flag;
        }

        return minMaxGame(newNums);
    }
}
```

# [Contest_2_划分数组是最大差为K](https://leetcode.cn/problems/partition-array-such-that-maximum-difference-is-k/)

## 解法

### 思路

- 数组排序
- 遍历数组，内层继续循环向后搜索，直到起始元素+k的和小于内层遍历到的元素
- 内层遍历结束后，计数1次
- 整体遍历结束以后，返回计数总和

### 代码

```java
class Solution {
    public int partitionArray(int[] nums, int k) {
        int n = nums.length, ans = 0;
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            int cur = nums[i];

            while (i + 1 < n && nums[i + 1] <= cur + k) {
                i++;
            }

            ans++;
        }

        return ans;
    }
}
```

# [Contest_3_替换数组中元素](https://leetcode.cn/problems/replace-elements-in-an-array/)

## 解法

### 思路

- 使用map记录元素值和坐标的映射关系
- 遍历nums数组，填充map
- 遍历operations数组，更新map
- 遍历map，更新nums数组

### 代码

```java
class Solution {
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], i);
        }

        for (int[] operation : operations) {
            int pre = operation[0], cur = operation[1];
            int pi = map.get(pre);

            map.remove(pre);
            map.put(cur, pi);
        }

        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            nums[entry.getValue()] = entry.getKey();
        }

        return nums;
    }
}
```

# [Contest_4_设计一个文本编辑器](https://leetcode.cn/problems/design-a-text-editor/)

## 解法

### 思路

- 记录游标的坐标
- 记录游标前面和后面两部分的字符串内容
- 不同的行为模拟更新3个变量的内容
- 注意字符串内容使用java的StringBuilder记录

### 代码

```java
class TextEditor {
    private StringBuilder post = new StringBuilder(), pre = new StringBuilder();
    int ci = 0;

    public TextEditor() {

    }

    public void addText(String text) {
        pre.append(text);
        ci += text.length();
    }

    public int deleteText(int k) {
        int preLen = pre.length();
        if (k >= pre.length()) {
            pre = new StringBuilder();
            ci -= preLen;
            return preLen;
        }

        pre.delete(preLen - k, preLen);
        ci -= k;
        return k;
    }

    public String cursorLeft(int k) {
        int preLen = pre.length(), move = Math.min(preLen, k);
        String moveStr = pre.substring(preLen - move, preLen);
        pre.delete(preLen - move, preLen);
        post.insert(0, moveStr);
        ci -= move;
        preLen = pre.length();
        int l = Math.min(preLen, 10);

        return pre.substring(preLen - l, preLen);
    }

    public String cursorRight(int k) {
        int postLen = post.length(), move = Math.min(postLen, k);
        String moveStr = post.substring(0, move);
        post.delete(0, move);
        pre.append(moveStr);
        ci += move;
        int preLen = pre.length();
        int l = Math.min(preLen, 10);
        return pre.substring(preLen - l, preLen);
    }
}
```