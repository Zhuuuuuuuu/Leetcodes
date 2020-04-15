---
title: Leetcode-面试题40-最小K个数
date: 2020-3-19 13:52:53
tags: ['Leetcode','春招每日一题','堆']
---

# Leecode-面试题40 [最小的k个数](https://leetcode-cn.com/problems/zui-xiao-de-kge-shu-lcof/)

## 思路：快排/堆

**题目描述**

输入整数数组 `arr` ，找出其中最小的 `k` 个数。

**示例 1：**

```
输入：arr = [3,2,1], k = 2
输出：[1,2] 或者 [2,1]
```

**示例 2：**

```
输入：arr = [0,1,2,1], k = 1
输出：[0]
```

<!--more-->

**Solution：**

- TopK问题，不管是求前K大/前K小/第K大/第K小等，都有3中不错的方法

  ​    **1. O(N)：用快排变形最最最高效解决TopK问题** 

  ​    **2.O (Nlogk):  大根堆（前K小）/小根堆（前K大）**

  ​    **3. O(NlogK)：二叉搜索树** 

## Java

**Solution 1 快排:**

​	注意找前K大/前K小/第K大/第K小，是不需要对整个数组进行O(NlogN)的排序的！因为可以通过快排切分直接O(N)找到第K大的数，如果只会先排序再找的话，那啥...基本上就交代了叭( ͡° ͜ʖ ͡°)...）。

​	因此本题先通过快排切分排好第K小的数，根据快排切分的性质，它左边的k-1个数都小于等于

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
       if (k == 0 || arr.length == 0) {
            return new int[0];
       }
       //注意最后一个参数传入我们要找的下标（第k小的数下标是k-1）
       return quickSearch(arr,0,arr.length - 1, k - 1);
    }

    private int[] quickSearch(int[] nums,int lo,int hi,int k){
        //每次快排切分一次，找到排序后小标为j的元素，如果j恰好等于k就返回j以及j左边所有的数
        int j = partition(nums,lo,hi);
        if(j == k){
            return Arrays.copyOf(nums,j + 1);
        }
        return j > k? quickSearch(nums,lo,j - 1,k) : quickSearch(nums,j + 1,hi,k);
    }

    //快排切分，返回下标j，使得比num[j]小的数都在j的左边，比num[j]大的数都在j的右边
    private int partition(int[] nums, int lo, int hi) {
        int v = nums[lo];
        int i = lo, j = hi + 1;
        while (true) {
            while (++i <= hi && nums[i] < v);
            while (--j >= lo && nums[j] > v);
            if (i >= j) {
                break;
            }
            int t = nums[j];
            nums[j] = nums[i];
            nums[i] = t;
        }
        nums[lo] = nums[j];
        nums[j] = v;
        return j;
    }
}
```

**快排变形时间复杂度分析：**

​    因为我们是要找下标为k的元素，第一次切分的时候需要遍历整个数组(0 ~ n)找到了下标是j的元素，假如k比j小的话，那么我们下次切分只要遍历数组(0~k-1)的元素就行啦，反之如果k比j大的话，那下次切分只要遍历数组(k+1～n)的元素就行啦，总之平均情况下，可以看作每次调用partition遍历的元素数目都是上一次遍历的1/2，因此时间复杂度是N + N/2 + N/4 + ... + N/N = 2N, 因此时间复杂度是**O(N)**。





**Solution 2 堆:**

用堆虽然时间复杂度比快排变形慢了点，但是因为Java中提供了现成的PriorityQueue（默认小根堆），所以不需要自己写大段的模版代码，因此实现起来最简单，没几行代码，写起来很快～～面试的时候可以先快点写出这个方案🤫

   注意本题是求**前K小**，因此用一个容量为K的**大根堆（**每次poll出最大的数，那堆中保留的就是前K小啦）。注意不是小根堆嗷！小根堆的话需要把全部的元素都入堆，那是O(NlogN)😂，就不是O(NlogK)啦～～

```java
// 保持堆的大小为K，然后遍历数组中的数字，遍历的时候做如下判断：
// 1. 若目前堆的大小小于K，将当前数字放入堆中。
// 2. 否则判断当前数字与大根堆堆顶元素的大小关系，如果当前数字比大根堆堆顶还大(或等于)，这个数就直接跳过；
//    反之如果当前数字比大根堆堆顶小，先poll掉堆顶，再将该数字放入堆中。
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // 默认是小根堆，实现大根堆需要重写一下比较器。
        Queue<Integer> pq = new PriorityQueue<>((v1, v2) -> v2 - v1);
        for (int num: arr) {
            if (pq.size() < k) {
                pq.offer(num);
            } else if (num < pq.peek()) {
                pq.poll();
                pq.offer(num);
            }
        }
        
        // 返回堆中的元素
        int[] res = new int[pq.size()];
        int idx = 0;
        for(int num: pq) {
            res[idx++] = num;
        }
        return res;
    }
}
```



**3. 二叉搜索树也可以\**O(NlogK)解决TopK问题～\****    

​    BST相对没有前两种方法辣么热门，但是也很简单，和大根堆的思路差不多～不得不提的是，与前两种方法相比，**BST的优势就是求得的前K个数字保证是有序的**。

​    因为有重复的数字，所以用的是TreeMap而不是TreeSet（有的语言的标准库自带TreeMultiset，也是可以的）。TreeMap的key是数字，value是该数字的个数。我们遍历数组中的数字，维护一个数字总个数为K的TreeMap，每遍历一个元素：

​    1. 若目前map中数字个数小于K，则将map中当前数字对应的个数+1；

    2. 否则，判断当前数字与map中最大的数字的大小关系：若当前数字大于等于map中的最大数字，就直接跳过该数字；若当前数字小于map中的最大数字，则将map中当前数字对应的个数+1，并将map中最大数字对应的个数减1.

```java
class Solution {
    public int[] getLeastNumbers(int[] arr, int k) {
        if (k == 0 || arr.length == 0) {
            return new int[0];
        }
        // TreeMap的key是数字, value是该数字的个数。
        // cnt表示当前map总共存了多少个数字。
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int cnt = 0;
        for (int num: arr) {
            // 1. 遍历数组，若当前map中的数字个数小于k，则map中当前数字对应个数+1
            if (cnt < k) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                cnt++;
                continue;
            } 
            // 2. 否则，取出map中最大的Key（即最大的数字), 判断当前数字与map中最大数字的大小关系：
            //    若当前数字比map中最大的数字还大(或等于)，就直接忽略；
            //    若当前数字比map中最大的数字小，则将当前数字加入map中，并将map中的最大数字的个数-1。
            Map.Entry<Integer, Integer> entry = map.lastEntry();
            if (entry.getKey() > num) {
                map.put(num, map.getOrDefault(num, 0) + 1);
                if (entry.getValue() == 1) {
                    map.pollLastEntry();
                } else {
                    map.put(entry.getKey(), entry.getValue() - 1);
                }
            }
            
        }

        // 最后返回map中的元素
        int[] res = new int[k];
        int idx = 0;
        for (Map.Entry<Integer, Integer> entry: map.entrySet()) {
            int freq = entry.getValue();
            while (freq-- > 0) {
                res[idx++] = entry.getKey();
            }
        }
        return res;
    }
}
```

## Python 



**Solution :**

```python

```



