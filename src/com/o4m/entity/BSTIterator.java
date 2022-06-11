package com.o4m.entity;

import java.util.Stack;

// lc173. 二叉搜索树迭代器
public class BSTIterator {

    TreeNode root;
    Stack<TreeNode> stack;

    public BSTIterator(TreeNode root) {
        this.stack = new Stack<>();
        this.root = root;
    }

    public int next() {
        while (root != null) {
            stack.push(root);
            root = root.left;
        }
        TreeNode tmp = stack.pop();
        root = tmp.right;
        return tmp.val;
    }

    public boolean hasNext() {
        return root != null || !stack.empty();
    }
}
