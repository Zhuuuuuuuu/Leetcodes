---
title: Leetcode-083
date: 2019-8-15 15:52:53
tags: ['Leetcode']
---

# Leecode-083 [Remove Duplicates from Sorted List](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list/)

## 思路：一次遍历

**题目描述**

给出一个链表，如果有重复的数只记录一次

```
Input: 1->1->2
Output: 1->2
```

```
Input: 1->1->2->3->3
Output: 1->2->3
```



**Solution：**

- 遍历一次 （直到为null）
  - 如果当前节点和下一个节点相同就跳过该节点
  - 如果当前节点和下一个节点不同就记录一次

<!--more-->



## Java

**Solution :**

```java
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode curr = head;
        while (curr != null && curr.next != null){
            if(curr.val == curr.next.val){
                curr.next = curr.next.next;
            }else{
                curr = curr.next;
            }
        }
        return head;
    }
}
```



## Python 



**Solution :**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def deleteDuplicates(self, head: ListNode) -> ListNode:
        curr = head
        while curr != None and curr.next != None:
            if curr.val == curr.next.val: curr.next = curr.next.next
            else: curr = curr.next
        return head
```



