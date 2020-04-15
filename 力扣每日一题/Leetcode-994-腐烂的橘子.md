---
title: Leetcode-994
date: 2020-3-4 15:52:53
tags: ['Leetcode','春招每日一题']
---

# Leecode-994 [Rotting Oranges](https://leetcode-cn.com/problems/rotting-oranges/)

## 思路：BFS

**题目描述**

- 在一个二维的网格中，0代表空的网格，1代表新鲜的橘子，2代表腐烂的橘子
- 每过一分钟，新鲜的橘子会被腐烂的橘子传染，传染方向来自上下左右
- 如果有无法感染的橘子，最终返回-1
- 如果全部感染，返回需要的分钟数（轮数）

Example1:

<img src='https://assets.leetcode.com/uploads/2019/02/16/oranges.png'>

```
Input: [[2,1,1],[1,1,0],[0,1,1]]
Output: 4
```

Example2:

```makefile
Input: [[2,1,1],[0,1,1],[1,0,1]]
Output: -1
Explanation:  The orange in the bottom left corner (row 2, column 0) is never rotten, because rotting only happens 4-directionally.
```



**Solution：BFS**

- 一开始，我们找出所有腐烂的橘子，将他们放入队列，作为第0层的节点。
- 然后进行BFS遍历，每个节点的相邻节点可能是上下左右四个方向的节点，注意判断网格边界的特殊情况
- 由于可能存在无法被感染的橘子，我们需要记录新鲜橘子的数量。在BFS中，每遍历到一个橘子（污染了一个橘子）就将新鲜橘子的数量减掉1。

<!--more-->



## Java

**Solution :**

```java
class Solution {
    public int orangesRotting(int[][] grid) {
        int M = grid.length;
        int N = grid[0].length;
        Queue<int[]> queue = new LinkedList<>();

        int count = 0;
        for(int r = 0;r < M;r++){
            for(int c = 0;c < N;c++){
                if(grid[r][c] == 1) count++;
                else if(grid[r][c] == 2) queue.add(new int[]{r,c});
            }
        }


        int round = 0;
        while(count > 0 && queue.size()>0){
            round ++;
            int n = queue.size();
            for(int i = 0;i < n;i++){
                int[] orange = queue.poll();
                int r = orange[0];
                int c = orange[1];
                if(r - 1>= 0 && grid[r-1][c] == 1){
                    grid[r-1][c] = 2;
                    count --;
                    queue.add(new int[]{r-1,c});
                } 
                if (r+1 < M && grid[r+1][c] == 1) {
                    grid[r+1][c] = 2;
                    count--;
                    queue.add(new int[]{r+1, c});
                }
                if (c-1 >= 0 && grid[r][c-1] == 1) {
                    grid[r][c-1] = 2;
                    count--;
                    queue.add(new int[]{r, c-1});
                }
                if (c+1 < N && grid[r][c+1] == 1) {
                    grid[r][c+1] = 2;
                    count--;
                    queue.add(new int[]{r, c+1});
                }                
            }
        }
        if(count > 0){
            return -1;
        }else{
            return round;
        }
    }
}
```



## Python 

**Solution :**

```python
class Solution:
    def orangesRotting(self, grid: List[List[int]]) -> int:
        M = len(grid)
        N = len(grid[0])
        queue = []

        count = 0 # count用来记录新鲜橘子的数量
        for r in range(M):
            for c in range(N):
                if grid[r][c] == 1:
                    count += 1
                elif grid[r][c] == 2:
                    queue.append((r,c))

        round = 0 # round表示腐烂需要的轮数，或者是分钟数
        while count > 0 and len(queue) > 0:
            round += 1
            n = len(queue)
            for i in range(n):
                r,c = queue.pop(0)
                if r-1 >= 0 and grid[r-1][c] == 1:
                    grid[r-1][c] = 2
                    count -= 1
                    queue.append((r-1,c))
                if r+1 < M and grid[r+1][c] == 1:
                    grid[r+1][c] = 2
                    count -= 1
                    queue.append((r+1,c))
                if c-1 >= 0 and grid[r][c-1] == 1:
                    grid[r][c-1] = 2
                    count -= 1
                    queue.append((r,c-1))
                if c+1 < N and grid[r][c+1] == 1:
                    grid[r][c+1] = 2
                    count -= 1
                    queue.append((r,c+1))
        if count > 0:
            return -1
        else:
            return round
```



