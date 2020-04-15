---
title: Leetcode-021
date: 2019-5-25 15:52:53
tags: Leetcode
---

# Leecode-021 [Merge Two Sorted Lists]

## 思路：

**Solution：遍历，判断链表值的大小**

- 需要的参数：
  - l1指向第一个链表
  - l2指向第二个链表
  - curr节点，dummy节点 （初始化为空节点）
- 步骤：
  - 首先把curr节点指向dummy
  - 如果l1和l2节点不为空的情况下
    - 如果l1的值比l2小，那么curr指向l1, 并且l1向后移一位
    - 如果l1的值比l2大,   那么curr指向l2, 并且l2向后移一位
    - curr再向后移动一位
  - 如果l1或者l2出现一个为空了，那么 curr就指向不为空的那个

<!--more-->

## Java

**Solution：判断链表值的大小**

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
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode curr  = dummy;
        while(l1 != null && l2 != null){
            if(l1.val < l2.val){
                curr.next = l1;
                l1 = l1.next;
            }else{
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }
        if(l1 == null) curr.next = l2;
        if(l2 == null) curr.next = l1;
        return dummy.next;
    }
}
```

## Python 



**Solution : 判断链表值的大小**

- 需要的参数：
  - l1指向第一个链表
  - l2指向第二个链表
  - curr节点，dummy节点 （初始化为空节点）
- 步骤：
  - 首先把curr节点指向dummy
  - 如果l1和l2节点不为空的情况下
    - 如果l1的值比l2小，那么curr指向l1, 并且l1向后移一位
    - 如果l1的值比l2大,   那么curr指向l2, 并且l2向后移一位
    - curr再向后移动一位
  - 如果l1或者l2出现一个为空了，那么 curr就指向不为空的那个

```python
class Solution:
    def mergeTwoLists(self, l1: ListNode, l2: ListNode) -> ListNode:
        curr = dummy = ListNode(0)
        while l1 and l2:
            if l1.val < l2.val:
                curr.next=l1
                l1=l1.next
            else:
                curr.next=l2
                l2=l2.next
            curr=curr.next
        curr.next =l1 or l2
        return dummy.next
```



