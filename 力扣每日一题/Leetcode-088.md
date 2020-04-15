---
title: Leetcode-088
date: 2019-8-17 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-088 [Merge Sorted Array](https://leetcode-cn.com/problems/merge-sorted-array/)

## 思路：双指针

**题目描述**

```
Input:
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3

Output: [1,2,2,3,5,6]
```

**把归并结果存到第一个数组上。**



**Solution：**

- 需要从尾开始遍历，否则在nums1上归并得到的值会覆盖还未进行归并比较的值。



<!--more-->



## Java

**Solution :**

```java
public void merge(int[] nums1, int m, int[] nums2, int n) {
    int index1 = m - 1, index2 = n - 1;
    int indexMerge = m + n - 1;
    while (index1 >= 0 || index2 >= 0) {
        if (index1 < 0) {
            nums1[indexMerge--] = nums2[index2--];
        } else if (index2 < 0) {
            nums1[indexMerge--] = nums1[index1--];
        } else if (nums1[index1] > nums2[index2]) {
            nums1[indexMerge--] = nums1[index1--];
        } else {
            nums1[indexMerge--] = nums2[index2--];
        }
    }
}
```

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200310194017.png)



## Python 



**Solution :**

```python
class Solution:
    def merge(self, nums1: List[int], m: int, nums2: List[int], n: int) -> None:
        """
        Do not return anything, modify nums1 in-place instead.
        """
        mergeIndex = m + n - 1
        index1,index2 = m - 1, n  - 1
        while index1 >= 0 or index2 >= 0:
            if index1 < 0:
                nums1[mergeIndex] = nums2[index2]
                mergeIndex -= 1
                index2 -= 1
            elif index2 < 0:
                nums1[mergeIndex] = nums1[index1]
                mergeIndex -= 1
                index1 -= 1
            elif nums1[index1] > nums2[index2]:
                nums1[mergeIndex] = nums1[index1]
                mergeIndex -= 1
                index1 -= 1
            else:
                nums1[mergeIndex] = nums2[index2]
                mergeIndex -= 1
                index2 -= 1
```



![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200310193948.png)