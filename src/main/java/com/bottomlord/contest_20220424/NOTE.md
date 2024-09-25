# [Contest_1_多个数组求交集](https://leetcode-cn.com/problems/intersection-of-multiple-arrays/)
## 解法
### 思路
- 用set存储交集数字
- 通过二维数组的第一个数组，初始化set
- 从第二个元素开始遍历二维数组，每次都以set为基础找到交集数字
- 遍历结束后，将set转为list并排序，最后返回
### 代码
```java
class Solution {
    public List<Integer> intersection(int[][] nums) {
        int n = nums.length;
        if (n == 0) {
            return new ArrayList<>();
        }

        Set<Integer> set = new HashSet<>();
        for (int num : nums[0]) {
            set.add(num);
        }

        for (int i = 1; i < n; i++) {
            Set<Integer> curSet = new HashSet<>();
            for (int num : nums[i]) {
                if (set.contains(num)) {
                    curSet.add(num);
                }
            }
            
            set = curSet;
        }

        List<Integer> ans = new ArrayList<>(set);
        Collections.sort(ans);
        return ans;
    }
}
```
# [Contest_2_统计圆内格点数目](https://leetcode-cn.com/problems/count-lattice-points-inside-a-circle/)
## 解法
### 思路
- 根据所有圆数组，找到所有可能格点的x和y轴的最大最小值，确定范围
- 根据x和y的范围，遍历所有可能的格点
- 最内层遍历所有可能的圆，基于曼哈顿距离公式计算是否在圆内，如果在就终止当前格点的判断，并累加找到的数值
- 遍历结束后返回结果
### 代码
```java
class Solution {
    public int countLatticePoints(int[][] circles) {
        int minX = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE,
            minY = Integer.MAX_VALUE, maxY = Integer.MIN_VALUE;

        for (int[] circle : circles) {
            int x = circle[0], y = circle[1], r = circle[2];
            minX = Math.min(x - r, minX);
            maxX = Math.max(x + r, maxX);
            minY = Math.min(y - r, minY);
            maxY = Math.max(y + r, maxY);
        }

        int count = 0;
        for (int i = minX; i <= maxX; i++) {
            for (int j = minY; j <= maxY; j++) {
                for (int[] circle : circles) {
                    int x = circle[0], y = circle[1], r = circle[2];

                    if (distance(i, j, x, y) <= r) {
                        count++;
                        break;
                    }
                }
            }
        }
        
        return count;
    }

    private double distance(int xr, int xc, int yr, int yc) {
        return Math.sqrt(Math.pow(xr - yr, 2) + Math.pow(xc - yc, 2));
    }
}
```
# [Contest_3_统计包含每个点的矩形数目](https://leetcode-cn.com/problems/count-number-of-rectangles-containing-each-point/)
## 失败解法
### 原因
超时
### 思路
- 对矩形数组和点数组根据x轴坐标升序排列
- 嵌套遍历并做判断，并根据排序状态对结果空间进行减枝
- 将符合的结果进行统计，并放入结果数组中
- points因为做了排序，所以对points进行了封装，通过二维数组的第二个元素记录原来的坐标值，这样方便在排序后仍然找得到其原来的坐标位置
### 代码
```java
class Solution {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int[][] newPoints = new int[points.length][3];
        for (int i = 0; i < points.length; i++) {
            newPoints[i] = new int[]{points[i][0], points[i][1], i};
        }

        Arrays.sort(newPoints, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int[] ans = new int[points.length];
        for (int p = 0, r = 0; p < points.length && r < rectangles.length; p++) {
            int x = newPoints[p][0], y = newPoints[p][1];
            while (r < rectangles.length && rectangles[r][0] < x) {
                r++;
            }

            int count = 0;
            for (int i = r; i < rectangles.length; i++) {
                int ry = rectangles[i][1];
                if (ry >= y) {
                    count++;
                }
            }

            ans[newPoints[p][2]] = count;
        }

        return ans;
    }
}
```
## 解法二
### 思路
- 对数组根据x排序后，在处理y是否符合情况的时候，需要不停重复的遍历，如果这个过程能够加速，应该就可以符合题目要求
- 根据题目情况可以发现，y如果是排序的状态，那么通过二分查找就能以对数的时间复杂度找到答案，提升了速度
- 但是在解法一的逻辑中，y是不断减小的，这样需要不断删除某一个元素，因为删除比新增对于基于数组实现的列表来说更慢，所以可以更换思路，将排序以非升序的方式进行排列，这样y就是一个不断增加的状态，且只有在增加元素的时候做一次排序即可
### 代码
```java
class Solution {
    public int[] countRectangles(int[][] rectangles, int[][] points) {
        Arrays.sort(rectangles, (x, y) -> y[1] - x[1]);
        int n = points.length;
        Integer[] ids = new Integer[n];
        for (int i = 0; i < ids.length; i++) {
            ids[i] = i;
        }

        Arrays.sort(ids, (x, y) -> points[y][1] - points[x][1]);
        
        int[] ans = new int[n];
        List<Integer> xs = new ArrayList<>();
        int index = 0;
        for (Integer id : ids) {
            int start = index;
            while (index < rectangles.length && rectangles[index][1] >= points[id][1]) {
                xs.add(rectangles[index][0]);
                index++;
            }

            if (index > start) {
                Collections.sort(xs);
            }

            ans[id] = index - binarySearch(xs, points[id][0]);
        }
        
        return ans;
    }
    
    private int binarySearch(List<Integer> xs, int target) {
        int head = 0, tail = xs.size();
        while (head < tail) {
            int mid = head + (tail - head) / 2;
            
            if (xs.get(mid) < target) {
                head = mid + 1;
            } else {
                tail = mid;
            }
        }

        return head;
    }
}
```
# [Contest_4_花期内花的数目](https://leetcode-cn.com/problems/number-of-flowers-in-full-bloom/)
## 解法
### 思路
差分
- 因为花期数值很大，所以差分不能通过数组进行存储，可以使用map+小顶堆来替代
- 遍历flowers生成和更新存储花期的数据结构
  - flower[0]作为花期窗口的开始，map的key存储这个开始值，value存储差分值，并将开始值放入小顶堆
  - flower[1] + 1作为花期窗口结束的第一天，存储方式和开始时间类似，只是value减去1，并将结束第一天放入小顶堆
