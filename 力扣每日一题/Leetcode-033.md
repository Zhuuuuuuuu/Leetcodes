---
title: Leetcode-033
date: 2019-6-15 15:52:53
tags: Leetcode
---

# Leecode-033 [**Search in Rotated Sorted Array**]

## 思路：二分法查找

**Solution：binary search**

- 需要的参数
  - start 初始化为0
  - end  初始化为length-1
  - mid  初始化为start + (end-start)/2

- 分以下三种情况进行查找

  - 第一种情况：target = mid

  ![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200217214041.png)

  <!--more-->

  - 第二种情况    num[start] < nums[mid]

  ![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200217214618.png)

  1.  如果nums[start] <= target 并且 nums[mid>=target]  那么说明 target在start和mid之间，就把mid 复制给end
  2. 如果num[mid] 在 nums[start] 和 target之间  那么就把mid 赋值给 start

  - 第三种情况 nums[mid] < nums[end] < nums[start]

  ![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200217214901.png)

1. 如果  target 在 mid 和 end之间 ，那么就把mid赋值给start
2. 如果 target 小于mid, 那么就把mid赋值给end



- 最后判断 start 和 end 是不是本来就等于target

<!--more-->



## Java

**Solution **

```java
class Solution {
    public int search(int[] nums, int target) {
        if(nums == null || nums.length == 0) return -1;
        int start = 0;
        int end = nums.length - 1;
        while(start + 1 < end){
            int mid = start + (end - start)/2; //为了防止overflow的问题
            if(nums[mid] == target) return mid;
            if(nums[start] < nums[mid]){
                if(nums[start]<=target && target<= nums[mid]) end = mid;
                else start = mid
            }else if(nums[end] > nums[mid]){
                if(nums[end] >= target && target>= nums[mid]) start = mid;
                else end = mid;
            }
        }
        if(nums[start] == target) return start;
        if(nums[end]   == target) return end;
        return -1;
     }
}
```



## Python 

**Solution **

```python
class Solution:
    def search(self, nums: List[int], target: int) -> int:
        if not nums or len(nums) == 0: return -1
        start = 0
        end = len(nums) - 1
        while start + 1 < end:
            mid = start + (end - start)//2
            if nums[mid] == target:
                return mid
            elif nums[start] < nums[mid]:
                if nums[start] <= target and nums[mid]>= target:
                    end = mid
                else:
                    start = mid
            elif nums[mid] < nums[end]:
                if nums[end] >= target and nums[mid] <= target:
                    start = mid
                else:
                    end = mid
        if nums[start] == target: return start
        if nums[end]   == target: return  end;
        return -1
```



