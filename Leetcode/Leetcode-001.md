---
title: Leetcode-001
date: 2019-5-12 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-001 [Two Sum]

## 思路：

**Solution 1：暴力法**

- 使用两层循环，外层循环计算当前元素与 target之间的差值，内层循环寻找该差值，若找到该差值，则返回两个元素的下标。
- 时间复杂度O(n^2)



**Solution 2:  利用HashMap减少查询时间**

- 使用一层循环，每遍历到一个元素就计算该元素与 target 之间的差值
- 然后到 HashMapHashMap 中寻找该差值
- 如果找到，则返回两个元素在数组 nums的下标
- 如果没有找到，则将当前元素存入 HashMap 中 (Key: nums[i], Value: i)

![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200316214800.png)

<!--more-->



## Java

[Java版本地址](https://github.com/Zhuuuuuuuu/Leetcodes/blob/master/java/001__Two%20sum)

**Solution 1：暴力法**

```java
class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] res = new int[2];
        for (int i = 0; i < nums.length; i++) {
            int dif = target - nums[i];
            // j = i + 1 的目的是减少重复计算和避免两个元素下标相同
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[j] == dif){
                    res[0] = i;
                    res[1] = j;
                    return res;
                }
            }
        }
        return res;
    }
}

```

**Solution 2:  利用HashMap减少查询时间**

```java
import java.util.HashMap;

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int [] res  = new int[2];  //创建一个res数组长度为2
        if(nums == null || nums.length <= 1) return res;
        HashMap<Integer,Integer> map = new HashMap<Integer,Integer>();
        for(int i = 0;i < nums.length;i++){
            int num = nums[i]; //先拿到当前这个数
            int val = target - num; //另外一个数值是target-当前这个数
            if(map.containsKey(val)){ //如果找到这个数
                res[0] = i; //返回第一个索引
                res[1] = map.get(val); //返回第二个索引
                return res; //返回结果
            }else map.put(num,i); //没有找到就把当前数字放进hashmap中
        }
        return res;
    }
}
```



## Python 

[Python版本地址](https://github.com/Zhuuuuuuuu/Leetcodes/tree/master/python3)

**Solution :利用字典**

```python
class Solution:
    def twoSum(self, nums, target):
        """
        :type nums: List[int]
        :type target: int
        :rtype: List[int]
        """
        q={}
        for x in range(len(nums)):
            a = target -nums[x]
            if nums[x] in q:
                return q[nums[x]],x
            else:
                q[a] = x
```



