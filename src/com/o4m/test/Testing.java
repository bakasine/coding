package com.o4m.test;

import com.o4m.coding.Solution1;
import com.o4m.entity.ListNode;

public class Testing {
    public static void main(String[] args) {
        String test = "" + 1 + 2;
        System.out.println(test);
        Solution1 s1 = new Solution1();
        System.out.println(s1.largestNumber(new int[]{10 ,2}));
        ListNode head = new ListNode(3);
        ListNode next = new ListNode(2);
        ListNode next1 = new ListNode(0);
        ListNode next2 = new ListNode(4);
        head.next = next;
        next.next = next1;
        next1.next = next2;
        next2.next = next;
        System.out.println(s1.detectCycle(head));
    }
}
