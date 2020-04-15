---
title: Leetcode-003
date: 2019-5-15 15:52:53
tags: Leetcode
---

# Leecode-003 [Longest Substring Without Repeating Characters]

## 思路：快慢指针

**Solution：快慢指针**

- 需要的参数：
  - left指针：初始化为0
  - right指针： 初始化为0
  - boolean[128]： 长度的数组，代表ASCII的字符集
  - max ： 最大值，初始化为0

<!--more-->

- 步骤：
  - right 右指针先遍历到第一个重复的字符
  - 如果遍历到重复的字符,先计算一次长度，同时如果left<right并且左右指针对应的字符不一样的话，把left指针对应的字符剔除boolean数组,同时left向前移动一位，就这样while循环一直剔除left到right和left相同为止
  - 第二层while循环结束后，right和left同时向前移动一位
  - 最后再计算一次结尾处的长度

## Java

**Solution :**

```java
class Solution {
    public int lengthOfLongestSubstring(String s) {
        if(s == null || s.length() == 0) return 0;
        int left = 0,right = 0;
        int n = s.length();
        boolean[] used = new boolean[128];
        int max = 0;
        while(right < n){ //右指针先遍历到第一个重复的字符
            if (used[s.charAt(right)] == false){
                used[s.charAt(right)] = true;
                right ++;
            } else{ //如果遍历到了重复的字符
                max = Math.max(max,right-left);
                while(left < right && s.charAt(right) != s.charAt(left)){
                    //这样while循环一直剔除left到right和left相同为止
                    used[s.charAt(left)] = false ; //把left指针对应的字符剔除boolean数组
                    left ++ ;
                } //第二层while循环结束后，right和left同时向前移动一位
                left ++;
                right ++;
            }
        }
        max = Math.max(max,right - left); //最后再计算一次结尾处的长度
        return max;
    }
}
```



## Python 

**Solution :字典**

- 需要的参数
  - 一个空字典：d   (来记录s字符串中所有的位置)
  - 初始位置： start
  - 最大长度： ans
- 步骤：
  - 首先判断当前字符在字典中有没有存在
  - 如果不存在：往字典d中添加当前i,c的记录  同时计算长度为当前索引-初始索引+1
  - 如果之前存在过：那么改变start的位置，也就是把当前start的剔除掉，移动到start的下一位

```python
class Solution:
    def lengthOfLongestSubstring(self, s: str) -> int:
        d = {}
        start = 0
        ans = 0
        for i,c in enumerate(s):
            if c in d: #首先判断当前字符在字典中有没有存在
                # 如果之前存在过：那么改变start的位置，也就是把当前start的剔除掉，移动到start的下一位
                start = max(start,d[c] + 1) 
            d[c] = i #如果不存在：往字典d中添加当前i,c的记录  
            ans = max(ans,i-start+1) #同时计算长度为当前索引-初始索引+1
        return ans #遍历完字符串，返回最后的长度
```