- 对person排序，排序前，使用二维数组对person进行包装，增加坐标的记录，方便排序后仍能找到原来的坐标值，从而可以放入ans数组中
- 遍历排序后的person数组，用元素值与小顶堆的堆顶元素比较，将所有不大于person值的堆顶元素弹出，并累加，注意累加的是map中存储的值，累加出来的就是当前花期的个数，放入ans中，坐标就是之前记录的坐标值
- 可以将这个累加值暂存，这样就不用重复计算差分和
- 结束遍历，返回ans
### 代码

```java
class Solution {
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] flower : flowers) {
            int start = flower[0], end = flower[1] + 1;
            if (!map.containsKey(start)) {
                map.put(start, 1);
                queue.offer(start);
            } else {
                map.put(start, map.get(start) + 1);
            }

            if (!map.containsKey(end)) {
                map.put(end, -1);
                queue.offer(end);
            } else {
                map.put(end, map.get(end) - 1);
            }
        }

        int[][] newPersons = new int[persons.length][2];
        for (int i = 0; i < persons.length; i++) {
            newPersons[i] = new int[]{persons[i], i};
        }

        Arrays.sort(newPersons, (x, y) -> {
            if (x[0] == y[0]) {
                return x[1] - y[1];
            }

            return x[0] - y[0];
        });

        int sum = 0;


        int[] ans = new int[persons.length];

        for (int[] newPerson : newPersons) {
            int person = newPerson[0];
            while (!queue.isEmpty() && queue.peek() <= person) {
                sum += map.getOrDefault(queue.poll(), 0);
            }

            ans[newPerson[1]] = sum;
        }

        return ans;
    }
}
```