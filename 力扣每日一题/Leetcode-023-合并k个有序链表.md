---
title: Leetcode-023-合并K个有序链表
date: 2019-5-25 15:52:53
tags: ['Leetcode','堆']
---

# Leecode-023 [Merge k Sorted Lists](https://leetcode-cn.com/problems/merge-k-sorted-lists/)

## 思路：

**Solution：堆**

## Java

**Solution : 利用Comparator接口和PriorityQueue**

- 需要的参数：
  - 一个NodeComparator比较node.val的大小
  - 一个dummy node 初始为空节点
  - 一个current node 指向dummy
  - 一个PriorityQueue最小堆队列
- 步骤：
  - 首先构造NodeComparator比较node.val的大小
  - 一个current node 指向dummy
  - 遍历k个list中每一个的头结点，如果头结点不为空，都把它放进PriorityQueue形成一个最小堆
  - 如果PriorityQueue不为空，取出其中最小的节点（pqueue.poll()），current指向取出的节点，current向后移动一位; 同时如果取出后这个node的next不为空，那么把next丢进PriorityQueue中
  - 返回dummy.next

<!--more-->

```java
/*
Definition for singly-linked list.
pubulic class ListNode{
	int val;
	ListNode Next;
	ListNode(int x){val = x;}
}
*/

class Solution{
    class NodeComparator implements Comparator<ListNode>{
        public int compare(ListNode a, ListNode b){
            return a.val - b.val;
        }
    }
    public ListNode mergeKLists(ListNode[] lists){
        ListNode dummy = new ListNode(0);
        if(lists == null || lists.length == 0) return dummy.next;
        int size = lists.length;
        ListNode current = dummy;
        NodeComparator cmp = new NodeComparator();
        PriorityQueue<ListNode> pqueue = new PriorityQueue(cmp);
        for(int i = 0;i < size;i++){ 
            //遍历k个list中每一个的头结点，都把它放进PriorityQueue形成一个最小堆
            if(lists[i] != null) pqueue.add(lists[i]);
    }
        while(pqueue.size()!=0){
            ListNode node = pqueue.poll(); //取出其中最小的节点
            current.next = node; //current指向取出的节点
            current = current.next; //current向后移动一位
            //同时如果取出后这个node的next不为空，那么把next丢进PriorityQueue中
            if(node.next!=null) pqueue.add(node.next);
        }
        return dummy.next;
    }
}
```

#### 补充知识

**Comparator**

```java
public interface Comparator<T>
```

1.比较功能，对一些对象的集合施加了一个整体排序 。 可以将比较器传递给排序方法（如Collections.sort或Arrays.sort ），以便对排序顺序进行精确控制。 比较器还可以用来控制某些数据结构（如顺序sorted sets或sorted maps ），或对于不具有对象的集合提供的排序natural ordering 。 
2.通过比较c上的一组元素S的确定的顺序对被认为是与equals一致当且仅当c.compare(e1, e2)==0具有用于S每e1和e2相同布尔值e1.equals(e2)。 
3.一般是在比较器例如: Collections.sort(List<T> list, Comparator<? super T> c) ，Arrays.sort(T[] a, Comparator<? super T> c)根据指定的比较器引起的顺序对指定的列表进行排序。 在Comparator比较器中重写int compare(T o1, T o2) 如果遇到数字的比较，直接在方法内返回两个对象的属性的差值，例如o1.getValue()-o2.getValue() 是升序，o2.getValue()-o1.getValue() 是降序；如果遇到字符形式的比较利用compareTo(T o) 方法进行比较，该方法比较从头开始每一个字符，当前者大于后者返回1，当前者小于后者返回-1。

**代码举例**

```java
import java.util.*;

public class HashTest {
    public static void main(String[] args){
        List<Node> list = new ArrayList<Node>();
        list.add(new Node("yguo",25));
        list.add(new Node("msdfj",22));
        list.add(new Node("skf",20));
        list.add(new Node("sfe",23));
        System.out.println("===Age排序===");
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getAge()-o2.getAge();
            }
        });

        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println("===Name排序====");
        Collections.sort(list, new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o1.getName().compareTo(o2.getName());
            }
        });
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }
        System.out.println("===Age排序===");
        Collections.sort(list);
        for(Iterator<Node> it = list.iterator(); it.hasNext();){
            System.out.println(it.next());
        }

    }
}
class Node implements Comparable<Node>{
    private String name;
    private int age;
    public Node(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public int compareTo(Node other){
        if(age > other.getAge())
            return -1;
        if(age < other.getAge())
            return 1;
        return 0;
    }

    public String toString(){
        return "Name "+name+" age "+age;
    }
}
```

```java
//输出：
===Age排序===
Name skf age 20
Name msdfj age 22
Name sfe age 23
Name yguo age 25
===Name排序====
Name msdfj age 22
Name sfe age 23
Name skf age 20
Name yguo age 25
===Age排序===
Name yguo age 25
Name sfe age 23
Name msdfj age 22
Name skf age 20
```





## Python 

**Solution :heapq堆**

```python
# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, x):
#         self.val = x
#         self.next = None

class Solution:
    def mergeKLists(self, lists: List[ListNode]) -> ListNode:
        import heapq
        dummy = ListNode(0)
        p = dummy
        head = []
        #遍历k个list中每一个的头结点，如果头结点不为空，都把它放进PriorityQueue形成一个最小堆
        for i in range(len(lists)):
            if lists[i]:
                heapq.heappush(head,(lists[i].val,i))
                lists[i] = lists[i].next
        #如果PriorityQueue不为空，取出其中最小的节点
        while head:
            val,idx = heapq.heappop(head)
            p.next = ListNode(val)
            p = p.next
            if lists[idx]:
                heapq.heappush(head,(lists[idx].val,idx))
                lists[idx] = lists[idx].next
        return dummy.next
```

### 补充知识

#### python高级（堆heapq模块）

1. 一种著名的数据结构是堆（heap），它是一种优先队列。
2. 优先队列让你能够以任意顺序添加对象，并随时（可能是在两次添加对象之间）找出（并删除）最小的元素。相比于列表方法min，这样做的效率要高得多。
3. 实际上，Python没有独立的堆类型，而只有一个包含一些堆操作函数的模块。这个模块名为heapq（其中的q表示队列），它包含6个函数，其中前4个与堆操作直接相关。必须使用列表来表示堆对象本身。

```python
                              模块heapq中一些重要的函数
                                                       函 数                                                           描 述
                                                      heappush(heap, x)                                        将x压入堆中
                                                        heappop(heap)                                      从堆中弹出最小的元素
                                                        heapify(heap)                                         让列表具备堆特征
                                                  heapreplace(heap, x)                                     弹出最小的元素，并将x压入堆中
                                                      nlargest(n, iter)                                     返回iter中n个最大的元素
                                                        nsmallest(n, iter)                                   返回iter中n个最小的元素

```

