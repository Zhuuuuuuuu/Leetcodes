---
title: Leetcode-面试题01.07-旋转矩阵
date: 2020-04-08 10:52:53
tags: ['Leetcode']
---

# Leecode-[面试题 01.07. Rotate Matrix LCCI](https://leetcode-cn.com/problems/rotate-matrix-lcci/)

## 思路：对称旋转

**题目描述**

给定矩阵：N*N大小的矩阵

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200408105441.png)



要得到旋转后矩阵为：

```
[7,4,1]
[8,5,2]
[9,6,3]
```

<!--more-->



**Solution：一次旋转，一次行对称**

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200408105441.png)



如上图：先由对角线[1,5,9]为轴进行旋转：

于是数组变成了：

```
[1,4,7]
[2,5,8]
[3,6,9]
```



然后再以每一行中点进行翻转：就得到了

```
[7,4,1]
[8,5,2]
[9,3,6]
```





## Java

**Solution :**

```java
class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        // 先以对角线（左上-右下）为轴进行翻转
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = tmp;
            }
        }
        // 再对每一行以中点进行翻转
        int mid = n/2;
        // int mid = n >> 1; 相当于除2   
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < mid; j++) {
                int tmp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = tmp;
            }
        }
    }
}
```



## Python 



**Solution :**

```python

```



