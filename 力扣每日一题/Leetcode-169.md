---
title: Leetcode-169
date: 2020-3-12 12:52:53
tags: ['Leetcode','排序','投票法','哈希表']
---

# Leecode-169 [Majority Element](https://leetcode-cn.com/problems/majority-element/)

## 思路：哈希表，投票法，快排

**题目描述**

- 给定一个长度为n的数组，找出出现次数超过n/2的众数。

示例1：

```
Input: [3,2,3]
Output: 3
```

示例2：

```
Input: [2,2,1,1,1,2,2]
Output: 2
```

**Solution：**

- 哈希表
- 排序法
- 投票法

<!--more-->

1. 哈希表

   出现次数最多的元素大于n/2次，所以可以用哈希表来统计每个元素出现的次数

   使用哈希映射（HashMap）来存储每个元素以及出现的次数。对于哈希映射中的每个键值对，键表示一个元素，值表示该元素出现的次数。

   用一个循环遍历数组nums来将每个元素加入到哈希映射中，在这之后，**我们遍历哈希映射所有的键值对，返回值最大的键。同样在nums中使用打擂台的方式，维护一个最大的值，这样省去了最后对哈希映射的遍历。**

   **时间复杂度：O(n) , 空间复杂度O(n)**

   

2. 排序

   **在下图中，第一个例子是 *n*为奇数的情况，第二个例子是 *n*为偶数的情况。**

   ![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200315143508.png)



​	**如果将数组nums中的所有元素按照单调递增或者单调递减的顺序来排序，那么下标为n/2的数一定是众数（下标从0开始）**

​	**时间复杂度：O(nlogn),空间复杂度：O(logn) （语法自带的排序算法）**



3. #### Boyer-Moore 投票算法

- 如果候选人不是maj 则 maj,会和其他非候选人一起反对 会反对候选人,所以候选人一定会下台(maj==0时发生换届选举)

- 如果候选人是maj , 则maj 会支持自己，其他候选人会反对，同样因为maj 票数超过一半，所以maj 一定会成功当选



## Java

**Solution 1:哈希表**

```java
class Solution {
    //将数组中的值遍历，存入hashmap
    private Map<Integer, Integer> countNums(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<Integer, Integer>();
        for (int num : nums) {
            if (!counts.containsKey(num)) {
                counts.put(num, 1);
            }
            else {
                counts.put(num, counts.get(num)+1);
            }
        }
        return counts;
    }

    public int majorityElement(int[] nums) {
        //首先返回counts
        Map<Integer, Integer> counts = countNums(nums);
        //majorityEntry作为最后的返回结果
        Map.Entry<Integer, Integer> majorityEntry = null;
        //维护一个最大的键值对
        for (Map.Entry<Integer, Integer> entry : counts.entrySet()) {
            if (majorityEntry == null || entry.getValue() > majorityEntry.getValue()) {
                majorityEntry = entry;
            }
        }
        //返回这个值的key
        return majorityEntry.getKey();
    }
}
```



**Solution 2:排序**

```java
class Solution {
    public int majorityElement(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length/2];
    }
}
```



**Solution 3:摩尔投票法**

```java
class Solution {
    public int majorityElement(int[] nums) {
        int count = 0;
        Integer candidate = null;

        for (int num : nums) {
            if (count == 0) {
                candidate = num;
            }
            count += (num == candidate) ? 1 : -1;
        }
        return candidate;
    }
}
```





## Python 

**Solution 1:哈希表**

```python
class Solution:
    def majorityElement(self, nums: List[int]) -> int:
        #将nums以键值对返回
        counts = collections.Counter(nums)
        #返回值次数出现最大的键
        return max(counts.keys(),key = counts.get)
```



**Solution 2:排序**

```python
class Solution:
    def majorityElement(self, nums):
        nums.sort()
        return nums[len(nums)//2]
```



**Solution 3:摩尔投票法**

```python
class Solution:
    def majorityElement(self, nums):
        count = 0
        candidate = None

        for num in nums:
            if count == 0:
                candidate = num
            count += (1 if num == candidate else -1)

        return candidate
```

