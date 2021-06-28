# [LeetCode_815_公交路线](https://leetcode-cn.com/problems/bus-routes/)
## 失败解法
### 原因
超时
### 思路
bfs
### 代码
```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Set<Integer>  memo = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();
        
        for (int[] route : routes) {
            for (int num : route) {
                List<int[]> list = map.getOrDefault(num, new ArrayList<>());
                list.add(route);
                map.put(num, list);
            }
        }
        
        List<int[]> list = map.get(source);
        if (list == null) {
            return -1;
        }
        
        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] ints : list) {
            queue.offer(ints);
        }
        
        memo.add(source);
        int bus = 0;
        
        while (!queue.isEmpty()) {
             bus++;
             
             int size = queue.size();
             for (int i = 0; i < size; i++) {
                 int[] arr = queue.poll();
                 if (arr == null) {
                     continue;
                 }
                 
                 for (int num : arr) {
                     if (Objects.equals(num, target)) {
                         return bus;
                     }
                     
                     if (memo.contains(num)) {
                         continue;
                     }
                     
                     memo.add(num);
                     List<int[]> nextRoute = map.get(num);
                     if (nextRoute == null) {
                         continue;
                     }
                     
                     for (int[] nextBus : nextRoute) {
                         queue.offer(nextBus);
                     }
                 }
             }
        }
        
        return -1;
    }
}
```
## 失败解法
### 原因
超时
### 思路
在失败解法一的基础上，将记忆集合中的存储元素保存为数组
### 代码
```java
class Solution {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        if (source == target) {
            return 0;
        }

        Set<int[]> memo = new HashSet<>();
        Map<Integer, List<int[]>> map = new HashMap<>();

        for (int[] route : routes) {
            for (int num : route) {
                List<int[]> list = map.getOrDefault(num, new ArrayList<>());
                list.add(route);
                map.put(num, list);
            }
        }

        List<int[]> list = map.get(source);
        if (list == null) {
            return -1;
        }

        Queue<int[]> queue = new ArrayDeque<>();
        for (int[] ints : list) {
            queue.offer(ints);
            memo.add(ints);
        }

        int bus = 0;

        while (!queue.isEmpty()) {
            bus++;

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int[] arr = queue.poll();
                if (arr == null) {
                    continue;
                }

                for (int num : arr) {
                    if (Objects.equals(num, target)) {
                        return bus;
                    }

                    List<int[]> nextRoute = map.get(num);
                    if (nextRoute == null) {
                        continue;
                    }

                    for (int[] nextBus : nextRoute) {
                        if (memo.contains(nextBus)) {
                            continue;
                        }
                        
                        queue.offer(nextBus);
                    }
                }
            }
        }

        return -1;
    }
}
```
## 解法
### 思路
- 初始化一个map：
    - key：对应的是路线中的站点的值
    - value：对应的是当前这个站点在哪些路线上存在，记录所有路线在routes数组中的坐标的集合
- 遍历routes数组：
    - 外层遍历每一个路线
    - 内层遍历遍历当前路线的每一个站点，通过该站点，到map中找到其存在的所有路线，如果存在路线，那么就将当前外层遍历的路线与内层map中得到的路线坐标联通，联通的方式就是通过一个二维布尔数组，将2个坐标对应的布尔值赋为true
    - 同时内层将外层的坐标放到site对应的list中，存到map里
- 这次循环后，建立了路线与路线之间基于相同站点的联系，同时维护除了站点和路线之间的映射关系
- 接着初始化一个数组，长度为routes的长度，用于计算从souce到达每个对应route需要的步数，初始化为-1，代表未曾到达
- 然后通过source，从map中获取对应的route，将这些route对应的距离都设置成1，代表从source到达这些路线都需要1步
- 然后将source对应的route坐标放入queue中，进行bfs搜索，搜索的目的是找到相通的且没有搜索过的route，对这些route对应的长度做计算，也就是在前一个route的距离基础上+1，这样就把所有能够从souce出发到达的route都计算过距离了
- 最后，通过target从map中获取一个route的坐标集合，求这些集合中，不是-1且值最小的距离作为结果，如果都是-1，那就返回-1
### 代码
```java

```