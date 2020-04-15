---
title: Leetcode-074
date: 2019-7-12 15:52:53
tags: Leetcode
---

# Leecode-074 [Search a 2D Matrix](https://leetcode-cn.com/problems/search-a-2d-matrix/)

## 思路：两次二分法查找

**题目描述**

- 从一个二维矩阵中找出想要的数值

```java
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 3
Output: true
//=============================
Input:
matrix = [
  [1,   3,  5,  7],
  [10, 11, 16, 20],
  [23, 30, 34, 50]
]
target = 13
Output: false
```



**Solution：两次二分法查找**

- 第一次：设置中间行mid,从纵向维度寻找target所在的行（row）
- 第二次：设置中间数mid,从横向维度寻找target
- 时间复杂度：两次二分查找（一次为O(logm) 两次是O(logm)(logn)）

<!--more-->



## Java

**Solution :**

```java
class Solution {
    public boolean searchMatrix(int[][] matrix, int target) {
        if(matrix == null || matrix.length == 0|| matrix[0].length == 0) return false;
        //第一次二分法，判断在哪一行
        int startRow = 0;
        int endRow   = matrix.length - 1;
        int row      = -1;
        int endCol   = matrix[0].length - 1;
        while(startRow + 1 < endRow){
            int mid = startRow + (endRow - startRow)/2;
            if(matrix[mid][endRow] < target) startRow = mid;
            else endRow = mid;
        }
        if (matrix[startRow][endCol] >= target) row = startRow;
        else if (matrix[endRow][endCol] >= target) row = endRow;
        else return false;
        //第二次二分法，判断这一行中是否存在
    	int start = 0;
        int end   = endCol;
        while (start + 1 < end){
            int mid = start + (end - start)/2;
            if(matrix[row][mid] < target) start = mid;
            else end = mid;
        }
        if(matrix[row][start]==target || matrix[row][end] == target) return true;
        else return false;
    }
}
```



## Python 



**Solution : 两次二分查找**

```python
class Solution:
    def searchMatrix(self, matrix: List[List[int]], target: int) -> bool:
        if not matrix: return False
        startRow = 0
        endRow   = len(matrix) - 1
        endCol   = len(matrix[0])  - 1
        row      =  -1
        while startRow + 1 < endRow:
            mid = startRow + (startRow - endRow)//2
            if matrix[mid][endCol] < target: startRow = mid
            else: endRow = mid
        if matrix[startRow][endCol] >= target: row = startRow
        elif matrix[endRow][endCol] >= target: row = endRow
        else: return False

        start = 0
        end = endCol
        while start + 1 < end:
            mid = start + (end - start)//2
            if matrix[row][mid] < target: start = mid
            else: end = mid
        if matrix[row][start] == target and matrix[row][end] == target: return True
        else: return False
```



