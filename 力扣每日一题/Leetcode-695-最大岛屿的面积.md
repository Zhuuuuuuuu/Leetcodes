---
title: Leetcode-695
date: 2020-3-15 15:52:53
tags: ['Leetcode','深度优先搜索']
---

# Leecode-695 [Max Area of Island](https://leetcode-cn.com/problems/max-area-of-island/)

## 思路：DFS

**题目描述**

- 有一个2D的数组，0代表海洋，1代表陆地
- 如果陆地有上下左右连在一起的，那么面积加1

示例1：

```
[[0,0,1,0,0,0,0,1,0,0,0,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,1,1,0,1,0,0,0,0,0,0,0,0],
 [0,1,0,0,1,1,0,0,1,0,1,0,0],
 [0,1,0,0,1,1,0,0,1,1,1,0,0],
 [0,0,0,0,0,0,0,0,0,0,1,0,0],
 [0,0,0,0,0,0,0,1,1,1,0,0,0],
 [0,0,0,0,0,0,0,1,1,0,0,0,0]]
```

输出结果为6



示例2：

```
[[0,0,0,0,0,0,0,0]]
```

输出结果为0



**Solution：DFS**

- 每次调用的时候默认num=1，进入后判断如果不是岛屿，则直接返回0，就可以避免错误的情况。
- 每次找到岛屿，就直接把岛屿改成0，这就是传说中的沉岛思想，就是遇到岛屿就把他和周围的全部沉默。

<!--more-->



## Java

**Solution :**

```java
class Solution {
    public int maxAreaOfIsland(int[][] grid) {
        int res = 0; 
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    res = Math.max(res, dfs(i, j, grid));
                }
            }
        } 
        return res;
    }
    
    public int dfs(int i, int j, int[][] grid) {
  // 每次调用的时候默认num为1，进入后判断如果不是岛屿，则直接返回0，就可以避免预防错误的情况。
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == 0) { 
            return 0;
        } 
// 每次找到岛屿，则直接把找到的岛屿改成0，这是传说中的沉岛思想，就是遇到岛屿就把他和周围的全部沉默。
        grid[i][j] = 0;
        int num = 1;
        num += dfs(i + 1, j, grid);
        num += dfs(i - 1, j, grid);
        num += dfs(i, j + 1, grid);
        num += dfs(i, j - 1, grid);
        return num;
        
    }
}
```



## Python 

**Solution :**

```python
class Solution:
        def dfs(self,i,j,grid):
            if i < 0 or j < 0 or i >= len(grid) or j >= len(grid[0]) or grid[i][j] == 0:
                return 0
            grid[i][j] = 0
            num = 1 #初始岛屿的面积
            num += self.dfs(i + 1, j, grid);
            num += self.dfs(i - 1, j, grid);
            num += self.dfs(i, j + 1, grid);
            num += self.dfs(i, j - 1, grid);
            return num


        def maxAreaOfIsland(self, grid: List[List[int]]) -> int:
            res = 0
            for i in range(len(grid)):
                for j in range(len(grid[0])):
                    if grid[i][j] == 1:
                        res = max(res,self.dfs(i,j,grid))
            return res

```



