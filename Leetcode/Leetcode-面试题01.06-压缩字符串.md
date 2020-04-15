---
title: Leetcode-面试题01.06-压缩字符串
date: 2020-3-16 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-面试题01.06 [Compress String LCCI](https://leetcode-cn.com/problems/compress-string-lcci/)

## 思路：双指针

**题目描述：压缩字符串**

- 重复的字母用数字代替，只显示出现第一次的字母
- 如果压缩后的字符串比之前的长就返回原来的字符串

示例1：

```
Input: "aabcccccaaa"
Output: "a2b1c5a3"
```



示例2：

```
Input: "abbccd"
Output: "abbccd"
Explanation: 
The compressed string is "a1b2c2d1", which is longer than the original string.
```



![](https://zhuuu-bucket.oss-cn-beijing.aliyuncs.com/img/20200316214454.gif)

<!--more-->

**Solution：双指针**

- 快慢指针，一个从头开始遍历，另一个从当前位置往后判断字符是否相等
- 如果判断到相等，就一直往后判断，直至不相等出现，返回出现的次数（即j - i）
- 同时要更新慢指针到新的位置



## Java

**Solution :**

```java
class Solution {
    public String compressString(String S) {
        int n = S.length();
        int i = 0;
        StringBuilder sb = new StringBuilder();
        while(i < n){
            int j = i;  //两个指针都从头开始遍历
            while (j < n && S.charAt(j) == S.charAt(i)){
                j++;
            }
            sb.append(S.charAt(i)); //输出这个字母
            sb.append(j-i);        //输出此字母出现的次数
            i = j;                //更新慢指针位置
        }

        String res = sb.toString();  //将sb转换成字符串
        if(res.length() < S.length()){  // 判断新的字符串和原来的字符串相比
            return res;
        }else {
            return S;
        }
    }
}
```



## Python 

**Solution :**

```python
class Solution:
    def conpressString(self,S:str)->str:
        n   = len(S)  #字符串的长度
        res =  ""     #res是返回结果
        i   =  0      # 初始的指针
        while i < n:
            j = i     # j从i开始遍历
            while j < n and S[j] == S[i]:
                j += 1
            res += S[i] + str(j-i) # 字符串拼接
            if len(res) < len(S):  # 判断新的字符串和原来的字符串相比
                return res
            else:
                return S
```



