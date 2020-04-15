---
title: Leetcode-082
date: 2019-6-12 15:52:53
tags: Leetcode
---

# Leecode-082 [Remove Duplicates from Sorted List II](https://leetcode-cn.com/problems/remove-duplicates-from-sorted-list-ii/)

## 思路：

**题目描述**

- 本题是83题目的升级版本
- 给出一个链表，如果重复就直接把所有重复的元素都去掉，只保留没有重复的元素



**Solution：一次遍历**

- 需要的参数
  - dummynode(注意这个节点不能初始化时候就跟后面连起来)
  - preNode  前一个节点
  - currNode 当前指向的节点
  - realNode  记录不重复的节点（初始化为dummy）

- 步骤
  - 如果currNode的值跟前一个节点preNode不一样并且和下一个节点next的值不一样。那么realNode就记录下当前节点的位置
  - 同时需要考虑preNode为dummy 还有 currentNode.next最后为空的情况

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
        if(head == null) return null;
        ListNode dummy     = new ListNode(0);
        ListNode preNode   = dummy;
        ListNode currNode  = head;
        ListNode realNode  = dummy;
        while(currNode != null){
            if((preNode == dummy || currNode.val != preNode.val) && (currNode.next == null || currNode.val != currNode.next.val)){
                realNode.next = currNode;
                realNode = currNode;
            }
            preNode = currNode;
            currNode = currNode.next;
            preNode.next = null;
        }
        return dummy.next;
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
        if not head: return None
        dummy    = ListNode(0)
        preNode  = dummy
        realNode = dummy
        currNode = head
        while currNode:
            if (preNode == dummy or currNode.val != preNode.val) and (currNode.next == None or currNode.next.val != currNode.val):
                realNode.next = currNode
                realNode = currNode
            preNode  = currNode
            currNode = currNode.next
            preNode.next = None   #这里必要把preNode 和 currNode断开链接
        return dummy.next

```



