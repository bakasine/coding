package com.o4m.coding;

import com.o4m.entity.ListNode;
import com.o4m.entity.TreeNode;

import java.util.HashMap;
import java.util.Map;

public class Solution1 {

    // lc236 二叉树的最近公共祖先
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null) {
            return null;
        }

        if (root == p || root == q) {
            return root;
        }

        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);

        if (left == null) {
            return right;
        } else if (right == null) {
            return left;
        }

        return root;

    }

    // lc42 接雨水
    public int trap(int[] height) {
        int lMax = 0, rMax = 0, l = 0, r = height.length - 1;
        int ans = 0;
        while (l < r) {
            lMax = Math.max(lMax, height[l]);
            rMax = Math.max(rMax, height[r]);
            if (lMax < rMax) {
                ans += lMax - height[l];
                l++;
            } else {
                ans += rMax - height[r];
                r--;
            }
        }
        return ans;
    }

    // lc1 两数之和
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            if (hash.containsKey(nums[i])) {
                return new int[]{i, hash.get(nums[i])};
            }
            hash.put(target - nums[i], i);
        }
        return null;
    }

    // lc160 相交链表
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode a = headA, b = headB;
        while (a != b) {
            a = a == null ? headB : a.next;
            b = b == null ? headA : b.next;
        }
        return a;
    }

    // lc200 岛屿数量
    public int numIslands(char[][] grid) {
        if (grid.length == 0) {
            return 0;
        }
        int[][] dp = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == '1') {
                    landDfs(grid, dp, i, j);
                }
            }
        }
        return 0;
    }

    public void landDfs(char[][] grid, int[][] dp, int x, int y) {
        if (x >= grid.length || x < 0 || y >= grid[0].length || y < 0 || dp[x][y] != 0 || grid[x][y] != '1') {
            return;
        }

        dp[x][y] = 2;

        landDfs(grid, dp, x+1 , y);
        landDfs(grid, dp, x , y+1);
        landDfs(grid, dp, x-1 , y);
        landDfs(grid, dp, x , y-1);

        dp[x][y] = 1;


    }


    // lc450 删除二叉搜索树中的节点
    public TreeNode deleteNode(TreeNode root, int key) {
        deleteDfs(root, key);
        return root;
    }

    public TreeNode leftNode(TreeNode root) {
        if (root.right != null) {
            return root.right;
        }
        return leftNode(root.left);
    }

    public void deleteDfs(TreeNode root, int key) {
        if (root == null) {
            return;
        }
        if (root.val > key) {
            deleteNode(root.left, key);
        } else if (root.val < key) {
            deleteNode(root.right, key);
        } else {
            if (root.left == null && root.right == null) {
                root = null;
            } else if (root.right != null) {
                root.val = root.right.val;
                deleteNode(root.right, root.val);
            } else {
                TreeNode left = leftNode(root.left);
                root.val = left.val;
                left = null;
            }
        }
    }
}
