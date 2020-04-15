---
title: Leetcode-524
date: 2019-6-12 15:52:53
tags: ['Leetcode','双指针']
---

# Leecode-524 [Longest Word in Dictionary through Deleting](https://leetcode-cn.com/problems/longest-word-in-dictionary-through-deleting/)

## 思路：双指针

**题目描述：**

给定一个字符串和一个字符串字典，找到字典里面最长的字符串，该字符串可以通过删除给定字符串的某些字符来得到。如果答案不止一个，返回长度最长且字典顺序最小的字符串。如果答案不存在，则返回空字符串。

示例1：

```
输入:
s = "abpcplea", d = ["ale","apple","monkey","plea"]
```

输出

```
"apple"
```



示例2：

```
输入:
s = "abpcplea", d = ["a","b","c"]
```

输出：

```
"a"
```

```
所有输入的字符串只包含小写字母。
字典的大小不会超过 1000。
所有输入的字符串长度不会超过 1000。
```

**Solution：**

- 题目中“字符串可以通过删除给定字符串的某些字符来得到” ==== 这句话的意思是：找的这个是给定字符串的子串，相同字符串的顺序相等。
- “如果答案不止一个，返回长度最长且字典顺序最小的字符串” ====这个话的意思是如果找到的字符串长度相等，那么需要比较字典顺序，用CompareTo()方法
- 考虑先对字符串进行条件判断.先排除不符合条件的，接下来对符合条件的字符串进行比对:
- 采用双指针：遍历给定字符串，因为找的是自己，与字典中的字符串一个个比，如果相等，他两的下标都加一，在判断字典中的字符串是否和下标相等了，如果相等，则证明找到了。



<!--more-->



## Java

**Solution :**

```java
class Solution {
    public String findLongestWord(String s, List<String> d) {
        char[] sc = s.toCharArray();
        String result = "";
        for(String ds : d){
            // 先对长度进行判断,(如果小于结果字符串,不用比了 || (如果相等 && 字典顺序小 也不用比了))
            if(result.length() > ds.length() || (result.length() == ds.length() && result.compareTo(ds) < 0)){
                continue;
            }

            if(isSubStr(sc,ds)){
                result = ds;
            }
        }
        return result;
    }
    public boolean isSubStr(char[] sc,String ds){
        // 字典字符串下标
        int i = 0;
        char[] dsc = ds.toCharArray();
        for(char s : sc){
            if(s == dsc[i]){
                i ++;
                // 如果下标和长度相等,就证明找到了
                if(i == dsc.length){
                    return true;
                }
            }
        }
        // 这还找不到,就证明失败了
        return false;
    }
}

```













## Python 



**Solution :**

```python

```



