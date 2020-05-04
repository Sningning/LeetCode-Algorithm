import java.util.HashMap;

/**
 * 106. 从中序与后序遍历序列构造二叉树
 * 根据一棵树的中序遍历与后序遍历构造二叉树。
 *
 * 注意:
 * 你可以假设树中没有重复的元素。
 *
 * https://leetcode-cn.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/
 *
 * @Author: Song Ningning
 * @Date: 2020-05-04 14:38
 */
public class ConstructBinaryTreeFromInorderAndPostorderTraversal_106 {

    HashMap<Integer, Integer> map = new HashMap<>();

    public TreeNode buildTree(int[] inorder, int[] postorder) {

        // 递归，参照 105 题分析

        int inLen = inorder.length;
        int postLen = postorder.length;

        if (inLen != postLen)
            throw new RuntimeException("Error");

        for (int i = 0; i < inLen; i++)
            map.put(inorder[i], i);

        return build(postorder, 0, postLen - 1, inorder, 0, inLen - 1);
    }


    private TreeNode build(int[] postorder, int postL, int postR, int[] inorder, int inL, int inR) {

        if (postL > postR || inL > inR)
            return null;

        int pivot = postorder[postR];
        TreeNode root = new TreeNode(pivot);
        int pivotIdx = map.get(pivot);

        root.right = build(postorder, postR - (inR - pivotIdx), postR - 1,
                inorder, pivotIdx + 1, inR);

        root.left = build(postorder, postL,postR - (inR - pivotIdx) - 1,
                inorder, inL, pivotIdx - 1);

        return root;
    }
}

