package com.o4m.coding;

import com.o4m.entity.ListNode;
import com.o4m.entity.TreeNode;
import sun.reflect.generics.tree.Tree;

import java.util.Arrays;
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
        if (root == null) {
            return null;
        }
        if (root.val > key) {
            root.left = deleteNode(root.left, key);
            return root;
        }

        if (root.val < key) {
            root.right = deleteNode(root.right, key);
            return root;
        }

        if (root.val == key) {
            if (root.left == null && root.right == null) {
                return null;
            }
            if (root.right != null) {
                TreeNode tmp = root.right;
                while (tmp.left != null) {
                    tmp = tmp.left;
                }
                root.val = tmp.val;
                root.right = deleteNode(root.right, root.val);
            }
            if (root.right == null) {
                TreeNode tmp = root.left;
                while (tmp.right != null) {
                    tmp = tmp.right;
                }
                return tmp;
            }
        }
        return root;
    }

    // lc415. 字符串相加
    public String addStrings(String num1, String num2) {

        int len1 = num1.length() - 1, len2 = num2.length() - 1, carry = 0;
        StringBuilder ans = new StringBuilder();
        while (len1 >= 0 || len2 >= 0 || carry != 0) {
            int sum = 0;
            sum += len1 < 0 ? 0 : num1.charAt(len1) - '0';
            sum += len2 < 0 ? 0 : num2.charAt(len2) - '0';
            sum += carry;
            carry = sum / 10;
            sum %= 10;
            ans.append(sum);
            len1--;
            len2--;
        }
        return ans.reverse().toString();
    }

    // lc179. 最大数
    public String largestNumber(int[] nums) {
        int n = nums.length;
        String[] numsArr = new String[n];
        for (int i = 0; i < n; i++) {
            numsArr[i] = ""+nums[i];
        }

        Arrays.sort(numsArr, (x, y) -> {
            String a = "" + x + y, b = "" + y + x;
            return b.compareTo(a);
        });

        StringBuilder ans = new StringBuilder();
        for (String i : numsArr) {
            ans.append(i);
        }

        return ans.charAt(0) == 0 ? "0" : ans.toString();
    }

    // lc198. 打家劫舍
    public int rob(int[] nums) {
        int first = nums[0];
        int second = 0;
        if (nums.length > 1) {
            second = nums[1];
        }
        int max = Math.max(first, second);
        for (int i = 2; i < nums.length; i++) {
            int tmp = first + nums[i];
            first = Math.max(first, second);
            second = Math.max(second, tmp);
            max = Math.max(tmp, max);
        }
        return max;
    }


    // lc142. 环形链表 II
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head, slow = head;
        do {
            if (fast == null || slow == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != slow);

        ListNode tmp = head;
        while (tmp != slow) {
            tmp = tmp.next;
            slow = slow.next;
        }

        return slow;
    }

}
