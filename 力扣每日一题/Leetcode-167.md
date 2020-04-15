---
title: Leetcode-167
date: 2019-7-12 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-167[Two Sum II - Input array is sorted](https://leetcode-cn.com/problems/two-sum-ii-input-array-is-sorted/)

## 思路：双指针

**题目描述：**

```
Input: numbers={2, 7, 11, 15}, target=9
Output: index1=1, index2=2
```



**Solution：双指针**

- 需要的参数
  - 头指针指向index= 0
  - 尾指针指向index=length-1 (最后)
  - sum = nums[i] + num[j]
- 步骤：
  - 使用双指针，一个指针指向值较小的元素，一个指针指向值较大的元素。指向较小元素的指针从头向尾遍历，指向较大元素的指针从尾向头遍历。
  - 如果两个指针指向元素的和sum == target ,那么直接返回两个数的index
  - 如果 sum > target，移动较大的元素，使得sum变小一些
  - 如果 sum < target,   移动较小的元素，使得sum变大一些

- 算法复杂度分析：
  - 数组中的元素最多遍历一次，时间复杂度为 O(N)。
  - 只使用了两个额外变量，空间复杂度为  O(1)。

<!--more-->

## Java

**Solution :**

```java
class Solution {
    public int[] twoSum(int[] numbers, int target) {
        if(numbers == null) return null;
        int i = 0;
        int j = numbers.length-1;
        while(i < j){
            int sum = numbers[i] + numbers[j];
            if(sum == target){
                return new int[]{i + 1,j + 1};
            }else if(sum < target){
                i++;
            }else{
                j--;
            }
        }
        return null;
    }
}
```



## Python 

**Solution :**

```python
class Solution:
    def twoSum(self, numbers: List[int], target: int) -> List[int]:
        if not numbers: return None
        i = 0
        j = len(numbers) - 1
        while i < j:
            sum = numbers[i] + numbers[j]
            if sum == target:
                return[i + 1,j + 1]
            elif sum <= target:
                i += 1
            else:
                j -= 1
        return None
```



